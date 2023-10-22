package com.ding.utils;

import com.ding.exception.ServiceException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExcelUtils {
    private static Logger log = LoggerFactory.getLogger(ExcelUtils.class);
    /**
     * 默认分片数量
     */
    private static int block= 6000 ;

    public static void exportExcel(String title, String[] tableHead, List<Object[]> dataList){
        Cell cell ;
        SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
        SXSSFSheet sheet = wb.createSheet(title);
        sheet.setDefaultColumnWidth(15);
        sheet.setRandomAccessWindowSize(-1);

        Row row = getRow(sheet,0);
        row.setHeight((short) 500); //设置行高
        CellStyle titleCellStyle = settingTableHeadColumnStyle(wb);
        CellStyle rowStyle = settingColumnStyle(wb);

        // 自动调整列宽
        sheet.trackAllColumnsForAutoSizing();
        for (int i = 0; i < tableHead.length; i++) {
            cell = row.createCell((short) i);
            cell.setCellValue(tableHead[i]);
            cell.setCellStyle(titleCellStyle);
            //自适应列宽度
            sheet.setColumnWidth(i,tableHead[i].getBytes().length * 2 *256);
        }
        int dataSize = dataList.size();
        int ceil = (int) Math.ceil(dataSize/block);
        CountDownLatch downLatch = new CountDownLatch(ceil);
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i <=ceil ; i++) {
            int start = i * block ; //计算开始项,如start=0,end=999,start=1000,end=1999,
            int end = (i+1) * block -1; //结束项
            if (i ==  ceil){// //最后分片list,end = list.size-1(原因list 从0开始)
                end = dataSize-1;
            }
            es.submit(new PoiWriter(downLatch,sheet,tableHead,dataList,rowStyle,start,end));
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        es.shutdown();
//        for(int rowNum = 0; rowNum < dataList.size(); rowNum++){
//            row = sheet.createRow(rowNum + 1);
//            Object[] obj = dataList.get(rowNum);
//            for(int cellNum = 0; cellNum < tableHead.length; cellNum++){
//                cell = row.createCell(cellNum);
//                cell.setCellValue(obj[cellNum]==null?"":obj[cellNum].toString());
//                cell.setCellStyle(rowStyle);
//            }
//        }
        long endTime = System.currentTimeMillis();
        writeExcel(title,wb);
    }

    /**
     * http 响应下载excel 文件
     * @param fileName
     * @param workbook
     */
    private static void writeExcel(String fileName, SXSSFWorkbook workbook){
        fileName += ".xlsx";
        HttpServletResponse response = getResponse();
        response.reset();
        //页面弹出下载或保存
        response.setHeader("Content-Type", "multipart/form-data"); // 告诉浏览器输出内容为流
        try {
            response.setHeader("Content-disposition","attachment;filename="+  URLEncoder.encode(fileName,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("utf-8");
        try (ByteArrayOutputStream os = new ByteArrayOutputStream();
             OutputStream outputStream = response.getOutputStream();) {
            workbook.write(os);
            outputStream.write(os.toByteArray());
        }catch (IOException e){
            log.error("excel文件导出失败!",e);
            throw  new ServiceException("excel error",e);
        }finally {
            if (workbook!=null){
                workbook.dispose();
            }
        }
    }

    /**
     * sheet的row使用treeMap存储的，是非线程安全的，所以在创建row时需要进行同步操作。
     * @param sheet
     * @param rowNum
     * @return
     */
    private static synchronized Row getRow(SXSSFSheet sheet, int rowNum) {
        return sheet.createRow(rowNum);
    }
    /**
     * 进行sheet写操作
     */
    protected static class PoiWriter implements Runnable {
        private final CountDownLatch downLatch;
        private SXSSFSheet sheet;
        private int start;
        private int end;
        private  String[] tableHead;
        private List<Object[]> dataList;
        private CellStyle rowStyle;

        public PoiWriter(CountDownLatch downLatch, SXSSFSheet sheet, String[] tableHead, List<Object[]> dataList, CellStyle rowStyle , int start, int end) {
            this.downLatch = downLatch;
            this.sheet = sheet;
            this.tableHead = tableHead;
            this.rowStyle = rowStyle;
            this.dataList = dataList;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            int i = start;
            try{
                while (i <= end) {
                    Object[] obj = dataList.get(i);
                    Row row = getRow(sheet,i+1);
                    for(int cellNum = 0; cellNum < tableHead.length; cellNum++){
                        Cell cell = row.createCell(cellNum);
                        cell.setCellValue(obj[cellNum]==null?"":obj[cellNum].toString());
                        cell.setCellStyle(rowStyle);
                    }
                    i++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                downLatch.countDown();
            }

        }
    }

    /**
     * excel 设置表头样式
     * @param workbook
     */
    private static CellStyle settingTableHeadColumnStyle(Workbook workbook){
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN); // 下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);// 左边框
        cellStyle.setBorderTop(BorderStyle.THIN);// 上边框
        cellStyle.setBorderRight(BorderStyle.THIN);// 右边框
        cellStyle.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中

        cellStyle.setFillBackgroundColor(IndexedColors.BLUE.getIndex());// 设置背景色
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); //设置加粗


//        cellStyle.setWrapText(true); //单元格自动换行

        Font font = workbook.createFont();
        font.setBold(true);  //字体加粗
        font.setFontName("微软雅黑");// 设置字体名称
        font.setFontHeightInPoints((short) 14);// 设置字号
        font.setColor(IndexedColors.WHITE.getIndex());// 设置字体颜色
        cellStyle.setFont(font);

        return cellStyle ;
    }

    /**
     * excel 设置单元格样式
     * @return
     */
    private static CellStyle settingColumnStyle(Workbook workbook){
        //设置样式;
        CellStyle style = workbook.createCellStyle();
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置底边框;
        style.setBorderBottom(BorderStyle.THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        //设置左边框;
        style.setBorderLeft(BorderStyle.THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        //设置右边框;
        style.setBorderRight(BorderStyle.THIN);
        //设置右边框颜色;
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        //设置顶边框;
        style.setBorderTop(BorderStyle.THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());


        // 设置字体
        Font font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short)10);
        //设置字体名字
        font.setFontName("宋体");
        //在样式用应用设置的字体;
        style.setFont(font);
//       设置自动换行;
        style.setWrapText(true);

        return style;
    }

    public static HttpServletResponse getResponse (){
        ServletRequestAttributes requestAttributes = ServletRequestAttributes.class.cast(RequestContextHolder.currentRequestAttributes());
        return requestAttributes.getResponse();
    }
}

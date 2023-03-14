package com.ding;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTHoleSize;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.main.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class WordTest {
    public static void  main(String[] args) throws IOException, InvalidFormatException {
        String chartTitleName = "使用POI创建的饼图";
        String[] xAxisData = new String[] {
                "2021-01","2021-02","2021-03","2021-04","2021-05","2021-06",
                "2021-07","2021-08","2021-09","2021-10","2021-11","2021-12",
        };
        Integer[] yAxisData = new Integer[]{
                10, 35, 21, 46, 79, 88,
                39, 102, 71, 28, 99, 57
        };
//        pieChart(chartTitleName,xAxisData,yAxisData,"D:\\poi\\pieChart.docx");
////        //柱状图
////        histogram();
////        //环形图
////        doughnutChart("使用POI创建的环形图",xAxisData,yAxisData,"D:\\poi\\doughnutChart.docx");
////
////        SURFACEChart("sureca",xAxisData,yAxisData,"D:\\poi\\surecaChart.docx");
////        createWord();

        String[] xAxisCityData = new String[] {
                "深圳","广州","东莞","江门","韶关"
        };
        Integer[] yAxisCityData = new Integer[]{
                10, 35, 21, 46, 79
        };
        radarChartData("雷达图",xAxisCityData,yAxisCityData,"D:\\poi\\radarChart.docx");
    }

    /**
     *   生成饼图
     * @param chartTitleName  饼图标题
     * @param categoricalData  饼图分类名称数据
     * @param graphicData      饼图数据
     * @param outFilePath      生成文件路径
     */
    public static void pieChart(String chartTitleName,String[] categoricalData,Integer[] graphicData,String outFilePath) throws IOException, InvalidFormatException {
        // 1、创建word文档对象
        XWPFDocument document = new XWPFDocument();
        // 2、创建chart图表对象,抛出异常
        XWPFChart chart = document.createChart(15 * Units.EMU_PER_CENTIMETER, 10 * Units.EMU_PER_CENTIMETER);

        // 3、图表相关设置
        chart.setTitleText(chartTitleName); // 图表标题
        chart.setTitleOverlay(false); // 图例是否覆盖标题

        // 4、图例设置
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.RIGHT); // 图例位置:上下左右

        // 5、X轴(分类轴)相关设置:饼图中的图例显示
        XDDFCategoryDataSource xAxisSource = XDDFDataSourcesFactory.fromArray(categoricalData); // 设置分类数据

        // 6、Y轴(值轴)相关设置:饼图中的圆形显示
        XDDFNumericalDataSource<Integer> yAxisSource = XDDFDataSourcesFactory.fromArray(graphicData); // 设置值数据

        // 7、创建饼图对象,饼状图不需要X,Y轴,只需要数据集即可
        XDDFPieChartData pieChart = (XDDFPieChartData) chart.createData(ChartTypes.PIE, null, null);
        pieChart.setVaryColors(true);

        // 8、加载饼图数据集
        XDDFPieChartData.Series pieSeries = (XDDFPieChartData.Series) pieChart.addSeries(xAxisSource, yAxisSource);
        pieSeries.setTitle("粉丝数", null); // 系列提示标题

        // 9、绘制饼图
        chart.plot(pieChart);
        CTDLbls dLbls = chart.getCTChart().getPlotArea().getPieChartArray(0).getSerArray(0).addNewDLbls();
        dLbls.addNewShowVal().setVal(false);
        dLbls.addNewShowLegendKey().setVal(false);
        dLbls.addNewShowCatName().setVal(true);// 类别名称
        dLbls.addNewShowSerName().setVal(false);
        dLbls.addNewShowPercent().setVal(true);// 百分比
        dLbls.addNewShowLeaderLines().setVal(true);// 引导线
        dLbls.setSeparator("\n");// 分隔符为分行符
//        dLbls.addNewDLblPos().set(org.openxmlformats.schemas.drawingml.x2006.chart.STDLblPos.Enum.forString("inEnd"));// 数据标签内

        // 10、输出到word文档
        FileOutputStream fos = new FileOutputStream(outFilePath);
        document.write(fos); // 导出word

        // 11、关闭流
        fos.close();
        document.close();
    }

    public static void histogram() throws IOException, InvalidFormatException {
        // 1、创建word文档对象
        XWPFDocument document = new XWPFDocument();
        // 2、创建chart图表对象,抛出异常
        XWPFChart chart = document.createChart(15 * Units.EMU_PER_CENTIMETER, 10 * Units.EMU_PER_CENTIMETER);

        // 3、图表相关设置
        chart.setTitleText("使用POI创建的柱状图"); // 图表标题
        chart.setTitleOverlay(false); // 图例是否覆盖标题

        // 4、图例设置
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP); // 图例位置:上下左右

        // 5、X轴(分类轴)相关设置
        XDDFCategoryAxis xAxis = chart.createCategoryAxis(AxisPosition.BOTTOM); // 创建X轴,并且指定位置
        xAxis.setTitle("日期（年月）"); // x轴标题
        String[] xAxisData = new String[] {
                "2021-01","2021-02","2021-03","2021-04","2021-05","2021-06",
                "2021-07","2021-08","2021-09","2021-10","2021-11","2021-12",
        };
        XDDFCategoryDataSource xAxisSource = XDDFDataSourcesFactory.fromArray(xAxisData); // 设置X轴数据

        // 6、Y轴(值轴)相关设置
        XDDFValueAxis yAxis = chart.createValueAxis(AxisPosition.LEFT); // 创建Y轴,指定位置
        yAxis.setTitle("粉丝数（个）"); // Y轴标题
        yAxis.setCrossBetween(AxisCrossBetween.BETWEEN); // 设置图柱的位置:BETWEEN居中
        Integer[] yAxisData = new Integer[]{
                10, 35, 21, 46, 79, 88,
                39, 102, 71, 28, 99, 57
        };
        XDDFNumericalDataSource<Integer> yAxisSource = XDDFDataSourcesFactory.fromArray(yAxisData); // 设置Y轴数据

        // 7、创建柱状图对象
        XDDFBarChartData barChart = (XDDFBarChartData) chart.createData(ChartTypes.BAR, xAxis, yAxis);
        barChart.setBarDirection(BarDirection.COL); // 设置柱状图的方向:BAR横向,COL竖向,默认是BAR

        // 8、加载柱状图数据集
        XDDFBarChartData.Series barSeries = (XDDFBarChartData.Series) barChart.addSeries(xAxisSource, yAxisSource);
        barSeries.setTitle("粉丝数", null); // 图例标题

        // 9、绘制柱状图
        chart.plot(barChart);

        // 10、输出到word文档
        FileOutputStream fos = new FileOutputStream("D:\\poi\\barChart.docx");
        document.write(fos); // 导出word

        // 11、关闭流
        fos.close();
        document.close();
    }

    /**
     * 环形图
     */
    public static  void  doughnutChart(String chartTitleName,String[] categoricalData,Integer[] graphicData,String outFilePath) throws IOException, InvalidFormatException {
        // 1、创建word文档对象
        XWPFDocument document = new XWPFDocument();
        // 2、创建chart图表对象,抛出异常
        XWPFChart chart = document.createChart(15 * Units.EMU_PER_CENTIMETER, 10 * Units.EMU_PER_CENTIMETER);

        // 3、图表相关设置
        chart.setTitleText(chartTitleName); // 图表标题
        chart.setTitleOverlay(false); // 图例是否覆盖标题

        // 4、图例设置
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.RIGHT); // 图例位置:上下左右

        // 5、X轴(分类轴)相关设置:饼图中的图例显示
        XDDFCategoryDataSource xAxisSource = XDDFDataSourcesFactory.fromArray(categoricalData); // 设置分类数据

        // 6、Y轴(值轴)相关设置:饼图中的圆形显示
        XDDFNumericalDataSource<Integer> yAxisSource = XDDFDataSourcesFactory.fromArray(graphicData); // 设置值数据


        // x坐标轴 底部
        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        // y轴  左侧
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);

        // 7、创建饼图对象,饼状图不需要X,Y轴,只需要数据集即可
        XDDFDoughnutChartData doughnutChart = (XDDFDoughnutChartData) chart.createData(ChartTypes.DOUGHNUT, bottomAxis, leftAxis);
        doughnutChart.setVaryColors(true);

        // 8、加载饼图数据集
        XDDFDoughnutChartData.Series doughnutSeries = (XDDFDoughnutChartData.Series) doughnutChart.addSeries(xAxisSource, yAxisSource);
//        doughnutSeries.setTitle("粉丝数", null); // 系列提示标题

        // 9、绘制图
        chart.plot(doughnutChart);
        CTDLbls dLbls = chart.getCTChart().getPlotArea().getDoughnutChartArray(0).getSerArray(0).addNewDLbls();
        dLbls.addNewShowVal().setVal(false);
        dLbls.addNewShowLegendKey().setVal(false);
        dLbls.addNewShowCatName().setVal(true);// 类别名称
        dLbls.addNewShowSerName().setVal(false);
        dLbls.addNewShowPercent().setVal(true);// 百分比
        dLbls.addNewShowLeaderLines().setVal(true);// 引导线
        dLbls.setSeparator("\n");// 分隔符为分行符


        CTHoleSize holeSize = chart.getCTChart().getPlotArea().getDoughnutChartArray(0).addNewHoleSize();
        holeSize.setVal(50);


        // 10、输出到word文档
        FileOutputStream fos = new FileOutputStream(outFilePath);
        document.write(fos); // 导出word

        // 11、关闭流
        fos.close();
        document.close();
    }

    /**
     * SURFACE图
     */
    public static  void  SURFACEChart(String chartTitleName,String[] categoricalData,Integer[] graphicData,String outFilePath) throws IOException, InvalidFormatException {
        // 1、创建word文档对象
        XWPFDocument document = new XWPFDocument();
        // 2、创建chart图表对象,抛出异常
        XWPFChart chart = document.createChart(15 * Units.EMU_PER_CENTIMETER, 10 * Units.EMU_PER_CENTIMETER);

        // 3、图表相关设置
        chart.setTitleText(chartTitleName); // 图表标题
        chart.setTitleOverlay(false); // 图例是否覆盖标题

        // 4、图例设置
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP); // 图例位置:上下左右

        // 5、X轴(分类轴)相关设置:饼图中的图例显示
        XDDFCategoryDataSource xAxisSource = XDDFDataSourcesFactory.fromArray(categoricalData); // 设置分类数据

        // 6、Y轴(值轴)相关设置:饼图中的圆形显示
        XDDFNumericalDataSource<Integer> yAxisSource = XDDFDataSourcesFactory.fromArray(graphicData); // 设置值数据


        // x坐标轴 底部
        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        // y轴  左侧
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);

        // 7、创建饼图对象,饼状图不需要X,Y轴,只需要数据集即可
        XDDFSurfaceChartData surfaceChart = (XDDFSurfaceChartData) chart.createData(ChartTypes.SURFACE, bottomAxis, leftAxis);
//        surfaceChart.setVaryColors(true);

        // 8、加载饼图数据集
        XDDFChartData.Series doughnutSeries =  surfaceChart.addSeries(xAxisSource, yAxisSource);
        doughnutSeries.setTitle("粉丝数", null); // 系列提示标题

        // 9、绘制图
        chart.plot(surfaceChart);
//        CTDLbls dLbls = chart.getCTChart().getPlotArea().getSurfaceChartArray(0).getSerArray(0).add
//        dLbls.addNewShowVal().setVal(false);
//        dLbls.addNewShowLegendKey().setVal(false);
//        dLbls.addNewShowCatName().setVal(true);// 类别名称
//        dLbls.addNewShowSerName().setVal(false);
//        dLbls.addNewShowPercent().setVal(true);// 百分比
//        dLbls.addNewShowLeaderLines().setVal(true);// 引导线
//        dLbls.setSeparator("\n");// 分隔符为分行符
//
//
//        CTHoleSize holeSize = chart.getCTChart().getPlotArea().getDoughnutChartArray(0).addNewHoleSize();
//        holeSize.setVal(50);


        // 10、输出到word文档
        FileOutputStream fos = new FileOutputStream(outFilePath);
        document.write(fos); // 导出word

        // 11、关闭流
        fos.close();
        document.close();
    }

    /**
     * 雷达图
     */
    public static  void  radarChartData(String chartTitleName,String[] categoricalData,Integer[] graphicData,String outFilePath) throws IOException, InvalidFormatException {
        // 1、创建word文档对象
        XWPFDocument document = new XWPFDocument();
        // 2、创建chart图表对象,抛出异常
        XWPFChart chart = document.createChart(15 * Units.EMU_PER_CENTIMETER, 10 * Units.EMU_PER_CENTIMETER);

        // 3、图表相关设置
        chart.setTitleText(chartTitleName); // 图表标题
        chart.setTitleOverlay(false); // 图例是否覆盖标题

        // 4、图例设置
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP); // 图例位置:上下左右

        // 5、X轴(分类轴)相关设置:饼图中的图例显示
        XDDFCategoryDataSource xAxisSource = XDDFDataSourcesFactory.fromArray(categoricalData); // 设置分类数据

        // 6、Y轴(值轴)相关设置:饼图中的圆形显示
        XDDFNumericalDataSource<Integer> yAxisSource = XDDFDataSourcesFactory.fromArray(graphicData); // 设置值数据


        // x坐标轴 底部
        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        // y轴  左侧
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);

        // 7、创建饼图对象,饼状图不需要X,Y轴,只需要数据集即可
        XDDFRadarChartData radarChartData = (XDDFRadarChartData) chart.createData(ChartTypes.RADAR, bottomAxis, leftAxis);
        radarChartData.setVaryColors(true);


        // 8、加载饼图数据集
        XDDFRadarChartData.Series series  = (XDDFRadarChartData.Series) radarChartData.addSeries(xAxisSource, yAxisSource);
//        doughnutSeries.setTitle("粉丝数", null); // 系列提示标题

        // 9、绘制图
        chart.plot(radarChartData);
        CTPlotArea ctPlotArea = chart.getCTChart().getPlotArea();
        CTCatAx ctCatAx = ctPlotArea.getCatAxArray(0);
//        ctCatAx.addNewMajorGridlines().addNewSpPr();

//        CTPlotArea ctPlotArea = ctChart.addNewPlotArea();
//        ctPlotArea.addNewLayout();
//        CTValAx ctValAx = ctPlotArea.addNewValAx();
//        // 设置网格线
        CTShapeProperties ctShapeProperties = ctCatAx.addNewMajorGridlines().addNewSpPr();
        CTLineProperties ctLineProperties1 = ctShapeProperties.addNewLn();
//        ctLineProperties1.setW(9525);
        ctLineProperties1.setCap(STLineCap.FLAT);
        ctLineProperties1.setCmpd(STCompoundLine.SNG);
        ctLineProperties1.setAlgn(STPenAlignment.CTR);

        ctLineProperties1.addNewRound();
//        ctLineProperties1.addNewExtLst().
        // 值的纵坐标线，不显示
//        ctCatAx.addNewSpPr().addNewLn().addNewNoFill();
        // 值的纵坐标值，不显示
//        ctCatAx.addNewTxPr().addNewP().addNewPPr().addNewDefRPr().addNewNoFill();

        // 10、输出到word文档
        FileOutputStream fos = new FileOutputStream(outFilePath);
        document.write(fos); // 导出word

        // 11、关闭流
        fos.close();
        document.close();
    }


    public static void createWord() throws IOException {
        //创建文本对象
        XWPFDocument document = new XWPFDocument();
        //添加标题
        XWPFParagraph titleParagraph  = document.createParagraph();
        //设置段落居中
        titleParagraph .setAlignment(ParagraphAlignment.CENTER);

        XWPFRun titleParagraphRun = titleParagraph.createRun();
        titleParagraphRun.setText("Java PoI");
        titleParagraphRun.setColor("000000");
        titleParagraphRun.setFontSize(20);
        titleParagraphRun.setFontFamily("宋体");
        titleParagraphRun.addCarriageReturn();//回车键

        //段落
        XWPFParagraph firstParagraph = document.createParagraph();
        XWPFRun run = firstParagraph.createRun();
        run.setText("Java POI 生成word文件。");
        run.setColor("696969");
        run.setFontSize(16);

        //设置段落背景颜色
        CTShd cTShd = run.getCTR().addNewRPr().addNewShd();
        cTShd.setVal(STShd.CLEAR);
        cTShd.setFill("97FFFF");

        //换行
        XWPFParagraph paragraph1 = document.createParagraph();
        XWPFRun paragraphRun1 = paragraph1.createRun();
        paragraphRun1.setText("\r");


        //基本信息表格
        XWPFTable infoTable = document.createTable();
        //去表格边框
        infoTable.getCTTbl().getTblPr().unsetTblBorders();

        //列宽自动分割
        CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();
        infoTableWidth.setType(STTblWidth.DXA);
        infoTableWidth.setW(BigInteger.valueOf(9072));

        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("职位");
        infoTableRowOne.addNewTableCell().setText(": Java 开发工程师");

        //表格第二行
        XWPFTableRow infoTableRowTwo = infoTable.createRow();
        infoTableRowTwo.getCell(0).setText("姓名");
        infoTableRowTwo.getCell(1).setText(": seawater");

        //表格第三行
        XWPFTableRow infoTableRowThree = infoTable.createRow();
        infoTableRowThree.getCell(0).setText("生日");
        infoTableRowThree.getCell(1).setText(": xxx-xx-xx");

        //表格第四行
        XWPFTableRow infoTableRowFour = infoTable.createRow();
        infoTableRowFour.getCell(0).setText("性别");
        infoTableRowFour.getCell(1).setText(": 男");

        //表格第五行
        XWPFTableRow infoTableRowFive = infoTable.createRow();
        infoTableRowFive.getCell(0).setText("现居地");
        infoTableRowFive.getCell(1).setText(": xx");


        //两个表格之间加个换行
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun paragraphRun = paragraph.createRun();
        paragraphRun.setText("\r");


        //工作经历表格
        XWPFTable ComTable = document.createTable();

        //列宽自动分割
        CTTblWidth comTableWidth = ComTable.getCTTbl().addNewTblPr().addNewTblW();
        comTableWidth.setType(STTblWidth.DXA);
        comTableWidth.setW(BigInteger.valueOf(9072));

        //表格第一行
        XWPFTableRow comTableRowOne = ComTable.getRow(0);
        comTableRowOne.getCell(0).setText("开始时间");
        comTableRowOne.addNewTableCell().setText("结束时间");
        comTableRowOne.addNewTableCell().setText("公司名称");
        comTableRowOne.addNewTableCell().setText("title");

        //表格第二行
        XWPFTableRow comTableRowTwo = ComTable.createRow();
        comTableRowTwo.getCell(0).setText("2016-09-06");
        comTableRowTwo.getCell(1).setText("至今");
        comTableRowTwo.getCell(2).setText("seawater");
        comTableRowTwo.getCell(3).setText("Java开发工程师");

        //表格第三行
        XWPFTableRow comTableRowThree = ComTable.createRow();
        comTableRowThree.getCell(0).setText("2016-09-06");
        comTableRowThree.getCell(1).setText("至今");
        comTableRowThree.getCell(2).setText("seawater");
        comTableRowThree.getCell(3).setText("Java开发工程师");

        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);

        //添加页眉
        CTP ctpHeader = CTP.Factory.newInstance();
        CTR ctrHeader = ctpHeader.addNewR();
        CTText ctHeader = ctrHeader.addNewT();
        String headerText = "Java POI create MS word file.";
        ctHeader.setStringValue(headerText);
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
        //设置为右对齐
        headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
        XWPFParagraph[] parsHeader = new XWPFParagraph[1];
        parsHeader[0] = headerParagraph;
        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);

        //添加页脚
        CTP ctpFooter = CTP.Factory.newInstance();
        CTR ctrFooter = ctpFooter.addNewR();
        CTText ctFooter = ctrFooter.addNewT();
        String footerText = "http://blog.csdn.net/zhouseawater";
        ctFooter.setStringValue(footerText);
        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
        headerParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFParagraph[] parsFooter = new XWPFParagraph[1];
        parsFooter[0] = footerParagraph;
        policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);

        // 10、输出到word文档
        FileOutputStream fos = new FileOutputStream("D:\\poi\\word.docx");
        document.write(fos); // 导出word

        // 11、关闭流
        fos.close();
        document.close();

    }
}

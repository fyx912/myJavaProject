package com.ding.web;

import com.alibaba.fastjson2.JSONObject;
import com.ding.utils.ExcelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("file")
public class FileWeb {
    private Logger logger = LoggerFactory.getLogger(TestWeb.class);
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file")MultipartFile file){
        long startTime = System.currentTimeMillis();
        String fileName = file.getOriginalFilename();
        long endTime = System.currentTimeMillis()-startTime;
        logger.info("file upload 耗时:{}",endTime);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","success");
        json.put("fileName",fileName);
        json.put("time",endTime);
        return json.toString();
    }
    @GetMapping("excel")
    public void  export(){
        long startTime = System.currentTimeMillis();
        //组装excel title 数据
        String[] rowName = new String[]{"id","name","number","time","id","name","number","time","id","name","number","time","id","name","number","time"};
        List<Object[]> dataList = new ArrayList<>();
        List caseList = new ArrayList();
        for (int i = 0; i < 300000; i++) {
            caseList.add(i);
            caseList.add(UUID.randomUUID());
            caseList.add(new Random().nextLong());
            caseList.add(System.currentTimeMillis());
            caseList.add(UUID.randomUUID());
            caseList.add(UUID.randomUUID());
            caseList.add(new Random().nextLong());
            caseList.add(System.currentTimeMillis());
            caseList.add(UUID.randomUUID());
            caseList.add(UUID.randomUUID());
            caseList.add(new Random().nextLong());
            caseList.add(System.currentTimeMillis());
            caseList.add(UUID.randomUUID());
            caseList.add(UUID.randomUUID());
            caseList.add(new Random().nextLong());
            caseList.add(System.currentTimeMillis());

            dataList.add(caseList.toArray());
            caseList.clear();
        }
        ExcelUtils.exportExcel("data",rowName, dataList);

        long endTime = System.currentTimeMillis()-startTime;
        logger.info("export excel 耗时:{}",endTime);
    }
}

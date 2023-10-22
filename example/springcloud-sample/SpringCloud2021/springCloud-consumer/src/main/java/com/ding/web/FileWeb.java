package com.ding.web;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ding.remote.ProviderRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("file")
public class FileWeb {
    private Logger logger = LoggerFactory.getLogger(FileWeb.class);
    @Resource
    private ProviderRemote remote;

    @PostMapping("upload")
    public String uploadFile(@RequestParam("file")MultipartFile file){
        long startTime = System.currentTimeMillis();
        String fileName = remote.uploadFile(file);
        long endTime = System.currentTimeMillis()-startTime;
        logger.info("file upload 耗时:{}",endTime);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","success");
        json.put("fileName",fileName);
        json.put("time",endTime);
        return json.toString();
    }
}

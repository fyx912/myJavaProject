package com.oss.web;

import com.oss.utils.OssClientConfig;
import com.oss.utils.OssClientUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class FileWeb {
    @Resource
    private OssClientConfig ossClientConfig;

    @Resource
    private OssClientUtil ossClientUtil;

    @RequestMapping("upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String filePath= "test/";
            filePath += file.getOriginalFilename();
            return ossClientUtil.uploadFile(file.getInputStream(),filePath);
        } else {
            return "Please select a file to upload!";
        }
    }

    @DeleteMapping(value = "file")
    public String deleteFile(@RequestParam(value = "filePath") String filePath) {
        try{
            ossClientUtil.deleteFile(filePath);
            return "delete success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "delete fail";
    }


    @GetMapping(value = "preView")
    public String preView(@RequestParam(value = "filePath") String filePath) {
        return ossClientUtil.preView(filePath);
    }

    @GetMapping(value = "download")
    public void download(@RequestParam(value = "ossFilePath") String ossFilePath) throws IOException {
        String localFile = "D:\\Downloads";
        InputStream inputStream =  ossClientUtil.getObject(ossFilePath);
        File localFileObj = new File(localFile);
        FileOutputStream outputStream = new FileOutputStream(localFileObj);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    @GetMapping("ossClientConfig")
    public OssClientConfig ossClientConfig(){
        return ossClientConfig;
    }
}

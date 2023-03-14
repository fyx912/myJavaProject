package com.ding.web;

import com.ding.config.MinioClientConfig;
import com.ding.utils.MinioUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ding
 * @create 12 22:53
 * @description
 */
@RestController
@RequiredArgsConstructor
public class MinioWeb {

    private final  MinioUtils minioUtils;

    @PostMapping("upload")
    public Object upload(MultipartFile file){
        String objectName  = minioUtils.upload(file);
        return minioUtils.preView(objectName);
    }

    @PostMapping("delete")
    public Object deleteObject(String fileName){
        Boolean flag = minioUtils.remove(fileName);
        Map<String,Object> map = new HashMap<>();
        if (flag){
            map.put("code",0);
            map.put("msg","success");
        }else {
            map.put("code",1);
            map.put("msg","failed");
        }
        return map;
    }
}

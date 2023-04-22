package com.ding.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * IndexWeb
 *
 * @author ding
 * @version 1.0
 * @description
 * @date 2023-04-22:18:06
 */
@RestController
public class IndexWeb {
    private Logger logger = LoggerFactory.getLogger(IndexWeb.class);

    @GetMapping("index")
    public Map index(){
        Map map = new HashMap();
        map.put("code",0);
        map.put("msg","success");
        logger.info("初始日志.........");
        logger.info(" response data:",map);
        return map;
    }
}

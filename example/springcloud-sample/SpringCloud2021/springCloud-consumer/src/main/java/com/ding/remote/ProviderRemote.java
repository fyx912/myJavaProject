package com.ding.remote;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "service-provider",fallback = ProviderRemoteFallback.class)
public interface ProviderRemote {
    @GetMapping(value = "/echo/{name}")
    @Cacheable(cacheNames = "service-provider", key = "#keyParam")
    String echo(@PathVariable("name") String name);

    /**
     * FeignClient 文件远程上传
     * @param file
     * @return
     */
    @PostMapping(value = "file/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@RequestPart("file") MultipartFile file);

    @GetMapping("hello")
    String hello();
}

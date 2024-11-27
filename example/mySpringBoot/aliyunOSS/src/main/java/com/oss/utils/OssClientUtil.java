package com.oss.utils;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.util.Date;

@Component
public class OssClientUtil {
    private Logger logger = LoggerFactory.getLogger(OssClientUtil.class);
    private final OSS ossClient;
    private OssClientConfig ossClientConfig;


    public OssClientUtil(OssClientConfig ossClientConfig) {
        try {
            logger.info("Initializing OSS client with config: {}", ossClientConfig);
            this.ossClientConfig = ossClientConfig;
            this.ossClient = new OSSClientBuilder().build(
                    ossClientConfig.getEndpoint(),
                    ossClientConfig.getAccessKeyId(),
                    ossClientConfig.getAccessKeySecret()
            );
            logger.info("OSS client initialized successfully.");
        } catch (Exception e) {
            logger.error("Failed to initialize OSS client", e);
            throw new RuntimeException("Error initializing OSS client", e);
        }
    }

    /**
     * 上传文件到OSS
     * @param file 文件
     * @param objectName 上传到OSS后的文件名（包含路径）
     * @return 文件URL
     */
    public String uploadFile(File file, String objectName){
        if (!file.isFile()){
            throw new IllegalArgumentException("请上传正确的文件");
        }
        try (InputStream inputStream = new FileInputStream(file)) {
            return uploadFile(inputStream, objectName);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("文件未找到", e);
        } catch (IOException e) {
            throw new RuntimeException("文件读取失败", e);
        }
    }

    /**
     * 上传文件到OSS
     * @param inputStream 文件输入流
     * @param objectName 上传到OSS后的文件名（包含路径）
     * @return 文件URL
     */
    public String uploadFile(InputStream inputStream, String objectName) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(getContentType(objectName.substring(objectName.lastIndexOf("."))));
        PutObjectRequest putObjectRequest = new PutObjectRequest(ossClientConfig.getBucketName(),objectName, inputStream, metadata);
        ossClient.putObject(putObjectRequest);
        logger.info("upload file:{},success", objectName);
        try {
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
            URL url = ossClient.generatePresignedUrl(ossClientConfig.getBucketName(), objectName, expiration);
            return url.toString();
        } catch (Exception e) {
            throw new RuntimeException("生成文件URL失败", e);
        }finally {
            this.destroy();
        }
    }

    /**
     * 获取文件流
     * @param objectName  文件oss全路径
     * @return InputStream
     */
    public InputStream getObject(String objectName){
        GetObjectRequest getObjectRequest = new GetObjectRequest(ossClientConfig.getBucketName(), objectName);
        return ossClient.getObject(getObjectRequest).getObjectContent();
    }

    /**
     * 文件预览
     * @param objectName  文件oss全路径
     * @return 文件URL
     */
    public String preView(String objectName){
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        URL url = ossClient.generatePresignedUrl(ossClientConfig.getBucketName(), objectName, expiration);
        return url.toString();
    }

    /**
     * 文件预览
     * @param objectName  文件oss全路径
     * @param expiration 到期时间
     * @return 文件URL
     */
    public String preView(String objectName,Date expiration){
        URL url = ossClient.generatePresignedUrl(ossClientConfig.getBucketName(), objectName, expiration);
        return url.toString();
    }

    /**
     * 根据文件后缀获取其MIME类型
     *
     * @param suffix 文件后缀名
     * @return MIME类型字符串
     */
    private static String getContentType(String suffix) {
        if (".bmp".equalsIgnoreCase(suffix)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(suffix)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(suffix) || ".jpg".equalsIgnoreCase(suffix)) {
            return "image/jpeg";
        }
        if (".png".equalsIgnoreCase(suffix)) {
            return "image/png";
        }
        // ...其他类型的处理
        return "application/octet-stream";
    }

    /**
     * 从OSS下载文件到本地
     *
     * 注意：此方法未实现具体下载逻辑，仅提供方法签名示例
     *
     * @param objectName  OSS中的文件名
     * @param localPath   本地保存路径
     */
    public static void downloadFile(String objectName, String localPath) {
        // 实现下载逻辑...
    }

    /**
     * 从OSS删除文件
     * @param objectName 要删除的文件名
     */
    public  void deleteFile(String objectName) {
        try {
            ossClient.deleteObject(ossClientConfig.getBucketName(), objectName);
            logger.info("delete object: {},success", objectName);
        } catch (Exception e) {
            throw new RuntimeException("文件删除失败", e);
        } finally {
           this.destroy();
        }
    }

    @PreDestroy
    public void destroy() {
        if (ossClient != null) {
            ossClient.shutdown();
        }
    }
}

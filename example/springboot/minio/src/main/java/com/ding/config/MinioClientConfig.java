package com.ding.config;

import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ding
 * @create 12 22:01
 * @description
 */
@Primary
@Configuration
@ConfigurationProperties
@PropertySource(value = "classpath:minio.yml")
public class MinioClientConfig {
    private Logger logger = LoggerFactory.getLogger(MinioClientConfig.class);
    //是否启用
    private Boolean enable;
    //账号
    private String accessKey;
    // 密码
    private String secretKey;
    //存储MinIO桶名字
    private String bucketName;
    //minio站点
    private String endpoint;

    @Bean
    public MinioClient minioClient(){
        if (enable){
            MinioClient minioClient = MinioClient.builder().endpoint(endpoint).credentials(accessKey,secretKey).build();
            try {
                List<Bucket> buckets = minioClient.listBuckets();
                List<String> bucketNames = buckets.stream().map(Bucket::name).collect(Collectors.toList());
                boolean contains = bucketNames.contains(bucketName);
                if (!contains){
                    logger.error("minio Client Bucket not contains:{},Client build ERROR, Please Check your bucketName",bucketName);
                    return null;
                }
                logger.info("minio client build SUCCESS");
                return minioClient;
            } catch (ErrorResponseException e) {
                throw new RuntimeException(e);
            } catch (InsufficientDataException e) {
                throw new RuntimeException(e);
            } catch (InternalException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            } catch (InvalidResponseException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (ServerException e) {
                throw new RuntimeException(e);
            } catch (XmlParserException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}

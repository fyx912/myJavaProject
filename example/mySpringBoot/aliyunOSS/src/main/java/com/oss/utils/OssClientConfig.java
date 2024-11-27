package com.oss.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * oss配置
 */
@Component
@PropertySource("classpath:oss.yml")
@ConfigurationProperties(prefix = "oss")
public class OssClientConfig {
    //账号AccessKey ID
    private String accessKeyId;
    //账号Access Key Secret
    private String accessKeySecret;
    //存储空间名称
    private String bucketName;
    //OSS服务的访问域名
    private String endpoint;
    private String urlPrefix;

    public OssClientConfig() {
    }

    public OssClientConfig(String accessKeyId, String accessKeySecret, String bucketName, String endpoint, String urlPrefix) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.bucketName = bucketName;
        this.endpoint = endpoint;
        this.urlPrefix = urlPrefix;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
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

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }
}

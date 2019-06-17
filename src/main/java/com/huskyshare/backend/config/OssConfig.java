package com.huskyshare.backend.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "huskyshare.oss")
public class OssConfig implements InitializingBean {
    private String endpoint;

    private String keyId;

    private String pwd;

    private String filehost;

    private String bucket;

    public static String ENDPOINT;

    public static String KEY_ID;

    public static String PASSWORD;

    public static String FILE_HOST;

    public static String BUCKET;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String secret) {
        this.pwd = secret;
    }

    public String getFilehost() {
        return filehost;
    }

    public void setFilehost(String filehost) {
        this.filehost = filehost;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT  = endpoint;
        FILE_HOST =  filehost;
        PASSWORD = pwd;
        KEY_ID = keyId;
        BUCKET = bucket;
    }
}

package com.huskyshare.backend.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "huskyshare.resource")
public class DirConfig implements InitializingBean {
    private String dir;

    public static String DIR;

    @Override
    public void afterPropertiesSet() throws Exception{
        DIR = dir;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}

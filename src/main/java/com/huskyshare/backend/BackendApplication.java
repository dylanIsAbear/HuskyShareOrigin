package com.huskyshare.backend;

import com.huskyshare.backend.config.OssConfig;
import com.huskyshare.backend.utils.EmailHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({OssConfig.class})
public class BackendApplication {

   public static void main(String[] args) {
      SpringApplication.run(BackendApplication.class, args);
   }
}

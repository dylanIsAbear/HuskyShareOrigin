package com.huskyshare.backend.config.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class RedisTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Before
    public void init(){
        logger.info("Test begin--------");
        System.out.println("Test begin--------");
    }

    @After
    public void after(){
        logger.info("Test end--------");
        System.out.println("Test end--------");
    }
}

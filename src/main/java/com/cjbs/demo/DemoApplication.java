package com.cjbs.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author shihaijun
 */
@SpringBootApplication
@EnableJpaRepositories("com.cjbs.demo.repository")
@EnableCaching //开启缓存
@EnableJpaAuditing //开启审计
@EnableScheduling //开启任务调度
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

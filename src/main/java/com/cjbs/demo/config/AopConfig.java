package com.cjbs.demo.config;

import com.cjbs.demo.aop.JoinPointAspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author shihaijun
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(value = "com.cjbs.demo.aop")
public class AopConfig {

    public JoinPointAspect joinPointAspect() {
        return new JoinPointAspect();
    }
}

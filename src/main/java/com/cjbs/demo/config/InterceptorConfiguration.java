package com.cjbs.demo.config;

import com.cjbs.demo.web.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author shihaijun
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Bean
    MyInterceptor contextInterceptor() {
        return new MyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(contextInterceptor()).addPathPatterns("/api/**");
    }
}

package com.cjbs.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * 审计功能
 * @author shj
 */
@Configuration
@EnableJpaAuditing  //这个会创建该功能的相关类
public class AuditorConfiguration implements AuditorAware<String> {

    private String userName = "shj";

    @Override
    public Optional<String> getCurrentAuditor() {

          //在正式项目中需要从用户权限模块中获取到当前登录的用户信息
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return Optional.of(authentication.getPrincipal().toString());

        //这里只是将定义的一个常量User返回
        return Optional.of(userName);

    }
}

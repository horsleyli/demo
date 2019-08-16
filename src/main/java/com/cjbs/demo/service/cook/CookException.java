package com.cjbs.demo.service.cook;

import org.springframework.stereotype.Component;

/**
 * @author shj
 * 绑定异常test
 */
@Component
public class CookException {
    public void dealCook(String name) {
        if(name == null) {
            throw new IllegalArgumentException("iae Exception");
        } else {
            throw new RuntimeException("re Exception");
        }
    }
}

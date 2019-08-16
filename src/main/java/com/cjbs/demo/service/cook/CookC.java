package com.cjbs.demo.service.cook;

import org.springframework.stereotype.Service;


/**
 * @author shj
 * 绑定返回值test
 */
@Service
public class CookC implements Cook{

    /**
     * 绑定返回值test
     * @param name name
     * @return boolean
     */
    public boolean eat(String name) {
        System.out.println(name + "好吃吗？");
        return true;
    }


    public void good() {
        System.out.println("CookC good");
    }
}

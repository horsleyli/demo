package com.cjbs.demo.service.cook;

import com.cjbs.demo.annotation.MyAnnotation;
import org.springframework.stereotype.Service;

/**
 * @author shj
 * 绑定类注解对象test
 */
@MyAnnotation
@Service
public class CookB implements Cook{

    public void make() {
        System.out.println("B制作食品");
    }

}

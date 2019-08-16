package com.cjbs.demo.service.cook;

import org.springframework.stereotype.Service;

/**
 * @author shj
 */

@Service
public class CookA implements Cook {

    public void make() {
        System.out.println("A制作食品");
    }

    public void make(String name) {
        System.out.println("A制作" + name);
    }

    /**
     * 绑定连接点的方法入参test
     * @param name name
     * @param num num
     */
    public void make(String name, int num) {
        System.out.println("A制作" + name + num + "个");
    }



}

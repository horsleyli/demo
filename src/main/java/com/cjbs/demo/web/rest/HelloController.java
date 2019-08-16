package com.cjbs.demo.web.rest;


import com.cjbs.demo.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shihaijun
 */
@Controller
@RequestMapping("/api")
public class HelloController {

    private HelloService helloService;

    /**
     * 构造器方式注入
     * @param helloService helloService
     */
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    /**
     * test
     * @return String
     */
    @GetMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return helloService.getString();
    }
}

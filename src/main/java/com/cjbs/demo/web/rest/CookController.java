package com.cjbs.demo.web.rest;

import com.cjbs.demo.service.cook.CookA;
import com.cjbs.demo.service.cook.CookB;
import com.cjbs.demo.service.cook.CookC;
import com.cjbs.demo.service.cook.CookException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shj
 *
 * 1.@RestController注解，相当于Controller+ResponseBody两个注解的结合，返回json数据不需要在方法前面加@ResponseBody注解了，
 *   但使用@RestController这个注解，就不能返回jsp,html页面，视图解析器无法解析jsp,html页面
 * 2.使用@Controller 注解，在对应的方法上，视图解析器可以解析return 的jsp,html页面，并且跳转到相应页面若返回json等内容到页面，
 *   则需要加@ResponseBody注解
 */
@RestController
@RequestMapping("/api")
public class CookController {

    private CookA cookA;
    private CookB cookB;
    private CookC cookC;
    private CookException cookException;

    public CookController(CookA cookA, CookB cookB, CookC cookC, CookException cookException) {
        this.cookA = cookA;
        this.cookB = cookB;
        this.cookC = cookC;
        this.cookException = cookException;
    }

    /**
     * 通过Pointcut控制对象test
     */
    @GetMapping("/makeCookName")
    public void getMakeCook() {
        cookA.make("寿司");
    }

    /**
     * 通过Pointcut控制方法test
     */
    @GetMapping("/getMethod")
    public void getMethod() {
        cookC.good();
    }

    /**
     * 绑定连接点的方法入参test
     */
    @GetMapping("/makeCookNameNum")
    public void getMakeCookNum() {
        cookA.make("寿司", 3);
    }

    /**
     * 绑定类注解对象test
     */
    @GetMapping("/makeBCook")
    public void getBCook() {
        cookB.make();
    }

    /**
     * 绑定返回值test
     */
    @GetMapping("/eat")
    public void getAEat() {
        cookC.eat("寿司");
    }

    /**
     * 绑定异常test
     */
    @GetMapping("/error")
    public void getError() {
        cookException.dealCook(null);
    }



}

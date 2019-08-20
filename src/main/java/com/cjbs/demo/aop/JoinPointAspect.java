package com.cjbs.demo.aop;

import com.cjbs.demo.annotation.MyAnnotation;
import com.cjbs.demo.service.cook.Cook;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author shihaijun
 * 参考https://www.jianshu.com/p/0d7cf0aeb338
 *
 * --  @Before @Around @After @AfterReturning等执行顺序   https://www.cnblogs.com/lidj/p/7194193.html
 *
 * --  within和execution区别   https://blog.csdn.net/f45056231p/article/details/81489817
 *      1.通过类匹配模式串声明切点，within()函数定义的连接点是针对目标类而言的，而非针对运行期对象的类型而言，这一点和execution()是相同的。
 *      2.但是within()和execution()函数不同的是，within()所指定的连接点最小范围只能是类，而execution()所指定的连接点可以大到包，小到方法入参。
 *          所以从某种意义上讲，execution()函数功能涵盖了within()函数的功能
 *
 *--   什么是AOP切面编程
 *       1.在运行时，动态地将代码切入到类的指定方法、指定位置上的编程思想就是面向切面的编程。
 *       2.AOP是Spring提供的关键特性之一。AOP即面向切面编程，是OOP编程的有效补充。
 *       3.使用AOP技术，可以将一些系统性相关的编程工作，独立提取出来，独立实现，然后通过切面切入进系统。
 *       4.从而避免了在业务逻辑的代码中混入很多的系统相关的逻辑——比如权限管理，事物管理，日志记录等等。
 *          权限管理：可以在对象或方法执行之前使用@Around判断用户权限，符合要求执行切入点的procced()方法，不符合要求抛出异常等
 *          日志记录：可记录切入点执行日志，如下示例
 *
 *--   提高：spring中的多线程aop方法拦截 (待学习)
 *          使用CGLib 大致原理：CGLib会从被代理的类，派生出一个子类，然后在子类中覆写所有非final的public方法，从而达到"方法增强"的效果
 *
 */
@Aspect
@Component
public class JoinPointAspect {

    /**
     * 日志记录
     */
    private final Logger logger = LoggerFactory.getLogger(JoinPointAspect.class);

    public JoinPointAspect() {}

    @Pointcut("within(com.cjbs.demo.service.cook.CookA)")
    public void makeCookClass(){}

    /**
     * 通过Pointcut控制对象
     * 可使用如下注解 也可以直接使用@Around("within(com.cjbs.demo.service.cook.CookA)")
     * @param pjp pjp
     * @throws Throwable Throwable
     */
    @Around("makeCookClass()")
    public Object test(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("---------获取连接点对象【开始】---------");
        System.out.println("参数：" + pjp.getArgs()[0]);
        System.out.println("签名对象：" + pjp.getTarget().getClass());

        Object result = null;
        try{
            //执行目标对象方法  如果不写则被控制对象方法不执行
            result = pjp.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("---------获取连接点对象【结束】---------");

        return result;
    }

    /**
     * 通过Pointcut控制方法
     */
    @Pointcut("execution(* com.cjbs.demo.service.cook.CookC.good())")
    public void getMethod(){}
    @Around("getMethod()")
    public Object testMethod(ProceedingJoinPoint pjp) throws Throwable {
        //日志记录
        logger.debug("log sign object {}", pjp.getTarget().getClass());

        System.out.println("---------获取连接点方法【开始】---------");
        System.out.println("签名对象：" + pjp.getTarget().getClass());

        Object result = null;
        try{
            //执行目标对象方法  如果不写则被控制对象方法不执行
            result = pjp.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("---------获取连接点方法【结束】---------");

        return result;
    }



    /**
     * 绑定连接点的方法入参
     * 1.args()、this()、target()、@args()、@within()、@target() 和 @annotation()  这些切点函数除可以指定类名外，还可以指定参数名，
     * 将目标对象连接点上的方法入参绑定到增强的方法中 。 其中 args()  用于绑定连接点方法的入参， @annotation() 用于绑定连接点方法的注解对象，
     * 而 @args() 用于绑定连接点方法入参的注解
     * 2.切点匹配和参数绑定的过程是这样的：
     *   (1).args()会根据参数名称在增强方法中查到名称相同的入参并获得对应参数的类型，这样就得到了匹配连接点方法的入参类型
     *   (2).连接点方法入参类型所在的位置由参数名在 args() 函数中声明的位置决定
     *
     * @param name name
     * @param num num
     */
    @Before(value = "target(com.cjbs.demo.service.cook.CookA) && args(name,num)", argNames = "name,num")
    public void test1(String name, int num) {
        System.out.println("---------绑定连接点入参【开始】--------");
        System.out.println("name" + name);
        System.out.println("num"  + num + "个");
        System.out.println("---------绑定连接点入参【结束】--------");
    }


    /**
     * 绑定代理对象
     * 使用 this() 或 target() 可绑定被代理对象的实例
     * 1.首先通过 public void bind(cook cook) 找出 cook 所对应的类型，
     * 2.接着转换切点表达式为 this(com.cjbs.demo.service.cook.cook) 这样就表示该切点匹配所有代理对象为 cook 类中的所有方法
     * target() 绑定与 this() 相似
     *
     * @param cook cook
     */
    @Before(value = "this(cook)", argNames = "cook")
    public void bind(Cook cook) {
        System.out.println("--------绑定代理对象【开始】--------");
        System.out.println(cook.getClass().getName());
        System.out.println("--------绑定代理对象【结束】--------");
    }

    /**
     * 绑定类注解对象
     * 1.@within() 和 @target() 函数都可以将目标类的注解对象绑定到增强方法中
     * @param myAnnotation 自定义注解myAnnotation
     */
    @Before("@within(myAnnotation)")
    public void bind(MyAnnotation myAnnotation){
        System.out.println("----------绑定类注解对象【开始】----------");
        System.out.println(myAnnotation.getClass().getName());
        System.out.println("----------绑定类注解对象【结束】----------");
    }

    /**
     * 绑定返回值
     * 注意：returning 的值必须与方法参数名相同。
     * @param value value
     */
    @AfterReturning(value = "target(com.cjbs.demo.service.cook.CookC)", returning = "value")
    public void bind1(boolean value) {
        System.out.println("绑定返回值【开始】");
        System.out.println("value：" + value);
        System.out.println("绑定返回值【结束】");
    }

    /**
     * 绑定异常test
     * @param e e
     */
    @AfterThrowing(value = "target(com.cjbs.demo.service.cook.CookException)",throwing = "e")
    public void bind2(Throwable e){
        System.out.println("绑定异常【开始】");
        System.out.println("e：" + e.getMessage());
        System.out.println("绑定异常【结束】");
    }
}

package com.example.mzpspringboot.configuration.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 *
 * mzp： 切面：由  切点和通知  组成
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2023/1/6 11:19
 * @File : LogAop
 * @Software: IntelliJ IDEA 2019.2.04
 */
@Aspect  // 声明为切面
@Service // 一定要把这个类注册为bean，交给spring管理
public class LogAop {

    ThreadLocal<Long> spendTime = new ThreadLocal<>();


//=======================================@Pointcut注解使用=========================================
// 定义一个切点@Pointcut，这个切点不需要有方法体，这个切点的作用只是在其上面用表达式标注一个被切的方法的位置或者注解，只是用来被其他通知引用的，真正执行的是通知中的逻辑。
    /**
     * mzp：切点。
     *
     * execution方式声明的切点.
     * 切点不需要方法体。使用时，只需要在通知中引用切点的方法名即可
     */
    @Pointcut("execution(public * com.example.mzpspringboot.controller.AopController.testAspect(..))")
    private void executionPointcut() {
    }

    /**
     * mzp：切点。
     *
     * annotation（注解）方式声明的切点。可以拦截具有指定注解的方法。
     * 切点不需要方法体。使用时，只需要在通知中引用切点的方法名即可
     */
    @Pointcut("@annotation(com.example.mzpspringboot.annotation.AopLog)")
    private void annotationPointcut() {
    }

    /**
     * mzp： 通知
     *      前置通知：在目标方法前调用。
     *      直接在通知的注解中（比如@Before中）使用切点（比如@Pointcut注解标注的方法）定义的的方法即可
     *
     */
    @Before("executionPointcut()")
    public void logBefore() {
        System.out.println("LogAop.LogBefore： @Pointcut注解测试---【前置】通知启动================================");
    }

    /**
     * mzp：通知
     *      后置通知：在目标方法调用后返回。 @AfterReturning 注解对标 @Before注解
     */
    @AfterReturning("executionPointcut()")
    public void logAfterReturning() {
        System.out.println("LogAop.logAfterReturning： @Pointcut注解测试---【后置】通知启动================================");
    }

    /**
     * mzp: 通知。
     *      最终通知：正常执行的时机是在@AfterReturning"之前"。即使方法抛出异常，这个通知也会执行。类似于 finally
     */
    @After("executionPointcut()")
    public void logAfter() {
        System.out.println("LogAop.logAfter： @Pointcut注解测试---【最终】通知启动================================");
    }

    /**
     *  mzp: 通知。
     *       环绕通知。方法调用前后都能进行处理
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("annotationPointcut()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //创建一个Object类型的数组，业务代码有几个参数，该数组就需要几个元素
        Object[] myArgs = new Object[2];


        // 获取签名
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        // 获取执行的方法
        Method method = signature.getMethod();
        // 获取方法的参数,里面存的有参数名
        Parameter[] parameters = method.getParameters();
        // 获取方法名
        String methodName = method.getName();
        // 获取方法所在的类
        Class<?> declaringClass = method.getDeclaringClass();
        // 获取类名。 简单的类名，不是累的全路径名。
        String simpleName = declaringClass.getSimpleName();

        // 获取本次调用实际传递的参数值
        Object[] args = proceedingJoinPoint.getArgs();

        StringBuilder sb = new StringBuilder();
        sb.append("LogAop.logAround: @Pointcut注解测试---【Around通知】日志参数打印启动：================================");
        sb.append(simpleName).append(".").append(methodName).append("[");
        for (int i = 0; i < parameters.length; i++) {
            String name = parameters[i].getName();
            sb.append(name);
            sb.append(":");
            sb.append(args[i]);
            sb.append(";");
            // 保存一下这个参数。下面用于拦截修改这些参数
            myArgs[i] = args[i];
        }
        sb.setLength(sb.length() - 1);
        sb.append("]");

        /**
         * mzp： 这一句可以替换为日志打印。
         */
        System.out.println(sb.toString());

        // 业务代码方法执行。 返回执行结果
//        Object proceedResult = proceedingJoinPoint.proceed();
        myArgs[0] = "mzp,在aop中修改过了";
        Object proceedResult = proceedingJoinPoint.proceed(myArgs);

        // 下面的代码都是在业务代码执行之后，才执行的
        System.out.println("LogAop.logAround: @Pointcut注解测试---【Around通知】日志参数打印结束：================================");
        return proceedResult;
    }


//=======================================直接在通知上定义切点，不再额外定义切点=========================================
//这个切点表达式精确到了具体方法。 直接在通知上使用了切点表达式来确定切点。不需要再额外定义一个@Pointcut标注切点。

    @Before("@annotation(com.example.mzpspringboot.annotation.AopLog)")
    public void before() throws Throwable {
        System.out.println("=================注解方式切面启动(before)：日志=================");

        System.out.println("=================注解方式切面结束(before)：日志=================");
        return;
    }

    /**
     * mzp：通知
     *      后置通知：在目标方法调用后返回。 @AfterReturning 注解对标 @Before注解
     */
    @AfterReturning("@annotation(com.example.mzpspringboot.annotation.AopLog)")
    public void afterReturning() {
        System.out.println("LogAop.logAfterReturning： @Pointcut注解测试---【后置】通知启动================================");
    }

    /**
     * mzp: 通知。
     *      最终通知：正常执行的时机是在@AfterReturning"之前"。即使方法抛出异常，这个通知也会执行。类似于 finally
     */
    @After("execution(public * com.example.mzpspringboot.controller.AopController.testAspectAnnotation(..))")
    public void after() {
        System.out.println("LogAop.logAfter： @Pointcut注解测试---【最终】通知启动================================");
    }

    @Around("execution(public * com.example.mzpspringboot.controller.AopController.testAspect(..))")
    public Object spendTime(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("=================注解方式切面启动(around)：计算时间=================");
        Long startTime = System.currentTimeMillis();
        spendTime.set(startTime);
        //获取主业务中方法处的入参
        Object args = joinPoint.getArgs();
        /*
        //创建一个Object类型的数组，里面只有一个map元素，  这个map里面有两个键值对
        Object[] myArgs = new Object[]{   new HashMap(){{put("username","mzp");put("password","123");}}   };
        //proceed()中可以传入一个Object类型的数组进去。然后用这个数组去改变主业务中的入参。功能强大！
        Object object = joinPoint.proceed(myArgs);
        */
        Object object = joinPoint.proceed();
        System.out.println("参数是：" + args.toString());
        Long resultTime = System.currentTimeMillis() - spendTime.get();
        System.out.println(joinPoint.getSignature().getName() + "方法耗时：" + resultTime + "毫秒");
        System.out.println("=================注解方式切面结束(around)：计算时间=================");
        return object;
    }
}

package com.yang.controller_msg_aspect;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by dllo on 18/7/31.
 * ░░░░░░░░░░░░░░░░░░░░░░░░▄░░
 * ░░░░░░░░░▐█░░░░░░░░░░░▄▀▒▌░
 * ░░░░░░░░▐▀▒█░░░░░░░░▄▀▒▒▒▐
 * ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐
 * ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐
 * ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌
 * ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒
 * ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐
 * ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄
 * ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒
 * ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒
 * My Dear Taoism's Friend .Please SitDown.
 */
@Component
@Aspect
public class MethodLog {
    /**
     * 连接点  每个方法
     * 切点   选择有哪些连接点
     * 增强 adwise
     * 增强+切点 就是  切面
     * @Before @After
     * @AfterReturning
     * @AfterThrowing
     * @Arount 环绕增强
     *
     */
    @Before("within(com.yang.*.*.*)")
    public  void  beforeMethod(JoinPoint joinPoint){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String  methodName = joinPoint.getSignature().getName();
        Logger log = Logger.getLogger(MethodLog.class);
        //加载默认的配置信息
        BasicConfigurator.configure();
        log.log(Level.ERROR, "这是err log");
        log.debug("这是debug log+==类+"+className+"方法"+methodName+"将要执行");
        log.info("这是info log");
    }
    @After("within(com.yang.*.*.*)")
    public  void  afterMethod(JoinPoint joinPoint){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String  methodName = joinPoint.getSignature().getName();
        Logger log = Logger.getLogger(MethodLog.class);
        //加载默认的配置信息
        BasicConfigurator.configure();
        log.log(Level.ERROR, "这是err log");
        log.debug("这是debug log+==类+"+className+"方法"+methodName+"已经被执行");
        log.info("这是info log");
    }

    @AfterThrowing(throwing = "ex" ,pointcut="within(com.yang.*.*.*)")
    public void afterThrowingMethod(JoinPoint joinPoint,Throwable ex){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String  methodName = joinPoint.getSignature().getName();
        Logger log = Logger.getLogger(MethodLog.class);
        BasicConfigurator.configure();
        log.log(Level.ERROR, "这是err log");
        log.debug("@afterThrowing异常测试+"+className+"方法"+methodName+"出现异常"+"目标方法中抛出的异常:" + ex.toString());
        log.info("这是info log");
    }

    @AfterReturning(pointcut = "within(com.yang.*.*.*)")
    public void afterReturningMethod (JoinPoint joinPoint){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Logger log = Logger.getLogger(MethodLog.class);
        BasicConfigurator.configure();
        log.log(Level.ERROR, "这是err log");
        log.debug("@afterReturning方法执行测试+"+className+"方法"+methodName+"正常执行结束");
        log.info("这是info log");

    }
    //记录方法执行时间  
    @Around("within(com.yang.*.*.*)")
    public Object logMethodTime(ProceedingJoinPoint joinPoint){
        Object obj = null;
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            obj = joinPoint.proceed();
            watch.stop();
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            Long time = watch.getTotalTimeMillis();
            Logger log = Logger.getLogger(MethodLog.class);
            BasicConfigurator.configure();
            log.log(Level.ERROR, "这是err log");
            log.debug("@Around方法执行时间记录测试+"+className+"方法"+methodName+"执行时间"+time+"s");
            log.info("这是info log");


        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }
}

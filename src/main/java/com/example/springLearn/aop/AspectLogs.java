package com.example.springLearn.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;

@Aspect
public class AspectLogs {
    @Pointcut("execution(public * com.example.springLearn.service.AopService.*(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("Aop的前置方法-------------------------------");
        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" +joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:"+ joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:"+ Modifier.toString(joinPoint.getSignature().getModifiers()));
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i+1) + "个参数为:" + args[i]);
        }
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());
    }

    @After("pointCut()")
    public void after() {
        System.out.println("Aop的后置方法-------------------------------");
    }

    @Around("pointCut()")
    public void around(JoinPoint joinPoint) throws Throwable {
        System.out.println("AOP的环绕方法...................");
        System.out.println("开始增强方法的执行...................");
        //增强方法的执行
        ((ProceedingJoinPoint) joinPoint).proceed();
        System.out.println("增强方法执行结束-------------------------------");
    }

    @AfterReturning("pointCut()")
    public void returnAop() {
        System.out.println("Aop的正常返回方法-------------------------------");

    }

    @AfterThrowing("pointCut()")
    public void exceptionAop() {
        System.out.println("Aop的异常调用方法-------------------------------");

    }
}

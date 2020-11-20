package com.springcourse.kursspringboot2aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloAspect {

    @After("@annotation(HelloAcpect))")
    private void afterHello(){
        System.out.println("After Hello");
    }

    @Around("@annotation(HelloAcpect))")
    private void aroundHello(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("Around Hello");
        joinPoint.proceed();
    }

    @Before("@annotation(HelloAcpect))")
    private void beforeHello(){
        System.out.println("Before Hello");
    }
}

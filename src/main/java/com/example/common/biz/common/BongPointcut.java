package com.example.common.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BongPointcut {
    @Pointcut("execution(* com.example.common.biz..*Impl.*(..))")
    public void allPointcut() {}

    @Pointcut("execution(* com.example.common.view..*(..))")
    public void controllerPointcut() {}
}

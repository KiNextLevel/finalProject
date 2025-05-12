package com.example.common.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {
    @Pointcut("execution(* com.example.common.biz..*Impl.get*(..))")
    public void aPointcut() {}
    @Pointcut("execution(* com.example.common.biz..*Impl.*(..))")
    public void bPointcut() {}
}

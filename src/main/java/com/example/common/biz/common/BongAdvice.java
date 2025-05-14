package com.example.common.biz.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Slf4j
@Service    //공통로직 원래 service에서 분리한거라 @Service 어노테이션 와야함
@Aspect     //Aspect 명시
public class BongAdvice {

    @Before("BongPointcut.allPointcut()")
    public void before(JoinPoint jp) {
        log.info("BEFORE 로그");
        String methodName = jp.getSignature().getName();
        log.info("실행된 메서드: [" + methodName + "]");
    }

    @Before("BongPointcut.controllerPointcut()")
    public void beforeController(JoinPoint jp) {
        log.info("beforeController 로그");
        String methodName = jp.getSignature().getName();
        log.info("실행된 메서드: [" + methodName + "]");
    }
//    @Around("BongPointcut.allPointcut()")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable {
//        System.out.println("AROUND 공통 로그 시작");
//        String method = pjp.getSignature().getName();
//
//        System.out.println(method + "시간 측정 시작");
//
//        StopWatch sw = new StopWatch();
//        sw.start();
//
//        Object result = pjp.proceed();
//
//        sw.stop();
//        System.out.println(method + "시간 측정 끝.\n " +
//                method + "걸린 시간 >> " + sw.getTotalTimeMillis() + "ms");
//        System.out.println("AROUND 공통 로그 끝");
//
//        return result;
//    }
}

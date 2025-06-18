package com.example.common.biz.common;

import com.example.common.biz.board.BoardVO;
import com.example.common.biz.user.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Aspect
public class BenLogAdvice {
    @Before("PointcutCommon.bPointcut()")
    public void before(JoinPoint joinPoint) {
        log.info("Biz get, getList - Before log");
        log.info("methodName = [" + joinPoint.getSignature().getName() + "]");
    }

//    @After("PointcutCommon.bPointcut()")
//    public void after() {
//        log.info("After log");
//    }

//    @AfterReturning(pointcut = "PointcutCommon.aPointcut()", returning = "returnObj")
//    public Object afterReturning(JoinPoint joinPoint, Object returnObj) {
//        log.info("RETURNING 공통 로그");
//        log.info("methodName = [" + joinPoint.getSignature().getName() + "]");
//        System.out.print("\t");
//        if (returnObj == null) {
//            log.info("ReturnOBJ == null");
//        } else if(returnObj instanceof UserVO vo) {
//            log.info("회원 관련 서비스");
//            // 다운 캐스팅
//            if (vo.getUserRole() == null) {
//                log.info("userRole is null");
//            } else if(vo.getUserRole() == 0) {
//                log.info("\tROLE = 회원");
//            } else if(vo.getUserRole() == 1) {
//                log.info("\tROLE = 관리자");
//            }
//        } else if (returnObj instanceof List<?>) {
//            log.info(((List<?>) returnObj).getFirst().getClass().getName() + "관련 서비스");
//        } else {
//            log.info(returnObj.getClass().getName() + "관련 서비스");
//        }
//        return returnObj;
//    }

//    @AfterThrowing(pointcut="PointcutCommon.aPointcut()", throwing="exceptObj")
//    public void throwing(JoinPoint jp, Exception exceptObj) {
//        log.info("THROWING 공통 로그");
//
//        log.info("\t 에러 리포트 ["+exceptObj.getMessage()+"]");
//        System.out.print("\t[");
//        System.out.print("일부러 발생시킨 예외");
//        log.info("]");
//    }

    @Around("PointcutCommon.bPointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable { // 바인드 변수
        log.info("===AROUND 공통 로그 시작===");

        StopWatch sw = new StopWatch();

        sw.start();
        Object obj = pjp.proceed();
        sw.stop();

        String methodName = pjp.getSignature().getName();
        log.info("\t수행한 서비스 메서드명 ["+methodName+"]");
        log.info("\t걸린시간 ["+sw.getTotalTimeMillis()+"]ms");

        log.info("===AROUND 공통 로그 끝==="); // ms

        return obj;
    }
}

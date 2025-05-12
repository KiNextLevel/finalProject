package com.example.common.biz.common;

import com.example.common.biz.board.BoardVO;
import com.example.common.biz.user.UserVO;
import org.springframework.util.StopWatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class BenLogAdvice {
    @Before("PointcutCommon.bPointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("=== Biz - Before log ===");
        System.out.println("methodName = [" + joinPoint.getSignature().getName() + "]");
    }

//    @After("PointcutCommon.bPointcut()")
//    public void after() {
//        System.out.println("After log");
//    }

    @AfterReturning(pointcut = "PointcutCommon.aPointcut()", returning = "returnObj")
    public Object afterReturning(JoinPoint joinPoint, Object returnObj) {
        System.out.println("RETURNING 공통 로그");
        System.out.print("\t");
        if (returnObj == null) {
            System.out.println("ReturnOBJ == null");
        } else if(returnObj instanceof UserVO vo) {
            System.out.println("회원 관련 서비스");
            // 다운 캐스팅
            if (vo.getUserRole() == null) {
                System.out.println("userRole is null");
            } else if(vo.getUserRole() == 0) {
                System.out.println("\tROLE = 회원");
            } else if(vo.getUserRole() == 1) {
                System.out.println("\tROLE = 관리자");
            }
        }
        else {
            System.out.println(returnObj.getClass().getName() + "관련 서비스");
        }
        return returnObj;
    }

//    @AfterThrowing(pointcut="PointcutCommon.aPointcut()", throwing="exceptObj")
    public void throwing(JoinPoint jp, Exception exceptObj) {
        System.out.println("THROWING 공통 로그");

        System.out.println("\t 에러 리포트 ["+exceptObj.getMessage()+"]");
        System.out.print("\t[");
        System.out.print("일부러 발생시킨 예외");
        System.out.println("]");
    }

    @Around("PointcutCommon.bPointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable { // 바인드 변수
        System.out.println("AROUND 공통 로그 시작");

        StopWatch sw = new StopWatch();

        sw.start();
        Object obj = pjp.proceed();
        sw.stop();

        String methodName = pjp.getSignature().getName();
        System.out.println("\t수행한 서비스 메서드명 ["+methodName+"]");
        System.out.println("\t걸린시간 ["+sw.getTotalTimeMillis()+"]ms");

        System.out.println("AROUND 공통 로그 끝"); // ms

        if(methodName.equals("getMember")) {
            UserVO vo = (UserVO)obj;
            if(vo.getUserRole() == 1) {
                throw new Exception("!!!일반회원 로그인!!!");
            }
        }
        return obj;
    }
}

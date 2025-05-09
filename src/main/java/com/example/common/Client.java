//package com.example.common;
//
//import com.example.common.biz.alert.AlertVO;
//import com.example.common.biz.alert.impl.AlertServiceImpl;
//import com.example.common.biz.report.ReportVO;
//import com.example.common.biz.report.mybatis.ReportMapper;
//import com.example.common.biz.report.mybatis.ReportMybatisService;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.support.AbstractApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
//
//import java.util.Scanner;
//
//@SpringBootApplication
//@MapperScan("com.example.common.biz.report.mybatis")
//public class Client implements CommandLineRunner {
//
//	@Autowired
//	private ReportMybatisService reportService;
//
//	public static void main(String[] args) {
//		SpringApplication.run(NextLevelApplication.class, args);
//	}
//	@Override
//	public void run(String... args) {
////		ReportVO vo = new ReportVO();
//		vo.setCondition("INSERT");
//		vo.setReportDescription("테스트 설명");
//		vo.setReportReason("욕설");
//		vo.setReportReported("피신고자");
//		vo.setReportReporter("신고자");
//
//		boolean result = reportService.insert(vo);
//		System.out.println("Insert 성공 여부: " + result);
//	}
//	// 1. 스프링 컨테이너 구동
////	AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
////
////	Scanner sc = new Scanner(System.in);
//////		AlertServiceImpl alertService = (AlertServiceImpl) factory.getBean("alertService");
////
////	ReportMybatisService reportService = (ReportMybatisService) factory.getBean("reportMybatisService");
//
////		System.out.print("이메일 >> ");
////		String email = sc.next(); // 아무거나 입력
////		//test@test.com
////		AlertVO vo = new AlertVO();
////		vo.setUserEmail(email);
////		for(AlertVO s : alertService.getAlertList(vo) ) {
////			System.out.println(s);
////		}
////
////		System.out.print("내용 입력 >> ");
////		String content = sc.next();
////		vo.setAlertContent(content);
////		System.out.println(alertService.insert(vo));
////
////		vo.setAlertNumber(1);
////		System.out.println(alertService.update(vo));
////
////		vo.setUserEmail("test@test.com");
////		for(AlertVO s : alertService.getAlertList(vo) ) {
////			System.out.println(s);
////		}
//
//
//
//	// 3. 스프링 컨테이너 종료
//}

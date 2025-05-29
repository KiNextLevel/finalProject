package com.example.common;

import com.example.common.biz.alert.AlertVO;
import com.example.common.biz.alert.impl.AlertServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Client { // 웹 브라우저 역할
	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");

		AlertServiceImpl alertService = (AlertServiceImpl) factory.getBean("alertService");
		Scanner sc = new Scanner(System.in);
		System.out.print("이메일 >> ");
		String email = sc.next(); // 아무거나 입력
		//test@test.com
		AlertVO vo = new AlertVO();
		vo.setUserEmail(email);
		for(AlertVO s : alertService.getAlertList(vo) ) {
			System.out.println(s);
		}

		System.out.print("내용 입력 >> ");
		String content = sc.next();
		vo.setAlertContent(content);
		System.out.println(alertService.insert(vo));

		vo.setAlertNumber(1);
		System.out.println(alertService.update(vo));

		vo.setUserEmail("test@test.com");
		for(AlertVO s : alertService.getAlertList(vo) ) {
			System.out.println(s);
		}

		// 3. 스프링 컨테이너 종료
		factory.close();
	}
}

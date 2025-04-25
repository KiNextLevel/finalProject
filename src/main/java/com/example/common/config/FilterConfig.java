//package com.example.common.config;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration //설정 클래스 지정
//public class FilterConfig{
//
//    @Bean //반환 객체를 빈으로 등록
//    public FilterRegistrationBean<AuthFilter> authFilter() {
//        //FilterRegistrationBean: 서블릿 필터 등록 위한 객체
//        //등록할 필터 클래스는 AuthFilter
//        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new AuthFilter());
//        registrationBean.addUrlPatterns("*.jsp");  // .jsp 요청에 필터 적용
//        return registrationBean;
//    }
//}

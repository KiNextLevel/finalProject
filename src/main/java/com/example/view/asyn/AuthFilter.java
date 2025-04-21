package com.example.view.asyn;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        //요청 페이지 추출
        String requestURI = request.getRequestURI();
        String pageName = requestURI.substring(requestURI.lastIndexOf("/") + 1);

        // 세션에서 로그인된 사용자 정보 확인
        String userEmail = (String) request.getSession().getAttribute("userEmail");
        Integer userRole = (Integer) request.getSession().getAttribute("userRole");
        System.out.println("pageName: " + pageName);
        System.out.println("userRole: " + userRole);

        if (userEmail == null && !pageName.equals("Index.jsp") && !pageName.equals("Login.jsp") && !pageName.equals("JoinPage.jsp")) {
            response.sendRedirect("/Index.jsp");
            return;
        } else if (userEmail != null && !pageName.equals("JoinPage.jsp")) {
            if (pageName.equals("Index.jsp") || pageName.equals("Login.jsp")) {
                response.sendRedirect("/mainPage.do");
                return;
            } else if (userRole != null && userRole == 0 && pageName.contains("Admin")) {
                response.sendRedirect("/mainPage.do");
                return;
            }
        }

        filterChain.doFilter(req, res);

    }
}

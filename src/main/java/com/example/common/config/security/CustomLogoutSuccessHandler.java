//package com.example.common.config.security;
//
//import jakarta.servlet.http.HttpServletRequest; import jakarta.servlet.http.HttpServletResponse; import jakarta.servlet.http.HttpSession; import org.springframework.security.core.Authentication; import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//
//import java.io.IOException;
//
//public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
//    @Override
//    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
//                                Authentication authentication) throws IOException {
//        // 세션에서 사용자 정보 제거
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.removeAttribute("userEmail");
//            session.removeAttribute("userRole");
//            session.removeAttribute("userPremium");
//            session.removeAttribute("userLatitude");
//            session.removeAttribute("userLongitude");
//            session.invalidate();
//        }
//
//        // 로그아웃 후 리디렉션
//        response.sendRedirect("/Index.jsp");
//    }
//}

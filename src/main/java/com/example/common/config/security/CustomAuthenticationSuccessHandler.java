//package com.example.common.config.security;
//import com.example.common.biz.user.UserService; import com.example.common.biz.user.UserVO; import com.example.common.view.auth.LoginController; import jakarta.servlet.http.HttpServletRequest; import jakarta.servlet.http.HttpServletResponse; import org.springframework.security.core.Authentication; import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import java.io.IOException;
//
//public class CustomAuthenticationSuccessHandler  implements AuthenticationSuccessHandler{
//    private final LoginController loginController;
//    private final UserService userService;
//
//    public CustomAuthenticationSuccessHandler(LoginController loginController, UserService userService) {
//        this.loginController = loginController;
//        this.userService = userService;
//    }
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException {
//        // 사용자 정보 조회
//        String userEmail = authentication.getName();
//        UserVO userVO = new UserVO();
//        userVO.setUserEmail(userEmail);
//        userVO.setCondition("SELECTONE_NONSOCIAL");
//        userVO = userService.getUser(userVO);
//
//        if (userVO != null) {
//            // 기존 setSession 메서드 호출
//            loginController.setSession(userVO, request);
//
//            // userRole에 따라 리디렉션 경로 결정
//            String redirectUrl = userVO.getUserRole() == 0 ? "/mainPage.do" : "/adminPage.do";
//            response.sendRedirect(redirectUrl);
//        } else {
//            response.sendRedirect("/Login.jsp?error=true");
//        }
//    }
//}

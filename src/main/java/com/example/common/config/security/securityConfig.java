package com.example.common.config.security;

import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
import com.example.common.view.auth.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
public class securityConfig {
    @Autowired
    private UserService userService;
    @Autowired
    private LoginController loginController;
    private final static String[] ROLES = new String[]{"USER", "ADMIN", "BLACK", "WITHDRAW"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
                // 로그인 설정
                .formLogin(form -> form
                        .loginPage("/loginPage.do") // 사용자 지정 로그인 페이지
                        .loginProcessingUrl("/login.do") // 로그인 form action에 설정되는 URL
                        .successHandler(customAuthenticationSuccessHandler()) // 로그인 성공 시 처리
                        .failureHandler(customAuthenticationFailureHandler()) // 로그인 실패 시 처리
                        .failureUrl("/loginPage.do?error=true") // 실패 시 리다이렉트 URL
                        .permitAll() // 로그인 관련 경로는 인증 없이 접근 가능
                )
                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/logout.do")
                        .logoutSuccessUrl("/index.do")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(customAccessDeniedHandler()) // 인가 실패 시 처리
                        .authenticationEntryPoint((request, response, authException) -> {
                            System.out.println("Authentication required for: " + request.getRequestURI());
                            response.sendRedirect("/loginPage.do");
                        })
                )
                .authorizeHttpRequests(auth -> auth
                        // 인증 없이 접근 가능한 경로들
                        .requestMatchers(
                                "/Index.jsp", "/index.do",
                                "/Login.jsp", "/loginPage.do",
                                "/JoinPage.jsp", "/join.do",
                                "/mainPage.do", // mainPage.do 허용 (권한 부족 시 리디렉션 루프 방지)
                                "/css/**", "/js/**", "/images/**", "/Metronic-Shop-UI-master/**" // 정적 리소스 허용
                        ).permitAll() // 비로그인 허용
                        // Admin 페이지는 관리자만 접근 가능
                        .requestMatchers("/**Admin**", "/**admin**").hasRole("ADMIN")
                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            System.out.println("Attempting to load user: " + username);
            UserVO userVO = new UserVO();
            userVO.setUserEmail(username);
            userVO.setCondition("SELECTONE_SECURITY");
            userVO = userService.getUser(userVO);
            System.out.println("Fetched userVO: " + (userVO != null ? "userEmail=" + userVO.getUserEmail() + ", userPassword=" + userVO.getUserPassword() : "null"));

            if (userVO == null) {
                System.out.println("User not found in DB for email: " + username);
                throw new UsernameNotFoundException("User not found with email: " + username);
            }

            String role = ROLES[userVO.getUserRole()];
            System.out.println("User role: " + role + ", Password from DB: " + userVO.getUserPassword());
            return org.springframework.security.core.userdetails.User
                    .withUsername(userVO.getUserEmail())
                    .password(userVO.getUserPassword())
                    .roles(role)
                    .build();
        };
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            String userEmail = authentication.getName();
            System.out.println("Login successful for: " + userEmail);
            UserVO userVO = new UserVO();
            userVO.setUserEmail(userEmail);
            userVO.setCondition("SELECTONE_SECURITY");
            userVO = userService.getUser(userVO);

            if (userVO != null) {
                String role = ROLES[userVO.getUserRole()];

                // BLACK or WITHDRAWN 사용자 로그인 차단
                if (role.equals("BLACK") || role.equals("WITHDRAW")) {
                    System.out.println("블랙 / 탈퇴 회원이 로그인 시도: " + userEmail);
                    request.getSession().invalidate();
                    response.sendRedirect("/loginPage.do?blocked=true");
                    return;
                }
                loginController.setSession(userVO, request);
                String redirectUrl = role.equals("USER") ? "/mainPage.do" : "/adminPage.do";
                response.sendRedirect(redirectUrl);
            } else {
                response.sendRedirect("/loginPage.do?error=true");
            }
        };
    }

    @Bean
    public AccessDeniedHandler customAccessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            String uri = request.getRequestURI();
            System.out.println("Access denied for URI: " + uri);

            // 일반 사용자가 Admin 페이지 접근 시 mainPage.do로 리디렉션
            if (uri.contains("Admin") || uri.contains("admin")) {
                response.sendRedirect("/mainPage.do");
            } else {
                response.sendRedirect("/index.do");
            }
        };
    }

    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return (request, response, exception) -> {
            System.out.println("Login failed: " + exception.getMessage());
            response.sendRedirect("/loginPage.do?error=true");
        };
    }

    @Bean
    public HttpFirewall allowDoubleSlashesFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return firewall;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // 평문 비밀번호 사용
    }
    /* 운영 단계에서는 어떻게 설정해야할까?
    // CSRF는 비활성화하지 않는 것이 좋음
    http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    // 모든 요청은 인증 필요
    http.authorizeHttpRequests(auth -> auth
        .antMatchers("/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated()
    );

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    * */
}
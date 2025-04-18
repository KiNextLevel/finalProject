package com.example.view.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutController {
    @PostMapping("/logout.do")
    public String logout(HttpServletRequest request, HttpSession session, Model model) {
        System.out.println("LOG : LOGOUT CONTROLLER - LOGOUT METHOD");

        // session 비우기
        session.invalidate();

        // url, flag, msg 요청단위 저장
        // alert.jsp에 url, true, msg 보내기
        model.addAttribute("msg", "로그아웃 성공!");
        model.addAttribute("flag", true);
        model.addAttribute("url", "loginPage.do");
        return "Metronic-Shop-UI-master/theme/Alert";
    }
}

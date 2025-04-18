package com.example.view.mainPage;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainPageController {
    @GetMapping("/adminPage.do")
    public String adminMainPage(HttpSession session, Model model) {
        System.out.println("AdminMainPageAction 로그: 도착");
        System.out.println("AdminMainPageAction 로그 userRole:["+session.getAttribute("userRole")+"]");

        // 관리자는 이동 가능
        String path= "redirect:/target-free-admin-template/AdminMainPage.jsp";
        if((Integer)session.getAttribute("userRole") != 1) {  //관리자 아니면 이동 불가능
            model.addAttribute("msg", "관리자만 접근 가능합니다");
            model.addAttribute("flag", false);
            path = "/Metronic-Shop-UI-master/themeAlert";
        }
        return path;
    }
}
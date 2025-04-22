package com.example.common.view.mainPage;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainPageController {
    @GetMapping("/adminPage.do")
    public String adminMainPage(HttpSession session, Model model) {
        System.out.println("AdminMainPageAction 로그: 도착");
        System.out.println("AdminMainPageAction 로그 userRole:[" + session.getAttribute("userRole") + "]");

        return "redirect:/adminMainPage.do";
    }

    @GetMapping("/adminMainPage.do")
    public String adminMainPage() {
        return "/target-free-admin-template/AdminMainPage";
    }
}

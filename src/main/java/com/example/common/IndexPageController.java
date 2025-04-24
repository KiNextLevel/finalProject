package com.example.common;

import com.example.common.biz.user.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class IndexPageController {
    @GetMapping("/index.do")
    public String index() {
        System.out.println("index");
        return "Metronic-Shop-UI-master/theme/Index"; // /Metronic-Shop-UI-master/theme/index.jsp를 렌더링
    }

    @PostMapping("/test123.do")
    public String test123(@RequestParam(value = "userProfile", required = false) MultipartFile userProfileFile, UserVO userVO,
                           HttpServletRequest request, HttpSession session, Model model) {
        System.out.println("LOG : TEST 123 CONTROLLER - TEST 123 METHOD");
        return "Metronic-Shop-UI-master/theme/UserPreference";
    }
}

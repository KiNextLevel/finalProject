package com.example.common.view.board;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 관리자가 게시판 추가하는 페이지 이동 액션
@Controller
public class AdminAddBoardPageController {

    @GetMapping("/adminAddBoardPage.do")
    public String adminAddBoard(HttpSession session, Model model) {
        System.out.println("AdminAddBoardPageController 도착");

        // 관리자만 접근 허용
        if((Integer)session.getAttribute("userRole")==1) {	//관리자만 이동 가능
            //forward.setPath("/Metronic-Shop-UI-master/theme/AdminAddBoardPage.jsp");
            //forward.setRedirect(true);
            return "redirect:/adminAddBoardPageGO.do"; // 리다이렉트
        }
        else{
            model.addAttribute("msg", "관리자만 가능합니다");
            model.addAttribute("flag", false);
        }
        return "/Metronic-Shop-UI-master/theme/Alert";
    }

    @GetMapping("/adminAddBoardPageGO.do")
    public String adminAddBoardPage() {
        System.out.println("/adminAddBoardPageGO.do 도착");
        return "/Metronic-Shop-UI-master/theme/AdminAddBoardPage";
    }
}

package com.example.common.view.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//관리자 게시판 수정하는 페이지 이동 액션
@Controller
public class BoardPageController {
    @GetMapping("/boardPage.do")
    public String boardPage() {
        System.out.println("BoardPageController 진입");
        System.out.println("boardPage 로그: 도착");

        return "/Metronic-Shop-UI-master/theme/BoardPage";
    }
}
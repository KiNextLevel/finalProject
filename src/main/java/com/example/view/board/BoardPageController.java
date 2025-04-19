package com.example.view.board;

import com.example.biz.board.BoardService;
import com.example.biz.board.BoardVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

//관리자 게시판 수정하는 페이지 이동 액션
@Controller
public class BoardPageController {
    @Autowired
    private BoardService boardService;

    public String boardPage(HttpSession session, BoardVO boardVO, Model model, HttpServletRequest request ) {
        System.out.println("BoardPageController 진입");
        System.out.println("boardPage 로그: 도착");

        // HttpSession session = request.getSession();
        String Email = (String) session.getAttribute("userEmail");
        boardVO.setSearchKeyword(Email);

        List<BoardVO> datas = boardService.selectAll(boardVO);	//이벤트 리스트 조회
        System.out.println("board SELECTALL 로그:"+datas);

        model.addAttribute("datas", datas);
        return "/Metronic-Shop-UI-master/theme/BoardPage";
    }
}

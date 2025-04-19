package com.example.view.board;

import com.example.biz.board.BoardService;
import com.example.biz.board.BoardVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

//관리자 게시판 수정하는 페이지 이동 액션
@Controller
public class AdminModifyBoardPageController {
    private BoardService boardService;

    public String adminModifyBoardPage(HttpServletRequest request, BoardVO boardVO, Model model) {
        System.out.println("adminModifyBoardPageController 진입");

        int boardNum = Integer.parseInt(request.getParameter("boardNum"));//수정하려는 이벤트 번호
        System.out.println("boardNum["+boardNum+"]");
        boardVO.setBoardNumber(boardNum);
        boardVO = boardService.selectOne(boardVO);
        //System.out.println("boardDTO ="+boardVO.toString());

        if(boardVO == null) {
            model.addAttribute("msg", "이벤트를 찾을 수 없습니다");
            model.addAttribute("flag", false);
           //forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
            //forward.setRedirect(false);
            return "/Metronic-Shop-UI-master/theme/Alert"; // forward 방식
        }
        else {
            model.addAttribute("data", boardVO);//수정하려는 이벤트 정보 가지고 감
            //forward.setPath("/Metronic-Shop-UI-master/theme/AdminModifyBoardPage.jsp");
            //forward.setRedirect(false);
        }
        return "/Metronic-Shop-UI-master/theme/AdminModifyBoardPage"; // forward 방식
    }
    }

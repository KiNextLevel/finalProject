package com.example.view.board;

import com.example.biz.board.BoardService;
import com.example.biz.board.BoardVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

//관리자 게시판 수정 기능
@Controller
public class AdminModifyBoardController {
    @Autowired
    private BoardService boardService;

    public String adminModifyBoard(Model model, BoardVO boardVO, HttpServletRequest request) {
        boardVO.setBoardNumber((Integer.parseInt(request.getParameter("boardNum"))));//수정할 이벤트 번호
        boardVO = boardService.selectOne(boardVO);
       // System.out.println("boardDTO = "+boardVO.toString());
        boardVO.setBoardTitle(request.getParameter("boardTitle"));	//수정할 이벤트 제목
        boardVO.setBoardContent(request.getParameter("boardContent"));	//수정할 이벤트 내용
        System.out.println("boardLimit: ["+request.getParameter("boardLimit")+"]");
        boardVO.setBoardLimit(Integer.parseInt(request.getParameter("boardLimit")));	////수정할 이벤트 제한인원
        boardVO.setCondition("UPDATE_BOARD");

        if(boardService.update(boardVO)) {
            model.addAttribute("msg", "수정 완료");
            model.addAttribute("flag", true);
            model.addAttribute("url", "boardPage.do");
        }
        else {
            model.addAttribute("msg", "수정 실패");
            model.addAttribute("flag", false);
        }
        return "/Metronic-Shop-UI-master/theme/Alert";
    }
}

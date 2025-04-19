package com.example.view.board;

import com.example.biz.board.BoardService;
import com.example.biz.board.BoardVO;
import com.example.biz.participant.ParticipantService;
import com.example.biz.participant.ParticipantVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

//관리자가 게시판 삭제하는 기능
@Controller
public class AdminDeleteBoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private ParticipantService participantService;

    public String adminDeleteBoard(HttpServletRequest request, ParticipantVO participantVO, BoardVO boardVO, Model model) {
        System.out.println("adminDeleteBoardController 진입");

        System.out.println(Integer.parseInt(request.getParameter("boardNum")));
        boardVO.setBoardNumber(Integer.parseInt(request.getParameter("boardNum")));//삭제할 이벤트 번호
        participantVO.setParticipantBoardNumber(Integer.parseInt(request.getParameter("boardNum")));//삭제할 이벤트 번호
        participantVO.setCondition("DELETE_BOARD_NUM");

        if(boardService.delete(boardVO) && participantService.delete(participantVO)) {	//이벤트 삭제하면
            System.out.println("삭제 성공 로그");										//참가자 테이블에서도 해당 이벤트 삭제
            model.addAttribute("msg", "이벤트 삭제 성공");
            model.addAttribute("url", "boardPage.do");
            model.addAttribute("flag", true);
        }
        else{
            model.addAttribute("msg", "이벤트 삭제 실패");
            model.addAttribute("flag", false);
        }
        return "/Metronic-Shop-UI-master/theme/Alert";
    }
}

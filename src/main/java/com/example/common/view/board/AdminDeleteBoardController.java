package com.example.common.view.board;

import com.example.common.biz.board.BoardService;
import com.example.common.biz.board.BoardVO;
import com.example.common.biz.participant.ParticipantService;
import com.example.common.biz.participant.ParticipantVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

//관리자가 게시판 삭제하는 기능
@Controller
public class AdminDeleteBoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private ParticipantService participantService;

    @PostMapping("/adminDeleteBoard.do")
    public String adminDeleteBoard(HttpServletRequest request, ParticipantVO participantVO, BoardVO boardVO, Model model) {
        System.out.println("adminDeleteBoardController 진입");

        System.out.println(request.getParameter("boardNumber"));
//        boardVO.setBoardNumber(Integer.parseInt(request.getParameter("boardNum")));//삭제할 이벤트 번호

        //participant 테이블에 삭제하려는 이벤트 있는지
        participantVO.setParticipantBoardNumber(Integer.parseInt(request.getParameter("boardNumber")));
        participantVO.setCondition("SELECTALL_NUM");

        boolean participantDeleted = true;
        System.out.println("이벤트 있는지 "+participantService.getParticipantList(participantVO));

        if(!participantService.getParticipantList(participantVO).isEmpty()) {
            participantVO.setParticipantBoardNumber(Integer.parseInt(request.getParameter("boardNumber")));//삭제할 이벤트 번호
            participantVO.setCondition("DELETE_BOARD_NUM");
            participantDeleted = participantService.delete(participantVO);
            System.out.println("participantDeleted "+participantDeleted);

        }



//        boolean participantDeleted = participantService.delete(participantVO);
//        System.out.println("participantDeleted: "+participantDeleted);
        // 참가자 데이터가 있으면 삭제
//        if (participantService.getParticipant(participantVO) != null) {
//            System.out.println("해당 이벤트에 참가중인 유저 있음");
//            participantDeleted = participantService.delete(participantVO);
//            System.out.println("participantDeleted: ["+participantDeleted+"]");
//        }

        // 게시글은 무조건 삭제
        boolean boardDeleted = boardService.delete(boardVO);

        // 삭제 성공 여부 판단
        if (boardDeleted && participantDeleted) {
            System.out.println("삭제 성공 로그");
            model.addAttribute("msg", "이벤트 삭제 성공");
            model.addAttribute("flag", true);
            model.addAttribute("url", "boardPage.do");
        } else {
            System.out.println("삭제 실패 로그");
            model.addAttribute("msg", "이벤트 삭제 실패");
            model.addAttribute("flag", false);
        }
        return "/Metronic-Shop-UI-master/theme/Alert";
    }
}

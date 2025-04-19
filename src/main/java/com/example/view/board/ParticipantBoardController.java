package com.example.view.board;

import com.example.biz.board.BoardService;
import com.example.biz.board.BoardVO;
import com.example.biz.participant.ParticipantService;
import com.example.biz.participant.ParticipantVO;
import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

//이벤트 참가, 취소 액션
@Controller
public class ParticipantBoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private UserService userService;
    @Autowired
    private ParticipantService participantService;

    //@PostMapping("/participantBoard.do")
    public String participantBoard(Model model, HttpSession session, HttpServletRequest request, BoardVO boardVO, UserVO userVO, ParticipantVO participantVO ) {
        System.out.println("ParticipantBoardAction 액션 도착");

        int boardNum = Integer.parseInt(request.getParameter("boardNumber"));	//참가하려는 이벤트 번호
        boardVO.setBoardNumber(boardNum);
        System.out.println("boardNumber: ["+boardNum+"]");
        String userEmail = (String)session.getAttribute("userEmail");	//로그인 한 사용자 이메일
        System.out.println("userEmail: ["+userEmail+"]");


        participantVO.setParticipantUserEmail(userEmail);
        participantVO.setCondition("SELECTALL");
        List<ParticipantVO> datas = participantService.selectAll(participantVO);
        //ArrayList<ParticipantDTO> datas = participantDAO.selectAll(participantDTO);//로그인 한 사용자가 참가한 이벤트
        System.out.println("datas: ["+datas+"]");
        participantVO.setParticipantBoardNumber(boardNum);
        System.out.println("participantDTO: ["+participantVO+"]");
        System.out.println("participantDAO.selectOne(participantDTO)).getParticipantBoardNumber(): ["+participantService.selectOne(participantVO).getParticipantBoardNumber()+"]");
        System.out.println("boardDTO.getBoardLimit(): ["+boardService.selectOne(boardVO).getBoardLimit()+"]");


        for(ParticipantVO v: datas){   //이미 참가 신청한 이벤트 버튼 다시 누르면 참가 취소
            if (v.getParticipantBoardNumber() == boardNum) {
                participantVO.setCondition("DELETE");
                participantService.delete(participantVO);
                System.out.println("v.getParticipantBoardNumber: ["+v.getParticipantBoardNumber()+"]");
                System.out.println("v.getParticipantUserEmail: "+v.getParticipantUserEmail()+"]");
                model.addAttribute("msg", "참가 취소 되었습니다");
                model.addAttribute("url", "boardPage.do");
                model.addAttribute("flag", true);
                return "/Metronic-Shop-UI-master/theme/Alert";
            }
        }
        //인원 다 찼으면 참가 못함
        if((participantService.selectOne(participantVO)).getParticipantBoardNumber() >= boardService.selectOne(boardVO).getBoardLimit()){  //인원수 다 차면
            model.addAttribute("msg", "인원이 다 찼습니다");
            model.addAttribute("flag", false);
            return "/Metronic-Shop-UI-master/theme/Alert";
        }
        if(participantService.insert(participantVO)) {   //참가 성공
            model.addAttribute("msg", "이벤트 참가 성공");
            model.addAttribute("flag", true);
            model.addAttribute("url", "boardPage.do");
        }
        else {                              //참가 실패
            model.addAttribute("msg", "참가 실패");
            model.addAttribute("flag", false);
        }
        return "/Metronic-Shop-UI-master/theme/Alert";
    }
}

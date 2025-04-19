package com.example.view.board;
//관리자가 게시판 추가하는 기능
import com.example.biz.board.BoardService;
import com.example.biz.board.BoardVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class AdminAddBoardController {
    @Autowired
    private BoardService boardService;


    public String adminAddBoard(HttpServletRequest request, Model model, BoardVO boardVO) {
    System.out.println("AdminAddBoardController 진입");

        boardVO.setBoardTitle(request.getParameter("boardTitle"));	//입력한 제목
        boardVO.setBoardContent(request.getParameter("boardContent"));	//입력한 내용
        boardVO.setBoardLimit(Integer.parseInt((String)request.getParameter("boardLimit")));	//입력한 제한인원

        //만약 추가 성공한다면?
        if(boardService.insert(boardVO)){
            model.addAttribute("msg", "이벤트 추가 성공");
            model.addAttribute("flag", true);
            model.addAttribute("url", "boardPage.do");
        //만약 게시판 추가를 실패한다면?
        }
        else{
            model.addAttribute("msg", "이벤트 추가 실패");
            model.addAttribute("flag", false)
        }
        return "/Metronic-Shop-UI-master/theme/Alert";
    }
}

package com.example.common.view.board;

import com.example.common.biz.board.BoardService;
import com.example.common.biz.board.BoardVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BoardPageRestController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/boardPageData.do")
    public Map<String, Object> getBoardPageData(HttpSession session, BoardVO boardVO) {
        Map<String, Object> data = new HashMap<>();
        // HttpSession session = request.getSession();
        String Email = (String) session.getAttribute("userEmail");
        boardVO.setSearchKeyword(Email);

        List<BoardVO> datas = boardService.getBoardList(boardVO);    //이벤트 리스트 조회
        System.out.println("board SELECTALL 로그:" + datas);

        data.put("datas", datas);

        return data;
    }
}

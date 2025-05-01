package com.example.common.view.chatting;

import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChattingMoveController {
    @Autowired
    private UserService userService;
    // 채팅 시작 전, 토큰 1개를 차감하고 채팅방으로 이동하는 컨트롤러
    @GetMapping("/deductToken.do")
    public String deductToken(HttpSession session, @RequestParam String targetEmail, UserVO userVO) {
        // 현재 로그인된 사용자의 이메일 가져오기 (세션에서 가져옴)
        String userEmail = (String) session.getAttribute("userEmail");
        System.out.println("세션에서 가져온 사용자 이메일: " + userEmail);
        System.out.println("요청받은 targetEmail: " + targetEmail);

        // 로그인 상태일 경우에만 토큰 차감
        if (userEmail != null) {
            userVO.setUserEmail(userEmail);
            userVO.setCondition("UPDATE_MINUS_TOKEN");  // 토큰 1개 차감 쿼리
            System.out.println("토큰 차감 요청 전: " + userVO);
            // DB에 업데이트 요청
            userService.update(userVO);
            System.out.println("토큰 차감 완료");
        } else {
            System.out.println("세션에 로그인 정보가 없음 - 토큰 차감 생략");
        }

        // 채팅방 페이지로 리다이렉트하면서 targetEmail을 파라미터로 전달
        // 다음 컨트롤러 메서드 (moveToChatRoom)에게 targetEmail 파라미터를 전달
        return "redirect:/chattingRoom.do?targetEmail=" + targetEmail;
        //return "redirect:/Metronic-Shop-UI-master/theme/WebSocket.jsp" + targetEmail;
    }

    // 채팅방으로 이동하는 컨트롤러
    @GetMapping("/chattingRoom.do")
    public String moveToChatRoom(@RequestParam String targetEmail, Model model) {
        System.out.println("targetEmail 상대방 이메일 확인 = " + targetEmail);
        // View에서 사용할 수 있도록 모델에 담기
        model.addAttribute("targetEmail", targetEmail);
        // WebSocket.jsp 뷰로 이동 (JSP 경로)
        return "/Metronic-Shop-UI-master/theme/WebSocket";
    }
}

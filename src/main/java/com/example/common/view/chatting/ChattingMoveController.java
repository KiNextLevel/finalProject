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
    public String deductToken(HttpSession session, @RequestParam String targetEmail, Model model) {
        // 현재 로그인된 사용자의 이메일 가져오기 (세션에서 가져옴)
        String userEmail = (String) session.getAttribute("userEmail");
        System.out.println("세션에서 가져온 사용자 이메일: " + userEmail);
        System.out.println("요청받은 targetEmail: " + targetEmail);

        // 로그인 상태일 경우에만 토큰 차감
        if (userEmail != null) {
            // 먼저 현재 사용자의 토큰 수량을 확인
            UserVO checkUser = new UserVO();
            checkUser.setUserEmail(userEmail);
            checkUser.setCondition("SELECTONE_USERINFO");  // 사용자 정보 조회 쿼리
            UserVO currentUser = userService.getUser(checkUser);

            // 토큰이 0개 이하인 경우 채팅을 허용하지 않음
            if (currentUser == null || currentUser.getUserToken() <= 0) {
                System.out.println("토큰 부족: " + (currentUser != null ? currentUser.getUserToken() : 0));
                model.addAttribute("errorMessage", "채팅을 시작하기 위한 토큰이 부족합니다.");
                return "redirect:/insufficientToken.do";  // 토큰 부족 페이지로 리다이렉트
            }

            // 토큰이 충분하면 차감 진행
            UserVO userVO = new UserVO();
            userVO.setUserEmail(userEmail);
            userVO.setCondition("UPDATE_MINUS_TOKEN");  // 토큰 1개 차감 쿼리
            System.out.println("[로그] 토큰 차감 요청 전: " + userVO);
            // DB에 업데이트 요청
            boolean result = userService.update(userVO);

            if (!result) {
                // 업데이트 실패 (토큰 부족 등의 이유)
                System.out.println("토큰 차감 실패");
                model.addAttribute("errorMessage", "토큰 차감에 실패했습니다.");
                return "redirect:/insufficientToken.do";
            }

            System.out.println("토큰 차감 완료");
        } else {
            System.out.println("세션에 로그인 정보가 없음 - 토큰 차감 생략");
            return "redirect:/login.do";  // 로그인 페이지로 리다이렉트
        }

        // 채팅방 페이지로 리다이렉트하면서 targetEmail을 파라미터로 전달
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

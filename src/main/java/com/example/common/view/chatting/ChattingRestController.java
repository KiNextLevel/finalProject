package com.example.common.view.chatting;
// 보유 토큰 확인하고 차감하는 컨트롤러
import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
// 보유 토큰 확인 후, 채팅 가능 여부를 JSON 형태로 응답하는 컨트롤러
@RestController
public class ChattingRestController {
    @Autowired
    UserService userService;

    // 토큰 확인을 요청했을 때 실행되는 메서드
    @PostMapping("/checkToken.do")
    public Map<String, Object> checkToken(HttpSession session, UserVO userVO) {
        // 결과 데이터를 담을 Map 생성
        Map<String, Object> result = new HashMap<>();
        String userEmail = (String) session.getAttribute("userEmail");
        System.out.println("세션에서 사용자 이메일 조회: " + userEmail);

        // 로그인 정보 없으면 실패 처리
        if (userEmail == null) {
            result.put("status", "fail");
            result.put("message", "로그인이 필요합니다.");
            return result;
        }

        userVO.setUserEmail(userEmail);
        userVO.setCondition("SELECTONE_USERINFO");
        userVO = userService.getUser(userVO);
        System.out.println("사용자 정보 조회 요청: " + userVO);

        String userNickname = userVO.getUserNickname();
        System.out.println("사용자 닉네임: " + userNickname);

        // 보유 토큰이 1개 이상이면 채팅 가능 (성공)
        if (userVO.getUserToken() >= 0) {
            System.out.println("유저 잔여 토큰 확인: " + userVO.getUserToken() + "개 보유");
            result.put("status", "success");
            // 닉네임 반환
            result.put("userNickname", userNickname);
            session.setAttribute("userNickname", userNickname);
        } else {
            System.out.println("토큰 없음: 채팅 불가");
            result.put("status", "fail");
            result.put("message", "보유한 토큰이 없습니다.");
        }
        return result;
    }
}

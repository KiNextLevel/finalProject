package com.example.view.userAccount;

import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {
    @Autowired
    private UserService userService;

    // 회원가입 페이지로 이동하는 GET 요청 처리
    @GetMapping("/join.do")
    public String joinPage() {
        System.out.println("LOG : JOIN CONTROLLER - JOIN PAGE METHOD");
        return "Metronic-Shop-UI-master/theme/JoinPage";  // .jsp는 suffix로 자동 추가
    }

    // 회원가입 처리하는 POST 요청 처리
    @PostMapping("/join.do")
    public String join(UserVO userVO, HttpSession session, Model model) {
        System.out.println("LOG : JOIN CONTROLLER - JOIN METHOD");

        System.out.println("JOIN CONTROLLER ID[" + userVO.getUserEmail() + "]");
        System.out.println("JOIN CONTROLLER PW[" + userVO.getUserPassword() + "]");
        System.out.println("JOIN CONTROLLER NAME[" + userVO.getUserName() + "]");

        // 이메일 중복 확인을 위한 조건 설정
        userVO.setCondition("SELECTONE_NONSOCIAL");

        // 이메일 중복 확인
        UserVO existingUser = userService.getUser(userVO);

        // 중복 확인 결과 로깅
        System.out.println("CHECK EMAIL: " + (existingUser == null ? "no" : "yes"));

        // PATH 설정
        String path ="redirect:joinNext.do";
        // 중복이 없으면 회원가입 진행
        if (existingUser == null) {
            // 세션에 사용자 정보 저장
            session.setAttribute("userEmail", userVO.getUserEmail());
            session.setAttribute("userPassword", userVO.getUserPassword());
            session.setAttribute("userName", userVO.getUserName());
            session.setAttribute("socialType", userVO.getSocialType());
            session.setAttribute("condition", "join");

            model.addAttribute("userEmail", userVO.getUserEmail());
            model.addAttribute("userPassword", userVO.getUserPassword());
            model.addAttribute("userName", userVO.getUserName());
            model.addAttribute("socialType", userVO.getSocialType());
            model.addAttribute("msg", "회원가입 진행");
            model.addAttribute("flag", true);
            model.addAttribute("url", "/Metronic-Shop-UI-master/theme/JoinPage.jsp");

            return path; // 다음 회원가입 단계로 리다이렉트
        } else {
            // 중복된 이메일이 있는 경우
            model.addAttribute("msg", "이미 등록된 이메일입니다. 회원가입 실패!");
            model.addAttribute("flag", false);

            return "Metronic-Shop-UI-master/theme/Alert";  // 알림 페이지로 이동
        }
    }
}

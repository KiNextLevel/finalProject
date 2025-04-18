package com.example.view.userAccount;

import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;
import com.example.logic.SendEmail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeActivateController {
    @Autowired
    private UserService userService;

    // 회원탈퇴 페이지 이동
    @GetMapping("/accountDelete.do")
    public String deleteUserPage() {
        System.out.println("LOG : DELETE USER CONTROLLER - DELETE USER PAGE METHOD");
        return "/Metronic-Shop-UI-master/theme/AccountDelete";
    }

    // 회원 탈퇴처리
    @PostMapping("/deleteUser.do")
    public String deActivate(HttpServletRequest request, HttpSession session, Model model, UserVO userVO) {
        System.out.println("LOG: DEACTIVATE CONTROLLER - DEACTIVATE METHOD");

        // 세션으로 이메일 갖고오기
        String userEmail = (String) session.getAttribute("userEmail");

        // 사용자 이메일이 없으면 로그인 페이지로 리다이렉트
        if (userEmail == null || userEmail.isEmpty()) {
            model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
            model.addAttribute("flag", false);
            model.addAttribute("url", "/Metronic-Shop-UI-master/theme/LoginPage.jsp");
            return "Metronic-Shop-UI-master/theme/Alert";
        }

        // 사용자 정보 설정
        userVO.setUserEmail(userEmail);
        userVO.setUserRole(3); // 회원 탈퇴 상태를 나타내는 ROLE 3으로 설정
        userVO.setCondition("UPDATE_ROLE"); // UPDATE_ROLE 쿼리 사용 설정

        System.out.println("DELETE USER 로그: 탈퇴할 사용자 이메일 [" + userVO.getUserEmail() + "]");
        System.out.println("DELETE USER 로그: 변경할 ROLE [" + userVO.getUserRole() + "]");

        // 회원 상태 변경 (ROLE을 3으로 변경)
        if (userService.update(userVO)) {
            SendEmail.sendMail(userEmail,"[Next Love]회원 탈퇴 안내", "그동안 이용해주셔서 감사합니다");
            // 탈퇴 성공 시 세션 무효화
            session.invalidate();

            model.addAttribute("msg", "회원 탈퇴가 완료되었습니다. 그동안 서비스를 이용해주셔서 감사합니다.");
            model.addAttribute("flag", true);
            model.addAttribute("url", "indexPage.do");
        } else {
            // 탈퇴 실패
            model.addAttribute("msg", "회원 탈퇴 처리 중 오류가 발생했습니다. 다시 시도해주세요.");
            model.addAttribute("flag", false);
        }
        return "Metronic-Shop-UI-master/theme/Alert";
    }
}

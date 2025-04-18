package com.example.view.auth;

import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    // 로그인페이지로 이동. 파라미터에 userEmail 담아서 이동
    @GetMapping("/loginPage.do")
    public String loginPage(@RequestParam(value = "userEmail", required = false) String userEmail, Model model) {
        System.out.println("LOG : LOGIN CONTROLLER - LOGIN PAGE METHOD");

        if (userEmail != null) {
            model.addAttribute("userEmail", userEmail);
        }

        return "/Metronic-Shop-UI-master/theme/LoginPage";
    }

    // 로그인 처리하는 POST 요청 처리
    @PostMapping("/login.do")
    public String login(HttpServletRequest request, UserVO userVO, Model model, HttpSession session) {
        System.out.println("LOG : LOGIN CONTROLLER - LOGIN METHOD");

        userVO.setUserEmail(request.getParameter("userEmail"));
        userVO.setUserPassword(request.getParameter("userPassword"));

        // 컨디션"로그인"
        userVO.setCondition("SELECTONE_NONSOCIAL");
        if (userService.getUser(userVO) != null) {
            // url, flag, msg 요청단위 저장
            // alert.jsp에 url, true, msg 보내기
            if (userVO.getUserRole() == 0) { //유저
                model.addAttribute("msg", "로그인 성공!");
                model.addAttribute("url", "mainPage.do");
                model.addAttribute("flag", true);
                setSession(userVO, request); // 세션에 정보 저장
            } else if (userVO.getUserRole() == 1) { // 관리자
                model.addAttribute("msg", "관리자 로그인 성공!");
                model.addAttribute("url", "adminPage.do");
                model.addAttribute("flag", true);
                setSession(userVO, request); // 세션에 정보 저장
            } else if (userVO.getUserRole() == 2) { // 블랙
                model.addAttribute("msg", "블랙당한 계정입니다");
                model.addAttribute("flag", false);
            } else if (userVO.getUserRole() == 3) { // 탈퇴
                model.addAttribute("msg", "탈퇴한 계정입니다");
                model.addAttribute("flag", false);
            }
        } else {
            // url, flag, msg 요청단위 저장
            // alert.jsp에 url, false, msg 보내기
            model.addAttribute("msg", "로그인정보가 틀렸습니다");
            model.addAttribute("flag", false);
        }
        return "Metronic-Shop-UI-master/theme/Alert";
    }

    // 세션 저장 모듈화
    public void setSession(UserVO userVO, HttpServletRequest request) {
        HttpSession session = request.getSession();

        // session에 userId, userName, role저장
        session.setAttribute("userEmail", userVO.getUserEmail());
        session.setAttribute("userRole", userVO.getUserRole());
        session.setAttribute("userPremium", userVO.isUserPremium());
        System.out.println("userRole = "+userVO.getUserRole());

        // 컨디션 "위치 정보 가져옴"
        userVO.setCondition("SELECTONE_LOCATION");
        UserVO locationInfo = userService.getUser(userVO);

        session.setAttribute("userLatitude", locationInfo.getUserLatitude());
        session.setAttribute("userLongitude", locationInfo.getUserLongitude());

        // 모델에 사용자 이메일 저장
        request.setAttribute("userEmail", userVO.getUserEmail());
    }
}

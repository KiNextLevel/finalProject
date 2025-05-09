package com.example.common.view.auth;

import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
import com.example.common.view.logic.CheckVisit;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    @Autowired
    private CheckVisit checkVisit;

    // 로그인 페이지로 이동
    @GetMapping("/loginPage.do")
    public String loginPage(@RequestParam(value = "username", required = false) String userEmail,
                            Model model,
                            HttpServletResponse response) {
        System.out.println("LOG : LOGIN CONTROLLER - LOGIN PAGE METHOD");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        if (userEmail != null) {
            model.addAttribute("userEmail", userEmail);
        }

        return "Metronic-Shop-UI-master/theme/LoginPage";
    }

    // 세션 저장 메서드 (Spring Security에서 호출)
    public void setSession(UserVO userVO, HttpServletRequest request) {
        HttpSession session = request.getSession();

        session.setAttribute("userEmail", userVO.getUserEmail());
        session.setAttribute("userRole", userVO.getUserRole());
        session.setAttribute("userPremium", userVO.getUserPremium());
        System.out.println("userRole = " + userVO.getUserRole());

        userVO.setCondition("SELECTONE_LOCATION");
        UserVO locationInfo = userService.getUser(userVO);

        session.setAttribute("userLatitude", locationInfo.getUserLatitude());
        session.setAttribute("userLongitude", locationInfo.getUserLongitude());

        request.setAttribute("userEmail", userVO.getUserEmail());
    }
}

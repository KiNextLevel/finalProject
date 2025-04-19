package com.example.view.adminUserModeration;
//관리자가 블랙 해제하는 기능(유저롤 수정)
import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class AdminDeleteBlackController {
    @Autowired
    private UserService userService;

    public String adminDeleteBlack(HttpServletRequest request, Model model, UserVO userVO) {
        System.out.println("AdminDeleteBlackController 진입");
        String blackEmail = request.getParameter("blackEmail"); //블랙 당한 사용자 이메일
        userVO.setUserEmail(blackEmail);
        userVO.setUserName("UPDATE_ROLE");
        userVO.setUserRole(0);   //userRole을 일반 사용자로 바꿈
        if(UserService.update(userVO)) {
            model.addAttribute("msg", "사용자를 블랙 해제했습니다");
            model.addAttribute("flag", true);
            model.addAttribute("url", "adminReportPage.do");
        }
    else {
            model.addAttribute("msg", "블랙 해제 실패");
            model.addAttribute("flag", false);
        }

        return "/Metronic-Shop-UI-master/theme/Alert";

    }
}

package com.example.common.view.userInteraction;

import com.example.common.biz.preference.PreferenceService;
import com.example.common.biz.preference.PreferenceVO;
import com.example.common.biz.report.ReportService;
import com.example.common.biz.report.ReportVO;
import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserInteractionController {
    // 신고페이지 이동 액션
    @GetMapping("/reportPage.do")
    public String reportPage() {
        System.out.println("CONT 로그: REPORTPAGE ACTION 도착");
        return "/Metronic-Shop-UI-master/theme/Report";
    }

    // 신고하기 결과 페이지
    @GetMapping("/report.do")
    public String report() {
        System.out.println("CONT 로그: REPORT ACTION 도착");
        return "/Metronic-Shop-UI-master/theme/Alert";
    }

    // 다른 사용자 프로필 이동 액션
    @GetMapping("/userDetailPage.do")
    public String userDetailPage() {
        System.out.println("CONT 로그: USERDETAILPAGE ACTION 도착");
        return "/Metronic-Shop-UI-master/theme/UserDetail";
    }
}

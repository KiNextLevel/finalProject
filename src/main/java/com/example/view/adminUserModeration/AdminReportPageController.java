package com.example.view.adminUserModeration;
// 관리자 신고 페이지 이동 액션
import com.example.biz.report.ReportService;
import com.example.biz.report.ReportVO;
import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

// 관리자 신고 페이지 이동 액션
@Controller

public class AdminReportPageController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReportService reportService;

    public String adminReportPage (HttpSession session, Model model, UserVO userVO, ReportVO reportVO) {
        System.out.println("AdminReportPageController 진입");
        List<ReportVO> reported = reportService.selectAll(reportVO);  //신고 회원 전체
        System.out.println("AdminReportPageAction 로그: "+reported);  // 로그
        System.out.println("ReportPageAction log reported: "+reported);  //로그

        userVO.setCondition("SELECTALL_BLACK");
        List<UserVO> blacks = userService.selectAll(userVO); // 블랙리스트 전체
        System.out.println("ReportPageAction log blacks: "+blacks);

        if(((Integer)session.getAttribute("userRole"))==1) {  //관리자만 이동 가능
            model.addAttribute("blacks", blacks);
            model.addAttribute("reported", reported);
            return "/Metronic-Shop-UI-master/theme/Alert";
        }
        else {
            model.addAttribute("msg", "관리자만 이동 가능합니다");
            model.addAttribute("flag", false);
        }
            return "/Metronic-Shop-UI-master/theme/Alert";
    }

}

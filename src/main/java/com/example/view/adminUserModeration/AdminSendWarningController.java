package com.example.view.adminUserModeration;


import com.example.biz.alert.AlertService;
import com.example.biz.alert.AlertVO;
import com.example.biz.report.ReportService;
import com.example.biz.report.ReportVO;
import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

//관리자가 경고 보내는 액션
@Controller
public class AdminSendWarningController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private AlertService alertService;

    public String adminSendWarning(HttpServletRequest request, Model model, ReportVO reportVO, AlertVO alertVO) {
        String reportedUser = request.getParameter("reportedUser");	//피신고자 이메일
        String reason = request.getParameter("reason");	//신고 사유
        alertVO.setUserEmail(reportedUser);
        alertVO.setAlertContent(reason);
        System.out.println("reportNum: ["+request.getParameter("reportNum")+"]"); //해당 신고 번호
        reportVO.setReportNumber(Integer.parseInt(request.getParameter("reportNum")));
        reportVO.setCondition("DELETE_ONE");
        System.out.println("reportedUser: "+reportedUser);
        System.out.println("reason"+reason);

        //알림 추가하고 신고리스트에서 삭제
        if(alertService.insert(alertVO)&&reportService.delete(reportVO)){
            model.addAttribute("msg", "경고 보내기 완료");
            model.addAttribute("flag", true);
            model.addAttribute("url", "adminReportPage.do");
        }
        else{
            model.addAttribute("msg", "경고 보내기 실패");
            model.addAttribute("flag", false);
        }
        return "/Metronic-Shop-UI-master/theme/Alert";


    }
}

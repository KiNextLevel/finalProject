package com.example.common.view.adminUserModeration;


import com.example.common.biz.alert.AlertService;
import com.example.common.biz.alert.AlertVO;
import com.example.common.biz.report.ReportService;
import com.example.common.biz.report.ReportVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

//관리자가 경고 보내는 액션
@Controller
public class AdminSendWarningController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private AlertService alertService;

    @PostMapping("/adminSendWarning.do")
    public String adminSendWarning(Model model, ReportVO reportVO, AlertVO alertVO) {
        alertVO.setUserEmail(reportVO.getReportReported());//피신고자 이메일
        alertVO.setAlertContent(reportVO.getReportReason());//신고 사유
        reportVO.setReportNumber(reportVO.getReportNumber());
        reportVO.setCondition("DELETE_ONE");
        System.out.println("==================");
        System.out.println(alertVO);
        System.out.println(reportVO);
        System.out.println("==================");
        //알림 추가하고 신고리스트에서 삭제
        if (alertService.insert(alertVO) && reportService.delete(reportVO)) {
            model.addAttribute("msg", "경고 보내기 완료");
            model.addAttribute("flag", true);
            model.addAttribute("url", "/adminReportPage.do");
        } else {
            model.addAttribute("msg", "경고 보내기 실패");
            model.addAttribute("flag", false);
        }
        return "/Metronic-Shop-UI-master/theme/Alert";
    }
}

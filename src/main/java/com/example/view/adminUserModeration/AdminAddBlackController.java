package com.example.view.adminUserModeration;

import com.example.biz.report.ReportService;
import com.example.biz.report.ReportVO;
import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//관리자가 블랙 하는 기능( 유저롤 수정)
@Controller
@RequestMapping("/admin")  //admin/...으로 시작하는 요청은 여기서 처리
public class AdminAddBlackController {
    //객체 주입 받음
    @Autowired
    private UserService userService;
    @Autowired
    private ReportService reportService;

    // POST 방식으로 "/admin/addBlack.do" 요청이 들어오면 이 메서드가 실행됨
    @PostMapping("/addBlack.do")
    // 요청에서 넘어온 데이터를 UserVO와 ReportVO 객체에 자동으로 바인딩하고, 화면에 넘길 데이터를 위해 Model 사용
    public String adminAddBlack(UserVO userVO, ReportVO reportVO, Model model){
    System.out.println("AddBlackController 진입");
        //reportVO.setReportReported(reportReported); //신고된 유저 이메일

        reportVO.setCondition("DELETE"); //블랙 처리 되면 신고 리스트에서 삭제

        // 사용자가 null이면, 즉 아무 정보도 없으면 오류 메시지를 Alert.jsp에 전달
        if(userVO == null){
            // 로그 찍기
            System.out.println("UserVO is null(유저 없음");
            model.addAttribute("msg", "사용자를 찾을 수 없습니다");
            model.addAttribute("flag", false);
            return "/Metronic-Shop-UI-master/theme/Alert"; //// ViewResolver가 alert.jsp로 이동시킴
            //return "alert";
        }
        // 만약 이미 블랙리스트 상태인 사용자라면 처리 중단
        if(userVO.getUserRole()==2){
            // 로그 찍기
            System.out.println("이미 유저는 블랙처리 되었습니다");
            model.addAttribute("msg", "이미 블랙 처리 된 사용자입니다");
            model.addAttribute("flag", false);
            return "/Metronic-Shop-UI-master/theme/Alert";
            //return "alert";
        }
        //유저 롤 업데이트 하기
        userVO.setCondition("UPDATE_ROLE");
        //블랙으로 설정
        userVO.setUserRole(2);
        // 유저 권한을 DB에서 업데이트, 신고 기록 삭제까지 성공하면
        if(userService.update(userVO)&& reportService.delete(reportVO)){
            model.addAttribute("msg", "사용자를 블랙 처리 했습니다");
            model.addAttribute("flag", true);
            model.addAttribute("url", "adminReportPage.do");
            return "/Metronic-Shop-UI-master/theme/Alert";
        // 실패 시 처리
        }
        else{
           model.addAttribute("msg", "사용자 블랙 실패");
           model.addAttribute("flag", false);
        }
        // 결과를 보여줄 alert.jsp로 포워드
        return "/Metronic-Shop-UI-master/theme/Alert";
        //return "alert";
    }

}

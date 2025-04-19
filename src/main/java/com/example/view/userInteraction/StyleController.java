package com.example.view.userInteraction;

import com.example.biz.preference.PreferenceService;
import com.example.biz.preference.PreferenceVO;
import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;
import com.example.biz.user.impl.UserDAO;
import jakarta.servlet.http.HttpSession;
import org.hibernate.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StyleController {
    @Autowired
    private UserService userService;
    @Autowired
    private PreferenceService preferenceService;

    @GetMapping("report.do")
    public String report(Model model, HttpSession session) {
        System.out.println("CONT 로그: REPORT ACTION 도착");

        // 디버깅: 모든 파라미터 출력
//        System.out.println("모든 파라미터 목록:");
//        java.util.Enumeration<String> paramNames = request.getParameterNames();
//        while(paramNames.hasMoreElements()) {
//            String paramName = paramNames.nextElement();
//            System.out.println(paramName + ": " + request.getParameter(paramName));
//        }

        // getParameter()로 폼 데이터 가져오기
        String reportedUserEmail = request.getParameter("userEmail");

        // 사용자 닉네임 조회
        UserDTO userDTO = new UserDTO();
        UserDAO userDAO = new UserDAO();
        userDTO.setUserEmail(reportedUserEmail);
        userDTO.setCondition("SELECTONE_USERINFO");
        UserDTO userData = userDAO.selectOne(userDTO);

        String userNickname = "알 수 없음";
        if (userData != null && userData.getUserNickname() != null) {
            userNickname = userData.getUserNickname();
        }

        // 닉네임을 request에 저장
        request.setAttribute("reportedUserNickname", userNickname);

        // 체크박스 다중 선택 처리
        String[] reasons = request.getParameterValues("reason");
        String combinedReasons = "";

        if (reasons != null) {
            for (int i = 0; i < reasons.length; i++) {
                combinedReasons += reasons[i];
                if (i < reasons.length - 1) {
                    combinedReasons += ", ";
                }
            }
        }

        String description = request.getParameter("description");

        ReportDTO reportDTO = new ReportDTO();
        ReportDAO reportDAO = new ReportDAO();

        // 세션에서 현재 로그인한 사용자 이메일 가져오기
        String reporterEmail = (String) session.getAttribute("userEmail");

        // 필요한 정보 설정
        reportDTO.setReportReported(reportedUserEmail); // 피신고자
        reportDTO.setReportReporter(reporterEmail);     // 신고자
        reportDTO.setReportReason(combinedReasons);     // 신고 이유
        reportDTO.setReportDescription(description);    // 신고 상세 설명

        if (reportDAO.selectOne(reportDTO) != null) { // 신고자가 이미 피 신고자를 신고한 적이 있으며, 처리 대기중이다
            //신고 못하게 함
            request.setAttribute("msg", "해당 유저는 이미 신고하셨습니다.");
            request.setAttribute("flag", false);
            request.setAttribute("url", "mainPage.do");

        } else if (reportDAO.insert(reportDTO)) {// 신고 데이터 삽입 시도
            // 신고 성공
            request.setAttribute("msg", "신고가 완료되었습니다. 직원이 검토 후 처리됩니다.");
            request.setAttribute("flag", true);
            request.setAttribute("url", "mainPage.do");
        } else {
            // 신고 실패
            request.setAttribute("msg", "신고 처리 중 오류가 발생했습니다. 다시 시도해주세요.");
            request.setAttribute("flag", false);
            request.setAttribute("url", "mainPage.do");

            // 실패 원인 디버깅
            System.out.println("신고 실패 원인:");
            System.out.println("reportedUserEmail: " + reportedUserEmail);
            System.out.println("reporterEmail: " + reporterEmail);
            System.out.println("combinedReasons: " + combinedReasons);
            System.out.println("description: " + description);
        }

        forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
        forward.setRedirect(false);
        return forward;
    }
    @GetMapping("/userDetailPage.do")
    public String userDetailPage(UserVO userVO, PreferenceVO preferenceVO, Model model) {
        System.out.println("CONT 로그: USERDETAILPAGE ACTION 도착");
        //이메일 전달받음

        userVO.setCondition("SELECTONE_USERINFO");
        userVO = userService.getUser(userVO);

        preferenceVO.setCondition("SELECTONE");

        //System.out.println("CONT 로그: 선호 정보 조회 시작 - " + request.getParameter("userEmail"));
        preferenceVO = preferenceService.getPreference(preferenceVO);
        System.out.println("CONT 로그: 선호 정보 조회 결과 - " + preferenceVO);

        if (preferenceVO == null) {
            preferenceVO = new PreferenceVO(); // 기본 객체 생성
            System.out.println("CONT 로그: preferenceDTO가 null이어서 새 객체 생성");
        }

        model.addAttribute("preferenceVO", preferenceVO);
        if (userVO == null) {
            model.addAttribute("msg", "존재하지 않는 회원입니다");
            model.addAttribute("flag", false);
            return "redirect:/Metronic-Shop-UI-master/theme/Alert";
        } else {
            System.out.println(userVO);
            model.addAttribute("userVO", userVO);
            model.addAttribute("preferenceVO", preferenceVO);
            return "/Metronic-Shop-UI-master/theme/UserDetail";
        }
    }
}

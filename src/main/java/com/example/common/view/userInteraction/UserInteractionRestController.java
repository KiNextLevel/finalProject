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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserInteractionRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private PreferenceService preferenceService;
    @Autowired
    private ReportService reportService;

    // 신고 데이터 처리
    @GetMapping("/reportData.do")
    public Map<String, Object> reportData(HttpSession session, HttpServletRequest request, UserVO userVO, ReportVO reportVO) {
        Map<String, Object> result = new HashMap<>();

        // getParameter()로 폼 데이터 가져오기
        String reportedUserEmail = request.getParameter("userEmail");

        // 사용자 닉네임 조회
        userVO.setCondition("SELECTONE_USERINFO");
        UserVO userData = userService.getUser(userVO);

        String userNickname = "알 수 없음";
        if (userData != null && userData.getUserNickname() != null) {
            userNickname = userData.getUserNickname();
        }

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

        // 세션에서 현재 로그인한 사용자 이메일 가져오기
        String reporterEmail = (String) session.getAttribute("userEmail");

        // 필요한 정보 설정
        reportVO.setReportReason(combinedReasons);     // 신고 이유
        reportVO.setReportReporter(reporterEmail);     // 신고자

        if (reportService.getReport(reportVO) != null) {
            result.put("msg", "해당 유저는 이미 신고하셨습니다.");
            result.put("flag", false);
            result.put("url", "/mainPage.do");
        } else if (reportService.insert(reportVO)) {
            result.put("msg", "신고가 완료되었습니다. 직원이 검토 후 처리됩니다.");
            result.put("flag", true);
            result.put("url", "/mainPage.do");
        } else {
            result.put("msg", "신고 처리 중 오류가 발생했습니다. 다시 시도해주세요.");
            result.put("flag", false);
            result.put("url", "/mainPage.do");

            // 실패 원인 디버깅
            System.out.println("신고 실패 원인:");
            System.out.println("reportedUserEmail: " + reportedUserEmail);
            System.out.println("reporterEmail: " + reporterEmail);
            System.out.println("combinedReasons: " + combinedReasons);
            System.out.println("description: " + description);
        }

        return result;
    }

    // 신고 페이지 데이터
    @GetMapping("/reportPageData.do")
    public Map<String, Object> reportPageData(HttpServletRequest request, UserVO userVO) {
        Map<String, Object> result = new HashMap<>();

        try {
            // 요청 인코딩 설정
            request.setCharacterEncoding("UTF-8");

            // userEmail 파라미터 확인
            String userEmail = request.getParameter("userEmail");
            System.out.println("ReportPageAction에서 받은 userEmail: " + userEmail);

            // 사용자 정보 조회
            userVO.setCondition("SELECTONE_USERINFO");

            System.out.println("사용자 정보 조회 시작: " + userEmail);
            UserVO userData = userService.getUser(userVO);
            System.out.println("사용자 정보 조회 결과: " + userData);

            // 닉네임 설정
            String userNickname = "알 수 없음";
            if (userData != null && userData.getUserNickname() != null) {
                userNickname = userData.getUserNickname();
            }

            System.out.println("설정할 닉네임: " + userNickname);

            // 결과 설정
            result.put("reportedUserEmail", userEmail);
            result.put("reportedUserNickname", userNickname);
        } catch (Exception e) {
            System.out.println("ReportPageAction 실행 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            result.put("error", e.getMessage());
        }

        return result;
    }

    // 사용자 상세 정보 데이터
    @GetMapping("/userDetailData.do")
    public Map<String, Object> userDetailData(UserVO userVO, PreferenceVO preferenceVO) {
        Map<String, Object> result = new HashMap<>();

        userVO.setCondition("SELECTONE_USERINFO");
        userVO = userService.getUser(userVO);

        preferenceVO.setCondition("SELECTONE");
        preferenceVO = preferenceService.getPreference(preferenceVO);
        System.out.println("CONT 로그: 선호 정보 조회 결과 - " + preferenceVO);

        if (preferenceVO == null) {
            System.out.println("CONT 로그: preferenceDTO가 null이어서 새 객체 생성");
        }

        if (userVO == null) {
            result.put("msg", "존재하지 않는 회원입니다");
            result.put("flag", false);
        } else {
            System.out.println(userVO);
            result.put("userVO", userVO);
            result.put("preferenceVO", preferenceVO);
        }

        return result;
    }
}

package com.example.view.userAccount;


import com.example.biz.preference.PreferenceService;
import com.example.biz.preference.PreferenceVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserPreferenceController {
    @Autowired
    private PreferenceService preferenceService;

    // 취향 선택 페이지 이동 GET 요청
    @GetMapping("/userPreferencePage.do")
    public String userPreferencePage() {
        System.out.println("LOG : USER PREFERENCE CONTROLLER - USER PREFERENCE PAGE METHOD");
        return "Metronic-Shop-UI-master/theme/UserPreferencePage";  // .jsp는 suffix로 자동 추가
    }

    // 사용자 취향 정보 처리하는 POST 요청 처리
    @PostMapping("/userPreference.do")
    public String userPreference(PreferenceVO preferenceVO, HttpSession session, Model model) {
        System.out.println("LOG : USER PREFERENCE CONTROLLER - USER PREFERENCE METHOD");

        try {
            // 시스템 로그
            System.out.println("userEmail: " + preferenceVO.getUserEmail());
            System.out.println("preferenceHeight: " + preferenceVO.getPreferenceHeight());
            System.out.println("preferenceBody: " + preferenceVO.getPreferenceBody());
            System.out.println("preferenceAge: " + preferenceVO.getPreferenceAge());

            // userEmail이 없으면 세션에서 가져오기
            if (preferenceVO.getUserEmail() == null || preferenceVO.getUserEmail().isEmpty()) {
                preferenceVO.setUserEmail((String) session.getAttribute("userEmail"));
            }

            // 데이터 유효성 검사
            // 모든 필드가 다 입력되어있는지 확인
            if (preferenceVO.getUserEmail() == null ||
                    preferenceVO.getPreferenceBody() == null ||
                    preferenceVO.getPreferenceAge() == null) {

                model.addAttribute("msg", "필수 정보가 누락되었습니다!");
                model.addAttribute("flag", false);
                return "Metronic-Shop-UI-master/theme/Alert";
            }

            // preferenceHeight가 0인 경우 (폼에서 숫자가 아닌 값이 입력되었거나 값이 없는 경우)
            // Spring은 숫자 필드에 숫자가 아닌 값이 들어오면 기본값인 0으로 설정합니다
            if (preferenceVO.getPreferenceHeight() <= 0) {
                model.addAttribute("msg", "키 값은 유효한 숫자여야 합니다!");
                model.addAttribute("flag", false);
                return "Metronic-Shop-UI-master/theme/Alert";
            }

            // 시스템 로그
            System.out.println("preferenceVO[" + preferenceVO + "]");

            // 서비스를 통해 DB에 선호도 정보 저장
            boolean result = preferenceService.insert(preferenceVO);

            // 저장 성공 시
            if (result) {
                // 세션에서 기존에 받았던 기본 정보 제거
                session.invalidate();

                // 선호 입력 성공
                model.addAttribute("preferenceVO", preferenceVO);
                model.addAttribute("msg", "선호 내용 입력 성공!");
                model.addAttribute("flag", true);
                model.addAttribute("url", "loginPage.do");
            }
            // 저장 실패 시
            else {
                // 선호 입력 실패
                model.addAttribute("msg", "선호 내용 입력 실패!");
                model.addAttribute("flag", false);
            }

            return "Metronic-Shop-UI-master/theme/Alert";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "선호 입력 중 오류가 발생했습니다!");
            model.addAttribute("flag", false);
            return "Metronic-Shop-UI-master/theme/Alert";
        }
    }

    @GetMapping("/userPreferencePage.do")
    public String userPreferencePage(HttpSession session, Model model) {
        System.out.println("LOG : USER PREFERENCE CONTROLLER - USER PREFERENCE PAGE METHOD");

        // 세션에서 사용자 이메일 가져오기
        String userEmail = (String) session.getAttribute("userEmail");

        // Model에 사용자 이메일 저장
        model.addAttribute("userEmail", userEmail);

        return "Metronic-Shop-UI-master/theme/UserPreference";
    }

}
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserDetailRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private PreferenceService preferenceService;

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

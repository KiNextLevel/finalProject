package com.example.common.view.myPage;
// 정보 수정하는 마이페이지
import com.example.common.GeoCodingUtil;
import com.example.common.biz.preference.PreferenceService;
import com.example.common.biz.preference.PreferenceVO;
import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyPageEditController {
    @Autowired
    private PreferenceService preferenceService;
    @Autowired
    private UserService userService;

    @PostMapping("/updateProfile.do")
    public String updateProfile(HttpSession session,
                                UserVO userVO,
                                PreferenceVO preferenceVO) {
        String userEmail = (String) session.getAttribute("userEmail");

        if (userEmail == null) {
            return "redirect:/loginPage.do"; // 로그인 필요
        }

        userVO.setUserEmail(userEmail);
        userVO.setCondition("UPDATE");

        preferenceVO.setUserEmail(userEmail);
        preferenceVO.setCondition("UPDATE");

        // 주소 → 위도경도 변환
        try {
            double[] coords = GeoCodingUtil.getCoordinatesFromAddress(userVO.getUserRegion());
            double lat = Math.round(coords[0] * 10000) / 10000.0;
            double lng = Math.round(coords[1] * 10000) / 10000.0;
            userVO.setUserLatitude(lat);
            userVO.setUserLongitude(lng);
        } catch (Exception e) {
            userVO.setUserLatitude(0.0);
            userVO.setUserLongitude(0.0);
        }

        userService.update(userVO);

        PreferenceVO checkPreference = preferenceService.getPreference(preferenceVO);
        if (checkPreference == null) {
            preferenceService.insert(preferenceVO);
        } else {
            preferenceService.update(preferenceVO);
        }

        return "redirect:/myPage.do"; // 수정 후 다시 마이페이지로
    }


}

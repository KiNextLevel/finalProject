package com.example.common.view.mainPage;

import com.example.common.biz.alert.AlertService;
import com.example.common.biz.alert.AlertVO;
import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainPageRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private AlertService alertService;

    @GetMapping("/api/mainPageData")
    public Map<String, Object> mainPage(HttpSession session, AlertVO alertVO, UserVO userVO , Model model) {
        System.out.println("CONT 로그: MAINPAGEDATA ACTION 도착");

        Map<String, Object> result = new HashMap<>();

        String userEmail = (String) session.getAttribute("userEmail");

        // 알림 목록 mainpage로
        alertVO.setUserEmail(userEmail);
        List<AlertVO> alertDatas = alertService.getAlertList(alertVO);

        if (alertDatas == null) {
            alertDatas = new ArrayList<>(); // 빈 리스트로 초기화
        }
        // 디버깅용 출력
        for (AlertVO alertVO1 : alertDatas) {
            System.out.println(alertVO1);
        }

        userVO.setUserEmail(userEmail);
        userVO.setCondition("SELECTALL");
        List<UserVO> userDatas = userService.getUserList(userVO);

        result.put("userDatas", userDatas);
        result.put("alertDatas", alertDatas);

        return result;
    }
}
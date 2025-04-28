package com.example.common.view.userInteraction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserDetailController {
    // 다른 사용자 프로필 이동 액션
    @GetMapping("/userDetailPage.do")
    public String userDetailPage() {
        System.out.println("CONT 로그: USERDETAILPAGE ACTION 도착");
        return "/Metronic-Shop-UI-master/theme/UserDetail";
    }
}

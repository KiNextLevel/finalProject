package com.example.common.view.chatting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TokenShopPagingController {
    @GetMapping("/insufficientToken.do")
    public String insufficientToken(Model model) {
        return "/Metronic-Shop-UI-master/theme/insufficientToken";  // 토큰 부족 안내 페이지
    }

}

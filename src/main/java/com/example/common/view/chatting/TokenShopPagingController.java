package com.example.common.view.chatting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// 만약, 토큰이 부족하면 토큰 상품 쇼핑으로 이동하기
@Controller
public class TokenShopPagingController {
    @GetMapping("/insufficientToken.do")
    public String insufficientToken(Model model) {
        return "/Metronic-Shop-UI-master/theme/InsufficientToken";  // 토큰 부족 안내 페이지
    }

}

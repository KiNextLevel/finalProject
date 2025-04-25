package com.example.common.config.websocket;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebSocketController {
    @GetMapping("/websocket.do")
    public String websocket(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        System.out.println("웹소켓 테스트 컨트롤러...................................");

        return "/Metronic-Shop-UI-master/theme/WebSocket";
    }
}

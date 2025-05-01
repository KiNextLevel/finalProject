package com.example.common.config.websocket;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebSocketController {
    @GetMapping("/websocket.do")
    public String websocket(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");

        log.info("session test: {}", userEmail);
        model.addAttribute("currentUser", userEmail);
        return "/Metronic-Shop-UI-master/theme/WebSocket";
    }
}

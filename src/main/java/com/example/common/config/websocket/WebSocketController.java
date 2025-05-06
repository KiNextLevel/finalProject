//package com.example.common.config.websocket;
//
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Slf4j
//@Controller
//@RequiredArgsConstructor
//public class WebSocketController {
//    @GetMapping("/websocket.do")
//    public String websocket(HttpServletRequest request, HttpServletResponse response, Model model) {
//        HttpSession session = request.getSession();
//        // 지금 세션에서 내 정보를 가져오고 있음
//        // 하지만 내가 보이는 상대방은 세션에서 가져오면 안되는데..?
//        // 이럴 경우 어떻게 해야할까?
          // ++당연히 가져와질 수가 없었네.. 세션에 닉네임을 저장안했으니까
//        String userEmail = (String) session.getAttribute("userEmail");
//        String userNickname = (String) session.getAttribute("userNickname");
//
//        log.info("session test: {}", userEmail);
//        model.addAttribute("currentUser", userEmail);
//        model.addAttribute("currentUserNickname", userNickname);
//        return "/Metronic-Shop-UI-master/theme/WebSocket";
//    }
//}
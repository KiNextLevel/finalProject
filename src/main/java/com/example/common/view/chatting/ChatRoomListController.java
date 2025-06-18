//package com.example.common.view.chatting;
//
//import com.example.common.biz.chatRoom.ChatRoomService;
//import com.example.common.biz.chatRoom.ChatRoomVO;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//// "내가 참여 중인 채팅방 목록"을 화면에 보여주는  컨트롤러
//// 즉, 채팅 메인 리스트 페이지를 담당
//
//@Controller
//public class ChatRoomListController {
//
//    @Autowired
//    private ChatRoomService chatRoomService;
//    // 사용자가 채팅 목록 페이지에 접근할 때 실행되는 메서드
//    @GetMapping("/chatRoomList.do")
//    public String showChatRoomList(HttpSession session, Model model, ChatRoomVO chatRoomVO) {
//        //1. 세션에서 로그인한 사용자의 이메일 꺼내기
//        String userEmail = (String) session.getAttribute("userEmail");
//
//        // 만약 유저 로그인 상태가 아니라면? 로그인 페이지 이동하기
//        if (userEmail == null) {
//            return "redirect:/login.do";
//        }
//
//        // 2. 내 이메일을 ChatRoomVO에 담기 (내가 주인공인 채팅방만 찾기 위해)
//        chatRoomVO.setUserEmail(userEmail);  // 로그인 사용자 이메일 저장
//
//        // 3. DB에서 해당 사용자가 참여 중인 채팅방 목록 가져오기
//        List<ChatRoomVO> roomList = chatRoomService.getChatRoomList(chatRoomVO);
//        //  4. 가져온 채팅방 목록을 화면(MyMessageList.jsp)으로 전달
//        model.addAttribute("roomList", roomList);
//        System.out.println("채팅방 정보 : "+roomList);
//
//
//        return "/Metronic-Shop-UI-master/theme/MyMessageList";
//    }
//}

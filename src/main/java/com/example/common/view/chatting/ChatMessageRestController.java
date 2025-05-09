//package com.example.common.view.chatting;
//// 자신의 과거 채팅 리스트 불러오기
//// 과거 메시지를 JSON으로 주는 API
//import com.example.common.biz.chatMessage.ChatMessageService;
//import com.example.common.biz.chatMessage.ChatMessageVO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//@RestController
//public class ChatMessageRestController {
//
//    @Autowired
//    private ChatMessageService chatMessageService;
//
//    // 과거 메시지 불러오기 (chatRoomId로)
//    @GetMapping("/chatHistory.do")
//    public List<ChatMessageVO> getChatHistory(@RequestParam Long chatRoomId,ChatMessageVO chatMessagevo) {
//        chatMessagevo.setChatRoomId(chatRoomId);
//        System.out.println("채팅메시지에서 채팅방 번호 가져오기 로그 : "+chatMessagevo.getChatRoomId());
//
//        return chatMessageService.getChatMessageList(chatMessagevo);
//
//    }
//
//}
//

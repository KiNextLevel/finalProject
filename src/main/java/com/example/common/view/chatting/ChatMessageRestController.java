package com.example.common.view.chatting;
// 자신의 과거 채팅 리스트 불러오기
// 과거 메시지를 JSON으로 주는 API

import com.example.common.biz.chatMessage2.ChatMessageService;
import com.example.common.biz.chatMessage2.ChatMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatMessageRestController {

    @Autowired
    private ChatMessageService chatMessageService;

    // 과거 메시지 불러오기 (chatRoomId로)
    @GetMapping("/chat/messages")
    public List<ChatMessageVO> getChatHistory(@RequestParam int chatRoomId, ChatMessageVO chatMessagevo) {
        System.out.println(" 과거 채팅 내역 불러오는 컨트롤러 진입 : ChatMessageRestController ");
        System.out.println(" 과거 메시지 불러오기: chatRoomId(채팅방 아이디 필요) = " + chatRoomId);
        //chatMessagevo.setChatRoomId(chatRoomId);
        return chatMessageService.getChatMessageList(chatMessagevo); // 오름차순 정렬된 메시지들
    }
}


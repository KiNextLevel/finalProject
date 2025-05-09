package com.example.common.view.chatting;

import com.example.common.biz.chatRoom2.ChatRoomService;
import com.example.common.biz.chatRoom2.ChatRoomVO;
import com.example.common.biz.user.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 상대방과 나와의 채팅방이 있나 없나 확인하고
// 만약 없다면 생성해주고 채팅방으로 입장,
// 만약 있다면 바로 채팅방으로 입장
@Controller
public class ChatRoomController {
    @Autowired
    private ChatRoomService chatRoomService;

    @GetMapping("/prepareChatRoom.do")
    public String ChatRoomController(@RequestParam String targetEmail, HttpSession session, Model model, UserVO userVO, ChatRoomVO chatRoomVO) {
       // 먼저 세션에서 내 이메일 꺼내오고
        System.out.println( "채팅방 컨트롤러 로그 : " );
        String myEmail = (String) session.getAttribute("userEmail");
        System.out.println("내 이메일 정보 : "+ myEmail);

        // 1. 디비에 채팅방이 이미 존재하는지 객체에 담아서 확인하기
        chatRoomVO.setUser1Email(myEmail);
        chatRoomVO.setUser2Email(targetEmail);
        chatRoomVO.setCondition("SELECT_CHATROOM_BETWEEN_TWO_USERS");

        ChatRoomVO existingRoom = chatRoomService.getChatRoom(chatRoomVO);
        System.out.println(existingRoom);
        int chatRoomId;
        if (existingRoom == null) {
            // 2. 없으면 새로 생성
            chatRoomVO.setCondition("INSERT_CHAT_ROOM");
            chatRoomService.insert(chatRoomVO);  // 이때 ID 생성됨
            chatRoomId = chatRoomVO.getChatRoomId(); // 생성된 ID 가져오기
        } else {
            chatRoomId = existingRoom.getChatRoomId(); // 기존 ID
            System.out.println("chatroom 아이디 이미 있을때 : " + chatRoomId);
        }

        // 3. 생성된 chatRoomId와 targetEmail을 가지고 화면으로 이동
        return "redirect:/chattingRoom.do?chatRoomId=" + chatRoomId + "&targetEmail=" + targetEmail;


//        vo에 내 이메일 넣고
//                상대방 이메일 넣고
        // 2. 디비 호출
//                디비 불러서 있나 확인하고

//                만약 없다면 ? 채팅방을 만들고 채팅방으로 입장하기
//                만약 있다면? 바로 채팅방으로 이동하기
//
//                리다이렉트로 채팅방 이동하기

    }
}

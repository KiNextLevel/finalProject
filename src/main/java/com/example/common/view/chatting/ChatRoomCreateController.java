package com.example.common.view.chatting;

import com.example.common.biz.alert.AlertService;
import com.example.common.biz.alert.AlertVO;
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
public class ChatRoomCreateController {

    private final String newChatMessage = "누군가 채팅을 보냈습니다";
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private AlertService alertService;

    // 순서 변경
    // 원래는 토큰 확인이 먼저였지만, 채팅방 유무 확인후 -> 토큰 확인하고, 있으면? -> 채팅방 생성
    @GetMapping("/prepareChatRoom.do")

    public String ChatRoom(@RequestParam String targetEmail, HttpSession session, ChatRoomVO chatRoomVO, AlertVO alertVO) {
        System.out.println( "채팅방 컨트롤러 진입 성공 : ChatRoomController" );

        //System.out.println("채팅방 번호 : " + chatRoomId);  // 채팅방 번호 받아오기 ( 채팅방 리스트에서)
        // 먼저 세션에서 내 이메일 꺼내오고
        String myEmail = (String) session.getAttribute("userEmail");
        System.out.println("내 이메일 정보 : " + myEmail);

        // 1. 디비에 채팅방이 이미 존재하는지 객체에 담아서 확인하기
        chatRoomVO.setUser1Email(myEmail);  // 객체에 본인 이메일 넣기
        chatRoomVO.setUser2Email(targetEmail); // 객체에 상대 이메일 넣기
        //chatRoomVO.setCondition("SELECTONE_CHATROOM_BETWEEN_TWO_MEMBERS");  // 컨디션 설정한거 넣기

        // DB에서 본인과 상대방 사이에 이미 존재하는 채팅방이 있는지 확인 (있으면 해당 방 정보를 반환)
        ChatRoomVO existingRoom = chatRoomService.getChatRoom(chatRoomVO);

        System.out.println("두 사람 사이에 방이 있니?(있으면 ChatRoomVO 객체 출력)  : " +existingRoom);
        int chatRoomId;
        // 만약 방이 없다면?
        if (existingRoom == null) {
            chatRoomVO.setCondition("INSERT_CHAT_ROOM");  // 컨디션 설정
            chatRoomService.insert(chatRoomVO);  // 이때 ID 생성됨

            //chatRoomId = chatRoomVO.getChatRoomId(); // DB에서 생성된 ID 가져오기
            // -> 여기서 문제점 발생
            // 이렇게 바로 DB에서 ID를 가져오면, 방은 생겼는데 방 번호가 안담겨져 있음
            // 다시 방 번호를 물어봐야함 -> DB호출


        // 만약 채팅방이 있다면?
        if (existingRoom != null) {
            int chatRoomId = existingRoom.getChatRoomId(); //채팅방 아이디 가져오기
            System.out.println("채팅방 생성 컨트롤러- 기존 채팅방 ID: " + chatRoomId);
            System.out.println("채팅방 생성 컨트롤러- 상대방 이메일: " + targetEmail);
            return "redirect:/chattingRoom.do?chatRoomId=" + chatRoomId + "&targetEmail=" + targetEmail;
        } else {
            // 채팅방이 없으므로, 토큰 확인 로직으로 먼저 이동
            return "redirect:/deductToken.do?targetEmail=" + targetEmail;
        }
    }

//    @GetMapping("/prepareChatRoom.do")
//    public String ChatRoom(@RequestParam String targetEmail, HttpSession session, ChatRoomVO chatRoomVO) {
//        System.out.println( "채팅방 컨트롤러 진입 성공 : ChatRoomController" );
//        //System.out.println("채팅방 번호 : " + chatRoomId);  // 채팅방 번호 받아오기 ( 채팅방 리스트에서)
//        // 먼저 세션에서 내 이메일 꺼내오고
//        String myEmail = (String) session.getAttribute("userEmail");
//        System.out.println("내 이메일 정보 : "+ myEmail);
//
//        // 1. 디비에 채팅방이 이미 존재하는지 객체에 담아서 확인하기
//        chatRoomVO.setUser1Email(myEmail);  // 객체에 본인 이메일 넣기
//        chatRoomVO.setUser2Email(targetEmail); // 객체에 상대 이메일 넣기
//        //chatRoomVO.setCondition("SELECTONE_CHATROOM_BETWEEN_TWO_MEMBERS");  // 컨디션 설정한거 넣기
//
//        // DB에서 본인과 상대방 사이에 이미 존재하는 채팅방이 있는지 확인 (있으면 해당 방 정보를 반환)
//        ChatRoomVO existingRoom = chatRoomService.getChatRoom(chatRoomVO);
//        System.out.println("두 사람 사이에 방이 있니?(있으면 ChatRoomVO 객체 출력)  : " +existingRoom);
//        int chatRoomId;
//        // 만약 방이 없다면?
//        if (existingRoom == null) {
//            chatRoomVO.setCondition("INSERT_CHAT_ROOM");  // 컨디션 설정
//            chatRoomService.insert(chatRoomVO);  // 이때 ID 생성됨
//            //chatRoomId = chatRoomVO.getChatRoomId(); // DB에서 생성된 ID 가져오기
//            // -> 여기서 문제점 발생
//            // 이렇게 바로 DB에서 ID를 가져오면, 방은 생겼는데 방 번호가 안담겨져 있음
//            // 다시 방 번호를 물어봐야함 -> DB호출
//
//            //chatRoomVO.setCondition("SELECTONE_CHATROOM_BETWEEN_TWO_MEMBERS"); // 다시 SELECT용 조건 설정
//
//            // 생성된 채팅방 번호의 정보를 알기 위해 다시 DB에서 받아와서
//            ChatRoomVO createdRoom = chatRoomService.getChatRoom(chatRoomVO); // 반환값(채팅방 번호) 받아오기
//            // chatRoomId에 저장하기
//            chatRoomId = createdRoom.getChatRoomId(); // 실제 생성된 ID 저장
//
//        } // 만약 있다면?
//        else {
//            chatRoomId = existingRoom.getChatRoomId(); // 기존 ID
//            System.out.println("chatroom 아이디 이미 있을때 : " + chatRoomId);
//        }
//
//        // 3. 생성된 chatRoomId와 targetEmail을 가지고 화면으로 이동
//        return "redirect:/chattingRoom.do?chatRoomId=" + chatRoomId + "&targetEmail=" + targetEmail;
//
////        vo에 내 이메일 넣고
////                상대방 이메일 넣고
//        // 2. 디비 호출
////                디비 불러서 있나 확인하고
//

    /// /                만약 없다면 ? 채팅방을 만들고 채팅방으로 입장하기
    /// /                만약 있다면? 바로 채팅방으로 이동하기
    /// /
    /// /                리다이렉트로 채팅방 이동하기
//
//    }
}

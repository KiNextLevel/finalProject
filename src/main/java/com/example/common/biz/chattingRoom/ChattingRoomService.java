package com.example.common.biz.chattingRoom;

import com.example.common.biz.chatRoom2.ChatRoomService;
import com.example.common.biz.chatRoom2.ChatRoomVO;
import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChattingRoomService {
    @Autowired
    ChatRoomService chatRoomService;
    @Autowired
    UserService userService;

    //채팅방 생성해주는 메서드
    public boolean chatRoomCreate(ChatRoomVO chatRoomVO, String userEmail, String targetEmail) {
        //그럼 채팅방 생성됐니? 안됐니? boolean타입으로 쓰기
        boolean result = false; // 채팅생성 실패
        chatRoomVO.setUser1Email(userEmail);    // 객체에 본인 넣기
        chatRoomVO.setUser2Email(targetEmail);  // 객체에 대화하고자 하는 상대방
        // 이거 두개 안넣었더니 null이 나와버려서 추가

        chatRoomVO.setCondition("INSERT_CHAT_ROOM");
        result=chatRoomService.insert(chatRoomVO);

        return result;
    }

    // 채팅방 조회하고 채팅방 번호 반환
    // 채팅방 재조회 → chatRoomId 반환
    public int chatRoomIdCheck(ChatRoomVO chatRoomVO, UserVO userVO) {

        ChatRoomVO createdRoom =chatRoomService.getChatRoom(chatRoomVO);
        int chatRoomId = createdRoom.getChatRoomId();

        // DB에 업데이트 요청
        // userService.update(userVO);

        return chatRoomId;
    }

}
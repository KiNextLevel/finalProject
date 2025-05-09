package com.example.common.biz.chatRoom2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.sql.Timestamp;
@Getter
@Setter
@ToString

public class ChatRoomVO {
    private int chatRoomId;             // 채팅방 번호 (PK)
    private String user1Email;           // 유저 1 이메일
    private String user2Email;           // 유저 2 이메일
    private String lastMessage;          // 마지막 메시지
    private Timestamp lastTime;          // 마지막 메시지 보낸 시간
    private Timestamp createdTimeChattingRoom; // 채팅방 생성 시간
    private String condition;            // 다양한 메서드를 위한 컨디션
    private String searchKeyword;

}

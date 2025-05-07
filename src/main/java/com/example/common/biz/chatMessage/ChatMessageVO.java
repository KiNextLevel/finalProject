package com.example.common.biz.chatMessage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class ChatMessageVO {

    private Long messageId;          // 메시지 번호 (PK)
    private Long chatRoomId;         // 채팅방 번호 (FK)
    private String senderEmail;      // 발신자 이메일 (FK)
    private String messageContent;   // 메시지 내용
    private Timestamp sentTime;      // 메시지 보낸 시각
    private String condition;        // 다양한 메서드를 위한 컨디션
    private String searchKeyword;

}
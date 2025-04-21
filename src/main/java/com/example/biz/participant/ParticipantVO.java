package com.example.biz.participant;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParticipantVO {
    // 참가한 유저의 이벤트 번호
    private int participantBoardNumber;
    // 참가한 유저의 이메일
    private String participantUserEmail;
    private String boardTitle;
    private String condition; // 컨디션
    private String searchKeyword; // SearchKeyword

}

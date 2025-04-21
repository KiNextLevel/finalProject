package com.example.biz.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class BoardVO {
    private int boardNumber;
    // 이벤트 제목
    private String boardTitle;
    // 이벤트 내용
    private String boardContent;
    // 작성 날짜
    private Date boardDate;
    // 최대 참여인원
    private int boardLimit;
    // 다양한 메서드를 위한 컨디션
    private String condition;
    // 검색키워드
    private String searchKeyword;
    // 이벤트 참여자
    private int boardParticipant;
    // 참여자 목록
    private int participant;
    // 사용자 이메일 추가
    private String userEmail;

}

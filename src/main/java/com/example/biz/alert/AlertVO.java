package com.example.biz.alert;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
public class AlertVO {
    private int alertNumber; 	// 알림 번호 (PK)
    private Date alertDate; // 알림 날짜
    private String alertContent; // 알림 내용
    private boolean alertIsWatch; // 열람 여부
    private String userEmail; // 이메일 (FK)
    private String condition; // 컨디션
    private String searchKeyword; // SearchKeyword

}

package com.example.biz.report;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
public class ReportVO {
    private int reportNumber; // 신고 번호 (PK)
    private String reportReported; // 피신고자 (FK)
    private String reportReporter; // 신고자 (FK)
    private String reportReason; // 신고 사유
    private String reportDescription; //신고 설명
    private Date reportDate; // 신고 날짜
    private String condition; // 컨디션
    private String searchKeyword; // SearchKeyword

}

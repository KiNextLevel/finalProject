package com.example.common.biz.alert.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data //getter, setter, toString 대체
@NoArgsConstructor //기본생성자 자동 생성
@AllArgsConstructor //모든 멤버변수를 매개변수로 받는 생성자 자동 생성
@Table(name = "Alert")
@Entity(name = "AlertEntity")
public class AlertEntity {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 자동증가
    @Column(name = "ALERT_NUM", nullable = false)   //테이블 칼럼과 매핑
    private int alertNumber;

    //Date 타입을 oracle의 DATE 타입에 매핑
    @Column(name = "ALERT_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate alertDate;

    @Column(name = "ALERT_CONTENT", nullable = false, length = 225)
    private String alertContent;

    @Column(name = "ALERT_ISWATCH", nullable = false)
    private boolean alertIsWatch;

    @Column(name = "ALERT_MEMBER_EMAIL", nullable = false, length = 100)
    private String userEmail;



}



package com.example.common.biz.visitor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;
@Getter
@Setter
@ToString
public class VisitorVO {
    private String userEmail;
    private Date visitorDate;
    private Timestamp visitorTime;
    private int visitorToday;
    private int visitorDaily;
    private String condition;
    private int visitorGender;
}

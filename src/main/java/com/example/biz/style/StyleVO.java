package com.example.biz.style;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StyleVO {
    private int styleNumber; // 스타일 번호 (PK)
    private String styleType; // 스타일
    private String userEmail; // 스타일 유저 이메일 (FK)
    private String condition; // 컨디션
    private String searchKeyword; // SearchKeyword

}

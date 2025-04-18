package com.example.biz.preference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PreferenceVO {
    private String userEmail; // 이메일(PK)
    private String userName;
    private int preferenceHeight; // 선호 키
    private String preferenceBody; // 선호 체형
    private String preferenceAge; // 선호 나이
    private String condition; // 컨디션
    private String searchKeyword; // SearchKeyword

}

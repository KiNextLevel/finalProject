package com.example.biz.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
public class UserVO {
    // 사용자 이메일
    private String userEmail;
    // 사용자 비밀번호
    private String userPassword;
    // 사용자 닉네임
    private String userNickname;
    // 전화번호
    private String userPhone;
    // 회원가입 날짜
    private Date userRegdate;
    // 성별
    private boolean userGender;
    // 성별 변경 여부를 나타내는 플래그
    private boolean userGenderChanged = false;
    // 생년월일
    private String userBirth;
    // 사용자 키
    private int userHeight;
    // 사용자 체형
    private String userBody;
    // MBTI
    private String userMbti;
    // 회원 사진
    private String userProfile;
    // 학력
    private String userEducation;
    // 종교
    private String userReligion;
    // 음주
    private int userDrink;
    // 흡연
    private boolean userSmoke;
    // 흡연 변경 여부를 나타내는 플래그
    private boolean userSmokeChanged = false;
    // 직업
    private String userJob;
    // 역할
    private Integer userRole;
    // 구독 여부
    private boolean userPremium;
    // 토큰 개수
    private int userToken;
    // 지역
    private String userRegion;
    // 자기소개
    private String userDescription;
    // 이름
    private String userName;
    // 컨디션
    private String condition;
    // 키워드 검색
    private String searchKeyword;
    // 위도
    private double userLatitude;
    // 경도
    private double userLongitude;
    // 회원가입 타입
    private String socialType;

}

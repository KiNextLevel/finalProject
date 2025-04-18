package com.example.biz.payment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
public class PaymentVO {
    private int paymentNumber; // 결제 번호 (PK)
    private int paymentPrice; // 결제 금액
    private Date paymentDate; // 결제 날짜
    private String paymentType; // 구매 종류
    private String userEmail; // 이메일 (FK)
    private String userName; //유저 이름
    private int productNumber; // 구매한 상품 번호 (FK)
    private String productName; // 상품명
    private int productPrice; // 상품 가격
    private String condition; // 컨디션
    private String searchKeyword; // SearchKeyword

}

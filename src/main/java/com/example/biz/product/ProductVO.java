package com.example.biz.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
public class ProductVO {
    private int productNumber; // 상품 번호 (PK)
    private String productName; // 상품 이름
    private String productDescription; // 상품 설명
    private int productPrice; // 상품 가격
    private Date paymentDate;  //결제 날짜
    private int paymentPrice; //결제 금액
    private String userEmail; //유저 이메일
    private String condition; // 컨디션
    private String searchKeyword; // SearchKeyword

}

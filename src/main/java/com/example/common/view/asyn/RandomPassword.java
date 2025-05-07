package com.example.common.view.asyn;

import org.springframework.stereotype.Component;

import java.util.UUID;

// 랜덤패스워드 설정
@Component
public class RandomPassword {
    public String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
}
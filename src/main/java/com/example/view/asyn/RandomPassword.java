package com.example.view.asyn;

import java.util.UUID;

// 랜덤패스워드 설정
public class RandomPassword {
    public static String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
}

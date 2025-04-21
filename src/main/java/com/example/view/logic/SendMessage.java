package com.example.view.logic;

import jakarta.annotation.PostConstruct;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class SendMessage {

    private DefaultMessageService messageService;

    //application.properties의 값 주입하기 위한 어노테이션
    @Value("${coolsms.api-key}")
    private String apiKey;

    @Value("${coolsms.api-secret}")
    private String apiSecret;

    @Value("${coolsms.api-url}")
    private String apiUrl;

    @PostConstruct
    public void init() {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, apiUrl);
    }

//    public void sendOne() {
//        Message message = new Message();
//        message.setFrom("01012345678");
//        message.setTo("01098765432");
//        message.setText("테스트 메시지입니다.");
//
//        SingleMessageSentResponse response =
//                this.messageService.sendOne(new SingleMessageSendingRequest(message));
//        System.out.println(response);
//    }

    public void sendPay(String phoneNumber, String userName, int price, String productName, String orderId) {
        Message message = new Message();
        message.setFrom(phoneNumber);
        message.setTo(phoneNumber);
        message.setText(userName + "님, " + productName + "을(를) 구매해 주셔서 감사합니다.\n" +
                "결제 금액: " + price + "원\n" +
                "주문 번호: " + orderId);

        SingleMessageSentResponse response =
                this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);
    }
}

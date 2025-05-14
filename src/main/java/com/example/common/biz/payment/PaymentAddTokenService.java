package com.example.common.biz.payment;

import com.example.common.biz.payment.impl.PaymentTemplateDAO;
import com.example.common.biz.user.UserVO;

public interface PaymentAddTokenService {
    boolean paymentAddToken(PaymentVO VO, UserVO userVO);
}

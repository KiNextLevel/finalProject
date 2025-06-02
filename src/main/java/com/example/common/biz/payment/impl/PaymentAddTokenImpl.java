package com.example.common.biz.payment.impl;

import com.example.common.biz.payment.PaymentAddTokenService;
import com.example.common.biz.payment.PaymentVO;
import com.example.common.biz.user.UserVO;
import com.example.common.biz.user.impl.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("paymentAddTokenService")
public class PaymentAddTokenImpl implements PaymentAddTokenService {
    @Autowired
    private PaymentTemplateDAO paymentDAO;
    @Autowired
    private UserDAO userDAO;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean paymentAddToken(PaymentVO paymentVO, UserVO userVO) {
        paymentDAO.insert(paymentVO);   //결제 내역 저장
        System.out.println("트랜잭션 로그: insert 성공");
        userDAO.update(userVO); //사용자 정보 업데이트
        return true;
    }
}

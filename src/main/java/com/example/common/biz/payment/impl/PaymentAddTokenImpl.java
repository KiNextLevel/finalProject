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
        paymentDAO.insert(paymentVO);
        System.out.println("트랜잭션 로그: insert 성공");
        //UserVO uservo = new UserVO();    // 테스트용
        //if (!userDAO.update(uservo)) throw new RuntimeException("강제 예외"); // 테스트용
        userDAO.update(userVO);
        return true;
    }
}

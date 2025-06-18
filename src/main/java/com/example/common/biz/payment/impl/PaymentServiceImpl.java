package com.example.common.biz.payment.impl;

import com.example.common.biz.payment.PaymentService;
import com.example.common.biz.payment.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentTemplateDAO paymentDAO;

    //결제 내역 저장
    @Override
    public boolean insert(PaymentVO vo) {
        return paymentDAO.insert(vo);
    }

    @Override
    public boolean update(PaymentVO vo) {
        return false;
    }

    @Override
    public boolean delete(PaymentVO vo) {
        return false;
    }

    @Override
    public PaymentVO getPayment(PaymentVO vo) {
        return paymentDAO.getPayment(vo);
    }

    @Override
    public List<PaymentVO> getPaymentList(PaymentVO vo) {
        return paymentDAO.getPaymentList(vo);
    }
}

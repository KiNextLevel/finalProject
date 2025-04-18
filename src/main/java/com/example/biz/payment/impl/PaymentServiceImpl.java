package com.example.biz.payment.impl;

import com.example.biz.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDAO paymentDAO;

    @Override
    public boolean insert(PaymentVO vo) {
        return paymentDAO.insert();
    }

    @Override
    public boolean update(PaymentVO vo) {
        return paymentDAO.update();
    }

    @Override
    public boolean delete(PaymentVO vo) {
        return paymentDAO.delete();
    }

    @Override
    public PaymentVO getPayment(PaymentVO vo) {
        return paymentDAO.getPayment();
    }

    @Override
    public List<PaymentVO> getPaymentList(PaymentVO vo) {
        return paymentDAO.getPaymentList();
    }
}

package com.example.biz.payment.impl;

import com.example.biz.payment.PaymentService;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    @Override
    public boolean insert(PaymentVO vo) {
        return false;
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
        return null;
    }

    @Override
    public List<PaymentVO> getPaymentList(PaymentVO vo) {
        return List.of();
    }
}

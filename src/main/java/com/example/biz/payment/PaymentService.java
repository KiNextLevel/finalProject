package com.example.biz.payment;

import java.util.List;

public interface PaymentService {
    boolean insert(PaymentVO vo);
    boolean update(PaymentVO vo);
    boolean delete(PaymentVO vo);
    PaymentVO getPayment(PaymentVO vo);
    List<PaymentVO> getPaymentList(PaymentVO vo);
}

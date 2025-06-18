package com.example.common.view.adminPaymentList;

import com.example.common.biz.payment.PaymentService;
import com.example.common.biz.payment.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
public class AdminPaymentListPageRestController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("getPaymentList.do")
    public Map<String, Object> AdminPaymentListPageRestController(PaymentVO paymentVO) {
        System.out.println("로그: getPaymentList.do");
        Map<String, Object> datas = new HashMap<>();
        paymentVO.setCondition("SELECTALL_ADMIN_PAYMENTS");
        List<PaymentVO> list = paymentService.getPaymentList(paymentVO);	//결제 내역
        System.out.println("PaymentListPage log datas: "+datas);

        datas.put("list", list);
        return datas;

    }
}

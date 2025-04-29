package com.example.common.view.mainPage;

import com.example.common.biz.payment.PaymentService;
import com.example.common.biz.payment.PaymentVO;
import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminMainPageRestController {

@Autowired
private PaymentService paymentService;
@Autowired
private UserService userService;

    //상품별 매출 가져오기
    @GetMapping("/getProductPrice.do")
    public Map<String, Object> getProductPrice(PaymentVO paymentVO) {
        System.out.println("/getProductPrice.do 도착");
        Map<String, Object> result = new HashMap<>();
        paymentVO.setCondition("SELECTALL_PRODUCT_PRICE");
        List<PaymentVO> paymentList = paymentService.getPaymentList(paymentVO);
        System.out.println("paymentList: ["+paymentList+"]");
        result.put("result", paymentList);
        return result;
    }

    //일별 매출 가져오기
    @GetMapping("/getDayPrice.do")
    public Map<String, Object> getDayPrice(PaymentVO paymentVO) {
        Map<String, Object> result = new HashMap<>();
        paymentVO.setCondition("SELECTALL_DAY");
        List<PaymentVO> paymentList = paymentService.getPaymentList(paymentVO);
        System.out.println("paymentList: "+paymentList);
        result.put("dayResult", paymentList);
        return result;
    }

    //주별 매출 가져오기
    @GetMapping("/getWeekPrice.do")
    public Map<String, Object> getWeekPrice(PaymentVO paymentVO) {
        Map<String, Object> result = new HashMap<>();
        paymentVO.setCondition("SELECTALL_WEEK");
        List<PaymentVO> paymentList = paymentService.getPaymentList(paymentVO);
        System.out.println("paymentList: "+paymentList);
        result.put("weekResult", paymentList);
        return result;
    }

    //월별 매출 가져오기
    @GetMapping("/getMonthPrice.do")
    public Map<String, Object> getMonthPrice(PaymentVO paymentVO) {
        Map<String, Object> result = new HashMap<>();
        paymentVO.setCondition("SELECTALL_MONTH");
        List<PaymentVO> paymentList = paymentService.getPaymentList(paymentVO);
        System.out.println("paymentList: "+paymentList);
        result.put("monthResult", paymentList);
        return result;
    }

    //가입한 회원 최신순 4명
    @GetMapping("/getFourUser.do")
    public Map<String, Object> getFourUser(UserVO userVO) {
        Map<String, Object> result = new HashMap<>();
        userVO.setCondition("USER_FOUR");
        List<UserVO> datas = userService.getUserList(userVO);
        System.out.println("datas" + datas);
        result.put("users", datas);
        return result;
    }

    //전체 회원 수 가져오기

    //결제 한 회원 수 가져오기
}

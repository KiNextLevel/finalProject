package com.example.common.view.adminPaymentList;
import com.example.common.biz.payment.PaymentService;
import com.example.common.biz.payment.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class AdminPaymentListPageController{


	@GetMapping("/adminPaymentListPage.do")
	public String adminPaymentListPage(Model model, PaymentVO paymentVO) {
		System.out.println("로그: adminPaymentListPage.do");

		return "/target-free-admin-template/AdminPaymentListPage";
	}
}
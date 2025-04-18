package com.example.view.adminPaymentList;
import com.example.biz.payment.PaymentService;
import com.example.biz.payment.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class AdminPaymentListPageController{
	@Autowired
	private PaymentService paymentService;

	@GetMapping("/adminPaymentListPage.do")
	public String adminPaymentListPage(Model model, PaymentVO paymentVO) {
		paymentVO.setCondition("SELECTALL_ADMIN_PAYMENTS");
		List<PaymentVO> datas = paymentService.getPaymentList(paymentVO);	//결제 내역
		System.out.println("PaymentListPage log datas: "+datas);

		String path = "/Metronic-Shop-UI-master/theme/Alert";
		if(datas != null) {
			model.addAttribute("datas", datas);	//결제 내역 가지고 페이지 이동
			path = "/target-free-admin-template/AdminPaymentListPage";
		}
		else {
			System.out.println("매출페이지 에러["+datas+"]");
			model.addAttribute("datas", datas);
			model.addAttribute("msg", "매출 페이지 이동 실패");
			model.addAttribute("flag", false);
		}
		return path;
	}
}
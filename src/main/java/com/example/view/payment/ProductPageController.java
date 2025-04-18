package com.example.view.payment;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;

public class ProductPageController implements Action {
    @GetMapping("payment.do")
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: PRODUCTPAGE ACTION 도착");
		ActionForward forward = new ActionForward();
	    forward.setPath("/Metronic-Shop-UI-master/theme/ProductPage.jsp");
		forward.setRedirect(true);
		return forward;
	}
}

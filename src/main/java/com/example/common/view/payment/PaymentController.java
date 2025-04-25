package com.example.common.view.payment;

import com.example.common.biz.payment.PaymentService;
import com.example.common.biz.payment.PaymentVO;
import com.example.common.biz.product.ProductSerivce;
import com.example.common.biz.product.ProductVO;
import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductSerivce productService;

	private final int[] tokenList = {1, 5, 10};

	//결제 내역 payment 테이블에 추가
	//그리고 사용자 정보 update
    @PostMapping("/payment.do")
	public String payment(HttpServletRequest request, HttpSession session, Model model, PaymentVO paymentVO, ProductVO productVO, UserVO userVO) {
		System.out.println("CONT 로그: PAYMENT ACTION 도착1");
		//SendMessage send = new SendMessage();

		String userEmail = (String)session.getAttribute("userEmail");
		userVO.setUserEmail(userEmail);
		userVO.setCondition("SELECTONE_USERINFO");
		userVO = userService.getUser(userVO);
		String userName = userVO.getUserName();	//구매자 이름
		System.out.println("userName: ["+userName+"]");
		String phone = userVO.getUserPhone();	//구매자 핸드폰 번호
		System.out.println("phone: ["+phone+"]");

        // 상품에 해당하는 정보 가져오기
        productVO = productService.getProduct(productVO);	//상품 정보
		int productPrice = productVO.getProductPrice();
		int productNumber = productVO.getProductNumber();
		String productName = productVO.getProductName();
		System.out.println("productPrice: ["+productPrice+"]");
		System.out.println("productName:["+productName+"]");
		System.out.println("productVO: ["+productVO+"]");
		System.out.println("productNumber:["+productNumber+"]");

		String path = "/mainPage.do";
		if(productVO == null){	//상품을 못 찾으면
			model.addAttribute("msg", "상품을 찾을 수 없습니다");
			model.addAttribute("flag", false);
			path = "/Metronic-Shop-UI-master/theme/Alert";
		}
		else{	//결제 내역 payment 테이블에 추가
			paymentVO.setProductNumber(productNumber);
			paymentVO.setUserEmail((String)session.getAttribute("userEmail"));
			paymentVO.setPaymentPrice(productPrice);
			paymentVO.setProductName(productName);
			paymentService.insert(paymentVO);	//payment 테이블에 구매정보 추가
			//send.sendPay(phone, userName, productPrice, productName);	//구매자에게 구매정보 문자 전송
		}

		//그리고 사용자 정보 update
		int userToken = userVO.getUserToken(); //로그인 한 사용자의 토큰 개수
		System.out.println("userToken: ["+userToken+"]");

		if(productNumber == 1){    //프리미엄 결제
			userVO.setCondition("UPDATE_PREMIUM");
			if(userService.update(userVO)) {
				System.out.println("update successs");
				userVO.setCondition("SELECTONE_USERINFO");
				userVO = userService.getUser(userVO);   //DB에서 업데이트 된 프리미엄 여부 가져옴
				session.setAttribute("userPremium", userVO.getUserPremium());//세션에 다시 저장
			}
			else{
				System.out.println("update fail");
			}
		}
		else {
			userToken += tokenList[productNumber-2];
			userVO.setUserToken(userToken);
			userVO.setCondition("UPDATE_ADD_TOKEN");
			userService.update(userVO);
		}
        return null;
	}

	@GetMapping("/productPage.do")
	public String paymentPage() {
		System.out.println("CONT 로그: PRODUCTPAGE ACTION 도착");
		return "/Metronic-Shop-UI-master/theme/ProductPage";
	}

	@PostMapping("/tossPaymentPage.do")
	public String tossPaymentPage(ProductVO productVO, HttpSession session, UserVO userVO) {
		userVO.setUserEmail((String)session.getAttribute("userEmail"));
		userVO.setCondition("SELECTONE_USERINFO");
		userVO = userService.getUser(userVO);   //사용자 정보 가져오기
		String userEmail = userVO.getUserEmail();
		String userName = userVO.getUserName();
		System.out.println("TossPaymentPage 로그: 도착");
		String productNum = String.valueOf(productVO.getProductNumber());
		System.out.println("TossPaymentPageAction 로그: productNum = " + productNum);
		String productName = productVO.getProductName();
		System.out.println("TossPaymentPageAction 로그: productName = " + productName);
		String productPrice = String.valueOf(productVO.getProductPrice());
		System.out.println("TossPaymentPageAction 로그: productPrice = "+productPrice);

		// 한글을 URL 인코딩
		String encodedProductName = URLEncoder.encode(productName, StandardCharsets.UTF_8);
		String encodedUserName = URLEncoder.encode(userName, StandardCharsets.UTF_8);
		System.out.println("TossPaymentPageAction 로그: encodedProductName = [" + encodedProductName + "]");

		// .html로 갈거라 url에 담아서  Query parameter로 전달보냄
		return "redirect:/widget/index.html?productName=" + encodedProductName +
				"&productPrice=" + productPrice + "&productNum=" +
				productNum +"&userEmail=" + userEmail + "&userName=" + encodedUserName;
	}
}


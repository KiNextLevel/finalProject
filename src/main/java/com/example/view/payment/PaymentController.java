package com.example.view.payment;

import controller.common.Action;
import controller.common.ActionForward;
import controller.logic.SendMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.PaymentDAO;
import org.example.webapp.model.dao.ProductDAO;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.PaymentDTO;
import org.example.webapp.model.dto.ProductDTO;
import org.example.webapp.model.dto.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;


public class PaymentController implements Action {

	@PostMapping("payment.do")
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: PAYMENT ACTION 도착1");
		ActionForward forward = new ActionForward();
        HttpSession session = request.getSession();
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO();
		ProductDAO productDAO = new ProductDAO();
		ProductDTO productDTO = new ProductDTO();
		PaymentDAO paymentDAO = new PaymentDAO();
		PaymentDTO paymentDTO = new PaymentDTO();
		SendMessage send = new SendMessage();

        int productNum = Integer.parseInt(request.getParameter("productNum")); //구매하려는 상품 번호
		System.out.println("productNum = "+productNum);
		System.out.println("CONT 로그: PAYMENT ACTION 도착2");
		userDTO.setUserEmail((String)session.getAttribute("userEmail"));
		userDTO.setCondition("SELECTONE_USERINFO");
		userDTO = userDAO.selectOne(userDTO);
		String userName = userDTO.getUserName();	//구매자 이름
		System.out.println("userName: ["+userName+"]");
		String phone = userDTO.getUserPhone();	//구매자 핸드폰 번호
		System.out.println("phone: ["+phone+"]");

        // 상품에 해당하는 정보 가져오기
        productDTO.setProductNumber(productNum);
        productDTO = productDAO.selectOne(productDTO);	//상품 정보
		int productPrice = productDTO.getProductPrice();
		String productName = productDTO.getProductName();
		System.out.println("productPrice: ["+productPrice+"]");
		System.out.println("productName:["+productName+"]");
		System.out.println("productDTO: ["+productDTO+"]");
		if(productDTO == null){	//상품을 못 찾으면
			request.setAttribute("msg", "상품을 찾을 수 없습니다");
			request.setAttribute("flag", false);
			forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
			forward.setRedirect(false);
		}
		else{
			paymentDTO.setProductNumber(productDTO.getProductNumber());
			paymentDTO.setUserEmail((String)session.getAttribute("userEmail"));
			paymentDTO.setPaymentPrice(productDTO.getProductPrice());
			paymentDTO.setProductName(productDTO.getProductName());
			paymentDAO.insert(paymentDTO);	//payment 테이블에 구매정보 추가
			//send.sendPay(phone, userName, productPrice, productName);	//구매자에게 구매정보 문자 전송
		}
		request.setAttribute("productNum", productNum);
		forward.setPath("addToken.do");
		forward.setRedirect(false);
        return forward;
        }
	}


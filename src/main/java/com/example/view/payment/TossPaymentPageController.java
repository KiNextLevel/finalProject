package com.example.view.payment;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TossPaymentPageController implements Action {
    @PostMapping("tossPaymentPage.do")
    public ActionForward execute(HttpServletRequest request) {
        ActionForward forward = new ActionForward();
        HttpSession session = request.getSession();;
        UserDTO userDTO = new UserDTO();
        UserDAO userDAO = new UserDAO();
        userDTO.setUserEmail((String)session.getAttribute("userEmail"));
        userDTO.setCondition("SELECTONE_USERINFO");
        userDTO = userDAO.selectOne(userDTO);   //사용자 정보 가져오기
        String userEmail = userDTO.getUserEmail();
        String userName = userDTO.getUserName();
        System.out.println("TossPaymentPage 로그: 도착");
        String productNum = request.getParameter("Product_Num");
        System.out.println("TossPaymentPageAction 로그: productNum = " + productNum);
        String productName = request.getParameter("Product_Name");
        System.out.println("TossPaymentPageAction 로그: productName = " + productName);
        String productPrice = request.getParameter("Product_Price");
        System.out.println("TossPaymentPageAction 로그: productPrice = "+productPrice);

        // 한글을 URL 인코딩
        String encodedProductName = URLEncoder.encode(productName, StandardCharsets.UTF_8);
        String encodedUserName = URLEncoder.encode(userName, StandardCharsets.UTF_8);
        System.out.println("TossPaymentPageAction 로그: encodedProductName = [" + encodedProductName + "]");

        // .html로 갈거라 url에 담아서  Query parameter로 전달보냄
        forward.setPath("/widget/index.html?productName=" + encodedProductName +
                "&productPrice=" + productPrice + "&productNum=" +
                productNum +"&userEmail=" + userEmail + "&userName=" + encodedUserName);
        forward.setRedirect(true);
        return forward;
    }
}


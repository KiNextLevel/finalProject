package com.example.view.payment;

import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddTokenController{
    @Autowired
    private UserService userService;

    @PostMapping("addToken.do")
    public String addToken(HttpServletRequest request, HttpSession session, UserVO userVO, Model model) {
        System.out.println("AddTokenAction log!");
        int productNum = (Integer)request.getAttribute("productNum");   //구매한 상품
        System.out.println("productNum: "+productNum);

        String email = (String)session.getAttribute("userEmail"); //로그인 한 사용자 이메일
        System.out.println("email: "+email);

        userVO.setCondition("SELECTONE_USERINFO"); //selectOne 한 번 하는게 서버비용 아낄 수 있음
        userVO.setUserEmail(email);
        userVO = userService.getUser(userVO);
        System.out.println("userVO: "+userVO);
        int userToken = userVO.getUserToken(); //로그인 한 사용자의 토큰 개수
        System.out.println("userToken: ["+userToken+"]");

        if(productNum == 1){    //프리미엄 결제
            userVO.setCondition("UPDATE_PREMIUM");
            if(userService.update(userVO)) {
                System.out.println("update successs");
                userVO.setCondition("SELECTONE_USERINFO");
                userVO = userService.getUser(userVO);   //DB에서 업데이트 된 프리미엄 여부 가져옴
                session.setAttribute("userPremium", userVO.isUserPremium());//세션에 다시 저장
            }
            else{
                System.out.println("update fail");
            }
        }
        else if(productNum == 2){   //토큰 1개 구매
            userToken += 1;
            userVO.setUserToken(userToken);
            userVO.setCondition("UPDATE_ADD_TOKEN");
            userService.update(userVO);
        }
        else if(productNum == 3){   //토큰 5개 구매
            userToken += 5;
            userVO.setUserToken(userToken);
            userVO.setCondition("UPDATE_ADD_TOKEN");
            userService.update(userVO);
        }
        else if(productNum == 4){   //토큰 10개 구매
            userToken += 10;
            userVO.setUserToken(userToken);
            userVO.setCondition("UPDATE_ADD_TOKEN");
            userService.update(userVO);
        }
        model.addAttribute();
        return null;
    }
}

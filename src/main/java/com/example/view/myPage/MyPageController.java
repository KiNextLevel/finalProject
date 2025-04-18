package com.example.view.myPage;

import com.example.biz.participant.ParticipantService;
import com.example.biz.participant.ParticipantVO;
import com.example.biz.payment.PaymentService;
import com.example.biz.payment.PaymentVO;
import com.example.biz.preference.PreferenceService;
import com.example.biz.preference.PreferenceVO;
import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MyPageController {
    @Autowired
    private UserService userService;
    @Autowired
    private PreferenceService preferenceService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ParticipantService participantService;

    @GetMapping("/myPage.do")
    public String myPage(UserVO userVO, PreferenceVO preferenceVO, PaymentVO paymentVO,
                         ParticipantVO participantVO, HttpSession session, Model model) {
        //전달받을거: userEmail,
        System.out.println("CTRL 로그: MyPageAction");
        String userEmail = (String) session.getAttribute("userEmail");

        if (userEmail == null) {
            // 로그인되지 않은 경우
            System.out.println("Mypage Action Log: userEmail is null");
            return "redirct:login.jsp";
        }

        userVO.setCondition("SELECTONE_USERINFO"); // 조건 설정 추가

        preferenceVO.setCondition("SELECTONE");    // 조건 설정 추가

        paymentVO.setCondition("SELECTALL_PRODUCTLIST");// 조건 설정 추가

        participantVO.setCondition("SELECTALL_EVENTPRINT");// 조건 설정 추가

        UserVO getUserVO = userService.getUser(userVO);//selectOne
        //PreferenceDTO preference = preferenceDAO.selectOne(preferenceDTO);
        PreferenceVO getPreferenceVO = preferenceService.getPreference(preferenceVO);
        List<PaymentVO> paymentList = paymentService.getPaymentList(paymentVO);
        System.out.println("paymentList: ["+paymentList+"]");
        List<ParticipantVO> participantList = participantService.getParticipantList(participantVO);
        for(ParticipantVO p : participantList) {
            System.out.println(p);
        }

        if (userService.getUser(userVO) != null) {
            //request.setAttribute("paymentList", paymentList);
            model.addAttribute("paymentList", paymentList);
            //request.setAttribute("userDTO", getUserVO); // 첫 번째 사용자 정보
            model.addAttribute("userVO", getUserVO);
            //request.setAttribute("preferenceDTO", getPreferenceVO);
            model.addAttribute("preferenceVO", getPreferenceVO);
            //request.setAttribute("participantList", participantList);
            model.addAttribute("participantList", participantList);
            //forward.setPath("/Metronic-Shop-UI-master/theme/MyPage.jsp");
            //forward.setRedirect(false);
            return "/Metronic-Shop-UI-master/theme/MyPage.jsp";
        } else {
            System.out.println("마이페이지 로그[사용자 정보 없음]");
            // 사용자 정보가 없을 때 처리
//            forward.setPath("loginPage.do");
//            forward.setRedirect(true);
            return "redirect:/login.do";
        }
    }
}

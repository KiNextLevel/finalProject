//package com.example.common.view.chatting;
//// 사용자 채팅 시작하면 토큰 차감해주는 컨트롤러
//
//
//import com.example.common.biz.user.UserService;
//import com.example.common.biz.user.UserVO;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//
////채팅 토큰 차감
////채팅 방 이동
//@Controller
//public class ChattingController {
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/chatting.do")
//    public String chatting(HttpSession session, Model model, UserVO userVO) {
//
//        //1. Stirng 변수 담기 = 세션에서 사용자 이메일 가져오기
//        // 사용자 정보 조회하기 ? 가 필요할까?
//        String userEmail = (String) session.getAttribute("userEmail");
//        userVO.setUserEmail(userEmail);
//        userVO.setCondition("SELECTONE_USERINFO");
//
//
//        //사용자 정보 서비스에서 가져오기
//        userVO = userService.getUser(userVO);
//        int userToken = userVO.getUserToken();
//        System.out.println("userToken 정보 가져오기: [" + userToken + "]");
//
//
//        // 그리고 사용자의 토큰 개수 가져오기? (가져와야하나?)
//        // 가져와야, 채팅을 할 수 있을지 없을지 알 수 있는 거 아닌가?
//
////        String result = "/Metronic-Shop-UI-master/theme/Alert";
////        if(만약 사용자가 1대1 채팅 버튼을 눌렀다면?){
////            // 알럿으로 띄우기, '네' 누르면 바로 토큰 1개 차감
////            // 대화를 시작하겠습니까?(알럿 띄우기)
////            // ('네'를 누르면 토큰이 1개 차감됩니다)
////            model.addAttribute("msg", "대화를 시작하시겠습니까? ('네'를 누르면 토큰이 1개 차감됩니다)");
////            model.addAttribute("flag", false);  //? false 맞나..
////        }
////        else{
////            model.addAttribute("msg", "보유한 토큰이 없습니다.";
////            model.addAttribute("flag", false);
////
////        }
//
//
//        //토큰이 1개 차감 됐습니다(알럿 띄우기)
//
//        //사용자 토큰 업데이트 하기(DB업데이트하기)
//        //int userToken = userVO.getUserToken(); //로그인 한 사용자의 토큰 개수
//        System.out.println("사용자 잔여 토큰 개수 출력: [" + userToken + "]");
//        //쿼리문 조건 설정하기
//        userVO.setCondition("UPDATE_ADD_TOKEN");
//        userService.update(userVO);
//
//
////        String result =
////                userVO.getUserToken ==
////                        만약 토큰이 0개 라면?
////        토큰이 부족합니다! (알럿 띄우기)
////
////        만약 실행이 되지 않았다면?
////        다시 시도해주세요!(알럿 띄우기)
//
//
//    }
//
//}
//
//
//
//
//

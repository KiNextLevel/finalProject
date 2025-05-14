package com.example.common.view.chatting;
//  채팅 시작 전, 토큰 1개를 차감하는 컨트롤러
//  로그인 사용자, 토큰, 입장 흐름 담당
import com.example.common.biz.alert.AlertService;
import com.example.common.biz.alert.AlertVO;
import com.example.common.biz.chatRoom2.ChatRoomService;
import com.example.common.biz.chatRoom2.ChatRoomVO;
import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TokenDeductController {

    private final String newChatMessage = "채팅 도착";
    @Autowired
    private UserService userService;
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private AlertService alertService;

    @GetMapping("/deductToken.do")
    public String deductToken(HttpSession session, @RequestParam String targetEmail,
                              Model model, UserVO userVO, ChatRoomVO chatRoomVO, AlertVO alertVO) {
        System.out.println( "토큰 차감하는 컨트롤러 진입 성공 : deductToken" );
        // 현재 로그인된 사용자의 이메일 가져오기 (세션에서 가져옴)
        String userEmail = (String) session.getAttribute("userEmail");
        System.out.println("세션에서 가져온 사용자 이메일: " + userEmail);
        System.out.println("요청받은 targetEmail: " + targetEmail);

        // 로그인 상태일 경우에만 토큰 차감
        if (userEmail != null) {
            // 먼저 현재 사용자의 토큰 수량을 확인
            //UserVO checkUser = new UserVO();
            userVO.setUserEmail(userEmail);
            userVO.setCondition("SELECTONE_USERINFO");  // 사용자 정보 조회 쿼리(토큰 개수 조회)
            UserVO currentUser = userService.getUser(userVO);

            // 토큰이 0개 이하인 경우 채팅을 허용하지 않음
            if (currentUser == null || currentUser.getUserToken() <= 0) {
                System.out.println("토큰 부족: " + (currentUser != null ? currentUser.getUserToken() : 0));
                model.addAttribute("errorMessage", "채팅을 시작하기 위한 토큰이 부족합니다.");
                return "redirect:/insufficientToken.do";  // 토큰 부족 페이지로 리다이렉트
            }

            // 토큰이 충분하면 차감 진행
            //UserVO userVO = new UserVO();
            userVO.setUserEmail(userEmail);
            userVO.setCondition("UPDATE_MINUS_TOKEN");  // 토큰 1개 차감 쿼리
            // 이제 여기서 채팅룸생성을 해줘야함
            // 채팅방 생성해줬으면? +1 됐으니까
            // 다시 조회해주기

            // 채팅방 생성
            chatRoomVO.setUser1Email(userEmail);    // 본인
            chatRoomVO.setUser2Email(targetEmail);  // 대화하고자 하는 상대방
            // 이거 두개 안넣었더니 null이 나와버려서 추가

            chatRoomVO.setCondition("INSERT_CHAT_ROOM");
            chatRoomService.insert(chatRoomVO);

            //새로운 채팅방 생성되면 alert 테이블에 추가
            alertVO.setUserEmail(targetEmail);
            alertVO.setAlertContent(newChatMessage);
            alertService.insert(alertVO);

            // 생성된 방 ID 재조회
            ChatRoomVO createdRoom = chatRoomService.getChatRoom(chatRoomVO);
            int chatRoomId = createdRoom.getChatRoomId();

            System.out.println("[로그] 토큰 차감 요청 전: " + userVO);

            // DB에 업데이트 요청
            boolean result = userService.update(userVO);

            if (!result) {
                // 업데이트 실패 (토큰 부족 등의 이유)
                System.out.println("토큰 차감 실패");
                model.addAttribute("errorMessage", "토큰 차감에 실패했습니다.");
                return "redirect:/insufficientToken.do";
            }

            System.out.println("토큰 차감 완료후 채팅방 이동하는 컨트롤러로 이동");
            //return "redirect:/chattingRoom.do";
            return "redirect:/chattingRoom.do?chatRoomId=" + chatRoomId + "&targetEmail=" + targetEmail;

        }
        else {
            System.out.println("세션에 로그인 정보가 없음 - 토큰 차감 생략");
            return "redirect:/login.do";  // 로그인 페이지로 리다이렉트
        }

        // 채팅방 페이지로 리다이렉트하면서 targetEmail을 파라미터로 전달
        //return "redirect:/chattingRoom.do?targetEmail=" + targetEmail;
        //
        // return "redirect:/prepareChatRoom.do?targetEmail=" + targetEmail;
    }


//    @GetMapping("/deductToken.do")
//    public String deductToken(HttpSession session, @RequestParam String targetEmail, Model model,  UserVO userVO) {
//        System.out.println( "토큰 차감하는 컨트롤러 진입 성공 : deductToken" );
//        // 현재 로그인된 사용자의 이메일 가져오기 (세션에서 가져옴)
//        String userEmail = (String) session.getAttribute("userEmail");
//        System.out.println("세션에서 가져온 사용자 이메일: " + userEmail);
//        System.out.println("요청받은 targetEmail: " + targetEmail);
//
//        // 로그인 상태일 경우에만 토큰 차감
//        if (userEmail != null) {
//            // 먼저 현재 사용자의 토큰 수량을 확인
//            //UserVO checkUser = new UserVO();
//            userVO.setUserEmail(userEmail);
//            userVO.setCondition("SELECTONE_USERINFO");  // 사용자 정보 조회 쿼리(토큰 개수 조회)
//            UserVO currentUser = userService.getUser(userVO);
//
//            // 토큰이 0개 이하인 경우 채팅을 허용하지 않음
//            if (currentUser == null || currentUser.getUserToken() <= 0) {
//                System.out.println("토큰 부족: " + (currentUser != null ? currentUser.getUserToken() : 0));
//                model.addAttribute("errorMessage", "채팅을 시작하기 위한 토큰이 부족합니다.");
//                return "redirect:/insufficientToken.do";  // 토큰 부족 페이지로 리다이렉트
//            }
//
//            // 토큰이 충분하면 차감 진행
//            //UserVO userVO = new UserVO();
//            userVO.setUserEmail(userEmail);
//            userVO.setCondition("UPDATE_MINUS_TOKEN");  // 토큰 1개 차감 쿼리
//            System.out.println("[로그] 토큰 차감 요청 전: " + userVO);
//            // DB에 업데이트 요청
//            boolean result = userService.update(userVO);
//
//            if (!result) {
//                // 업데이트 실패 (토큰 부족 등의 이유)
//                System.out.println("토큰 차감 실패");
//                model.addAttribute("errorMessage", "토큰 차감에 실패했습니다.");
//                return "redirect:/insufficientToken.do";
//            }
//
//            System.out.println("토큰 차감 완료");
//        } else {
//            System.out.println("세션에 로그인 정보가 없음 - 토큰 차감 생략");
//            return "redirect:/login.do";  // 로그인 페이지로 리다이렉트
//        }
//
//        // 채팅방 페이지로 리다이렉트하면서 targetEmail을 파라미터로 전달
//        //return "redirect:/chattingRoom.do?targetEmail=" + targetEmail;
//        //
//        return "redirect:/prepareChatRoom.do?targetEmail=" + targetEmail;
//    }




    // 채팅 시작 전, 토큰 1개를 차감하고 채팅방으로 이동하는 컨트롤러
//    @GetMapping("/deductToken.do")
//    public String deductToken(HttpSession session, @RequestParam String targetEmail, UserVO userVO) {
//        // 현재 로그인된 사용자의 이메일 가져오기 (세션에서 가져옴)
//        String userEmail = (String) session.getAttribute("userEmail");
//        System.out.println("세션에서 가져온 사용자 이메일: " + userEmail);
//        System.out.println("요청받은 targetEmail: " + targetEmail);
//
//        // 로그인 상태일 경우에만 토큰 차감
//        if (userEmail != null) {
//            userVO.setUserEmail(userEmail);
//            userVO.setCondition("UPDATE_MINUS_TOKEN");  // 토큰 1개 차감 쿼리
//            System.out.println("토큰 차감 요청 전: " + userVO);
//            // DB에 업데이트 요청
//            userService.update(userVO);
//            System.out.println("토큰 차감 완료");
//        } else {
//            System.out.println("세션에 로그인 정보가 없음 - 토큰 차감 생략");
//        }
//
//        // 채팅방 페이지로 리다이렉트하면서 targetEmail을 파라미터로 전달
//        // 다음 컨트롤러 메서드 (moveToChatRoom)에게 targetEmail 파라미터를 전달
//        return "redirect:/chattingRoom.do?targetEmail=" + targetEmail;
//        //return "redirect:/Metronic-Shop-UI-master/theme/WebSocket.jsp" + targetEmail;
//    }


//    // 채팅방이동, 상대방 닉네임 jsp에 주는 컨트롤러
//    @GetMapping("/chattingRoom.do")
//    public String moveToChatRoom(@RequestParam String targetEmail ,Model model, UserVO userVO, HttpSession session ) {
//        System.out.println("targetEmail 상대방 이메일 확인 = " + targetEmail);
//        // 나의 닉네임 가져오기
//        String currentUserNickname = (String) session.getAttribute("userNickname"); // 애초에 가져올 수 없었음
//
//        // View에서 사용할 수 있도록 모델에 담기
//        // model.addAttribute("targetEmail", targetEmail);
//        // WebSocket.jsp 뷰로 이동 (JSP 경로)
//
//        // 이 객체에 상대방 이메일을 넣어주기(누구랑 채팅할지 지정)
//        userVO.setUserEmail(targetEmail);
//        userVO.setCondition("SELECTONE_USERINFO");  // 상대방에 대한 정보 불러오기
//
//        //상대방 사용자의 정보가 담긴 객체 targetUser에 담기
//        UserVO targetUser = userService.getUser(userVO);
//
//        // 상대방의 닉네임을 가져오기
//        String targetNickname = targetUser.getUserNickname();
//
//        // 닉네임을 JSP 화면에 전달 (웹소켓JSP 파일에서 ${targetNickname} 이렇게 써서 상대방 닉네임 가져오고 있음)
//        model.addAttribute("targetNickname", targetNickname);
//        // 상대방의 닉네임 뿐만아니라, 내 닉네임도 가져오기
    // 로그인 시 세션에 저장한 닉네임 자체가 없었기 때문에 애초에 가져오지 못했음
//        model.addAttribute("currentUserNickname", currentUserNickname);
//        // WebSocket.jsp라는 채팅 화면 이동
//        return "/Metronic-Shop-UI-master/theme/WebSocket";
//    }
//}

//    @GetMapping("/chattingRoom.do")
//    public String moveToChatRoom(@RequestParam String targetEmail,@RequestParam int chatRoomId, Model model, UserVO userVO, HttpSession session) {
//        System.out.println( "채팅방 이동하는 컨트롤러 진입 성공 : moveToChatRoom" );
//        System.out.println("targetEmail 상대방 이메일 확인 = " + targetEmail);
//        System.out.println("채팅방 ID: " + chatRoomId);   // 채팅방 ID 확인
//
//        // 상대방 정보 조회
//        userVO.setUserEmail(targetEmail);  // 객체에 상대방 이메일 넣기
//        userVO.setCondition("SELECTONE_USERINFO");  // 이메일을 통해 정보 조회 실시
//        UserVO targetUser = userService.getUser(userVO);  // 이메일 담아서 디비 실행해서 targetUser에 담기
//        System.out.println("상대방 정보 targetUser 내용: " + targetUser);
//        String targetNickname = targetUser.getUserNickname();  // 정보 가져온 것 중에 해당 이메일만 빼서 담기
//
//        // 내 닉네임을 세션이 아닌 DB에서 조회
//        String myEmail = (String) session.getAttribute("userEmail");  // 세션에서 이메일 가져오기
//        userVO.setUserEmail(myEmail); // 객체에 내 이메일 넣기
//        userVO.setCondition("SELECTONE_USERINFO");  // 정보 불러오는 쿼리문 실행
//        UserVO myNikname = userService.getUser(userVO);  // 이메일 담아서 디비 실행해서 myNikname에 담기
//        System.out.println("내 정보 myNikname 내용: " + myNikname);
//        String currentUserNickname = myNikname.getUserNickname();  // 불러온 정보 중에 닉네임만 빼서 currentUserNickname 담기
//
//        // 모델에 담기
//        model.addAttribute("chatRoomId", chatRoomId);  // 추가
//        model.addAttribute("targetNickname", targetNickname);  // 웹소켓 jsp로 상대방 닉네임 보내주기
//        model.addAttribute("currentUserNickname", currentUserNickname);  // 웹소켓 jsp로 본인 닉네임 보내주기
//        return "/Metronic-Shop-UI-master/theme/WebSocket";
//    }
}
package com.example.common.view.chatting;
// 1대1 채팅 버튼 누르면 채팅방으로 이동하는 컨트롤러
import com.example.common.biz.chatRoom2.ChatRoomService;
import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatRoomMoveController {
    @Autowired
    private UserService userService;

    @GetMapping("/chattingRoom.do")
    public String moveToChatRoom(@RequestParam String targetEmail, @RequestParam int chatRoomId, Model model, UserVO userVO, HttpSession session) {
        System.out.println( "채팅방 이동하는 컨트롤러 진입 성공 : moveToChatRoom" );
        System.out.println("targetEmail 상대방 이메일 확인 = " + targetEmail);
        System.out.println("채팅방 ID: " + chatRoomId);   // 채팅방 ID 확인

        // 상대방 정보 조회
        userVO.setUserEmail(targetEmail);  // 객체에 상대방 이메일 넣기
        userVO.setCondition("SELECTONE_USERINFO");  // 이메일을 통해 정보 조회 실시
        UserVO targetUser = userService.getUser(userVO);  // 이메일 담아서 디비 실행해서 targetUser에 담기
        System.out.println("상대방 정보 targetUser 내용: " + targetUser);
        String targetNickname = targetUser.getUserNickname();  // 정보 가져온 것 중에 해당 이메일만 빼서 담기

        // 내 닉네임을 세션이 아닌 DB에서 조회
        String myEmail = (String) session.getAttribute("userEmail");  // 세션에서 이메일 가져오기
        userVO.setUserEmail(myEmail); // 객체에 내 이메일 넣기
        userVO.setCondition("SELECTONE_USERINFO");  // 정보 불러오는 쿼리문 실행
        UserVO myNikname = userService.getUser(userVO);  // 이메일 담아서 디비 실행해서 myNikname에 담기
        System.out.println("내 정보 myNikname 내용: " + myNikname);
        String currentUserNickname = myNikname.getUserNickname();  // 불러온 정보 중에 닉네임만 빼서 currentUserNickname 담기

        // 모델에 담기
        model.addAttribute("chatRoomId", chatRoomId);  // 추가
        model.addAttribute("targetNickname", targetNickname);  // 웹소켓 jsp로 상대방 닉네임 보내주기
        model.addAttribute("currentUserNickname", currentUserNickname);  // 웹소켓 jsp로 본인 닉네임 보내주기
        return "/Metronic-Shop-UI-master/theme/WebSocket";
    }
}

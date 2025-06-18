package com.example.common.view.chatting;
// 상단에 "메시지"를 누르면 자신의 채팅 리스트로 이동하는 컨트롤러
import com.example.common.biz.chatRoom2.ChatRoomService;
import com.example.common.biz.chatRoom2.ChatRoomVO;
import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// 나의 채팅방 리스트를 보여주는 컨트롤러
@Controller
public class MyChatRoomListController {
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private UserService userService;

    @GetMapping("/myChatRoomList.do")
    public String MyChatRoomList(HttpSession session, Model model, ChatRoomVO chatRoomVO, UserVO userVO) {
        System.out.println("MyChatRoomListController 진입");
//        //세션에서 자신의 이메일 정보 가져오기
//        String userEmail = (String) session.getAttribute("userEmail");
//        // 만약 유저 로그인 상태가 아니라면? 로그인 페이지 이동하기
//        if (userEmail == null) {
//            return "redirect:/login.do";
//        }
//        // 이렇게 가져오는게 맞나...????
//        // 2. 내 이메일을 ChatRoomVO에 담기 (내가 주인공인 채팅방만 찾기 위해)
//        chatRoomVO.setUser1Email(userEmail);  // 로그인 사용자 이메일 저장
//
//        // 3. DB에서 해당 사용자가 참여 중인 채팅방 목록 가져오기
//        List<ChatRoomVO> roomList = chatRoomService.getChatRoomList(chatRoomVO);
//
//        //  4. 가져온 채팅방 목록을 화면(MyMessageList.jsp)으로 전달
//        model.addAttribute("roomList", roomList);
//        System.out.println("나의 채팅방 리스트 페이지 - 채팅방 정보 : " + roomList);
//
         // 1. 먼저 세션에서 내 이메일 가져오기
        String myEmail = (String) session.getAttribute("userEmail");

        // 2. 내 이메일 넣기 (내 기준인 채팅방 리스트를 찾아야하기 때문에)
        chatRoomVO.setUser1Email(myEmail);

        // 3. DB에서 해당 사용자가 참여 중인 채팅방 리스트 가져오기
        List<ChatRoomVO> roomList = chatRoomService.getChatRoomList(chatRoomVO);

        for (ChatRoomVO room : roomList) {
            // 누가 상대방인지 찾기 = 내 이메일이 User1Email과 같다면 ? 상대방은 User2Email / 아니라면? User1Email
            String opponentEmail = myEmail.equals(room.getUser1Email()) ? room.getUser2Email() : room.getUser1Email();

            // 상대방 닉네임 찾기
            //UserVO temp = new UserVO();
            userVO.setUserEmail(opponentEmail); // 객체에 상대방 이메일 넣기
            userVO.setCondition("SELECTONE_USERINFO");  // 사용자 정보 조회 컨디션 설정
            UserVO opponent = userService.getUser(userVO); // DB실행해서 opponent 변수에 저장하기

            // 상대방 정보 담기
            room.setOpponentEmail(opponentEmail); //채팅방 객체에 “상대방 이메일” 저장 , ← 반드시 넣어줘야 JSP에서 꺼낼 수 있음
            room.setOpponentNickname(opponent.getUserNickname());  // 채팅방 객체에 상대방 닉네임 저장
            System.out.println("상대 이메일: " + room.getOpponentEmail() + ", 닉네임: " + room.getOpponentNickname());
        }
        model.addAttribute("roomList", roomList);
        System.out.println("roomList 크기: " + roomList.size());

        return "/Metronic-Shop-UI-master/theme/MyChatRoomList";
    }
}

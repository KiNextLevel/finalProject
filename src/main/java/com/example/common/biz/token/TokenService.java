package com.example.common.biz.token;

import com.example.common.biz.chatRoom2.ChatRoomService;
import com.example.common.biz.chatRoom2.ChatRoomVO;
import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    UserService userService;
    @Autowired
    ChatRoomService chatRoomService;

    //토큰 확인 메서드
    public int tokenCheckNumber(UserVO userVO, String userEmail) {
        System.out.println("[토큰 확인 로직 진입]");
        // 먼저 현재 사용자의 토큰 수량을 확인
        userVO.setUserEmail(userEmail);
        userVO.setCondition("SELECTONE_USERINFO");  // 사용자 정보 조회 쿼리(토큰 개수 조회)
        UserVO currentUser = userService.getUser(userVO);

        System.out.println("현재 유저 토큰 개수: " + currentUser.getUserToken());

        int userToken = currentUser.getUserToken();

        //return currentUser.getUserToken();
        return userToken;
    }

    //토큰 차감 메서드
//   public boolean tokenDeduct(UserVO userVO, String userEmail) {
//       System.out.println("[토큰 차감 로직 진입]");
//
//       // 토큰이 충분하면 차감 진행
//        //UserVO userVO = new UserVO();
//       boolean result = false;   // 토큰 차감 실패
//
//       userVO.setUserEmail(userEmail);
//       userVO.setCondition("UPDATE_MINUS_TOKEN");  // 토큰 1개 차감 쿼리
//
//       //result =chatRoomService.insert(chatRoomVO); // 이건 채팅방 생성해주는 건데 잘못 씀
//       return userService.update(userVO); // 차감하기
//
//
//       // 이제 여기서 채팅룸생성을 해줘야함
//        // 채팅방 생성해줬으면? +1 됐으니까
//        // 다시 조회해주기
//
//   }
    // 토큰 차감 메서드
    public boolean tokenDeduct(UserVO userVO, String userEmail) {
        System.out.println("[토큰 차감 로직 진입]");

        userVO.setUserEmail(userEmail);
        userVO.setCondition("UPDATE_MINUS_TOKEN");

        boolean result = userService.update(userVO);

        System.out.println("[토큰 차감 완료 여부] result = " + result);

        return result;
    }

}
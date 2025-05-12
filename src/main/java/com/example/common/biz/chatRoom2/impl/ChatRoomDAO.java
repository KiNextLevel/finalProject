package com.example.common.biz.chatRoom2.impl;



import com.example.common.JDBCUtil;
import com.example.common.biz.chatRoom2.ChatRoomVO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository
public class ChatRoomDAO {
    // 로그인한 사용자가 참여 중인 모든 채팅방을 조회하는 쿼리문
    private final String SELECTALL_CHAT_ROOMS = "SELECT * FROM CHATROOM WHERE CHATROOM_MEMBER_EMAIL1 = ? or CHATROOM_MEMBER_EMAIL2 = ? ORDER BY CHATROOM_ID DESC";
    // 또는 이렇게 바꿔줘도 됨 (자신이 먼저 채팅하기를 시작하면 자신의 메시지 리스트에는 나오지만 상대방에서는 안나왔음)
    // SELECT * FROM CHATROOM
    // WHERE ? IN (CHATROOM_MEMBER_EMAIL1, CHATROOM_MEMBER_EMAIL2)
    // ORDER BY CHATROOM_ID DESC


    //서로의 채팅방이 존재하는지
    private final String SELECTONE_CHATROOM_BETWEEN_TWO_MEMBERS =
            "SELECT * FROM CHATROOM " +
                    "WHERE (CHATROOM_MEMBER_EMAIL1 = ? AND CHATROOM_MEMBER_EMAIL2 = ?) " +
                    "OR (CHATROOM_MEMBER_EMAIL1 = ? AND CHATROOM_MEMBER_EMAIL2 = ?)";

    // 새로운 채팅방을 생성하는 쿼리문
    private final String INSERT_CHAT_ROOM = "INSERT INTO CHATROOM (CHATROOM_ID, CHATROOM_MEMBER_EMAIL1, CHATROOM_MEMBER_EMAIL2) VALUES ((SELECT NVL(MAX(CHATROOM_ID), 0) + 1 FROM CHATROOM), ?, ?)";
//    // 채팅방 나가기
//    private final String DELETE_ROOM = "DELETE FROM CHATROOM WHERE CHAT_ROOM_ID = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // 1. 채팅방 목록 가져오기 (시간순)
    public ArrayList<ChatRoomVO> getChatRoomList(ChatRoomVO chatRoomVO) {
        ArrayList<ChatRoomVO> datas = new ArrayList<>();
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTALL_CHAT_ROOMS);
            pstmt.setString(1, chatRoomVO.getUser1Email());  // User1Email로 통일
            pstmt.setString(2, chatRoomVO.getUser1Email());  // 여기 똑같게 만들어줌
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ChatRoomVO data = new ChatRoomVO();
                data.setChatRoomId(rs.getInt("CHATROOM_ID"));
                data.setUser1Email(rs.getString("CHATROOM_MEMBER_EMAIL1"));
                data.setUser2Email(rs.getString("CHATROOM_MEMBER_EMAIL2"));
                datas.add(data);
            }
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // 5. 두 사용자의 채팅방 존재 여부 확인
    public ChatRoomVO getChatRoom(ChatRoomVO chatRoomVO) {
        ChatRoomVO resultVO = null;
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTONE_CHATROOM_BETWEEN_TWO_MEMBERS);
            pstmt.setString(1, chatRoomVO.getUser1Email()); // 첫 번째 조합
            pstmt.setString(2, chatRoomVO.getUser2Email());
            pstmt.setString(3, chatRoomVO.getUser2Email()); // 반대 조합
            pstmt.setString(4, chatRoomVO.getUser1Email());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                resultVO = new ChatRoomVO();
                resultVO.setChatRoomId(rs.getInt("CHATROOM_ID"));
                resultVO.setUser1Email(rs.getString("CHATROOM_MEMBER_EMAIL1"));
                resultVO.setUser2Email(rs.getString("CHATROOM_MEMBER_EMAIL2"));
            }
            return resultVO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }



    // 2. 새로운 채팅방 생성
    public boolean insert(ChatRoomVO chatRoomVO) {
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(INSERT_CHAT_ROOM);
            pstmt.setString(1, chatRoomVO.getUser1Email());
            pstmt.setString(2, chatRoomVO.getUser2Email());
            int result = pstmt.executeUpdate();
            System.out.println("chatroom insert 로그 : "+ result);
            if (result > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // 3. 마지막 메시지 갱신
    public boolean update(ChatRoomVO chatRoomVO) {
//        try {
//            conn = JDBCUtil.connect();
//            pstmt = conn.prepareStatement(UPDATE_LAST_MESSAGE);
//            pstmt.setString(1, chatRoomVO.getLastMessage());
//            pstmt.setLong(2, chatRoomVO.getChatRoomId());
//            pstmt.executeUpdate();
//            int result = pstmt.executeUpdate();
//            return result > 0; // 성공 여부 반환
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            JDBCUtil.disconnect(conn, pstmt);
//        }
        return false;
    }

    // 4. 채팅방 나가기(삭제)
    public boolean delete(ChatRoomVO chatRoomVO) {
//        try{
//            conn = JDBCUtil.connect();
//            pstmt = conn.prepareStatement(DELETE_ROOM);
//            pstmt.setLong(1, chatRoomVO.getChatRoomId());
//            int result = pstmt.executeUpdate();
//            return result > 0;
//        } catch (Exception e){
//            e.printStackTrace();
//            return false;
//        } finally {
//            JDBCUtil.disconnect(conn, pstmt);
//        }
//    }
        return false;
    }
}


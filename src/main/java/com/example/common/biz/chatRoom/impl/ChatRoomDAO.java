package com.example.common.biz.chatRoom.impl;



import com.example.common.JDBCUtil;
import com.example.common.biz.chatRoom.ChatRoomVO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository
public class ChatRoomDAO {
    // 로그인한 사용자가 참여 중인 모든 채팅방을 조회하는 쿼리문
    private final String SELECTALL_CHAT_ROOMS = "SELECT * FROM CHAT_ROOM WHERE CHATROOM_MEMBER_EMAIL1 = ? || CHATROOM_MEMBER_EMAIL2 = ? ORDER BY CHATROOM_ID DESC";
    // 새로운 채팅방을 생성하는 쿼리문
    private final String INSERT_CHAT_ROOM = "INSERT INTO CHAT_ROOM (CHATROOM_ID, USER1_EMAIL, USER2_EMAIL) VALUES ((SELECT NVL(MAX(CHAT_ROOM_ID), 0) + 1 FROM CHAT_ROOM), ?, ?)";
//    // 채팅방 나가기
//    private final String DELETE_ROOM = "DELETE FROM CHAT_ROOM WHERE CHAT_ROOM_ID = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // 1. 채팅방 목록 가져오기 (시간순)
    public ArrayList<ChatRoomVO> getChatRoomList(ChatRoomVO chatRoomVO) {
        ArrayList<ChatRoomVO> datas = new ArrayList<>();
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTALL_CHAT_ROOMS);
            pstmt.setString(1, chatRoomVO.getUser1Email());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ChatRoomVO data = new ChatRoomVO();
                data.setChatRoomId(rs.getLong("CHATROOM_ID"));
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

    // 2. 새로운 채팅방 생성
    public boolean insert(ChatRoomVO chatRoomVO) {
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(INSERT_CHAT_ROOM);
            pstmt.setString(1, chatRoomVO.getUser1Email());
            pstmt.setString(2, chatRoomVO.getUser2Email());
            int result = pstmt.executeUpdate();
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



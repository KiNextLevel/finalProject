package com.example.common.biz.chatMessage2.impl;

import com.example.common.JDBCUtil;
import com.example.common.biz.chatMessage2.ChatMessageVO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


@Repository
public class ChatMessageDAO {
    // 특정 채팅방(CHAT_ROOM_ID)에 속한 모든 메시지를 보낸 시간(SENT_TIME) 기준으로 오름차순 정렬하여 조회하는 쿼리문
    // 채팅방에 입장했을 때 과거부터 현재까지의 메시지를 시간 순서대로 출력하기 위해 사용됨
    private final String SELECTALL_CHAT_MESSAGES = "SELECT * FROM CHATMESSAGE WHERE CHATMESSAGE_CHATROOM_ID = ? ORDER BY CHATMESSAGE_DATE DESC";
    // 새로운 채팅 메시지를 CHAT_MESSAGE 테이블에 삽입하는 쿼리문
    private final String INSERT_CHAT_MESSAGE = "INSERT INTO CHATMESSAGE (CHATMESSAGE_ID, CHATMESSAGE_CHATROOM_ID, CHATMESSAGE_MEMBER_EMAIL1, CHATMESSAGE_CONTENT) VALUES ((SELECT NVL(MAX(CHATMESSAGE_ID), 0) + 1 FROM CHATMESSAGE), ?, ?, ?)";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // 1. 채팅방의 메시지 불러오기
    public ArrayList<ChatMessageVO> getChatMessageList(ChatMessageVO chatMessageVO) {
        ArrayList<ChatMessageVO> datas = new ArrayList<>();
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTALL_CHAT_MESSAGES);
            pstmt.setLong(1, chatMessageVO.getChatRoomId());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ChatMessageVO data = new ChatMessageVO();
                data.setMessageId(rs.getInt("CHATMESSAGE_ID"));
                data.setChatRoomId(rs.getInt("CHATMESSAGE_CHATROOM_ID"));
                data.setSenderEmail(rs.getString("CHATMESSAGE_MEMBER_EMAIL1"));
                data.setMessageContent(rs.getString("CHATMESSAGE_CONTENT"));
                data.setSentTime(rs.getTimestamp("CHATMESSAGE_DATE"));
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

    // 2. 메시지 추가
    public boolean insert(ChatMessageVO messageVO) {
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(INSERT_CHAT_MESSAGE);
            pstmt.setLong(1, messageVO.getChatRoomId());
            pstmt.setString(2, messageVO.getSenderEmail());
            pstmt.setString(3, messageVO.getMessageContent());
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
}


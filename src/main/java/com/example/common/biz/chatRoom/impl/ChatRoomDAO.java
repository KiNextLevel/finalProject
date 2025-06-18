//package com.example.common.biz.chatRoom.impl;
//
//
//import com.example.common.JDBCUtil;
//import com.example.common.biz.chatRoom.ChatRoomVO;
//import org.springframework.stereotype.Repository;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//
//@Repository
//public class ChatRoomDAO {
//    // 로그인한 사용자가 참여 중인 모든 채팅방을 조회하는 쿼리문
//    // 내 이메일, 상대 이메일, 닉네임도 나올 수 있도록 쿼리문 변경
//    private final String SELECTALL_CHAT_ROOMS =
//            "SELECT C.CHAT_ROOM_ID, C.USER1_EMAIL, C.USER2_EMAIL, " +
//                    "C.LAST_MESSAGE, C.LAST_TIME, C.CREATEDTIME_CHATTINGROOM, " +
//                    "U.MEMBER_NICKNAME AS OPPONENT_NICKNAME " +
//                    "FROM CHAT_ROOM C " +
//                    "JOIN MEMBER U ON (CASE WHEN C.USER1_EMAIL = ? THEN C.USER2_EMAIL ELSE C.USER1_EMAIL END) = U.MEMBER_EMAIL " +
//                    "WHERE C.USER1_EMAIL = ? OR C.USER2_EMAIL = ? " +
//                    "ORDER BY C.LAST_TIME DESC";
//
//    //"SELECT * FROM CHAT_ROOM WHERE USER1_EMAIL = ? OR USER2_EMAIL = ? ORDER BY LAST_TIME DESC";
//
//
//    // 채팅방 존재 여부 확인하는 쿼리문 추가
//    // 내 이메일과 상대 이메일,또는 내 이메일과 상대 이메일
//    private final String SELECTONE_ROOM_BY_USERS = "SELECT * FROM CHAT_ROOM WHERE (USER1_EMAIL = ? AND USER2_EMAIL = ?) OR (USER1_EMAIL = ? AND USER2_EMAIL = ?)";
//
//
//    // 새로운 채팅방을 생성하는 쿼리문
//    // 채팅방 아이디, 내 이메일, 상대 이메일, 채팅방 생성 시간
//    private final String INSERT_CHAT_ROOM = "INSERT INTO CHAT_ROOM (CHAT_ROOM_ID, USER1_EMAIL, USER2_EMAIL, CREATEDTIME_CHATTINGROOM) VALUES ((SELECT NVL(MAX(CHAT_ROOM_ID), 0) + 1 FROM CHAT_ROOM), ?, ?, CURRENT_TIMESTAMP)";
//    // 채팅방의 마지막 메시지와 해당 시간 정보를 업데이트하는 쿼리문
//    // 해당하는 채팅방의 마지막 메시지는?
//    private final String UPDATE_LAST_MESSAGE = "UPDATE CHAT_ROOM SET LAST_MESSAGE = ?, LAST_TIME = CURRENT_TIMESTAMP WHERE CHAT_ROOM_ID = ?";
//    // 채팅방 나가기
//    private final String DELETE_ROOM = "DELETE FROM CHAT_ROOM WHERE CHAT_ROOM_ID = ?";
//
//    Connection conn = null;
//    PreparedStatement pstmt = null;
//    ResultSet rs = null;
//
//    // 1. 채팅방 목록 가져오기 (시간순)
//    public ArrayList<ChatRoomVO> getChatRoomList(ChatRoomVO chatRoomVO) {
//        ArrayList<ChatRoomVO> datas = new ArrayList<>();
//        try {
//            conn = JDBCUtil.connect();
//            pstmt = conn.prepareStatement(SELECTALL_CHAT_ROOMS);
//            // 로그인한 사용자 이메일 한 개만 사용
//            String loginEmail = chatRoomVO.getUserEmail();
//            pstmt.setString(1, loginEmail); // CASE WHEN용
//            pstmt.setString(2, loginEmail); // WHERE
//            pstmt.setString(3, loginEmail); // WHERE
//
//            rs = pstmt.executeQuery();
//
//            // 현재 로그인한 사용자 이메일을 미리 저장
//            //String currentUserEmail = chatRoomVO.getUser1Email();
//
//            while (rs.next()) {
//                ChatRoomVO data = new ChatRoomVO();
//                String user1 = rs.getString("USER1_EMAIL");  // 추가
//                String user2 = rs.getString("USER2_EMAIL");  // 추가
//
//                data.setChatRoomId(rs.getLong("CHAT_ROOM_ID"));
//                data.setUser1Email(user1);   // 추가
//                data.setUser2Email(user2);  // 추가
//                data.setLastMessage(rs.getString("LAST_MESSAGE"));
//                data.setLastTime(rs.getTimestamp("LAST_TIME"));
//                data.setCreatedTimeChattingRoom(rs.getTimestamp("CREATEDTIME_CHATTINGROOM"));
//                data.setOpponentNickname(rs.getString("OPPONENT_NICKNAME")); // 추가된 닉네임
//                datas.add(data);
//
//                // 상대방 이메일도 계산해서 VO에 담기
//                String opponentEmail = loginEmail.equals(user1) ? user2 : user1;
//                data.setOpponentEmail(opponentEmail);
//
////                // 상대방 이메일 계산
////                String opponent = currentUserEmail.equals(data.getUser1Email())
////                        ? data.getUser2Email()
////                        : data.getUser1Email();
////                data.setOpponentEmail(opponent);
////
////                // 상대방 닉네임은 User 테이블에서 조회 (별도 DAO 호출 필요)
////                // data.setOpponentNickname(userDAO.getNicknameByEmail(opponent));
////                // 또는 지금은 임시로 이메일만 닉네임으로 넣어도 OK
////                data.setOpponentNickname(opponent);  // 상대방 이메일을 VO에 담기
//            }
//            return datas;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            JDBCUtil.disconnect(conn, pstmt);
//        }
//    }
//
//    //  채팅방 존재 여부 확인 추가 함
//    public ChatRoomVO getChatRoom(ChatRoomVO chatRoomVO) {
//        try {
//            conn = JDBCUtil.connect();
//            pstmt = conn.prepareStatement(SELECTONE_ROOM_BY_USERS);
//            pstmt.setString(1, chatRoomVO.getUser1Email());
//            pstmt.setString(2, chatRoomVO.getUser2Email());
//            pstmt.setString(3, chatRoomVO.getUser1Email());
//            pstmt.setString(4, chatRoomVO.getUser2Email());
//            rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                ChatRoomVO data = new ChatRoomVO();
//                data.setChatRoomId(rs.getLong("CHAT_ROOM_ID"));
//                data.setUser1Email(rs.getString("USER1_EMAIL"));
//                data.setUser2Email(rs.getString("USER2_EMAIL"));
//                data.setLastMessage(rs.getString("LAST_MESSAGE"));
//                data.setLastTime(rs.getTimestamp("LAST_TIME"));
//                data.setCreatedTimeChattingRoom(rs.getTimestamp("CREATEDTIME_CHATTINGROOM"));
//                return data;
//            }
//            return null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            JDBCUtil.disconnect(conn, pstmt);
//        }
//    }
//
//    // 2. 새로운 채팅방 생성
//    public boolean insert(ChatRoomVO chatRoomVO) {
//        try {
//            conn = JDBCUtil.connect();
//            pstmt = conn.prepareStatement(INSERT_CHAT_ROOM);
//            pstmt.setString(1, chatRoomVO.getUser1Email());
//            pstmt.setString(2, chatRoomVO.getUser2Email());
//            int result = pstmt.executeUpdate();
//            if (result > 0) {
//                return true;
//            }
//            return false;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            JDBCUtil.disconnect(conn, pstmt);
//        }
//    }
//
//    // 3. 마지막 메시지 갱신
//    public boolean update(ChatRoomVO chatRoomVO) {
//        try {
//            conn = JDBCUtil.connect();
//            pstmt = conn.prepareStatement(UPDATE_LAST_MESSAGE);
//            pstmt.setString(1, chatRoomVO.getLastMessage());
//            pstmt.setLong(2, chatRoomVO.getChatRoomId());
//            int result = pstmt.executeUpdate();
//            return result > 0; // 성공 여부 반환
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            JDBCUtil.disconnect(conn, pstmt);
//        }
//    }
//
//    // 4. 채팅방 나가기(삭제)
//    public boolean delete(ChatRoomVO chatRoomVO) {
//        try {
//            conn = JDBCUtil.connect();
//            pstmt = conn.prepareStatement(DELETE_ROOM);
//            pstmt.setLong(1, chatRoomVO.getChatRoomId());
//            int result = pstmt.executeUpdate();
//            return result > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            JDBCUtil.disconnect(conn, pstmt);
//        }
//    }
//}
//
//

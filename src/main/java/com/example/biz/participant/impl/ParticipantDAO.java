package com.example.biz.participant.impl;

import com.example.biz.participant.ParticipantVO;
import com.example.common.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ParticipantDAO {
    // 사용자가 참여한 이벤트 목록
    private final String SELECTALL = "SELECT B.*, P.PARTICIPANT_MEMBER_EMAIL " +
            " FROM BOARD B " +
            " JOIN PARTICIPANT P ON B.BOARD_NUM = P.PARTICIPANT_BOARD_NUM " +
            " WHERE P.PARTICIPANT_MEMBER_EMAIL = ? " +
            " ORDER BY B.BOARD_NUM DESC";

    // 사용자가 참여한 이벤트 목록
    private final String SELECTALL_EVENTPRINT =
            "SELECT B.BOARD_TITLE " +
                    " FROM PARTICIPANT P " +
                    " JOIN BOARD B ON P.PARTICIPANT_BOARD_NUM = B.BOARD_NUM " +
                    " WHERE P.PARTICIPANT_MEMBER_EMAIL = ?";
    // 이벤트에 참여중인 사용자 수
    private final String SELECTONE = "SELECT COUNT(P.PARTICIPANT_MEMBER_EMAIL) " +
            " FROM PARTICIPANT P " +
            " JOIN BOARD B ON P.PARTICIPANT_BOARD_NUM = B.BOARD_NUM " +
            " WHERE B.BOARD_NUM = ?";
    // 이벤트 참여하기
    private final String INSERT = "INSERT INTO PARTICIPANT (PARTICIPANT_BOARD_NUM, PARTICIPANT_MEMBER_EMAIL) " +
            " VALUES (?, ?)";
    // 참여한 이벤트 취소하기
    private final String DELETE = "DELETE FROM PARTICIPANT " +
            " WHERE PARTICIPANT_BOARD_NUM = ? " +
            " AND PARTICIPANT_MEMBER_EMAIL = ?";
    // 이벤트 삭제
    private final String DELETE_BOARD_NUM = "DELETE FROM PARTICIPANT " +
            " WHERE PARTICIPANT_BOARD_NUM = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // 사용자가 참여한 이벤트 목록
    public ArrayList<ParticipantVO> getParticipantList(ParticipantVO ParticipantVO) {
        ArrayList<ParticipantVO> list = new ArrayList<>();
        try {
            conn = JDBCUtil.connect();

            // 이벤트 페이지에서 보여지는 참가중인 이벤트 목록
            // 이벤트의 제목, 내용, 작성일 전부 다 가져옴
            if (ParticipantVO.getCondition().equals("SELECTALL")) {
                pstmt = conn.prepareStatement(SELECTALL);
                pstmt.setString(1, ParticipantVO.getParticipantUserEmail());
            }
            // 마이페이지에서 보여지는 사용자가 참가중인 이벤트 목록
            // 이벤트의 제목만 가져옴
            else if (ParticipantVO.getCondition().equals("SELECTALL_EVENTPRINT")) {
                pstmt = conn.prepareStatement(SELECTALL_EVENTPRINT);
                pstmt.setString(1, ParticipantVO.getParticipantUserEmail());
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ParticipantVO dto = new ParticipantVO();
                if (ParticipantVO.getCondition().equals("SELECTALL")) {
                    dto.setParticipantBoardNumber(rs.getInt("BOARD_NUM"));
                    dto.setParticipantUserEmail(rs.getString("PARTICIPANT_USER_EMAIL"));
                }   if (ParticipantVO.getCondition().equals("SELECTALL_EVENTPRINT")) {
                    dto.setBoardTitle(rs.getString("BOARD_TITLE"));
                }
                list.add(dto);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // 현재 참여중인 회원 수(COUNT)
    public ParticipantVO getParticipant(ParticipantVO ParticipantVO){
        ParticipantVO list = new ParticipantVO(); // null이 아닌 새 객체 생성
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTONE);
            pstmt.setInt(1, ParticipantVO.getParticipantBoardNumber());
            rs = pstmt.executeQuery();
            if(rs.next()){
                // COUNT 결과를 ParticipantBoardNumber에 저장, 참가한 USER_EMAIL도 저장
                list.setParticipantBoardNumber(rs.getInt(1));
            } else {
                // 결과가 없으면 0으로 설정
                list.setParticipantBoardNumber(0);
            }
            return list;
        } catch (Exception e){
            e.printStackTrace();
            // 오류 발생 시에도 기본값을 가진 객체 반환
            ParticipantVO errorDto = new ParticipantVO();
            errorDto.setParticipantBoardNumber(0);
            return errorDto;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // 참가하기
    public boolean insert(ParticipantVO ParticipantVO){
        ParticipantDAO list = null;
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(INSERT);
            pstmt.setInt(1, ParticipantVO.getParticipantBoardNumber());
            pstmt.setString(2, ParticipantVO.getParticipantUserEmail());
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // x
    private boolean update(ParticipantVO ParticipantVO){
        return false;
    }

    // 참가 취소
    public boolean delete(ParticipantVO ParticipantVO){
        try {
            conn = JDBCUtil.connect();
            //이벤트 삭제했을 때
            if(ParticipantVO.getCondition().equals("DELETE_BOARD_NUM")) {
                pstmt = conn.prepareStatement(DELETE_BOARD_NUM);
                pstmt.setInt(1, ParticipantVO.getParticipantBoardNumber());
            }
            //참가 취소했을 때
            if(ParticipantVO.getCondition().equals("DELETE")){
                pstmt = conn.prepareStatement(DELETE);
                pstmt.setInt(1, ParticipantVO.getParticipantBoardNumber());
                pstmt.setString(2, ParticipantVO.getParticipantUserEmail());
            }
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }
}


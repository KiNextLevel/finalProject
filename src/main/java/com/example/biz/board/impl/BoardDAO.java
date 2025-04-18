package com.example.biz.board.impl;

import com.example.biz.board.BoardVO;
import com.example.common.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO {
    // 이벤트 내용 전부 다 불러오기
    private String SELECTALL = "SELECT " +
            " B.BOARD_NUM," +
            " B.BOARD_TITLE," +
            " B.BOARD_CONTENT," +
            " B.BOARD_DATE," +
            " B.BOARD_LIMIT," +
            " COUNT(P.PARTICIPANT_MEMBER_EMAIL) AS CNT," +
            " MAX(CASE WHEN P.PARTICIPANT_MEMBER_EMAIL = ? THEN 1 ELSE 0 END) AS IS_PARTICIPANT " +
            "FROM BOARD B " +
            "LEFT JOIN PARTICIPANT P " +
            "    ON B.BOARD_NUM = P.PARTICIPANT_BOARD_NUM " +
            "GROUP BY " +
            "    B.BOARD_NUM, B.BOARD_TITLE, B.BOARD_CONTENT, B.BOARD_DATE, B.BOARD_LIMIT " +
            "ORDER BY B.BOARD_NUM DESC ";

    // 이벤트 게시판 들어가기
    private String SELECTONE = "SELECT * FROM BOARD WHERE BOARD_NUM = ?";

    // 이벤트 추가하기
    private String INSERT = "INSERT INTO BOARD (BOARD_NUM, BOARD_TITLE, BOARD_CONTENT, BOARD_LIMIT) VALUES (?, ?, ?)";

    // 이벤트 수정
    private String UPDATE_BOARD = "UPDATE BOARD SET BOARD_TITLE = ?, BOARD_CONTENT = ?, BOARD_LIMIT = ? WHERE BOARD_NUM = ?";

    // 쓰일지 안 쓰일지 몰라 일단 추가
    // 이벤트 수정 경우의 수 3가지
    private String UPDATE_TITLE = "UPDATE BOARD SET BOARD_TITLE = ? WHERE BOARD_NUM = ?";
    private String UPDATE_CONTENT = "UPDATE BOARD SET BOARD_CONTENT = ? WHERE BOARD_NUM = ?";
    private String UPDATE_LIMIT = "UPDATE BOARD SET BOARD_LIMIT = ? WHERE BOARD_NUM = ?";

    // 이벤트 삭제하기
    private String DELETE = "DELETE FROM BOARD WHERE BOARD_NUM = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // 이벤트 내용 전부 다 불러오기
    public ArrayList<BoardVO> getBoardList(BoardVO BoardVO) {
        ArrayList<BoardVO> datas = new ArrayList<>();
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTALL);
            pstmt.setString(1, BoardVO.getSearchKeyword());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BoardVO data = new BoardVO();
                data.setBoardNumber(rs.getInt("BOARD_NUM"));
                data.setBoardTitle(rs.getString("BOARD_TITLE"));
                data.setBoardContent(rs.getString("BOARD_CONTENT"));
                data.setBoardLimit(rs.getInt("BOARD_LIMIT"));
                data.setBoardParticipant(rs.getInt("CNT"));
                data.setParticipant(rs.getInt("IS_PARTICIPANT"));
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

    // 이벤트 게시판 하나 들어가기
    public BoardVO getBoard(BoardVO BoardVO) {
        BoardVO datas = null;
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTONE);
            pstmt.setInt(1, BoardVO.getBoardNumber());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                datas = new BoardVO();  // 이제 올바른 타입으로 객체 생성
                datas.setBoardNumber(rs.getInt("BOARD_NUM"));
                datas.setBoardTitle(rs.getString("BOARD_TITLE"));
                datas.setBoardContent(rs.getString("BOARD_CONTENT"));
                datas.setBoardLimit(rs.getInt("BOARD_LIMIT"));
            }
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // 이벤트 게시판 글 작성
    public boolean insert(BoardVO BoardVO) {
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(INSERT);
            pstmt.setString(1, BoardVO.getBoardTitle());
            pstmt.setString(2, BoardVO.getBoardContent());
            pstmt.setInt(3, BoardVO.getBoardLimit());
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


    // 이벤트 게시판 글 수정
    public boolean update(BoardVO BoardVO) {
        try {
            conn = JDBCUtil.connect();

            // 제목 수정이 필요한 경우
            if (BoardVO.getBoardTitle() != null && !BoardVO.getBoardTitle().equals("UPDATE_TITLE")) {
                pstmt = conn.prepareStatement(UPDATE_TITLE);
                pstmt.setString(1, BoardVO.getBoardTitle());
                pstmt.setInt(2, BoardVO.getBoardNumber());
                pstmt.executeUpdate();
                pstmt.close();
            }

            // 내용 수정이 필요한 경우
            if (BoardVO.getBoardContent() != null && !BoardVO.getBoardContent().equals("UPDATE_CONTENT")) {
                pstmt = conn.prepareStatement(UPDATE_CONTENT);
                pstmt.setString(1, BoardVO.getBoardContent());
                pstmt.setInt(2, BoardVO.getBoardNumber());
                pstmt.executeUpdate();
                pstmt.close();
            }

            // 제한 수정이 필요한 경우
            if (BoardVO.getBoardLimit() != 0 && BoardVO.getBoardLimit() != BoardVO.getBoardLimit()) {
                pstmt = conn.prepareStatement(UPDATE_LIMIT);
                pstmt.setInt(1, BoardVO.getBoardLimit());
                pstmt.setInt(2, BoardVO.getBoardNumber());
                pstmt.executeUpdate();
                pstmt.close();
            }
            if(BoardVO.getCondition().equals("UPDATE_BOARD")){
                pstmt = conn.prepareStatement(UPDATE_BOARD);
                pstmt.setString(1, BoardVO.getBoardTitle());
                pstmt.setString(2, BoardVO.getBoardContent());
                pstmt.setInt(3, BoardVO.getBoardLimit());
                pstmt.setInt(4, BoardVO.getBoardNumber());
                pstmt.executeUpdate();
                pstmt.close();
            }
            return true; // 수정 성공 (수정할 내용이 없어도 성공으로 간주)
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // 이벤트 게시판 글 삭제
    public boolean delete(BoardVO BoardVO) {
        System.out.println("deleteBoard dao 로그");
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(DELETE);
            pstmt.setInt(1, BoardVO.getBoardNumber());

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

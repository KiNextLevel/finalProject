package com.example.biz.alert.impl;

import com.example.biz.alert.AlertVO;
import com.example.common.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlertDAO {
    // 00유저의 알림번호, 알림내용, 알림 날짜, 알림여부 불러오기
    // 사용자의 알림이 여러개가 있을 수 있으니까 SELECTALL
    private final String SELECTALL = "SELECT ALERT_NUM, ALERT_CONTENT, ALERT_DATE, ALERT_ISWATCH FROM ALERT WHERE ALERT_USER_EMAIL = ? ORDER BY ALERT_NUM DESC";

    private final String SELECTONE = ""; // 기능 없음

    // (관리자) - 유저에게 경고 알림 보내기
    // 유저 이메일, 내용, 알림보낸날짜, 읽음 여부
    private final String INSERT = "INSERT INTO ALERT (ALERT_USER_EMAIL, ALERT_CONTENT, ALERT_DATE, ALERT_ISWATCH) "
            + "VALUES (?, ?, NOW(), false)";

    // 유저 알림 열람여부(읽음, 안읽음) 0 == 안읽음, 1 == 읽음
    // 한 알림만 읽음 처리해야 하기 때문에, WHERE ALERT_NUM
    private final String UPDATE_ISWATCH = "UPDATE ALERT SET ALERT_ISWATCH = 1 WHERE ALERT_NUM = ? "; //1(읽음)으로 바꾸기

    private final String UPDATE = ""; // 기능 없음

    private final String DELETE = ""; // 기능 없음

    public List<AlertVO> selectAlertList(AlertVO AlertVO) {
        List<AlertVO> datas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTALL);
            pstmt.setString(1, AlertVO.getUserEmail()); // 유저 이메일 추가
            rs = pstmt.executeQuery();
            while (rs.next()) {
                AlertVO data = new AlertVO();
                data.setAlertNumber(rs.getInt("ALERT_NUM")); // 알림 번호
                data.setAlertDate(rs.getDate("ALERT_DATE"));  //알림 날짜 추가
                data.setAlertContent(rs.getString("ALERT_CONTENT"));  //알림 내용
                data.setAlertIsWatch(rs.getBoolean("ALERT_ISWATCH"));  //알림 읽음 여부
                datas.add(data);
            }
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            JDBCUtil.disconnect(conn, pstmt);
        }
    }
    // 기능 없음
    private
    AlertVO selectAlert(AlertVO AlertVO) {
        throw new UnsupportedOperationException("단일 알림 조회는 제공되지 않습니다.");
    }

    public boolean insert(AlertVO AlertVO) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(INSERT);
            pstmt.setString(1, AlertVO.getUserEmail()); // 알림 대상 유저
            pstmt.setString(2, AlertVO.getAlertContent()); // 알림 내용

            int result = pstmt.executeUpdate();
            return result > 0; // 성공하면 true

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    public boolean update(AlertVO AlertVO) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(UPDATE_ISWATCH);
            //pstmt.setBoolean(1, AlertVO.isAlertIsWatch()); // 0 (안 읽음) 또는 1 (읽음)
            pstmt.setInt(1, AlertVO.getAlertNumber()); //  ALERT_NUM (PK)로 특정 알림 지정

            int result = pstmt.executeUpdate();
            return result > 0; // 1개 이상 업데이트되면 true

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    private boolean delete(AlertVO AlertVO) {
        throw new UnsupportedOperationException("삭제 기능은 제공되지 않습니다.");
    }
}

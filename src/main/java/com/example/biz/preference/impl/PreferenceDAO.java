package com.example.biz.preference.impl;

import com.example.biz.preference.PreferenceVO;
import com.example.common.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PreferenceDAO {
    // 모든 사용자의 선호키, 체형, 나이 전체 출력
    private final String SELECTALL =
            " SELECT " +
                    " M.MEMBER_EMAIL, " +
                    " M.MEMBER_NAME, " +
                    " P.PREFERENCE_HEIGHT, " +
                    " P.PREFERENCE_BODY, " +
                    " P.PREFERENCE_AGE " +
                    " FROM PREFERENCE P " +
                    " JOIN MEMBER M ON P.PREFERENCE_MEMBER_EMAIL = M.MEMBER_EMAI";

    // 유저 마이페이지 - 00 유저의 선호 키, 체형, 나이 출력
    private final String SELECTONE = "SELECT " +
            " PREFERENCE_HEIGHT, " +
            " PREFERENCE_BODY, " +
            " PREFERENCE_AGE " +
            " FROM PREFERENCE " +
            " WHERE PREFERENCE_MEMBER_EMAIL = ?";

    // 선호키, 체형, 나이 추가해줘(이메일 필수)
    private final String INSERT = "INSERT INTO PREFERENCE " +
            " (PREFERENCE_MEMBER_EMAIL, PREFERENCE_HEIGHT, PREFERENCE_BODY, PREFERENCE_AGE) " +
            " VALUES (?, ?, ?, ?)";

    // 00 유저의 선호키, 체형, 나이 수정
    private final String UPDATE = "UPDATE PREFERENCE " +
            " SET " +
            " PREFERENCE_HEIGHT = ?, " +
            " PREFERENCE_BODY = ?, " +
            " PREFERENCE_AGE = ? " +
            " WHERE PREFERENCE_MEMBER_EMAIL = ?";

    // 삭제 기능은 사용자의 선호 선택이 필수이기 때문에 제공하지 않음
    private final String DELETE = "";

    public ArrayList<PreferenceVO> getPreferenceList(PreferenceVO PreferenceVO) {
        ArrayList<PreferenceVO> datas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null; // ResultSet 추가(scope 이슈)
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTALL);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                PreferenceVO data = new PreferenceVO();
                data.setUserEmail(rs.getString("USER_EMAIL"));  //유저 메일
                data.setUserName(rs.getString("USER_NAME"));   //유저 이름
                data.setPreferenceHeight(rs.getInt("PREFERENCE_HEIGHT"));  //유저 키
                data.setPreferenceBody(rs.getString("PREFERENCE_BODY"));  //유저 체형
                data.setPreferenceAge(rs.getString("PREFERENCE_AGE"));    // 유저 나이
                datas.add(data);
            }

            return datas;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close(); // ResultSet 닫기
            } catch (Exception e) {
                e.printStackTrace();
            }
            JDBCUtil.disconnect(conn, pstmt);
        }
        return datas;
    }


    public PreferenceVO getPreference(PreferenceVO PreferenceVO) {
        PreferenceVO data = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null; // ResultSet 추가(scope 이슈)

        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTONE);
            pstmt.setString(1, PreferenceVO.getUserEmail());

            rs = pstmt.executeQuery();// set결과 =쿼리실행
            if (rs.next()) {
                data = new PreferenceVO();
                data.setPreferenceHeight(rs.getInt("PREFERENCE_HEIGHT"));  // 유저 취향 키
                data.setPreferenceBody(rs.getString("PREFERENCE_BODY"));   // 유저 취향 체형
                data.setPreferenceAge(rs.getString("PREFERENCE_AGE"));     // 유저 취향 나이

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close(); // ResultSet 닫기
            } catch (Exception e) {
                e.printStackTrace();
            }
            JDBCUtil.disconnect(conn, pstmt);
        }
        return data;
    }

    public boolean insert(PreferenceVO PreferenceVO) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtil.connect();

            pstmt = conn.prepareStatement(INSERT);
            pstmt.setString(1, PreferenceVO.getUserEmail());       // 유저 메일
            pstmt.setInt(2, PreferenceVO.getPreferenceHeight());   // 유저 취향 키
            pstmt.setString(3, PreferenceVO.getPreferenceBody());  // 유저 취향 체형
            pstmt.setString(4, PreferenceVO.getPreferenceAge());   // 유저 취향 나이
            pstmt.executeUpdate();

            return true; // 수정 성공 (수정할 내용이 없어도 성공으로 간주)
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    public boolean update(PreferenceVO PreferenceVO) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(UPDATE);

            pstmt.setInt(1, PreferenceVO.getPreferenceHeight());  // 유저 취향 키
            pstmt.setString(2, PreferenceVO.getPreferenceBody()); // 유저 취향 체형
            pstmt.setString(3, PreferenceVO.getPreferenceAge());  // 유저 취향 나이
            pstmt.setString(4, PreferenceVO.getUserEmail()); // WHERE 조건 값 설정

            int result = pstmt.executeUpdate(); // 실행 후 변경된 행 수 반환
            return result > 0; //  0이면 실패, 1 이상이면 성공

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }
    // 삭제 기능은 필요하지 않기 때문에 구현하지 않음
    private boolean delete(PreferenceVO PreferenceVO) {
        throw new UnsupportedOperationException("삭제 기능은 제공되지 않습니다.");
    }

}

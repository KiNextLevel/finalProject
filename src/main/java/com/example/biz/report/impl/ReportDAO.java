package com.example.biz.report.impl;

import org.springframework.stereotype.Repository;

import com.example.common.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository("reportDAO")
public class ReportDAO {
    // (관리자용) 00유저 신고자, 신고사유, 신고날짜, 00유저 피신고자, 신고 설명 전체 출력하기
    private final String SELECTALL = "SELECT REPORT_NUM, REPORT_REPORTER, REPORT_REASON, REPORT_DATE, REPORT_REPORTED, REPORT_DESCRIPTION "
            + "FROM REPORT ORDER BY REPORT_NUM DESC";

    // (유저용) - 마이페이지 상품명, 결제일, 결제 금액
    private final String SELECTONE = "SELECT REPORT_REPORTER FROM REPORT WHERE REPORT_REPORTER = ? AND REPORT_REPORTED = ?";

    // (유저용) 사용자가 또 다른 사용자를 신고하는 쿼리문(신고자, 신고이유, 신고날짜, 피신고자, 신고설명)
    private final String INSERT = "INSERT INTO REPORT (REPORT_NUM, REPORT_REPORTER, REPORT_REASON, REPORT_DATE, REPORT_REPORTED, REPORT_DESCRIPTION) " +
        "VALUES (NVL((SELECT MAX(REPORT_NUM)+1 FROM REPORT), 1), ?, ?, SYSDATE, ?, ?)";

    private final String UPDATE = "";

    // (관리자용) 블랙리스트된 00 유저 삭제하기
    private final String DELETE = "DELETE FROM REPORT WHERE REPORT_REPORTED = ?";
    //(관리자용) 경고 보내면 그 신고건 하나만 삭제하기
    private final String DELETE_ONE = "DELETE FROM REPORT WHERE REPORT_NUM = ?";

    public ArrayList<ReportVO> getReportList(ReportVO reportVO) {
        ArrayList<ReportVO> datas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ReportVO data = new ReportVO();
                data.setReportNumber(rs.getInt("REPORT_NUM"));
                data.setReportReported(rs.getString("REPORT_REPORTED"));
                data.setReportReason(rs.getString("REPORT_REASON"));
                data.setReportDate(rs.getDate("REPORT_DATE"));
                data.setReportReporter(rs.getString("REPORT_REPORTER"));
                data.setReportDescription(rs.getString("REPORT_DESCRIPTION"));
                datas.add(data);
            }
            return datas;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    public ReportVO getReport(ReportVO reportVO) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ReportVO data = null;
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTONE);
            pstmt.setString(1, reportVO.getReportReporter());
            pstmt.setString(2, reportVO.getReportReported());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                data = new ReportVO();
                data.setReportReported(rs.getString("REPORT_REPORTER"));
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    public boolean insert(ReportVO reportVO) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(INSERT);
            pstmt.setString(1, reportVO.getReportReporter()); // 신고자
            pstmt.setString(2, reportVO.getReportReason()); // 신고 이유
            pstmt.setString(3, reportVO.getReportReported()); // 피신고자
            pstmt.setString(4, reportVO.getReportDescription()); // 신고 상세 설명

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    private boolean update(ReportVO reportVO) {
        return false;
    }

    public boolean delete(ReportVO reportVO) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtil.connect();
            if(reportVO.getCondition().equals("DELETE")) {
                pstmt = conn.prepareStatement(DELETE);
                pstmt.setString(1, reportVO.getReportReported());
            }
            if(reportVO.getCondition().equals("DELETE_ONE")){
                pstmt = conn.prepareStatement(DELETE_ONE);
                pstmt.setInt(1, reportVO.getReportNumber());
            }
            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }
}

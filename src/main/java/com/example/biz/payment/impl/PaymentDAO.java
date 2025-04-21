package com.example.biz.payment.impl;

import com.example.biz.payment.PaymentVO;
import com.example.common.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PaymentDAO {
    // 결제 번호, 유저 이메일, 이름 , 상품명, 결제금액, 결제 날짜 조회하기
    private final String SELECTALL_ADMIN_PAYMENTS = "SELECT " +
            " P.PAYMENT_NUM," +
            " M.MEMBER_EMAIL," +
            " M.MEMBER_NAME, " +
            " PR.PRODUCT_NAME," +
            " P.PAYMENT_PRICE," +
            " P.PAYMENT_DATE "+
            " FROM PAYMENT P" +
            " JOIN MEMBER M ON P.PAYMENT_MEMBER_EMAIL = M.MEMBER_EMAIL" +
            " JOIN PRODUCT PR ON P.PRODUCT_NUM = PR.PRODUCT_NUM " +
            " ORDER BY P.PAYMENT_DATE DESC";
    // 추가하기(일별, 월별, 연도별 매출 조회

    // 유저 마이페이지 - 결제한 상품명, 결제일, 결제 날짜
    private final String SELECTALL_PRODUCTLIST =
            "SELECT" +
                    "  P.PRODUCT_NAME," +
                    "  P.PRODUCT_PRICE," +
                    "  M.PAYMENT_DATE " +
                    " FROM PAYMENT M " +
                    " JOIN PRODUCT P ON M.PRODUCT_NUM = P.PRODUCT_NUM " +
                    " WHERE M.PAYMENT_MEMBER_EMAIL = ? " +
                    " ORDER BY M.PAYMENT_DATE DESC";


    // 사용자 결제 내역 저장하기
    // 유저 이메일, 금액, 결제 날짜, 결제 방법, 상품 번호
    private final String INSERT = "INSERT INTO PAYMENT " +
            " (PAYMENT_MEMBER_EMAIL, PAYMENT_PRICE, PAYMENT_DATE, PAYMENT_TYPE, PRODUCT_NUM) " +
            " VALUES (?, ?, CURRENT_TIMESTAMP, ?, ?)";

    private final String UPDATE = "";
    private final String DELETE = "";

    public ArrayList<PaymentVO> getPaymentList(PaymentVO PaymentVO) {
        ArrayList<PaymentVO> datas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.connect();

            // 조건 분기
            if ("SELECTALL_ADMIN_PAYMENTS".equals(PaymentVO.getCondition())) {
                pstmt = conn.prepareStatement(SELECTALL_ADMIN_PAYMENTS);
            } else if ("SELECTALL_PRODUCTLIST".equals(PaymentVO.getCondition())) {
                pstmt = conn.prepareStatement(SELECTALL_PRODUCTLIST);
                pstmt.setString(1, PaymentVO.getUserEmail());
            } else {
                throw new IllegalArgumentException("알 수 없는 condition입니다: " + PaymentVO.getCondition());
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                PaymentVO data = new PaymentVO();

                // 관리자 전용 데이터 컬럼들
                if ("SELECTALL_ADMIN_PAYMENTS".equals(PaymentVO.getCondition())) {
                    data.setPaymentNumber(rs.getInt("PAYMENT_NUM"));
                    data.setUserEmail(rs.getString("USER_EMAIL"));
                    data.setUserName(rs.getString("USER_NAME"));
                    data.setProductName(rs.getString("PRODUCT_NAME"));
                    data.setPaymentPrice(rs.getInt("PAYMENT_PRICE"));
                    data.setPaymentDate(rs.getDate("PAYMENT_DATE"));
                }
                if ("SELECTALL_PRODUCTLIST".equals(PaymentVO.getCondition())) {
                    data.setProductName(rs.getString("PRODUCT_NAME"));
                    data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
                    //   data.setPaymentPrice(rs.getInt("PAYMENT_PRICE"));
                    data.setPaymentDate(rs.getTimestamp("PAYMENT_DATE"));
                }

                datas.add(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }

        return datas;
    }


    public boolean insert(PaymentVO PaymentVO) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtil.connect();

            pstmt = conn.prepareStatement(INSERT);
            pstmt.setString(1, PaymentVO.getUserEmail());
            pstmt.setInt(2, PaymentVO.getPaymentPrice());
            pstmt.setString(3, PaymentVO.getPaymentType());
            pstmt.setInt(4, PaymentVO.getProductNumber());

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

    private boolean update(PaymentVO PaymentVO) {
        return false;
    }

    private boolean delete(PaymentVO PaymentVO) {
        return false;
    }
}
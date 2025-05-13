package com.example.common.biz.payment.impl;

import com.example.common.biz.payment.PaymentVO;
import com.example.common.JDBCUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentTemplateDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 결제 번호, 유저 이메일, 이름 , 상품명, 결제금액, 결제 날짜 조회하기
    private final String SELECTALL_ADMIN_PAYMENTS = "SELECT " +
            " P.PAYMENT_NUM," +
            " M.MEMBER_EMAIL," +
            " M.MEMBER_NAME, " +
            " PR.PRODUCT_NAME," +
            " P.PAYMENT_PRICE," +
            " P.PAYMENT_DATE " +
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
            " (PAYMENT_NUM, PAYMENT_MEMBER_EMAIL, PAYMENT_PRICE, PAYMENT_DATE, PAYMENT_TYPE, PRODUCT_NUM) " +
            " VALUES (NVL((SELECT MAX(PAYMENT_NUM) + 1 FROM PAYMENT), 1), ?, ?, CURRENT_TIMESTAMP, ?, ?)";


    private final String UPDATE = "";
    private final String DELETE = "";

    public List<PaymentVO> getPaymentList(PaymentVO PaymentVO) {
        // 조건 분기
        if ("SELECTALL_ADMIN_PAYMENTS".equals(PaymentVO.getCondition())) {
            return jdbcTemplate.query(SELECTALL_ADMIN_PAYMENTS, new PaymentRowMapper());
        } else if ("SELECTALL_PRODUCTLIST".equals(PaymentVO.getCondition())) {
            Object[] args = {PaymentVO.getUserEmail()};
            return jdbcTemplate.query(SELECTALL_PRODUCTLIST, args, new UserPaymentRowMapper());
        } else {
            throw new IllegalArgumentException("알 수 없는 condition입니다: " + PaymentVO.getCondition());
        }
    }

    public boolean insert(PaymentVO PaymentVO) {
        int result = jdbcTemplate.update(INSERT, PaymentVO.getUserEmail(),
                PaymentVO.getPaymentPrice(), PaymentVO.getPaymentType(), PaymentVO.getProductNumber());
        if (result <= 0) {
            return false;
        }
        return true;
    }

    private boolean update(PaymentVO PaymentVO) {
        return false;
    }

    private boolean delete(PaymentVO PaymentVO) {
        return false;
    }
}

class PaymentRowMapper implements RowMapper<PaymentVO> {

    @Override
    public PaymentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        PaymentVO data = new PaymentVO();
        data.setPaymentNumber(rs.getInt("PAYMENT_NUM"));
        data.setUserEmail(rs.getString("MEMBER_EMAIL"));
        data.setUserName(rs.getString("MEMBER_NAME"));
        data.setProductName(rs.getString("PRODUCT_NAME"));
        data.setPaymentPrice(rs.getInt("PAYMENT_PRICE"));
        data.setPaymentDate(rs.getDate("PAYMENT_DATE"));
        return data;
    }

}
class UserPaymentRowMapper implements RowMapper<PaymentVO> {
    @Override
    public PaymentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        PaymentVO data = new PaymentVO();
        data.setProductName(rs.getString("PRODUCT_NAME"));
        data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
        data.setPaymentDate(rs.getDate("PAYMENT_DATE"));
        return data;
    }
}
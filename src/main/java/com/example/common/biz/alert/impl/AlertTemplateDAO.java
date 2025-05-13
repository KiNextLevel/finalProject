package com.example.common.biz.alert.impl;

import com.example.common.biz.alert.AlertVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository("alertDAO")
public class AlertTemplateDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 00유저의 알림번호, 알림내용, 알림 날짜, 알림여부 불러오기
    // 사용자의 알림이 여러개가 있을 수 있으니까 SELECTALL
    private final String SELECTALL = "SELECT ALERT_NUM, ALERT_CONTENT, ALERT_DATE, ALERT_ISWATCH FROM ALERT WHERE ALERT_MEMBER_EMAIL = ? ORDER BY ALERT_NUM DESC";

    private final String SELECTONE = ""; // 기능 없음

    // (관리자) - 유저에게 경고 알림 보내기
    // 유저 이메일, 내용, 알림보낸날짜, 읽음 여부
    private final String INSERT = "INSERT INTO ALERT (ALERT_NUM, ALERT_MEMBER_EMAIL, ALERT_CONTENT, ALERT_DATE, ALERT_ISWATCH) "
            + "VALUES (NVL((SELECT MAX(ALERT_NUM)+1 FROM ALERT), 1),?, ?, CURRENT_TIMESTAMP, 0)";

    // 유저 알림 열람여부(읽음, 안읽음) 0 == 안읽음, 1 == 읽음
    // 한 알림만 읽음 처리해야 하기 때문에, WHERE ALERT_NUM
    private final String UPDATE_ISWATCH = "UPDATE ALERT SET ALERT_ISWATCH = 1 WHERE ALERT_NUM = ? "; //1(읽음)으로 바꾸기

    private final String UPDATE = ""; // 기능 없음

    private final String DELETE = ""; // 기능 없음

    public ArrayList<AlertVO> getAlertList(AlertVO AlertVO) {
        Object[] args = { AlertVO.getUserEmail() };
        return (ArrayList<AlertVO>) jdbcTemplate.query(SELECTALL, args, new AlertRowMapper() {});
    }
    // 기능 없음
    private AlertVO selectOne(AlertVO AlertVO) {
        throw new UnsupportedOperationException("단일 알림 조회는 제공되지 않습니다.");
    }

    public boolean insert(AlertVO AlertVO) {
        return jdbcTemplate.update(INSERT, AlertVO.getUserEmail(), AlertVO.getAlertContent()) == 1;
    }

    public boolean update(AlertVO AlertVO) {
        return jdbcTemplate.update(UPDATE_ISWATCH, AlertVO.getAlertNumber()) == 1;
    }

    boolean delete(AlertVO AlertVO) {
        throw new UnsupportedOperationException("삭제 기능은 제공되지 않습니다.");
    }
    public AlertVO getAlert(AlertVO AlertVO) {
        return null;
    }
}

class AlertRowMapper implements RowMapper<AlertVO> {
    @Override
    public AlertVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        AlertVO data = new AlertVO();
        data.setAlertNumber(rs.getInt("ALERT_NUM")); // 알림 번호
        data.setAlertDate(rs.getDate("ALERT_DATE"));  //알림 날짜 추가
        data.setAlertContent(rs.getString("ALERT_CONTENT"));  //알림 내용
        data.setAlertIsWatch(rs.getBoolean("ALERT_ISWATCH"));  //알림 읽음 여부
        data.setAlertDate(rs.getDate("ALERT_DATE"));    //알림 날짜
        return data;
    }
}


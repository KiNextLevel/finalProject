package com.example.common.biz.visitor.impl;

import com.example.common.biz.visitor.VisitorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VisitorDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    // 쿼리문
    // 중복검사
    private final static String GETONE = "SELECT COUNT(*)" +
            " FROM VISITOR WHERE VISITOR_MEMBER_EMAIL = ? AND VISITOR_DATE = TRUNC(SYSDATE)";
    // 오늘 방문자 수
    private final static String GETONE_TODAY= "SELECT COUNT(DISTINCT VISITOR_MEMBER_EMAIL) AS today_visitor_count " +
            "FROM VISITOR " +
            "WHERE VISITOR_DATE = TRUNC(SYSDATE)";

    // 일별 방문자 수(남녀 구분)
    private final static String GETALL_DAILY =
            "SELECT V.VISITOR_DATE, M.MEMBER_GENDER, COUNT(DISTINCT V.VISITOR_MEMBER_EMAIL) AS daily_visitor_count " +
                    "FROM VISITOR V " +
                    "JOIN MEMBER M ON V.VISITOR_MEMBER_EMAIL = M.MEMBER_EMAIL " +
                    "GROUP BY V.VISITOR_DATE, M.MEMBER_GENDER " +
                    "ORDER BY V.VISITOR_DATE DESC";

    private final static String GETALL_TIME = "";

    // 한명 insert
    private final static String INSERT= "INSERT INTO VISITOR (VISITOR_MEMBER_EMAIL, VISITOR_DATE, VISITOR_TIME)" +
            "VALUES (?, TRUNC(SYSDATE), SYSTIMESTAMP)";

    public boolean insert(VisitorVO vo) {
        return jdbcTemplate.update(INSERT, vo.getUserEmail()) >= 1;
    }

    private boolean update(VisitorVO vo) {
        return false;
    }

    private boolean delete(VisitorVO vo) {
        return false;
    }

    public VisitorVO getVisitor(VisitorVO vo) {
        if (vo.getCondition().equals("GETONE_TODAY")) {
            return (VisitorVO) jdbcTemplate.query(GETONE_TODAY, new VisitorRowMapperGetOneToday() {});
        } else if (vo.getCondition().equals("GETALL_DAILY")) {
            String[] args = {vo.getUserEmail()};
            return (VisitorVO) jdbcTemplate.query(GETONE, args, new VisitorRowMapperGetOne() {});
        } else {
            return null;
        }
    }

    public List<VisitorVO> getVisitorList(VisitorVO vo) {
        return jdbcTemplate.query(GETALL_DAILY, new VisitorRowMapperGetAllDaily() {});
    }
}

class VisitorRowMapperGetOne implements RowMapper<VisitorVO> {
    @Override
    public VisitorVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.next() ? new VisitorVO() : null; // 결과 있음 = 빈객체, 없음 = null
    }
}

class VisitorRowMapperGetOneToday implements RowMapper<VisitorVO> {
    @Override
    public VisitorVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        VisitorVO vo = new VisitorVO();
        if (rs.next()) {
            vo.setVisitorToday(rs.getInt("today_visitor_count"));
        }
        return vo;
    }
}

class VisitorRowMapperGetAllDaily implements RowMapper<VisitorVO> {
    @Override
    public VisitorVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        VisitorVO vo = new VisitorVO();
        if (rs.next()) {
            vo.setVisitorDate(rs.getDate("VISITOR_DATE"));
            vo.setVisitorGender(rs.getInt("VISITOR_GENDER"));
            vo.setVisitorToday(rs.getInt("today_visitor_count"));
        }
        return vo;
    }
}
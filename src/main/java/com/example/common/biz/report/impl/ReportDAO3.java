package com.example.common.biz.report.impl;

import com.example.common.biz.report.ReportVO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.common.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("ReportDAO3")
public class ReportDAO3 {

    @Autowired
    private SqlSessionTemplate mybatis;

    public List<ReportVO> getReportList(ReportVO reportVO) {
    return mybatis.selectList("ReportDAO.getReportList", reportVO);
    }

    public ReportVO getReport(ReportVO reportVO) {
        return mybatis.selectOne("ReportDAO.getReport", reportVO);
    }

    public boolean insert(ReportVO reportVO) {
        int result = mybatis.insert("ReportDAO.insert", reportVO);
        if(result <= 0) {
            return false;
        }
        return true;
    }

    private boolean update(ReportVO reportVO) {
        return false;
    }

    public boolean delete(ReportVO reportVO) {
        int result = mybatis.delete("ReportDAO.delete", reportVO);
        if(result <= 0) {
            return false;
        }
        return true;
    }
}

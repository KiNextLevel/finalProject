package com.example.common.biz.alert.impl;

import com.example.common.biz.alert.AlertVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ReportMyBatisDAO")
public class AlertMyBatisDAO {
    @Autowired
    private SqlSessionTemplate mybatis;

    public List<AlertVO> getAlertList(AlertVO alertVO) {
        return mybatis.selectList("AlertDAO.getAlertList", alertVO);
    }

    public boolean insert(AlertVO alertVO) {
        int result = mybatis.insert("AlertDAO.insert", alertVO);
        if(result <= 0) {
            return false;
        }
        return true;
    }

    public boolean update(AlertVO alertVO) {
        int result = mybatis.update("AlertDAO.update", alertVO);
        if(result <= 0) {
            return false;
        }
        return true;
    }
}

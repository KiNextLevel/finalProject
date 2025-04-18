package com.example.biz.alert.impl;

import com.example.biz.alert.AlertService;
import com.example.biz.alert.AlertVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("alertService")
public class AlertSerivceImpl implements AlertService {
    @Autowired
    private AlertDAO alertDAO;

    @Override
    public boolean insert(AlertVO vo) {
        return alertDAO.insert(vo);
    }

    @Override
    public boolean update(AlertVO vo) {
        return alertDAO.update(vo);
    }

    @Override
    public boolean delete(AlertVO vo) {
        return false;
    }

    @Override
    public AlertVO getAlert(AlertVO vo) {
        return null;
    }

    @Override
    public List<AlertVO> getAlertList(AlertVO vo) {
        return alertDAO.getAlertList(vo);
    }
}

package com.example.common.biz.alert.impl;

import com.example.common.biz.alert.AlertService;
import com.example.common.biz.alert.AlertVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("alertService")
public class AlertServiceImpl implements AlertService {
    @Autowired
    private AlertTemplateDAO alertDAO;

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
        return alertDAO.delete(vo);
    }

    @Override
    public AlertVO getAlert(AlertVO vo) {
        return alertDAO.getAlert(vo);
    }

    @Override
    public List<AlertVO> getAlertList(AlertVO vo) {
        return alertDAO.getAlertList(vo);
    }
}

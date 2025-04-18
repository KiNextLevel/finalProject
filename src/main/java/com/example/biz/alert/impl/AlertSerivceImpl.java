package com.example.biz.alert.impl;

import com.example.biz.alert.AlertService;
import com.example.biz.alert.AlertVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("alertService")
public class AlertSerivceImpl implements AlertService {
    @Override
    public boolean insert(AlertVO vo) {
        return false;
    }

    @Override
    public boolean update(AlertVO vo) {
        return false;
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
        return List.of();
    }
}

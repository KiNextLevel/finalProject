package com.example.common.biz.alert.jpa;

import com.example.common.biz.alert.AlertVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AlertJPAService")
public class AlertJPAService{
    @Autowired
    private AlertRepository alertRepository;

    // 알림 조회
    public List<AlertEntity> getAlertList(AlertVO alertVO) {
        return alertRepository.getAlertList(alertVO.getUserEmail());
    }

    // 알림 읽음 여부 업데이트
    public int update(AlertVO alertVO) {
        return alertRepository.update(alertVO.getAlertNumber());
    }
}

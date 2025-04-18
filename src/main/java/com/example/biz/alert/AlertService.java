package com.example.biz.alert;

import java.util.List;

public interface AlertService {
    boolean insert(AlertVO vo);
    boolean update(AlertVO vo);
    boolean delete(AlertVO vo);
    AlertVO getAlert(AlertVO vo);
    List<AlertVO> getAlertList(AlertVO vo);
}

package com.example.view.asyn;

import com.example.biz.alert.AlertService;
import com.example.biz.alert.AlertVO;
import com.example.biz.alert.impl.AlertSerivceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController//반환값을 JSON 타입으로 변경
public class ReadAlertController {
    @Autowired
    private AlertService alertService;

    @PostMapping("/updateAlertStatus")
    public Map<String, Object> updateAlertStatus(@RequestParam("alertNumber") String alertNumber,
                                                 AlertVO alertVO, @RequestParam("alertIsWatch") boolean alertIsWatch) {

        boolean updateSuccess = updateAlertStatusInDatabase(alertNumber, alertIsWatch);

        return Map.of("success", updateSuccess);
    }

    private boolean updateAlertStatusInDatabase(String alertNumber, boolean alertIsWatch) {
        System.out.println(" -- updateAlertStatusInDatabase -- 로그: [updateAlertStatusInDatabase] ");
        // DB 연결 후, 알림 상태를 업데이트하는 코드 작성
        AlertVO alertVO = new AlertVO();
        System.out.println(" -- updateAlertStatusInDatabase -- alertDTO: " + alertNumber);
        alertVO.setAlertNumber(Integer.parseInt(alertNumber));
        System.out.println(" -- updateAlertStatusInDatabase -- alertDTO: " + alertVO);
        if(alertService.update(alertVO)) {
            System.out.println(" -- updateAlertStatusInDatabase -- alertDTO: " + true);
            return true;
        }
        return false;  // 성공적으로 업데이트 되었다면 true 반환
    }
}

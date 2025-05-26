package com.example.common.view.asyn;

import com.example.common.biz.alert.AlertService;
import com.example.common.biz.alert.AlertVO;
import com.example.common.biz.alert.jpa.AlertJPAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController//반환값을 JSON 타입으로 변경
public class ReadAlertController {
//    @Autowired
//    private AlertService alertService;
    @Autowired
    private AlertJPAService alertJPAService;

    @PostMapping("/updateAlertStatus.do")
    public Map<String, Object> updateAlertStatus(@RequestParam("alertNumber") String alertNumber) {
        System.out.println("===============test===============");
        boolean updateSuccess = updateAlertStatusInDatabase(alertNumber);

        return Map.of("success", updateSuccess);
    }

    private boolean updateAlertStatusInDatabase(String alertNumber) {
        System.out.println(" -- updateAlertStatusInDatabase -- 로그: [updateAlertStatusInDatabase] ");
        // DB 연결 후, 알림 상태를 업데이트하는 코드 작성
        AlertVO alertVO = new AlertVO();
        System.out.println(" -- updateAlertStatusInDatabase -- alertDTO: " + alertNumber);
        alertVO.setAlertNumber(Integer.parseInt(alertNumber));
        System.out.println(" -- updateAlertStatusInDatabase -- alertDTO: " + alertVO);
        if(alertJPAService.update(alertVO) > 0) {
            System.out.println(" -- updateAlertStatusInDatabase -- alertDTO: " + true);
            return true;
        }
        return false;  // 성공적으로 업데이트 되었다면 true 반환
    }
}

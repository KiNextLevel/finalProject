package com.example.common.view.logic;

import com.example.common.biz.user.UserVO;
import com.example.common.biz.visitor.VisitorService;
import com.example.common.biz.visitor.VisitorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("checkVisit")
public class CheckVisit {
    @Autowired
    private VisitorService visitorService;

    public void checkVisitIfFirstLogin(UserVO userVO) {
        System.out.println("checkVisitIfFirstLogin 로그 UserVO = " + userVO);
        VisitorVO visitorVO = new VisitorVO();
        visitorVO.setUserEmail(userVO.getUserEmail());
        visitorVO.setCondition("GETONE");
        // 방문자가 첫 방문일 경우
        if (visitorService.getVisitor(visitorVO) == null) {
            visitorService.insert(visitorVO);
        }
        visitorVO.setCondition("GETONE_TODAY");
        System.out.println("visitor Td = " + visitorService.getVisitor(visitorVO));
        for (VisitorVO vo : visitorService.getVisitorList(visitorVO)) {
            System.out.println(vo);
        }
    }
}

package com.example.common.view.myPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

 // MyPageViewController
 // 화면 이동(페이지 띄우기)만 담당하는 Controller
 // 데이터를 가져오거나 수정하는 건 하지 않음
@Controller
public class MyPageController {
     // 마이페이지로 이동
     // 페이지 화면만 보여줌
     // 데이터는 AJAX로 따로 불러오기
    @GetMapping("/myPage.do")
    public String myPage() {
        return "/Metronic-Shop-UI-master/theme/MyPage"; // myPage.jsp 띄움
    }
     // 프로필 수정 페이지로 이동
     // 수정 폼 화면만 보여줌
     // 기존 사용자 정보는 AJAX로 따로 가져오기
    @GetMapping("/updateProfilePage.do")
    public String updateProfilePage() {
        return "/Metronic-Shop-UI-master/theme/MyPageEdit"; // 수정 페이지 띄움
    }
}

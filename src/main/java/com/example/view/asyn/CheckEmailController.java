package com.example.view.asyn;

import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CheckEmailController {
    @Autowired
    private UserService userService;

    // GET 요청 처리
    @GetMapping(value = "/checkEmailDuplicate.do", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String checkEmailDuplicateGet(
            @RequestParam("userEmail") String userEmail,
            @RequestParam(value = "socialType", required = false, defaultValue = "normal") String socialType) {

        return processEmailCheck(userEmail, socialType);
    }

    // POST 요청 처리
    @PostMapping(value = "/checkEmailDuplicate.do", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String checkEmailDuplicatePost(
            @RequestParam("userEmail") String userEmail,
            @RequestParam(value = "socialType", required = false, defaultValue = "normal") String socialType) {

        return processEmailCheck(userEmail, socialType);
    }

    // 이메일 중복 체크 처리 로직
    private String processEmailCheck(String userEmail, String socialType) {
        System.out.println("CheckEmailController 실행");
        System.out.println("체크할 이메일: " + userEmail + ", 소셜 타입: " + socialType);

        // JSON 응답 객체 생성
        JSONObject jsonResponse = new JSONObject();

        try {
            // 파라미터 유효성 검사
            if (userEmail == null || userEmail.trim().isEmpty()) {
                jsonResponse.put("isDuplicate", true);
                jsonResponse.put("message", "이메일을 입력해주세요.");
                return jsonResponse.toString();
            }

            // UserVO 객체 생성 및 설정
            UserVO userVO = new UserVO();
            userVO.setUserEmail(userEmail);
            userVO.setSocialType(socialType);
            userVO.setCondition("SELECTONE_CHECK"); // 이메일 중복 체크 조건 설정

            // 디버깅을 위한 로그 추가
            System.out.println("데이터베이스 조회 시작 - 이메일: " + userEmail);

            // UserService를 통한 이메일 중복 체크
            UserVO result = userService.getUser(userVO);

            // 디버깅을 위한 결과 로그 추가
            System.out.println("select db: " + (result != null ? "EMAIL YET" : "YOU CAN USE"));

            if (result != null) {
                // 사용자 정보가 있으면 중복된 이메일
                System.out.println("중복된 이메일 발견: " + userEmail);
                jsonResponse.put("isDuplicate", true);
                jsonResponse.put("message", "이미 사용 중인 이메일입니다.");
            } else {
                // 사용자 정보가 없으면 사용 가능한 이메일
                System.out.println("사용 가능한 이메일: " + userEmail);
                jsonResponse.put("isDuplicate", false);
                jsonResponse.put("message", "사용 가능한 이메일입니다.");
            }

        } catch (Exception e) {
            // 예외 처리 로직 추가
            System.err.println("이메일 중복 체크 중 오류 발생: " + e.getMessage());
            e.printStackTrace();

            jsonResponse.put("isDuplicate", true); // 오류 발생 시 안전하게 중복으로 처리
            jsonResponse.put("message", "서버 오류가 발생했습니다. 다시 시도해주세요.");
        }

        System.out.println("CheckEmailController 종료");
        return jsonResponse.toString();
    }
}

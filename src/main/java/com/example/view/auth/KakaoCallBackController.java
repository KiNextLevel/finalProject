package com.example.view.auth;

import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;
import com.example.view.asyn.RandomPassword;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class KakaoCallBackController {

    @Autowired
    private UserService userService;

    private static final String CLIENT_ID = "cb9656ab4895e6ee319e89e74f28a308";
    private static final String REDIRECT_URI = "http://localhost:8088/Metronic-Shop-UI-master/theme/kakaoCallBack.do";

    @GetMapping("/kakaoCallBack.do")
    public String kakaoCallback(@RequestParam("code") String code, HttpServletRequest request, Model model, UserVO userVO) {
        System.out.println("kakaoCallBack log - code = [" + code + "]");

        try {
            // 액세스 토큰을 얻기 위한 POST 요청
            String accessToken = getAccessToken(code);
            System.out.println("kakaoCallBack log - accessToken = [" + accessToken + "]");

            // 액세스 토큰으로 사용자 정보 가져오기
            String userInfo = getUserInfo(accessToken);
            System.out.println("kakaoCallBack log - userInfo: [" + userInfo + "]");

            // JSONParser를 사용하여 사용자 정보에서 이메일과 닉네임 추출
            JSONParser parser = new JSONParser();
            JSONObject jsonResponse = (JSONObject) parser.parse(userInfo);
            // kakao_account 객체 추출
            JSONObject kakaoAccount = (JSONObject) jsonResponse.get("kakao_account");
            // 이메일 추출
            String email = (String) kakaoAccount.get("email");
            // 이름 추출 (이름이 있을 경우만)
            String name = (String) kakaoAccount.get("name");
            System.out.println("kakaologin log: email: [" + email + "], name: [" + name + "]");

            // DB에 사용자 정보 저장
            // UserService를 이용하여 회원 정보 조회
            userVO.setUserEmail(email);
            userVO.setSocialType("kakao");
            userVO.setCondition("SELECTONE_CHECK");

            UserVO user = userService.getUser(userVO);

            if (user == null) { // 회원가입 진행
                HttpSession session = request.getSession();
                session.setAttribute("userEmail", email);
                System.out.println("KakaoLogin Log: userEmail: [" + email + "]");
                session.setAttribute("userName", name);
                // 비밀번호는 랜덤하게 생성
                String randomPassword = RandomPassword.generateRandomPassword();
                session.setAttribute("userPassword", randomPassword);
                // 소셜 로그인 타입 저장
                session.setAttribute("socialType", "kakao");

                // 회원가입 페이지로 리다이렉트
                model.addAttribute("msg", "카카오 계정으로 회원가입을 진행합니다.");
                model.addAttribute("flag", true);
                model.addAttribute("url", "/Metronic-Shop-UI-master/theme/JoinPage.jsp");
            } else { // 기존 회원이면 로그인 처리
                userVO.setCondition("SELECTONE_USERINFO");
                user = userService.getUser(userVO);

                // 회원이나 관리자일 경우만 로그인
                if (user.getUserRole() == 0 || user.getUserRole() == 1) {
                    // 세션에 로그인 정보 저장
                    HttpSession session = request.getSession();
                    session.setAttribute("userEmail", user.getUserEmail());
                    session.setAttribute("userRole", user.getUserRole());
                    session.setAttribute("userPremium", user.isUserPremium());

                    // 컨디션 "위치 정보 가져옴"
                    userVO.setCondition("SELECTONE_LOCATION");
                    user = userService.getUser(userVO);
                    session.setAttribute("userLatitude", user.getUserLatitude());
                    session.setAttribute("userLongitude", user.getUserLongitude());

                    // 로그인 성공 메시지 및 메인 페이지로 리다이렉트
                    model.addAttribute("msg", "카카오 계정으로 로그인되었습니다.");
                    model.addAttribute("flag", true);
                    model.addAttribute("url", "mainPage.do");
                } else { // 블랙 or 탈퇴한 회원이면 로그인 불가능
                    // 로그인 페이지로 리다이렉트
                    model.addAttribute("msg", "블랙 계정이나 탈퇴한 계정은 로그인 할 수 없습니다.");
                    model.addAttribute("flag", true);
                    model.addAttribute("url", "loginPage.do");
                }
            }
            return "/Metronic-Shop-UI-master/theme/Alert";
        } catch (Exception e) {
            e.printStackTrace(); // 예외 로그
            model.addAttribute("msg", "카카오 로그인 중 오류가 발생했습니다.");
            model.addAttribute("flag", false);
            return "/Metronic-Shop-UI-master/theme/Alert";
        }
    }

    private String getAccessToken(String code) throws IOException, org.json.simple.parser.ParseException {
        String tokenUrl = "https://kauth.kakao.com/oauth/token";
        String params = "grant_type=authorization_code&client_id=" + CLIENT_ID +
                "&redirect_uri=" + REDIRECT_URI + "&code=" + code;

        // URL 연결 설정
        URL url = new URL(tokenUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.getOutputStream().write(params.getBytes("UTF-8"));

        // 서버 응답 받기
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer responseStr = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            responseStr.append(inputLine);
        }
        in.close();

        // JSON으로 응답받음 (Access Token 포함)
        JSONParser parser = new JSONParser();
        JSONObject jsonResponse = (JSONObject) parser.parse(responseStr.toString());

        // access_token 추출
        return (String) jsonResponse.get("access_token");
    }

    // Access Token을 이용해 사용자 정보 가져오기
    private String getUserInfo(String accessToken) throws IOException {
        String apiUrl = "https://kapi.kakao.com/v2/user/me";
        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + accessToken);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer responseStr = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            responseStr.append(inputLine);
        }
        in.close();

        return responseStr.toString();
    }
}
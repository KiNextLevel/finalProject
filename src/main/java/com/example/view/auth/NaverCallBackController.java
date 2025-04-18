package com.example.view.auth;

import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;
import com.example.view.asyn.RandomPassword;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Controller
public class NaverCallBackController {
    @Autowired
    private UserService userService;

    private static final String CLIENT_ID = "HPtl9HdFUiGzoDPAPQ4a";
    private static final String CLIENT_SECRET = "7H_NxuX3oo";
    private static final String REDIRECT_URI = "http://localhost:8088/Metronic-Shop-UI-master/theme/naverCallback.do";

    @GetMapping("/naverCallback.do")
    public String naverCallback(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletRequest request,
            HttpSession session, Model model, UserVO userVO) {

        try {
            // 접근 토큰 발급 요청 URL 구성
            String tokenUrl = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code"
                    + "&client_id=" + CLIENT_ID
                    + "&client_secret=" + CLIENT_SECRET
                    + "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "UTF-8")
                    + "&code=" + code
                    + "&state=" + state;

            // HTTP 연결 설정 및 토큰 요청
            URL url = new URL(tokenUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 응답 데이터 읽기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            // JSON 응답 파싱하여 접근 토큰 추출
            JSONParser parser = new JSONParser();
            JSONObject tokenResponse = (JSONObject) parser.parse(sb.toString());
            String accessToken = (String) tokenResponse.get("access_token");

            // 접근 토큰을 이용하여 사용자 정보 가져오기
            String apiURL = "https://openapi.naver.com/v1/nid/me";
            URL userInfoUrl = new URL(apiURL);
            HttpURLConnection userInfoConn = (HttpURLConnection) userInfoUrl.openConnection();
            userInfoConn.setRequestMethod("GET");
            userInfoConn.setRequestProperty("Authorization", "Bearer " + accessToken);

            // 사용자 정보 응답 데이터 읽기
            br = new BufferedReader(new InputStreamReader(userInfoConn.getInputStream()));
            sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            // 사용자 정보 JSON 파싱
            JSONObject userInfoResponse = (JSONObject) parser.parse(sb.toString());
            JSONObject userInfo = (JSONObject) userInfoResponse.get("response");

            // 시스템 로그
            System.out.println(userInfoResponse + " : userInfoResponse");
            System.out.println(userInfo + " : userInfo");

            // 네이버에서 받은 사용자 정보 추출
            String email = (String) userInfo.get("email");
            String name = (String) userInfo.get("name");

            // 시스템 로그
            System.out.println(email + " email");
            System.out.println(name + " name");

            // UserDAO를 이용하여 회원 정보 조회
            userVO.setUserEmail(email);
            userVO.setSocialType("naver");
            userVO.setCondition("SELECTONE_CHECK");
            userVO = userService.getUser(userVO);

            if (userVO == null) {
                // 회원 정보가 없으면 회원가입 진행
                session.setAttribute("userEmail", email);
                session.setAttribute("userName", name);
                String randomPassword = RandomPassword.generateRandomPassword();
                session.setAttribute("userPassword", randomPassword);
                session.setAttribute("socialType", "naver");

                // 회원가입 페이지로 이동
                model.addAttribute("msg", "네이버 계정으로 회원가입을 진행합니다.");
                model.addAttribute("flag", true);
                model.addAttribute("url", "/Metronic-Shop-UI-master/theme/JoinPage.jsp");
                return "/Metronic-Shop-UI-master/theme/Alert";
            } else {
                // 기존 회원이면 로그인 처리
                userVO.setCondition("SELECTONE_USERINFO");
                userVO = userService.getUser(userVO);

                // 사용자 권한 확인
                if (userVO.getUserRole() == 0 || userVO.getUserRole() == 1) {
                    // 세션에 로그인 정보 저장
                    session.setAttribute("userEmail", userVO.getUserEmail());
                    session.setAttribute("userRole", userVO.getUserRole());
                    session.setAttribute("userPremium", userVO.isUserPremium());

                    // 위치 정보 가져오기
                    userVO.setCondition("SELECTONE_LOCATION");
                    userVO = userService.getUser(userVO);
                    session.setAttribute("userLatitude", userVO.getUserLatitude());
                    session.setAttribute("userLongitude", userVO.getUserLongitude());

                    // 로그인 성공 메시지 및 메인 페이지로 이동
                    model.addAttribute("msg", "네이버 계정으로 로그인되었습니다.");
                    model.addAttribute("flag", true);
                    model.addAttribute("url", "mainPage.do");
                    return "/Metronic-Shop-UI-master/theme/Alert";
                } else {
                    // 블랙 or 탈퇴한 회원이면 로그인 불가능
                    model.addAttribute("msg", "블랙 계정이나 탈퇴한 계정은 로그인 할 수 없습니다.");
                    model.addAttribute("flag", true);
                    model.addAttribute("url", "loginPage.do");
                    return "/Metronic-Shop-UI-master/theme/Alert";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "네이버 로그인 중 오류가 발생했습니다.");
            model.addAttribute("flag", false);
            return "/Metronic-Shop-UI-master/theme/Alert";
        }
    }
}
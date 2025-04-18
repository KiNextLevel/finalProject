package com.example.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GeoCodingUtil {
    // 카카오 REST API 키 (Kakao Developers에서 발급받은 키를 사용)
    private static final String REST_API_KEY = "e34b74c34b7427302075f13f3cf0c7f1";

    // 주소를 전달받아 위도(latitude)와 경도(longitude)를 반환하는 메서드
    public static double[] getCoordinatesFromAddress(String address) {
        double[] result = new double[2]; // 결과를 담을 배열 생성 [위도, 경도]

        try {
            // 주소를 UTF-8 형식으로 인코딩 (공백, 한글 등을 안전하게 변환)
            String encodedAddress = URLEncoder.encode(address, "UTF-8");

            // 카카오 주소 검색 API의 요청 URL 구성
            String apiURL = "https://dapi.kakao.com/v2/local/search/address.json?query=" + encodedAddress;

            // URL 객체 생성
            URL url = new URL(apiURL);

            // HTTP 연결 객체 생성
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // HTTP 요청 헤더에 인증 키 추가 (필수)
            conn.setRequestProperty("Authorization", "KakaoAK " + REST_API_KEY);

            // 요청 방식 지정 (GET 방식)
            conn.setRequestMethod("GET");

            // 응답 코드 확인 (200이면 성공)
            int responseCode = conn.getResponseCode();

            if (responseCode == 200) { // 요청이 성공한 경우
                // 응답 스트림을 읽어들임 (JSON 문자열 형태로 들어옴)
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder responseBuilder = new StringBuilder(); // 전체 응답을 저장할 StringBuilder
                String line;

                // 한 줄씩 읽어서 StringBuilder에 추가
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }

                // 스트림 닫기
                reader.close();

                // JSON 응답 문자열로 변환
                String response = responseBuilder.toString();

                // "x":"경도", "y":"위도" 키워드의 위치를 찾음
                int xIndex = response.indexOf("\"x\":\"");
                int yIndex = response.indexOf("\"y\":\"");

                // 두 키워드가 모두 존재할 경우
                if (xIndex != -1 && yIndex != -1) {
                    // x(경도) 값 추출
                    String x = response.substring(xIndex + 5, response.indexOf("\"", xIndex + 5));

                    // y(위도) 값 추출
                    String y = response.substring(yIndex + 5, response.indexOf("\"", yIndex + 5));

                    // 추출한 값을 double 타입으로 변환하여 결과 배열에 저장
                    result[0] = Double.parseDouble(y); // 위도 (latitude)
                    result[1] = Double.parseDouble(x); // 경도 (longitude)
                }
            } else {
                // 요청 실패 시 응답 코드 출력
                System.out.println("API 호출 실패 - 응답 코드: " + responseCode);
            }

        } catch (Exception e) {
            // 예외 발생 시 스택 트레이스 출력
            e.printStackTrace();
        }

        // 위도와 경도를 담은 배열 반환
        return result;
    }
}

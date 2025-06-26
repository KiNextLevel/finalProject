package com.example.common.biz.crawling;

import com.example.common.biz.preference.PreferenceService;
import com.example.common.biz.preference.PreferenceVO;
import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
import com.example.common.view.asyn.RandomPassword;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class Crawling2 {
    @Autowired
    private UserService userService;
    @Autowired
    private PreferenceService preferenceService;
    @Autowired
    private RandomPassword randomPassword;

    // 샘플 데이터 배열
    private static final String[] body = {"마른", "보통", "근육질", "통통", "건장"};
    private static final String[] mbti = {"ISFJ", "ISTJ", "INFJ", "INTJ", "ISTP", "ISFP", "INFP", "INTP", "ESTP", "ESFP", "ENFP", "ENTP", "ESTJ", "ESFJ", "ENFJ", "ENTJ"};
    private static final String[] edu = {"고등학교 졸업", "전문대 졸업", "대학교 재학", "대학교 졸업", "대학원 재학", "대학원 졸업"};
    private static final String[] religion = {"무교", "기독교", "천주교", "불교", "이슬람교", "힌두교", "기타"};


    private static final String[] addresses = {
            "서울특별시 강남구 테헤란로 123",
            "부산광역시 해운대구 해운대로 456",
            "대구광역시 중구 동성로 789",
            "인천광역시 연수구 송도동 12-34",
            "광주광역시 서구 치평동 567",
            "대전광역시 유성구 대학로 99",
            "울산광역시 남구 삼산로 321",
            "경기도 수원시 팔달구 정조로 654",
            "강원도 춘천시 강남동 987",
            "제주특별자치도 제주시 한림읍 111"
    };

    private static final double[] latitudes = {
            37.5031,
            35.1631,
            35.8703,
            37.4058,
            35.1502,
            36.3504,
            35.5389,
            37.2807,
            37.8786,
            33.4081
    };

    private static final double[] longitudes = {
            127.0407,
            129.1636,
            128.5965,
            126.6568,
            126.8492,
            127.3845,
            129.3167,
            127.0119,
            127.7279,
            126.2674
    };

    @EventListener(ApplicationReadyEvent.class)
    public void crawl() {
        // ChromeDriver 경로 설정
        WebDriverManager.chromedriver().setup();

        // ChromeOptions 설정 (Headless 모드 활성화)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Headless 모드 활성화
        options.addArguments("--disable-gpu"); // GPU 비활성화
        options.addArguments("--no-sandbox"); // 샌드박스 비활성화
        options.addArguments("--disable-dev-shm-usage"); // 공유 메모리 사용 비활성화 (리소스 절약)
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        // DOM 로딩이 오래 걸리면 요소를 다 못 받아오거나 오류 발생 가능 -> 대기 시간: 최대 10초로 설정
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // 여성, 남성 배우 목록 링크 수집
            List<String> femaleNames = new ArrayList<>();
            List<String> femaleUrls = new ArrayList<>();
            List<String> maleNames = new ArrayList<>();
            List<String> maleUrls = new ArrayList<>();
            getLinksFromPage(driver, wait, femaleUrls, femaleNames, maleNames, maleUrls);

            // 남자 목록, 여자 목록, 50 중 가장 적은 분량 만큼만 조회
            int limit = Math.min(50, Math.min(femaleUrls.size(), maleUrls.size()));
            for (int i = 0; i < limit; i++) {
                processActor(driver, wait, femaleNames.get(i), femaleUrls.get(i), "여");
                processActor(driver, wait, maleNames.get(i), maleUrls.get(i), "남");
            }
        } catch (Exception e) {
            System.out.println("크롤링 중 에러 발생: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }

        System.out.println("크롤링 완료!");
    }

    // 링크 수집 메서드
    public void getLinksFromPage(WebDriver driver, WebDriverWait wait,
                                 List<String> femaleUrls, List<String> femaleNames,
                                 List<String> maleNames, List<String> maleUrls) {
        try {
            //여자, 남자 배우 목록 페이지
            String femaleUrl = "https://ko.wikipedia.org/wiki/%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD%EC%9D%98_%EC%97%AC%EC%9E%90_%EB%B0%B0%EC%9A%B0_%EB%AA%A9%EB%A1%9D";
            String maleUrl = "https://ko.wikipedia.org/wiki/%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD%EC%9D%98_%EB%82%A8%EC%9E%90_%EB%B0%B0%EC%9A%B0_%EB%AA%A9%EB%A1%9D";

            // 여성 배우 링크 수집
            driver.get(femaleUrl);
            // 페이지에서 링크가 DOM에 나타날 때까지 기다림
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='mw-content-text']//ul/li/a")));
            List<WebElement> femaleLinks = driver.findElements(By.xpath("//div[@id='mw-content-text']//ul/li/a"));
            for (WebElement link : femaleLinks) {
                femaleNames.add(link.getText().split("\\(")[0]);
                femaleUrls.add(link.getAttribute("href"));
            }

            // 남성 배우 링크 수집
            driver.get(maleUrl);
            // 페이지에서 링크가 DOM에 나타날 때까지 기다림
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='mw-content-text']//ul/li/a")));
            List<WebElement> maleLinks = driver.findElements(By.xpath("//div[@id='mw-content-text']//ul/li/a"));
            for (WebElement link : maleLinks) {
                maleNames.add(link.getText().split("\\(")[0]);
                maleUrls.add(link.getAttribute("href"));
            }

        } catch (Exception e) {
            System.out.println("링크 수집 실패: ");
        }
    }

    // 배우 정보 수집 메서드
    private void processActor(WebDriver driver, WebDriverWait wait, String nickName, String actorUrl, String gender) {
        try {
            System.out.println("Crawling Log - 크롤링 중: " + nickName + " (" + actorUrl + ")");
            driver.get(actorUrl);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@id='firstHeading']")));

            String name = nickName;
            String birthDate = "N/A";
//            String gender = "N/A";
            String profilePhotoUrl;

            WebElement infoTable = driver.findElement(By.xpath("//table[contains(@class, 'infobox')]"));
            List<WebElement> images = infoTable.findElements(By.xpath(".//td[contains(@class, 'infobox-image')]//img"));

            if (images.isEmpty()) {
                System.out.println(nickName + " - 프로필 사진 없음, 스킵");
                return;
            }

            profilePhotoUrl = images.getFirst().getAttribute("src");

            for (WebElement row : infoTable.findElements(By.xpath(".//tr"))) {
                List<WebElement> headers = row.findElements(By.xpath(".//th"));
                List<WebElement> values = row.findElements(By.xpath(".//td"));
                if (!headers.isEmpty() && !values.isEmpty()) {
                    String headerText = headers.getFirst().getText();
                    String valueText = values.getFirst().getText();
                    if ("본명".equals(headerText)) {
                        name = valueText.split("\\(")[0];
                    }
                    else if ("출생".equals(headerText)) {
                        birthDate = valueText.split("\n")[0];
                    }
//                    else if ("성별".equals(headerText)) {
//                        gender = valueText.split("\n")[0];
//                    }
                }
            }

            // UserVO 객체 생성
            UserVO userVO = new UserVO();
            userVO.setUserName(name);
            userVO.setUserNickname(nickName);
            userVO.setUserProfile(profilePhotoUrl);
            userVO.setUserGender(gender.contains("남") ? 1 : 0);
            userVO.setUserBirth(convertToIsoDateString(birthDate));

            // 저장 시도
            if (createRandomUserInfo(userVO)) {
                userService.insert(userVO);
                System.out.println("DB 저장 완료: " + userVO.getUserNickname());
            } else {
                System.out.println("중복으로 저장 실패: " + userVO.getUserNickname());
            }

        } catch (Exception e) {
            System.out.println("에러 - " + nickName + ": " + e.getMessage());
        }
    }

    private boolean createRandomUserInfo(UserVO vo) {
        Random rand = new Random();
        vo.setUserEmail(generateNumericEmail(vo.getUserNickname()) + "@NextLove.com");
        vo.setCondition("SELECTONE_USERINFO");

        // 중복 이메일 체크
        if (userService.getUser(vo) != null) {
            return false;
        }

        // 가입
        vo.setUserPassword(randomPassword.generateRandomPassword());
        vo.setUserPhone("01012345678");
        vo.setUserHeight(rand.nextInt(81) + 140);
        vo.setUserBody(body[rand.nextInt(body.length)]);
        vo.setUserMbti(mbti[rand.nextInt(mbti.length)]);
        vo.setUserEducation(edu[rand.nextInt(edu.length)]);
        vo.setUserReligion(religion[rand.nextInt(religion.length)]);
        vo.setUserDrink(rand.nextInt(3));
        vo.setUserSmoke(rand.nextInt(2));
        vo.setUserJob("배우");

        int index = rand.nextInt(addresses.length);
        vo.setUserRegion(addresses[index]);
        vo.setUserLatitude(latitudes[index]);
        vo.setUserLongitude(longitudes[index]);
        vo.setUserDescription("안녕하세요 배우 " + vo.getUserNickname() + " 입니다.");
        vo.setSocialType("normal");
        vo.setCondition("INSERT");
        System.out.println(vo);

        // 취향 입력
        PreferenceVO prefVO = new PreferenceVO();
        prefVO.setUserEmail(vo.getUserEmail());
        prefVO.setPreferenceAge((rand.nextInt(60) + 19) + "");
        prefVO.setPreferenceBody(body[rand.nextInt(body.length)]);
        prefVO.setPreferenceHeight(rand.nextInt(81) + 140);
        prefVO.setCondition("INSERT");
        preferenceService.insert(prefVO);
        return true;
    }

    public static String generateNumericEmail(String nickname) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(nickname.getBytes("UTF-8"));
            BigInteger number = new BigInteger(1, hashBytes);
            String numericId = number.toString();
            return numericId.substring(0, 12);
        } catch (Exception e) {
            throw new RuntimeException("해시 생성 실패", e);
        }
    }

    public static String convertToIsoDateString(String rawText) {
        try {
            String cleaned = rawText.replaceAll("\\(.*?\\)", "").trim();
            String cleaned2 = cleaned.replace("년 ", "-");
            String cleaned3 = cleaned2.replace("월 ", "-");
            return cleaned3.replace("일", "");
        } catch (Exception e) {
            System.err.println("날짜 변환 실패: " + rawText);
            return null;
        }
    }
}
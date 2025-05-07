package com.example.common.biz.crawling;

import com.example.common.biz.preference.PreferenceService;
import com.example.common.biz.preference.PreferenceVO;
import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
import com.example.common.view.asyn.RandomPassword;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Crawling {

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
    public void crawlAndSaveActors() {
        // JVM 인코딩을 UTF-8로 설정 (문자 깨짐 방지)
        System.setProperty("file.encoding", "UTF-8");

        // ChromeDriver 경로 설정
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        // ChromeOptions 설정 (Headless 모드 활성화)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Headless 모드 활성화
        options.addArguments("--disable-gpu"); // GPU 비활성화 (Headless 모드에서 권장)
        options.addArguments("--no-sandbox"); // 샌드박스 비활성화 (일부 환경에서 필요)
        options.addArguments("--disable-dev-shm-usage"); // 공유 메모리 사용 비활성화 (리소스 절약)
        options.addArguments("--window-size=1920,1080"); // 창 크기 설정 (일부 사이트에서 필요)

        WebDriver driver = new ChromeDriver(options);

        String mainUrl = "https://ko.wikipedia.org/wiki/%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD%EC%9D%98_%EC%97%AC%EC%9E%90_%EB%B0%B0%EC%9A%B0_%EB%AA%A9%EB%A1%9D";

        try {
            // 메인 페이지로 이동
            driver.get(mainUrl);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='mw-content-text']//ul/li/a")));

            // 링크와 이름 먼저 수집
            List<String> actorUrls = new ArrayList<>();
            List<String> actorNames = new ArrayList<>();
            List<WebElement> actorLinks = driver.findElements(By.xpath("//div[@id='mw-content-text']//ul/li/a"));
            for (WebElement link : actorLinks) {
                actorUrls.add(link.getAttribute("href"));
                actorNames.add(link.getText());
            }

            // 첫 2명만 테스트
            int limit = Math.min(100, actorUrls.size());
            for (int i = 0; i < limit; i++) {
                try {
                    String actorName = actorNames.get(i);
                    String actorUrl = actorUrls.get(i);
                    System.out.println("크롤링 중: " + actorName + " (" + actorUrl + ")");

                    // 배우 페이지로 이동
                    driver.get(actorUrl);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@id='firstHeading']")));

                    String nickName = driver.findElement(By.xpath("//h1[@id='firstHeading']")).getText().split("\\(")[0];
                    String name = nickName;
                    String birthDate = "N/A";
                    String gender = "N/A";
                    String profilePhotoUrl;

                    try {
                        WebElement infoTable = driver.findElement(By.xpath("//table[contains(@class, 'infobox')]"));
                        List<WebElement> rows = infoTable.findElements(By.xpath(".//tr"));

                        // 프로필 사진 URL 추출
                        List<WebElement> images = infoTable.findElements(By.xpath(".//td[contains(@class, 'infobox-image')]//img"));
                        if (!images.isEmpty()) {
                            profilePhotoUrl = images.getFirst().getAttribute("src"); // infobox-image 내 첫 번째 이미지의 src 속성
                            System.out.println("프로필 사진 URL: " + profilePhotoUrl);
                        } else {
                            continue;
                        }

                        for (WebElement row : rows) {
                            List<WebElement> headers = row.findElements(By.xpath(".//th"));
                            List<WebElement> values = row.findElements(By.xpath(".//td"));
                            if (!headers.isEmpty() && !values.isEmpty()) {
                                String headerText = headers.getFirst().getText();
                                String valueText = values.getFirst().getText();
                                if ("본명".equals(headerText)) {
                                    name = valueText.split("\n")[0];
                                }
                                if ("출생".equals(headerText)) {
                                    birthDate = valueText.split("\n")[0];
                                }
                                if ("성별".equals(headerText)) {
                                    gender = valueText.split("\n")[0];
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(nickName + "의 정보를 찾을 수 없습니다: " + e.getMessage());
                        continue;
                    }

                    // 콘솔에 결과 출력
                    System.out.printf("활동명: %s, 생년월일: %s, 성별: %s, 이름: %s, 프로필 사진 URL: %s%n",
                            nickName, birthDate, gender, name, profilePhotoUrl);

                    // UserVO 생성 및 랜덤 데이터 추가
                    UserVO userVO = new UserVO();
                    userVO.setUserName(name);
                    userVO.setUserNickname(nickName);
                    userVO.setUserProfile(profilePhotoUrl);
                    userVO.setUserGender(gender.contains("남") ? 1 : 0);
                    userVO.setUserBirth(convertToIsoDateString(birthDate));

                    // 랜덤 사용자 정보 생성 및 DB 저장
                    if (createRandomUserInfo(userVO)) {
                        userService.insert(userVO);
                        System.out.println("사용자 정보가 DB에 저장되었습니다: " + userVO.getUserNickname());
                    } else {
                        System.out.println("사용자 정보 저장 실패 (중복 이메일): " + userVO.getUserNickname());
                    }

                    driver.get(mainUrl); // 메인 페이지로 돌아가기
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='mw-content-text']//ul/li/a")));

                } catch (Exception e) {
                    System.out.println("에러 발생: " + e.getMessage());
                }
            }

        } finally {
            driver.quit();
        }

        System.out.println("크롤링 완료!");
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
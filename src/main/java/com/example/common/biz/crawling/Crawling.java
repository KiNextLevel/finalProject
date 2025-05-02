package com.example.common.biz.crawling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Crawling {

    public static void main(String[] args) {
        // JVM 인코딩을 UTF-8로 설정 (문자 깨짐 방지)
        System.setProperty("file.encoding", "UTF-8");

        // ChromeDriver 경로 설정
        System.setProperty("webdriver.chrome.driver", "D:/Ben/resource/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

//        List<ActressData> actorsData = new ArrayList<>();
        String mainUrl = "https://ko.wikipedia.org/wiki/%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD%EC%9D%98_%EC%97%AC%EC%9E%90_%EB%B0%B0%EC%9A%B0_%EB%AA%A9%EB%A1%9D";

        try {
            // 메인 페이지로 이동
            driver.get(mainUrl);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // div 태그 id가 mw-content-text인것 하위에 href링크가 들어있다. -> ul/li/a 태그가 각각 배우페이지로 이동하는 태그
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='mw-content-text']//ul/li/a")));

            // 링크와 이름 먼저 수집
            List<String> actorUrls = new ArrayList<>();
            List<String> actorNames = new ArrayList<>();
            List<WebElement> actorLinks = driver.findElements(By.xpath("//div[@id='mw-content-text']//ul/li/a"));
            for (WebElement link : actorLinks) {
                actorUrls.add(link.getAttribute("href"));
                actorNames.add(link.getText());
            }

            // 첫 5명만 테스트
            int limit = Math.min(2, actorUrls.size());
            for (int i = 0; i < limit; i++) {
                try {
                    String actorName = actorNames.get(i);
                    String actorUrl = actorUrls.get(i);
                    System.out.println("크롤링 중: " + actorName + " (" + actorUrl + ")");

                    // 배우 페이지로 이동
                    driver.get(actorUrl);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@id='firstHeading']")));

                    String nickName = driver.findElement(By.xpath("//h1[@id='firstHeading']")).getText();
                    String name = "N/A";
                    String birthDate = "N/A";
                    String gender = "N/A";


                    try {
                        WebElement infoTable = driver.findElement(By.xpath("//table[contains(@class, 'infobox')]"));
                        List<WebElement> rows = infoTable.findElements(By.xpath(".//tr"));
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
                    }

//                    actorsData.add(new ActressData(nickName, birthDate, debut, actorUrl));

                    // 콘솔에 결과 출력
                    System.out.printf("활동명: %s, 생년월일: %s, 성별: %s, 이름 %s, 링크: %s%n", nickName, birthDate, gender, name, actorUrl);

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

    private void createRandomUserInfo() {
        // 크롤 = 이름, 닉네임, 사진, 생년월일, 성별
        /*
        이메일
        pw
        전화번호
        (default) 레지데이트
        키
        body (건장, 마른, 보통, 근육질, 통통)
        지역
        음주 = 0 1 2
        흡연 = 0 1
        종교 = 무교
        mbti =
        학력 = 고등학교 졸업
        직업 = 배우
        프사 = 링크
        자기소개 = 이름
         */
    }

//    // ActressData 클래스
//    static class ActressData {
//        private String name;
//        private String birthDate;
//        private String debut;
//        private String url;
//
//        public ActressData(String name, String birthDate, String debut, String url) {
//            this.name = name;
//            this.birthDate = birthDate;
//            this.debut = debut;
//            this.url = url;
//        }
//
//        public String getName() { return name; }
//        public String getBirthDate() { return birthDate; }
//        public String getDebut() { return debut; }
//        public String getUrl() { return url; }
//    }


}
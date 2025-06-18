package com.example.common.view.excel;

import com.example.common.biz.payment.PaymentService;
import com.example.common.biz.payment.PaymentVO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import java.io.*;
import java.util.List;

@Controller
public class ExcelController {

    @Autowired
    PaymentService paymentService;
    @GetMapping("/excel.do")
    public void downloadCSV(PaymentVO paymentVO, HttpServletResponse response) {
        try {
            // Content-Type text/csv
            response.setContentType("text/csv");
            response.setCharacterEncoding("UTF-8"); // 한글도 쓸 수 있는 인코딩

            // 파일 이름 설정
            response.setHeader("Content-Disposition", "attachment;filename=paymentList.csv");

            // CSV 파일에 작성할 Writer
            PrintWriter writer = response.getWriter();

            // CSV 헤더 작성
            writer.println("결제 날짜,이메일,결제 금액,결제 상품");

            paymentVO.setCondition("SELECTALL_ADMIN_PAYMENTS");
            List<PaymentVO> list = paymentService.getPaymentList(paymentVO);

            // 각 행 작성
            for (PaymentVO p : list) {
                String line = String.format( // 스트링포멧을 이용하여 콤마로 구분된 csv형식의 문자열 생성
                        "\"%s\",\"%s\",%d,\"%s\"",
                        p.getPaymentDate(),
                        p.getUserEmail(),
                        p.getPaymentPrice(),
                        p.getProductName()
                );
                writer.println(line);
            }

//            writer.flush();
            writer.close();

            // 읽어오기
            System.out.println("Excel로그: csv 읽어와서 깨지는거 없나 확인");
            try {
                File file = new File("C:\\Users\\Administrator\\Downloads\\paymentList (6).csv");
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String line;
                String firstLine = br.readLine();
                System.out.println("첫 줄 비교: " + firstLine.equals("결제 날짜,이메일,결제 금액,결제 상품"));
                System.out.println("BOM출력 테스트: " + firstLine); // 스트링으로 나오나
                System.out.println("첫 글자 코드: " + (int) firstLine.charAt(0)); // BOM이면 65279 출력
                while ((line = br.readLine())!=null) {
                    System.out.println(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
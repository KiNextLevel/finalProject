//package com.example.common.view.excel;
//
//import com.example.common.biz.payment.PaymentService;
//import com.example.common.biz.payment.PaymentVO;
//import jakarta.servlet.http.HttpServletResponse;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@Controller
//public class ExcelControllerLegacy {
//
//    @Autowired
//    PaymentService paymentService;
//    @GetMapping("/excel.do")
//    public void downloadExcel(PaymentVO paymentVO, HttpServletResponse response) {
//
//        try (Workbook workbook = new XSSFWorkbook()) {
//            Sheet sheet = workbook.createSheet("결제내역"); // spreadsheet 생성 이름: 결제내역
//            int rowNum = 0; // 0행 = 칼럼 제목
//            Row headerRow = sheet.createRow(rowNum++);
//            headerRow.createCell(0).setCellValue("결제 날짜");
//            headerRow.createCell(1).setCellValue("이메일");
//            headerRow.createCell(2).setCellValue("결제 금액");
//            headerRow.createCell(3).setCellValue("결제 상품");
//
//            CreationHelper createHelper = workbook.getCreationHelper();
//            CellStyle dateCellStyle = workbook.createCellStyle();
//            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd hh:mm"));
//
//            paymentVO.setCondition("SELECTALL_ADMIN_PAYMENTS");
//            List<PaymentVO> list = paymentService.getPaymentList(paymentVO);
//            for (PaymentVO p : list) {
//                Row row = sheet.createRow(rowNum++); // 1행부터 = 데이타 값
////                row.createCell(0).setCellValue(p.getPaymentDate());
//                Cell dateCell = row.createCell(0);
//                dateCell.setCellValue(p.getPaymentDate()); // java.util.Date 또는 java.sql.Date
//                dateCell.setCellStyle(dateCellStyle);
//                row.createCell(1).setCellValue(p.getUserEmail());
//                row.createCell(2).setCellValue(p.getPaymentPrice());
//                row.createCell(3).setCellValue(p.getProductName());
//            }
//
////            .xlsx → application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
////            .xls → application/vnd.ms-excel
////            .pdf → application/pdf
////            .csv → text/csv
//            // xlsx 엑셀 파일을 위한 공식 MIME 타입 지정
//            // 응답의 콘텐트가 어떤 타입인지 브라우저에 알려줌
//            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            // Content-Disposition : 파일 이름과 다운 방식을 지정하겠다는 뜻
//            // "attachment": 파일 다운로드 창을 띄움
//            // filename=...: 다운로드 창에서 보여질 기본 파일명을 설정
//            response.setHeader("Content-Disposition", "attachment;filename=paymentList.xlsx");
//
//            workbook.write(response.getOutputStream());
//            response.getOutputStream().flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
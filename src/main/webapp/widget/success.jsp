<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.util.Base64.Encoder" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.net.URL" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.parser.ParseException" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.Reader" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.Set" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // ------ 결제 승인 API 호출 ------
    // 개발자센터 시크릿 키 설정
    String secretKey = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6:";
    Encoder encoder = Base64.getEncoder();
    byte[] encodedBytes = encoder.encode(secretKey.getBytes("UTF-8"));
    String authorizations = "Basic " + new String(encodedBytes, 0, encodedBytes.length);

    // 요청 파라미터
    String orderId = request.getParameter("orderId");
    String paymentKey = request.getParameter("paymentKey");
    String amount = request.getParameter("amount");
    String productName = request.getParameter("productName");
    String orderName = request.getParameter("orderName");
    System.out.println("Patameter orderName: ["+productName+"]");

    paymentKey = URLEncoder.encode(paymentKey, "UTF-8");

    // API URL 설정
    URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestProperty("Authorization", authorizations);
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setRequestMethod("POST");
    connection.setDoOutput(true);

    // 요청 데이터 생성
    JSONObject obj = new JSONObject();
    obj.put("paymentKey", paymentKey);
    obj.put("orderId", orderId);
    obj.put("amount", amount);
    obj.put("productName", productName);
    obj.put("orderName", orderName);

    //값 설정
    request.setAttribute("orderId", obj.get("orderId"));
    request.setAttribute("amount", obj.get("amount"));
    request.setAttribute("productName", obj.get("productName"));
    request.setAttribute("orderName", obj.get("orderName"));

    // 요청 데이터 전송
    OutputStream outputStream = connection.getOutputStream();
    outputStream.write(obj.toString().getBytes("UTF-8"));

    // 응답 코드 확인
    int code = connection.getResponseCode();
    boolean isSuccess = code == 200;

    // 응답 데이터 읽기
    InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();
    Reader reader = new InputStreamReader(responseStream, "UTF-8");
    JSONParser parser = new JSONParser();
    JSONObject jsonObject = (JSONObject) parser.parse(reader);
    responseStream.close();

    // 서버에서 처리한 값들
    request.setAttribute("isSuccess", isSuccess);   // 결제 성공 여부
    //로그
    System.out.println("orderId: ["+orderId+"]");
    System.out.println("productName: ["+productName+"]");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" type="text/css" href="style.css" />
    <link rel="icon" href="https://static.toss.im/icons/png/4x/icon-toss-logo.png" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>토스페이먼츠 샘플 프로젝트</title>
</head>
<body>
    <section>

        <c:choose>
            <c:when test="${isSuccess}">
                <script>
                    // 서버에 전송할 데이터 생성
                    function getQueryParam(name) {
                        const params = new URLSearchParams(window.location.search);
                        console.log("쿼리 파라미터:", params.toString()); // 전체 파라미터 확인
                        return params.get(name) || "defaultValue";
                    }
                    console.log(window.location.search);
                    const productNum = getQueryParam("productNum");
                    const encodedProductNum = encodeURIComponent(productNum);  // 인코딩된 값
                    console.log("전송할 데이터:", `productNum=`+encodedProductNum);

                    fetch("payment.do", {
                        method: "POST",
                        headers: { "Content-Type": "application/x-www-form-urlencoded" },
                        body: `productNum=`+encodedProductNum   //
                    })
                        .then(response => response.text())
                        .then(data => console.log("결제 성공 처리 완료:", data))
                        .catch(error => console.error("결제 처리 오류:", error));
                </script>
                <div class="box_section" style="width: 600px">
                    <img width="100px" src="https://static.toss.im/illusts/check-blue-spot-ending-frame.png" />
                    <h2>결제를 완료했어요</h2>

                    <div class="p-grid typography--p" style="margin-top: 50px">
                        <div class="p-grid-col text--left"><b>결제금액: ${amount}</b></div>
                        <div class="p-grid-col text--right" id="amount">${jsonObject.amount}</div>
                    </div>
                    <div class="p-grid typography--p" style="margin-top: 10px">
                        <div class="p-grid-col text--left"><b>결제 상품: ${productName}</b></div>
                        <div class="p-grid-col text--right" id="productName" style="white-space: initial; width: 250px">${jsonObject.paymentKey}</div>
                    </div>
                    <div class="p-grid typography--p" style="margin-top: 10px">
                        <div class="p-grid-col text--left"><b>주문번호: ${orderId}</b></div>
                        <div class="p-grid-col text--right" id="orderId">${jsonObject.orderId}</div>
                    </div>
                    <!--
                    <div class="p-grid typography--p" style="margin-top: 10px">
                        <div class="p-grid-col text--left"><b>paymentKey</b></div>
                        <div class="p-grid-col text--right" id="paymentKey" style="white-space: initial; width: 250px">${jsonObject.paymentKey}</div>
                    </div>
                    -->
                    <div class="p-grid" style="margin-top: 30px">
                        <!--
                        <button class="button p-grid-col5" onclick="location.href='https://docs.tosspayments.com/guides/payment/integration';">연동 문서</button>
                        -->
                        <button class="button p-grid-col5" onclick="location.href='mainPage.do';" style="background-color: #e8f3ff; color: #1b64da">메인으로 돌아가기</button>
                    </div>
                </div>
<!--
                <div class="box_section" style="width: 600px; text-align: left">
                    <b>Response Data :</b>
                    <div id="response">
                    <pre>
                <c:forEach var="key" items="${keyList}">
                    ${key}: ${jsonObject[key]}
                </c:forEach>
                    </pre>
                    </div>
                </div>
                -->
            </c:when>
            <c:otherwise>
                <div id="info" class="box_section" style="width: 600px">
                    <img width="100px" src="https://static.toss.im/lotties/error-spot-no-loop-space-apng.png" />
                    <h2>결제를 실패했어요</h2>

                    <div class="p-grid typography--p" style="margin-top: 50px">
                        <div class="p-grid-col text--left"><b>에러메시지</b></div>
                        <div class="p-grid-col text--right" id="message">${jsonObject.message}</div>
                    </div>
                    <div class="p-grid typography--p" style="margin-top: 10px">
                        <div class="p-grid-col text--left"><b>에러코드</b></div>
                        <div class="p-grid-col text--right" id="code">${jsonObject.code}</div>
                    </div>

                    <div class="p-grid">
                        <!--
                        <button class="button p-grid-col5" onclick="location.href='https://docs.tosspayments.com/guides/payment/integration';">연동 문서</button>
                        -->
                        <button class="button p-grid-col5" onclick="location.href='mainPage.do';" style="background-color: #e8f3ff; color: #1b64da">메인으로</button>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>

    </section>
</body>
</html>

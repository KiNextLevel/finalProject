package com.example.common;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class JDBCUtil {

    @Value("${spring.datasource.driver-class-name}")
    private String driverNameProd;
    @Value("${spring.datasource.url}")
    private String urlProd;
    @Value("${spring.datasource.username}")
    private String userProd;
    @Value("${spring.datasource.password}")
    private String passProd;

    private static String driverName;
    private static String url;
    private static String userName;
    private static String password;

    @PostConstruct
    public void init() {
        driverName = driverNameProd;
        url = urlProd;
        userName = userProd;
        password = passProd;
    }
    public static Connection connect() {
        Connection conn = null;
        try {
            // 1. 드라이버 연결(메모리에 데이터 적재)
            Class.forName(driverName);
            System.out.println("드라이버 연결 성공");
            // 2. conn 연결
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("conn 연결 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void disconnect(Connection conn, PreparedStatement pstmt) {
        // 4. DB 연결 해제
        try {
            // null 체크 추가
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
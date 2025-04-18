package com.example.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtil {

    static final String driverName = "oracle.jdbc.driver.OracleDriver";
    static final String url = "jdbc:oracle:thin:@localhost:1521:XE";

    static final String userName = "root";
    // db pw desktop은 030414, ki는 1234
    static final String password = "1234";

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
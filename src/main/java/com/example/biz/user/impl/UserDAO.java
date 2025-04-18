package com.example.biz.user.impl;

import com.example.biz.user.UserVO;
import com.example.common.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {
    // 아이디 중복 검사(네이버, 카카오 등 같은 이메일이더라도 소셜 타입 다르면 회원가입 가능)
    private final String SELECTONE_CHECK = "SELECT MEMBER_EMAIL FROM MEMBER WHERE MEMBER_EMAIL = ? AND MEMBER_SOCIAL_TYPE = ?";

    // 소셜 로그인
    private final String SELECTONE = "SELECT MEMBER_EMAIL, MEMBER_PASSWORD, MEMBER_ROLE, MEMBER_PREMIUM FROM MEMBER WHERE MEMBER_EMAIL = ? AND MEMBER_PASSWORD = ? AND MEMBER_SOCIAL_TYPE = ?";

    // 일반 로그인
    private final String SELECTONE_NONSOCIAL = "SELECT MEMBER_EMAIL, MEMBER_PASSWORD, MEMBER_ROLE, MEMBER_PREMIUM FROM MEMBER WHERE MEMBER_EMAIL = ? AND MEMBER_PASSWORD = ?";

    // 해당 유저 전체 정보 불러오기
    private final String SELECTONE_MEMBERINFO = "SELECT * FROM MEMBER WHERE MEMBER_EMAIL = ?";

    // 유저 위도, 경도 정보 불러오기
    private final String SELECTONE_LOCATION = "SELECT MEMBER_EMAIL, MEMBER_LATITUDE, MEMBER_LONGITUDE FROM MEMBER WHERE MEMBER_EMAIL = ?";

    // 유저 전체 정보 불러오기
    private final String SELECTALL = "SELECT * FROM MEMBER ORDER BY MEMBER_REGDATE DESC";

    // 유저 선호 취향 정보 불러오기
    private final String SELCETALL_FAVORITE = "SELECT * FROM PREFERENCE P LEFT JOIN MEMBER U ON P.PREFERENCE_MEMBER_EMAIL = U.MEMBER_EMAIL WHERE U.MEMBER_EMAIL = ?";

    // 사용자가 참가 중인 이벤트 목록 불러오기
    private final String SELECTALL_EVENT = "SELECT * FROM PARTICIPANT P LEFT JOIN MEMBER U ON P.PARTICIPANT_MEMBER_EMAIL = U.MEMBER_EMAIL WHERE U.MEMBER_EMAIL = ?";

    // 유저의 토큰 잔액 정보 불러오기
    private final String SELECTALL_TOKEN = "SELECT MEMBER_TOKEN FROM MEMBER WHERE MEMBER_EMAIL = ?";

    // 유저의 결제한 상품 목록 불러오기
    private final String SELECTALL_PRODUCT = "SELECT * FROM PAYMENT P LEFT JOIN MEMBER U ON P.PAYMENT_MEMBER_EMAIL = U.MEMBER_EMAIL WHERE U.MEMBER_EMAIL = ?";

    // 블랙리스트 유저 불러오기(관리자용)
    private final String SELECTALL_BLACK = "SELECT * FROM MEMBER WHERE MEMBER_ROLE = 2";

    // 회원가입(정보 다 입력) - social_type 컬럼 추가, MEMBER_LATITUDE, MEMBER_LONGITUDE 컬럼 추가
    private final String INSERT = "INSERT INTO MEMBER (MEMBER_EMAIL, MEMBER_PASSWORD, MEMBER_NICKNAME, MEMBER_PHONE, MEMBER_GENDER, MEMBER_BIRTH, MEMBER_HEIGHT, MEMBER_BODY, MEMBER_MBTI," +
            "MEMBER_PROFILE, MEMBER_EDUCATION, MEMBER_RELIGION, MEMBER_DRINK, MEMBER_SMOKE, MEMBER_JOB, MEMBER_REGION, MEMBER_LATITUDE, MEMBER_LONGITUDE, MEMBER_DESCRIPTION, MEMBER_NAME, MEMBER_SOCIAL_TYPE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


    // 회원가입(소개 뺴고, 프로필만 입력) - social_type 컬럼 추가, MEMBER_LATITUDE, MEMBER_LONGITUDE 컬럼 추가
    private final String INSERT_PROFILE = "INSERT INTO MEMBER (MEMBER_EMAIL, MEMBER_PASSWORD, MEMBER_NICKNAME, MEMBER_PHONE, MEMBER_GENDER, MEMBER_BIRTH, MEMBER_HEIGHT, MEMBER_BODY, MEMBER_MBTI," +
            "MEMBER_PROFILE, MEMBER_EDUCATION, MEMBER_RELIGION, MEMBER_DRINK, MEMBER_SMOKE, MEMBER_JOB, MEMBER_REGION, MEMBER_LATITUDE, MEMBER_LONGITUDE, MEMBER_NAME, MEMBER_SOCIAL_TYPE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";


    // 회원가입(프로필 빼고, 소개만 입력) - social_type 컬럼 추가
    private final String INSERT_DESCRIPTION = "INSERT INTO MEMBER (MEMBER_EMAIL, MEMBER_PASSWORD, MEMBER_NICKNAME, MEMBER_PHONE, MEMBER_GENDER, MEMBER_BIRTH, MEMBER_HEIGHT, MEMBER_BODY, MEMBER_MBTI," +
            "MEMBER_EDUCATION, MEMBER_RELIGION, MEMBER_DRINK, MEMBER_SMOKE, MEMBER_JOB, MEMBER_REGION, MEMBER_DESCRIPTION, MEMBER_NAME, MEMBER_SOCIAL_TYPE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    // 회원 정보 수정 - MEMBER_LATITUDE, MEMBER_LONGITUDE 컬럼 추가
    private final String UPDATE =
            "UPDATE MEMBER SET MEMBER_DESCRIPTION = ?,MEMBER_NICKNAME = ?, MEMBER_HEIGHT = ?, MEMBER_BODY = ?, MEMBER_EDUCATION = ?, MEMBER_JOB = ?, MEMBER_RELIGION = ?, " +
                    "MEMBER_REGION = ?, MEMBER_LATITUDE = ?, MEMBER_LONGITUDE = ?, MEMBER_MBTI = ?, MEMBER_DRINK = ?, MEMBER_SMOKE = ? WHERE MEMBER_EMAIL = ?";

    // 회원 ROLE 변경(사용자, 블랙, 탈퇴 등)
    private final String UPDATE_ROLE = "UPDATE MEMBER SET MEMBER_ROLE = ? WHERE MEMBER_EMAIL = ?";

    //토큰 추가
    private final String UPDATE_ADD_TOKEN = "UPDATE MEMBER SET MEMBER_TOKEN = ? WHERE MEMBER_EMAIL =?";

    // 회원 프로필사진 변경
    private final String UPDATE_PROFILE_IMAGE = "UPDATE MEMBER SET MEMBER_PROFILE = ? WHERE MEMBER_EMAIL = ?";

    // 회원 프리미엄 변경
    private final String UPDATE_PREMIUM = "UPDATE MEMBER SET MEMBER_PREMIUM = 1 WHERE MEMBER_EMAIL = ?";

    // 회원 탈퇴(유저 role 변경으로 바꿨지만 추후 유지보수 혹은 기능 추가를 위해 남겨둠)
    private final String DELETE = "DELETE FROM MEMBER WHERE MEMBER_EMAIL = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // 유저 전체 정보 불러오기
    // 유저 선호 취향 정보 불러오기
    // 참가 중인 이벤트 목록 불러오기
    // 토큰 잔액 정보 불러오기
    // 결제한 상품 목록 불러오기
    // 블랙리스트 유저 불러오기
    public ArrayList<UserVO> selectAll(UserVO userVO) {
        ArrayList<UserVO> datas = new ArrayList<>();
        try {
            conn = JDBCUtil.connect();
            // 유저 선호 취향 정보 불러오기
            if (userVO.getCondition().equals("SELECTALL")) {
                pstmt = conn.prepareStatement(SELECTALL);
            }
            // 유저 선호 취향 정보 불러오기
            else if (userVO.getCondition() != null && userVO.getCondition().equals("SELECTALL_FAVORITE")) {
                pstmt = conn.prepareStatement(SELCETALL_FAVORITE);
                pstmt.setString(1, userVO.getUserEmail());
            }
            // 사용자가 참가 중인 이벤트 목록 불러오기
            else if (userVO.getCondition() != null && userVO.getCondition().equals("SELECTALL_EVENT")) {
                pstmt = conn.prepareStatement(SELECTALL_EVENT);
                pstmt.setString(1, userVO.getUserEmail());
            }
            // 토큰 잔액 정보 불러오기
            else if (userVO.getCondition() != null && userVO.getCondition().equals("SELECTALL_TOKEN")) {
                pstmt = conn.prepareStatement(SELECTALL_TOKEN);
                pstmt.setString(1, userVO.getUserEmail());
            }
            // 결제한 상품 목록 불러오기
            else if (userVO.getCondition() != null && userVO.getCondition().equals("SELECTALL_PRODUCT")) {
                pstmt = conn.prepareStatement(SELECTALL_PRODUCT);
                pstmt.setString(1, userVO.getUserEmail());
            }
            // 블랙리스트 유저 불러오기
            else if (userVO.getCondition() != null && userVO.getCondition().equals("SELECTALL_BLACK")) {
                pstmt = conn.prepareStatement(SELECTALL_BLACK);
            }

            rs = pstmt.executeQuery();
            while (rs.next()) {
                UserVO data = new UserVO();
                data.setUserEmail(rs.getString("MEMBER_EMAIL"));
                data.setUserPassword(rs.getString("MEMBER_PASSWORD"));
                data.setUserNickname(rs.getString("MEMBER_NICKNAME"));
                data.setUserPhone(rs.getString("MEMBER_PHONE"));
                data.setUserRegdate(rs.getDate("MEMBER_REGDATE"));
                data.setUserGender(rs.getInt("MEMBER_GENDER") == 1);
                data.setUserBirth(rs.getString("MEMBER_BIRTH"));
                data.setUserHeight(rs.getInt("MEMBER_HEIGHT"));
                data.setUserBody(rs.getString("MEMBER_BODY"));
                data.setUserMbti(rs.getString("MEMBER_MBTI"));
                data.setUserProfile(rs.getString("MEMBER_PROFILE"));
                data.setUserEducation(rs.getString("MEMBER_EDUCATION"));
                data.setUserReligion(rs.getString("MEMBER_RELIGION"));
                data.setUserLatitude(rs.getDouble("MEMBER_LATITUDE"));  // 위도 추가
                data.setUserLongitude(rs.getDouble("MEMBER_LONGITUDE")); //경도 추가
                data.setUserDrink(rs.getInt("MEMBER_DRINK"));
                data.setUserSmoke(rs.getInt("MEMBER_SMOKE") == 1);
                data.setUserJob(rs.getString("MEMBER_JOB"));
                data.setUserRole(rs.getInt("MEMBER_ROLE"));
                data.setUserPremium(rs.getInt("MEMBER_PREMIUM") == 1);
                data.setUserToken(rs.getInt("MEMBER_TOKEN"));
                data.setUserRegion(rs.getString("MEMBER_REGION"));
                data.setUserDescription(rs.getString("MEMBER_DESCRIPTION"));
                data.setUserName(rs.getString("MEMBER_NAME"));
                datas.add(data);
            }
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // 로그인
    public UserVO selectOne(UserVO userVO) {
        UserVO data = null;
        try {
            conn = JDBCUtil.connect();

            // 조건 확인 및 적절한 쿼리 준비
            if (userVO.getCondition() != null) {
                // 아이디 중복 검사(네이버, 카카오 등 같은 이메일이더라도 소셜 타입 다르면 회원가입 가능)
                if (userVO.getCondition().equals("SELECTONE_CHECK")) {
                    pstmt = conn.prepareStatement(SELECTONE_CHECK);
                    pstmt.setString(1, userVO.getUserEmail());
                    pstmt.setString(2, userVO.getSocialType());
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        // 이메일이 존재하는 경우
                        data = new UserVO();
                        data.setUserEmail(rs.getString("MEMBER_EMAIL"));
                        System.out.println("이메일 중복 확인: 이미 존재하는 이메일입니다 - " + rs.getString("MEMBER_EMAIL"));
                    } else {
                        // 이메일이 존재하지 않는 경우
                        System.out.println("이메일 중복 확인: 사용 가능한 이메일입니다.");
                        return null; // 중복된 이메일이 없으면 null 반환
                    }
                }
                // 소셜 로그인
                else if (userVO.getCondition().equals("SELECTONE")) {
                    pstmt = conn.prepareStatement(SELECTONE);
                    pstmt.setString(1, userVO.getUserEmail());
                    pstmt.setString(2, userVO.getUserPassword());
                    pstmt.setString(3, userVO.getSocialType());

                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        data = new UserVO();
                        data.setUserEmail(rs.getString("MEMBER_EMAIL"));
                        data.setUserPassword(rs.getString("MEMBER_PASSWORD"));
                        data.setUserRole(rs.getInt("MEMBER_ROLE"));
                        data.setUserPremium(rs.getInt("MEMBER_PREMIUM") == 1);
                    }
                }
                // 일반 로그인
                else if (userVO.getCondition().equals("SELECTONE_NONSOCIAL")) {
                    pstmt = conn.prepareStatement(SELECTONE_NONSOCIAL);
                    pstmt.setString(1, userVO.getUserEmail());
                    pstmt.setString(2, userVO.getUserPassword());

                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        data = new UserVO();
                        data.setUserEmail(rs.getString("MEMBER_EMAIL"));
                        data.setUserPassword(rs.getString("MEMBER_PASSWORD"));
                        data.setUserRole(rs.getInt("MEMBER_ROLE"));
                        data.setUserPremium(rs.getInt("MEMBER_PREMIUM") == 1);
                    }
                }
                // 해당 유저 전체 정보 불러오기
                else if (userVO.getCondition().equals("SELECTONE_MEMBERINFO")) {
                    pstmt = conn.prepareStatement(SELECTONE_MEMBERINFO);
                    pstmt.setString(1, userVO.getUserEmail());

                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        data = new UserVO();
                        data.setUserEmail(rs.getString("MEMBER_EMAIL"));
                        data.setUserPassword(rs.getString("MEMBER_PASSWORD"));
                        data.setUserName(rs.getString("MEMBER_NAME"));
                        data.setUserNickname(rs.getString("MEMBER_NICKNAME"));
                        data.setUserPhone(rs.getString("MEMBER_PHONE"));
                        data.setUserRegdate(rs.getDate("MEMBER_REGDATE"));
                        data.setUserGender(rs.getInt("MEMBER_GENDER") == 1);
                        data.setUserBirth(rs.getString("MEMBER_BIRTH"));
                        data.setUserHeight(rs.getInt("MEMBER_HEIGHT"));
                        data.setUserBody(rs.getString("MEMBER_BODY"));
                        data.setUserMbti(rs.getString("MEMBER_MBTI"));
                        data.setUserProfile(rs.getString("MEMBER_PROFILE"));
                        data.setUserEducation(rs.getString("MEMBER_EDUCATION"));
                        data.setUserReligion(rs.getString("MEMBER_RELIGION"));
                        data.setUserLatitude(rs.getDouble("MEMBER_LATITUDE"));
                        data.setUserLongitude(rs.getDouble("MEMBER_LONGITUDE"));
                        data.setUserDrink(rs.getInt("MEMBER_DRINK"));
                        data.setUserSmoke(rs.getInt("MEMBER_SMOKE") == 1);
                        data.setUserJob(rs.getString("MEMBER_JOB"));
                        data.setUserRole(rs.getInt("MEMBER_ROLE"));
                        data.setUserPremium(rs.getInt("MEMBER_PREMIUM") == 1);
                        data.setUserToken(rs.getInt("MEMBER_TOKEN"));
                        data.setUserRegion(rs.getString("MEMBER_REGION"));
                        data.setUserDescription(rs.getString("MEMBER_DESCRIPTION"));

                        // social_type 필드 추가
                        try {
                            data.setSocialType(rs.getString("MEMBER_SOCIAL_TYPE"));
                        } catch (Exception e) {
                            // MEMBER_SOCIAL_TYPE 컬럼이 없는 경우 예외 처리
                            data.setSocialType(null);
                        }
                    }
                }
                // 유저 위도, 경도 정보 불러오기
                else if (userVO.getCondition().equals("SELECTONE_LOCATION")) {
                    pstmt = conn.prepareStatement(SELECTONE_LOCATION);
                    pstmt.setString(1, userVO.getUserEmail());

                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        data = new UserVO();
                        data.setUserEmail(rs.getString("MEMBER_EMAIL"));
                        data.setUserLatitude(rs.getDouble("MEMBER_LATITUDE"));
                        data.setUserLongitude(rs.getDouble("MEMBER_LONGITUDE"));
                    }
                }
                // 알 수 없는 조건인 경우 로그 출력 및 null 반환
                else {
                    System.out.println("알 수 없는 조건: " + userVO.getCondition());
                    return null;
                }
            }
            // 조건이 null인 경우 로그 출력
            else {
                System.out.println("조건이 null입니다.");
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("selectOne 메소드 예외 발생: " + e.getMessage());
            return null;
        } finally {
            // 리소스 정리
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            JDBCUtil.disconnect(conn, pstmt);
        }
    }



    // 회원가입
    public boolean insert(UserVO userVO) {
        try {
            conn = JDBCUtil.connect();
            // 회원가입 정보 전부 다 입력
            if (userVO.getCondition() != null && userVO.getCondition().equals("INSERT")) {
                pstmt = conn.prepareStatement(INSERT);
                pstmt.setString(1, userVO.getUserEmail());
                pstmt.setString(2, userVO.getUserPassword());
                pstmt.setString(3, userVO.getUserNickname());
                pstmt.setString(4, userVO.getUserPhone());
                pstmt.setInt(5, userVO.isUserGender() ? 1 : 0);
                pstmt.setString(6, userVO.getUserBirth());
                pstmt.setInt(7, userVO.getUserHeight());
                pstmt.setString(8, userVO.getUserBody());
                pstmt.setString(9, userVO.getUserMbti());
                pstmt.setString(10, userVO.getUserProfile());
                pstmt.setString(11, userVO.getUserEducation());
                pstmt.setString(12, userVO.getUserReligion());
                pstmt.setInt(13, userVO.getUserDrink());
                pstmt.setInt(14, userVO.isUserSmoke() ? 1 : 0);
                pstmt.setString(15, userVO.getUserJob());
                pstmt.setString(16, userVO.getUserRegion());
                pstmt.setDouble(17, userVO.getUserLatitude());
                pstmt.setDouble(18, userVO.getUserLongitude());
                pstmt.setString(19, userVO.getUserDescription());
                pstmt.setString(20, userVO.getUserName());
                pstmt.setString(21, userVO.getSocialType());

            }
            // 나머지 정보와 선택사항에서 프로필사진만 입력한 경우
            else if (userVO.getCondition() != null && userVO.getCondition().equals("INSERT_PROFILE")) {
                pstmt = conn.prepareStatement(INSERT_PROFILE);
                pstmt.setString(1, userVO.getUserEmail());
                pstmt.setString(2, userVO.getUserPassword());
                pstmt.setString(3, userVO.getUserNickname());
                pstmt.setString(4, userVO.getUserPhone());
                pstmt.setInt(5, userVO.isUserGender() ? 1 : 0);
                pstmt.setString(6, userVO.getUserBirth());
                pstmt.setInt(7, userVO.getUserHeight());
                pstmt.setString(8, userVO.getUserBody());
                pstmt.setString(9, userVO.getUserMbti());
                pstmt.setString(10, userVO.getUserProfile());
                pstmt.setString(11, userVO.getUserEducation());
                pstmt.setString(12, userVO.getUserReligion());
                pstmt.setInt(13, userVO.getUserDrink());
                pstmt.setInt(14, userVO.isUserSmoke() ? 1 : 0);
                pstmt.setString(15, userVO.getUserJob());
                pstmt.setString(16, userVO.getUserRegion());
                pstmt.setDouble(17, userVO.getUserLatitude());
                pstmt.setDouble(18, userVO.getUserLongitude());
                pstmt.setString(19, userVO.getUserName());
                pstmt.setString(20, userVO.getSocialType());
            }
            // 나머지 정보와 선택사항에서 설명만 입력한 경우
            else if (userVO.getCondition() != null && userVO.getCondition().equals("INSERT_DESCRIPTION")) {
                pstmt = conn.prepareStatement(INSERT_DESCRIPTION);
                pstmt.setString(1, userVO.getUserEmail());
                pstmt.setString(2, userVO.getUserPassword());
                pstmt.setString(3, userVO.getUserNickname());
                pstmt.setString(4, userVO.getUserPhone());
                pstmt.setInt(5, userVO.isUserGender() ? 1 : 0);
                pstmt.setString(6, userVO.getUserBirth());
                pstmt.setInt(7, userVO.getUserHeight());
                pstmt.setString(8, userVO.getUserBody());
                pstmt.setString(9, userVO.getUserMbti());
                pstmt.setString(10, userVO.getUserEducation());
                pstmt.setString(11, userVO.getUserReligion());
                pstmt.setInt(12, userVO.getUserDrink());
                pstmt.setInt(13, userVO.isUserSmoke() ? 1 : 0);
                pstmt.setString(14, userVO.getUserJob());
                pstmt.setString(15, userVO.getUserRegion());
                pstmt.setDouble(16, userVO.getUserLatitude());
                pstmt.setDouble(17, userVO.getUserLongitude());
                pstmt.setString(18, userVO.getUserDescription());
                pstmt.setString(19, userVO.getUserName());
                pstmt.setString(20, userVO.getSocialType());
            }
            int result = pstmt.executeUpdate();
            System.out.println("insert 로그:" + result);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // 정보변경
    public boolean update(UserVO userVO) {
        try {
            conn = JDBCUtil.connect();
            // 회원정보 변경
            if (userVO.getCondition() != null && userVO.getCondition().equals("UPDATE")) {
                pstmt = conn.prepareStatement(UPDATE);
                pstmt.setString(1, userVO.getUserDescription());
                pstmt.setString(2, userVO.getUserNickname());
                pstmt.setInt(3, userVO.getUserHeight());
                pstmt.setString(4, userVO.getUserBody());
                pstmt.setString(5, userVO.getUserEducation());
                pstmt.setString(6, userVO.getUserJob());
                pstmt.setString(7, userVO.getUserReligion());
                pstmt.setString(8, userVO.getUserRegion());
                pstmt.setDouble(9, userVO.getUserLatitude());
                pstmt.setDouble(10, userVO.getUserLongitude());
                pstmt.setString(11, userVO.getUserMbti());
                pstmt.setInt(12, userVO.getUserDrink());
                pstmt.setBoolean(13, userVO.isUserSmoke());
                pstmt.setString(14, userVO.getUserEmail());
            }
            // ROLE 변경
            else if (userVO.getCondition() != null && userVO.getCondition().equals("UPDATE_ROLE")) {
                pstmt = conn.prepareStatement(UPDATE_ROLE);
                pstmt.setInt(1, userVO.getUserRole());
                pstmt.setString(2, userVO.getUserEmail());
            }
            // 프로필사진 변경
            else if(userVO.getCondition() != null && userVO.getCondition().equals("UPDATE_PROFILE_IMAGE")) {
                pstmt = conn.prepareStatement(UPDATE_PROFILE_IMAGE);
                pstmt.setString(1, userVO.getUserProfile());
                pstmt.setString(2, userVO.getUserEmail());
            }
            // 프리미엄 변경
            else if(userVO.getCondition() != null && userVO.getCondition().equals("UPDATE_PREMIUM")){
                pstmt = conn.prepareStatement(UPDATE_PREMIUM);
                pstmt.setString(1, userVO.getUserEmail());
            }
            // 토큰 결제시 토큰 수 변경
            else if(userVO.getCondition().equals("UPDATE_ADD_TOKEN")){
                pstmt = conn.prepareStatement(UPDATE_ADD_TOKEN);
                pstmt.setInt(1, userVO.getUserToken());
                pstmt.setString(2, userVO.getUserEmail());
            }
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // x
    private boolean delete(UserVO userVO) {
        return false;
    }
}

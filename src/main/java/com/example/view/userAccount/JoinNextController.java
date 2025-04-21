package com.example.view.userAccount;


import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;
import com.example.common.GeoCodingUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
public class JoinNextController {
    @Autowired
    private UserService userService;

    // JoinNextAction
    @GetMapping("/joinNext.do")
    public String joinNextPage() {
        System.out.println("LOG : JOIN NEXT CONTROLLER - JOIN NEXT PAGE METHOD");
        return "Metronic-Shop-UI-master/theme/JoinPage";
    }

    @PostMapping("/joinNext.do")
    public String joinNext(UserVO userVO, @RequestParam(value = "userProfile", required = false) MultipartFile userProfileFile,
            HttpServletRequest request, HttpSession session, Model model) {

        System.out.println("LOG : JOIN NEXT CONTROLLER - JOIN NEXT METHOD");

        try {
            // 파일 업로드 처리
            String filePathForDB = null;
            if (userProfileFile != null && !userProfileFile.isEmpty()) {
                filePathForDB = processFileUpload(userProfileFile, request);
            }

            // 세션에서 기본 정보 가져와서 설정
            userVO.setUserEmail((String)session.getAttribute("userEmail"));
            userVO.setUserPassword((String)session.getAttribute("userPassword"));
            userVO.setUserName((String)session.getAttribute("userName"));

            // 소셜 로그인 타입 설정
            String socialType = (String)session.getAttribute("socialType");
            if (socialType != null && !socialType.isEmpty()) {
                userVO.setSocialType(socialType); // 소셜 로그인인 경우
            } else {
                userVO.setSocialType("normal"); // 일반 회원가입인 경우 'normal'로 설정
            }

            // 성별 설정 - RequestParam으로 받은 값을 UserVO에 설정
            String genderStr = request.getParameter("userGender");
            if (genderStr != null && !genderStr.isEmpty()) {
                userVO.setUserGender(Integer.parseInt(genderStr) == 1); // 0이면 true, 1이면 false
            }

            // 키 설정
            String heightStr = request.getParameter("userHeight");
            if (heightStr != null && !heightStr.isEmpty()) {
                userVO.setUserHeight(Integer.parseInt(heightStr));
            }

            // 음주 설정
            String drinkStr = request.getParameter("userDrink");
            if (drinkStr != null && !drinkStr.isEmpty()) {
                userVO.setUserDrink(Integer.parseInt(drinkStr));
            }

            // 흡연 설정
            String smokeStr = request.getParameter("userSmoke");
            if (smokeStr != null && !smokeStr.isEmpty()) {
                userVO.setUserSmoke(Integer.parseInt(smokeStr) == 0); // 0이면 true, 1이면 false
            }

            // 프로필 사진 경로 설정
            if (filePathForDB != null) {
                userVO.setUserProfile(filePathForDB);
            }

            // 주소 기반 위도/경도 설정
            try {
                // 회원가입 시 입력된 주소를 가져옴
                String address = userVO.getUserRegion();

                if (address != null && !address.isEmpty()) {
                    // GeoCodingUtil을 사용해서 해당 주소를 위도/경도로 변환
                    double[] coords = GeoCodingUtil.getCoordinatesFromAddress(address);

                    // 소수점 4자리까지만 저장되게 하기 (반올림 처리)
                    double lat = Math.round(coords[0] * 10000) / 10000.0;
                    double lng = Math.round(coords[1] * 10000) / 10000.0;

                    // 변환된 위도, 경도 값을 userVO에 저장
                    userVO.setUserLatitude(lat);
                    userVO.setUserLongitude(lng);

                    System.out.println("위도/경도 설정됨: " + lat + ", " + lng);
                }
            } catch (Exception e) {
                System.out.println("주소 → 위경도 변환 실패! 기본값 0.0으로 저장됨");
                // 변환 실패 시 기본값 저장
                userVO.setUserLatitude(0.0);
                userVO.setUserLongitude(0.0);
            }

            // 회원가입 조건 설정
            if (userVO.getUserProfile() != null && userVO.getUserDescription() != null && !userVO.getUserDescription().isEmpty()) {
                userVO.setCondition("INSERT");
            } else if (userVO.getUserProfile() != null) {
                userVO.setCondition("INSERT_PROFILE");
            } else if (userVO.getUserDescription() != null && !userVO.getUserDescription().isEmpty()) {
                userVO.setCondition("INSERT_DESCRIPTION");
            } else {
                userVO.setCondition("INSERT");
            }

            System.out.println("JOIN NEXT CONTROLLER userVO[" + userVO + "]");

            // 회원가입 처리
            boolean result = userService.insert(userVO);

            if (result) {
                // 세션에서 기본 정보 제거
                session.removeAttribute("userPassword");
                session.removeAttribute("userName");
                session.removeAttribute("socialType");

                // 회원가입 성공
                model.addAttribute("userVO", userVO);
                model.addAttribute("msg", "회원가입 성공!");
                model.addAttribute("flag", true);
                model.addAttribute("url", "userPreferencePage.do");

                return "Metronic-Shop-UI-master/theme/Alert";
            } else {
                // 회원가입 실패
                model.addAttribute("msg", "회원가입 실패!");
                model.addAttribute("flag", false);

                return "Metronic-Shop-UI-master/theme/Alert";
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "회원가입 중 오류가 발생했습니다!");
            model.addAttribute("flag", false);

            return "Metronic-Shop-UI-master/theme/Alert";
        }
    }

    // 파일 업로드 처리 메서드
    private String processFileUpload(MultipartFile file, HttpServletRequest request) throws Exception {
        String filePathForDB = null;

        try {
            // 파일 업로드 경로 설정
            String uploadPath = request.getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            if (file != null && !file.isEmpty()) {
                String fileName = file.getOriginalFilename();

                // 파일이 선택된 경우에만 처리
                if (fileName != null && !fileName.isEmpty()) {
                    // 파일 확장자 검사
                    String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    if (!ext.equals("jpg") && !ext.equals("jpeg") && !ext.equals("png")) {
                        throw new IllegalArgumentException("지원하지 않는 파일 형식입니다.");
                    }

                    // 고유한 파일명 생성
                    String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
                    File uploadedFile = new File(uploadPath, uniqueFileName);

                    // 파일 저장
                    file.transferTo(uploadedFile);

                    // DB에 저장할 파일 경로
                    filePathForDB = "/uploads/" + uniqueFileName;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return filePathForDB;
    }
}

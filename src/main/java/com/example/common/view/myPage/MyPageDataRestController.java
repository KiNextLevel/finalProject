package com.example.common.view.myPage;
// 마이페이지 읽기 전용(조회 전용)
// GET 방식으로 정보를 조회하는 API
import com.example.common.biz.participant.ParticipantService;
import com.example.common.biz.participant.ParticipantVO;
import com.example.common.biz.payment.PaymentService;
import com.example.common.biz.payment.PaymentVO;
import com.example.common.biz.preference.PreferenceService;
import com.example.common.biz.preference.PreferenceVO;
import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//
///**
// * MyPageRestController
// * - 데이터를 제공하거나 수정하는 API 담당
// * - @RestController는 반환하는 데이터가 JSON 형태로 자동 변환된다
// */
//@RestController
//public class MyPageRestController {
//
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private PreferenceService preferenceService;
//    @Autowired
//    private PaymentService paymentService;
//    @Autowired
//    private ParticipantService participantService;
//
//    //현재 로그인한 사용자 정보 가져오기
//    @GetMapping("/getUserInfo.do")
//    public UserVO getUserInfo(HttpSession session) {
//        String userEmail = (String) session.getAttribute("userEmail");
//
//        UserVO userVO = new UserVO();
//        userVO.setUserEmail(userEmail);
//        userVO.setCondition("SELECTONE_USERINFO");
//
//        return userService.getUser(userVO); // 사용자 정보 반환
//    }
//
//    // 현재 로그인한 사용자의 선호 정보 가져오기
//    @GetMapping("/getPreferenceInfo.do")
//    public PreferenceVO getPreferenceInfo(HttpSession session) {
//        String userEmail = (String) session.getAttribute("userEmail");
//
//        PreferenceVO preferenceVO = new PreferenceVO();
//        preferenceVO.setUserEmail(userEmail);
//        preferenceVO.setCondition("SELECTONE");
//
//        return preferenceService.getPreference(preferenceVO); // 선호 정보 반환
//    }
//
     // 현재 로그인한 사용자의 결제 내역 가져오기
//    @GetMapping("/getPaymentList.do")
//    public List<PaymentVO> getPaymentList(HttpSession session) {
//        String userEmail = (String) session.getAttribute("userEmail");
//
//        PaymentVO paymentVO = new PaymentVO();
//        paymentVO.setUserEmail(userEmail);
//        paymentVO.setCondition("SELECTALL_PRODUCTLIST");
//
//        return paymentService.getPaymentList(paymentVO); // 결제 리스트 반환
//    }
//
//    // 현재 로그인한 사용자의 이벤트 참가 내역 가져오기
//    @GetMapping("/getParticipantList.do")
//    public List<ParticipantVO> getParticipantList(HttpSession session) {
//        String userEmail = (String) session.getAttribute("userEmail");
//
//        ParticipantVO participantVO = new ParticipantVO();
//        participantVO.setParticipantUserEmail(userEmail);
//        participantVO.setCondition("SELECTALL_EVENTPRINT");
//
//        return participantService.getParticipantList(participantVO); // 이벤트 참가 리스트 반환
//    }
//
//    // 프로필 정보 수정 (자기소개, 닉네임 등)
//    @PostMapping("/updateProfile.do")
//    public Map<String, Object> updateProfile(HttpSession session,
//                                             @RequestBody UserVO userVO,
//                                             @RequestBody PreferenceVO preferenceVO) {
//        Map<String, Object> result = new HashMap<>();
//
//        String userEmail = (String) session.getAttribute("userEmail");
//
//        if (userEmail == null) {
//            result.put("success", false);
//            result.put("message", "로그인이 필요합니다.");
//            return result;
//        }
//
//        userVO.setUserEmail(userEmail);
//        userVO.setCondition("UPDATE");
//        preferenceVO.setUserEmail(userEmail);
//        preferenceVO.setCondition("UPDATE");
//
//        // 위도, 경도 변환 추가
//        try {
//            double[] coords = GeoCodingUtil.getCoordinatesFromAddress(userVO.getUserRegion());
//            double lat = Math.round(coords[0] * 10000) / 10000.0;
//            double lng = Math.round(coords[1] * 10000) / 10000.0;
//            userVO.setUserLatitude(lat);
//            userVO.setUserLongitude(lng);
//        } catch (Exception e) {
//            userVO.setUserLatitude(0.0);
//            userVO.setUserLongitude(0.0);
//        }
//
//        boolean userUpdateResult = userService.update(userVO);
//
//        PreferenceVO checkPreference = preferenceService.getPreference(preferenceVO);
//        boolean preferenceResult = true;
//
//        if (checkPreference == null) {
//            preferenceResult = preferenceService.insert(preferenceVO);
//        } else {
//            preferenceResult = preferenceService.update(preferenceVO);
//        }
//
//        if (userUpdateResult && preferenceResult) {
//            result.put("success", true);
//            result.put("message", "프로필이 성공적으로 업데이트되었습니다.");
//        } else {
//            result.put("success", false);
//            result.put("message", "프로필 업데이트 실패");
//        }
//
//        return result;
//    }
//
//    // 프로필 이미지 수정
//    @PostMapping("/updateProfileImage.do")
//    public Map<String, Object> updateProfileImage(
//            @RequestParam("profileImage") MultipartFile profileImage,
//            HttpSession session) {
//
//        Map<String, Object> result = new HashMap<>();
//        String userEmail = (String) session.getAttribute("userEmail");
//
//        if (userEmail == null) {
//            result.put("success", false);
//            result.put("message", "로그인이 필요합니다.");
//            return result;
//        }
//
//        if (profileImage == null || profileImage.isEmpty()) {
//            result.put("success", false);
//            result.put("message", "파일이 선택되지 않았습니다.");
//            return result;
//        }
//
//        if (profileImage.getSize() > 5 * 1024 * 1024) {
//            result.put("success", false);
//            result.put("message", "파일 크기는 5MB 이하여야 합니다.");
//            return result;
//        }
//
//        if (!profileImage.getContentType().startsWith("image/")) {
//            result.put("success", false);
//            result.put("message", "이미지 파일만 업로드 가능합니다.");
//            return result;
//        }
//
//        try {
//            String uploadDir = new File("src/main/webapp/uploads/profiles").getAbsolutePath();
//            File uploadDirFile = new File(uploadDir);
//            if (!uploadDirFile.exists()) {
//                uploadDirFile.mkdirs();
//            }
//
//            String originalFilename = profileImage.getOriginalFilename();
//            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//            String newFileName = UUID.randomUUID().toString() + extension;
//            String fullPath = uploadDir + File.separator + newFileName;
//
//            profileImage.transferTo(new File(fullPath)); // 파일 저장
//
//            String webPath = "/uploads/profiles/" + newFileName;
//
//            // DB 업데이트
//            UserVO userVO = new UserVO();
//            userVO.setUserEmail(userEmail);
//            userVO.setUserProfile(webPath);
//            userVO.setCondition("UPDATE_PROFILE_IMAGE");
//
//            boolean updateResult = userService.update(userVO);
//
//            if (updateResult) {
//                session.setAttribute("userProfile", webPath);
//                result.put("success", true);
//                result.put("message", "프로필 이미지가 성공적으로 업데이트되었습니다.");
//            } else {
//                result.put("success", false);
//                result.put("message", "프로필 이미지 업데이트에 실패했습니다.");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.put("success", false);
//            result.put("message", "프로필 이미지 처리 중 오류 발생: " + e.getMessage());
//        }
//
//        return result;
//    }
//}

@RestController
public class MyPageDataRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private PreferenceService preferenceService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ParticipantService participantService;

    @GetMapping("/api/getUserInfo")
    public Map<String, Object> getUserInfo(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        String userEmail = (String) session.getAttribute("userEmail");

        if (userEmail == null) {
            result.put("status", "fail");
            result.put("message", "로그인이 필요합니다.");
            return result;
        }

        UserVO userVO = new UserVO();
        userVO.setUserEmail(userEmail);
        userVO.setCondition("SELECTONE_USERINFO");

        PreferenceVO preferenceVO = new PreferenceVO();
        preferenceVO.setUserEmail(userEmail);
        preferenceVO.setCondition("SELECTONE");

        PaymentVO paymentVO = new PaymentVO();
        paymentVO.setUserEmail(userEmail);
        paymentVO.setCondition("SELECTALL_PRODUCTLIST");

        ParticipantVO participantVO = new ParticipantVO();
        participantVO.setParticipantUserEmail(userEmail);
        participantVO.setCondition("SELECTALL_EVENTPRINT");

        UserVO userDTO = userService.getUser(userVO);
        PreferenceVO preferenceDTO = preferenceService.getPreference(preferenceVO);
        List<PaymentVO> paymentList = paymentService.getPaymentList(paymentVO);
        List<ParticipantVO> participantList = participantService.getParticipantList(participantVO);

        result.put("status", "success");
        result.put("userDTO", userDTO);
        result.put("preferenceDTO", preferenceDTO);
        result.put("paymentList", paymentList);
        result.put("participantList", participantList);

        return result;
    }
}


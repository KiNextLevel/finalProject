package com.example.view.myPage;

import com.example.biz.participant.ParticipantService;
import com.example.biz.participant.ParticipantVO;
import com.example.biz.payment.PaymentService;
import com.example.biz.payment.PaymentVO;
import com.example.biz.preference.PreferenceService;
import com.example.biz.preference.PreferenceVO;
import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
public class MyPageController {
    @Autowired
    private UserService userService;
    @Autowired
    private PreferenceService preferenceService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ParticipantService participantService;

    //정보수정 페이지 이동
    @GetMapping("updateProfilePage.do")
    public String updateProfilePage(Model model, HttpSession session, UserVO userVO,
                                    PreferenceVO preferenceVO) {
        System.out.println("CTRL 로그: UpdateProfilePageAction");

        String userEmail = (String) session.getAttribute("userEmail");

        if (userEmail == null) {
            // 로그인되지 않은 경우
            System.out.println("UpdateProfilePage Action Log: userEmail is null");
//            forward.setPath("loginPage.do");
//            forward.setRedirect(true);
//            return forward;
            return "redirect:loginPage.do";
        }

        userVO.setUserEmail(userEmail);
        userVO.setCondition("SELECTONE_USERINFO"); // 조건 설정 추가

        preferenceVO.setUserEmail(userEmail);
        preferenceVO.setCondition("SELECTONE");

        userVO = userService.getUser(userVO);
        preferenceVO = preferenceService.getPreference(preferenceVO);

        if (userVO != null) {
//            request.setAttribute("userDTO", user); // 첫 번째 사용자 정보
//            request.setAttribute("preferenceDTO", preference);
//            forward.setPath("/Metronic-Shop-UI-master/theme/MyPageEdit.jsp");
//            forward.setRedirect(false);
            model.addAttribute("userVO", userVO);
            model.addAttribute("preferenceVO", preferenceVO);
            return "/Metronic-Shop-UI-master/theme/MyPageEdit";
        } else {
            System.out.println("마이페이지 로그[사용자 정보 없음]");
            // 사용자 정보가 없을 때 처리
//            forward.setPath("loginPage.do");
//            forward.setRedirect(true);
        return "redirect:loginPage.do";
        }
        //return forward;
    }

    //프로필 사진 수정
    @PostMapping("/updateProfileImage.do")
    public String updateProfileImage(
            @RequestParam("profileImage") MultipartFile profileImage,
            HttpSession session,Model model, UserVO userVO) {

        System.out.println("CTRL 로그: UpdateProfileImageAction - 시작");

        String userEmail = (String) session.getAttribute("userEmail");
        System.out.println("CTRL 로그: userEmail = " + userEmail);

        if (userEmail == null) {
            model.addAttribute("message", "로그인이 필요합니다.");
            return "redirect:/Metronic-Shop-UI-master/theme/myPage.do";
        }

        if (profileImage == null || profileImage.isEmpty()) {
            model.addAttribute("message", "파일이 선택되지 않았습니다.");
            return "redirect:/Metronic-Shop-UI-master/theme/myPage.do";
        }

        if (profileImage.getSize() > 5 * 1024 * 1024) {
            model.addAttribute("message", "파일 크기는 5MB 이하여야 합니다.");
            return "redirect:/Metronic-Shop-UI-master/theme/myPage.do";
        }

        if (!profileImage.getContentType().startsWith("image/")) {
            model.addAttribute("message", "이미지 파일만 업로드 가능합니다.");
            return "redirect:/Metronic-Shop-UI-master/theme/myPage.do";
        }

        try {
            String uploadDir = new File("src/main/webapp/uploads/profiles").getAbsolutePath();
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            String originalFilename = profileImage.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + extension;
            String fullPath = uploadDir + File.separator + newFileName;

            profileImage.transferTo(new File(fullPath)); // Spring 방식 저장

            String webPath = "/uploads/profiles/" + newFileName;

            // DB 업데이트
            //UserDTO userDTO = new UserDTO();
            //userDTO.setUserEmail(userEmail);
            //userDTO.setUserProfile(webPath);
            userVO.setCondition("UPDATE_PROFILE_IMAGE");

            boolean updateResult = userService.update(userVO); // → @Autowired or Service로 처리 권장

            if (updateResult) {
                session.setAttribute("userProfile", webPath);
                model.addAttribute("message", "프로필 이미지가 성공적으로 업데이트되었습니다.");
            } else {
                model.addAttribute("message", "프로필 이미지 업데이트에 실패했습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "프로필 이미지 처리 중 오류 발생: " + e.getMessage());
        }

        return "redirect:/Metronic-Shop-UI-master/theme/myPage.do";
    }

    //프로필 정보 수정
    @PostMapping("updateProfile.do")
    public String UpdateProfile(HttpSession session, Model model, UserVO userVO,
                                PreferenceVO preferenceVO) {
        System.out.println("CTRL 로그: UpdateProfileAction");

        String userEmail = (String) session.getAttribute("userEmail");

        // 로그인 체크
        if (userEmail == null) {
            System.out.println("UpdateProfile Action 로그: userEmail is null");
//            forward.setPath("loginPage.do");
//            forward.setRedirect(true);
            return "redirect:loginPage.do";
        }

        try {
            // 폼에서 전송된 데이터 받기
            // 사용자 정보
//            String userDescription = request.getParameter("userDescription"); // 자기소개
//            String userNickname = request.getParameter("userNickname");
//            String userHeightStr = request.getParameter("userHeight"); // 키
//            String userBody = request.getParameter("userBody"); // 체형
//            String userEducation = request.getParameter("userEducation"); // 학력
//            String userJob = request.getParameter("userJob"); // 직업
//            String userReligion = request.getParameter("userReligion"); // 종교
//            String userRegion = request.getParameter("userRegion"); // 지역
//            String userMbti = request.getParameter("userMbti"); // MBTI
//            String userDrinkStr = request.getParameter("userDrink"); // 음주
//            String userSmokeStr = request.getParameter("userSmoke"); // 흡연



            // 선호 정보
//            Integer preferenceHeight = Integer.valueOf(request.getParameter("preferenceHeight"));
//            String preferenceBody = request.getParameter("preferenceBody");
//            String preferenceAge = request.getParameter("preferenceAge");


            // 폼에서 전송된 데이터 받기 부분 바로 아래에 추가
//            System.out.println("폼 데이터 로그: userDescription=" + userDescription);
//            System.out.println("폼 데이터 로그: userHeight=" + userHeightStr);
//            System.out.println("폼 데이터 로그: userBody=" + userBody);
//            System.out.println("폼 데이터 로그: userEducation=" + userEducation);
//            System.out.println("폼 데이터 로그: userJob=" + userJob);
//            System.out.println("폼 데이터 로그: userReligion=" + userReligion);
//            System.out.println("폼 데이터 로그: userRegion=" + userRegion);
//            System.out.println("폼 데이터 로그: userMbti=" + userMbti);
//            System.out.println("폼 데이터 로그: userDrink=" + userDrinkStr);
//            System.out.println("폼 데이터 로그: userSmoke=" + userSmokeStr);
//            System.out.println("폼 데이터 로그: preferenceHeight=" + request.getParameter("preferenceHeight"));
//            System.out.println("폼 데이터 로그: preferenceBody=" + preferenceBody);
//            System.out.println("폼 데이터 로그: preferenceAge=" + preferenceAge);

            // UserDTO 객체 생성 및 설정
//            UserDTO userDTO = new UserDTO();
//            userDTO.setUserEmail(userEmail);
//            userDTO.setUserDescription(userDescription); // 자기소개 설정
//            userDTO.setUserNickname(userNickname); // 닉네임

            //String newNickname = request.getParameter("userNickName");
            if (userVO.getUserNickname() != null && !userVO.getUserNickname().trim().isEmpty()) {
                System.out.println("userNickname 안가져와짐");
                return "redirect:myPage.do";
               // userDTO.setUserNickname(newNickname);
            }

            // 숫자형 데이터 변환 처리 (키)

//            if (userHeightStr != null && !userHeightStr.isEmpty()) {
//                try {
//                    int userHeight = Integer.parseInt(userHeightStr);
//                    userDTO.setUserHeight(userHeight);
//                } catch (NumberFormatException e) {
//                    System.out.println("UpdateProfile Action 로그: 키 변환 오류 - " + e.getMessage());
//                }
//            }

//            userDTO.setUserBody(userBody); // 체형
//            userDTO.setUserEducation(userEducation); // 학력
//            userDTO.setUserJob(userJob); // 직업
//            userDTO.setUserReligion(userReligion); // 종교
//            userDTO.setUserRegion(userRegion); // 지역
//            userDTO.setUserMbti(userMbti); // MBTI

            // 위도,경도 변환 코드 삽입
            try {
                String address = userVO.getUserRegion(); // 이제 null 아님
                double[] coords = GeoCodingUtil.getCoordinatesFromAddress(address);
                double lat = Math.round(coords[0] * 10000) / 10000.0;
                double lng = Math.round(coords[1] * 10000) / 10000.0;
                userVO.setUserLatitude(lat);
                userVO.setUserLongitude(lng);
                System.out.println("위도/경도 설정됨: " + lat + ", " + lng);
            } catch (Exception e) {
                System.out.println("주소 → 위경도 변환 실패! 기본값 0.0으로 저장됨");
                userVO.setUserLatitude(0.0);
                userVO.setUserLongitude(0.0);
            }



            // 음주 정보 처리
//            String userDrink = Integer.toString(userVO.getUserDrink());
//            if (userDrink != null) {
//                int userDrink = 0; // 기본값: 전혀 안 함
//                if (userDrink.equals("가끔 마심")) {
//                    userDrink = 1;
//                } else if (userDrinkStr.equals("자주 마심")) {
//                    userDrink = 2;
//                }
//                userDTO.setUserDrink(userDrink);
//            }

            // 흡연 정보 처리
//            if (userSmokeStr != null) {
//                boolean userSmoke = userSmokeStr.equals("yes");
//                userDTO.setUserSmoke(userSmoke);
//            }

//            userDTO.setCondition("UPDATE");

            // PreferenceDTO 객체 생성 및 설정
//            PreferenceDTO preferenceDTO = new PreferenceDTO();
//            preferenceDTO.setUserEmail(userEmail); // 사용자 이메일
//            preferenceDTO.setPreferenceHeight(preferenceHeight); // 선호 키
//            preferenceDTO.setPreferenceBody(preferenceBody); // 선호 체형
//            preferenceDTO.setPreferenceAge(preferenceAge); // 선호 나이
            preferenceVO.setCondition("UPDATE");

            // DAO 객체 생성
//            UserDAO userDAO = new UserDAO();
//            PreferenceDAO preferenceDAO = new PreferenceDAO();

            // 데이터베이스 업데이트
            boolean userUpdateResult = userService.update(userVO);

            // 선호 정보 존재 여부 확인
//            PreferenceDTO existingPreference = new PreferenceDTO();
//            existingPreference.setUserEmail(userEmail);
            preferenceVO.setCondition("SELECTONE");
            preferenceVO = preferenceService.getPreference(preferenceVO);

            boolean preferenceResult = true; // 기본값 설정

            if (preferenceVO == null) {
                // 선호 정보가 없으면 새로 생성
                System.out.println("UpdateProfile Action 로그: 선호 정보 신규 생성");
                preferenceResult = preferenceService.insert(preferenceVO);
            } else {
                // 선호 정보가 있으면 업데이트
                System.out.println("UpdateProfile Action 로그: 선호 정보 업데이트");
                preferenceResult = preferenceService.update(preferenceVO);
            }

            if (userUpdateResult && preferenceResult) {
                System.out.println("UpdateProfile Action 로그: 사용자 정보 및 선호 정보 업데이트 성공");

                // 세션에 성공 메시지 저장
                session.setAttribute("updateMessage", "프로필 정보가 성공적으로 업데이트되었습니다.");

                // 마이페이지로 리다이렉트
                return "redirect:mypagePage.do";
//                forward.setPath("myPage.do");
//                forward.setRedirect(true);
            } else {
                System.out.println("UpdateProfile Action 로그: 사용자 정보 또는 선호 정보 업데이트 실패");

                // 업데이트 실패 시 에러 메시지와 함께 수정 페이지로 돌아감
                //request.setAttribute("errorMessage", "프로필 정보 업데이트에 실패했습니다. 다시 시도해주세요.");
                model.addAttribute("errorMessage", "프로필 정보 업데이트에 실패했습니다. 다시 시도해주세요.");

                // 기존 입력 정보 유지를 위해 다시 설정
                //request.setAttribute("userDTO", userVO);
                //request.setAttribute("preferenceDTO", preferenceDTO);
                model.addAttribute("userDTO", userVO);
                model.addAttribute("preferenceDTO", preferenceVO);

//                forward.setPath("/Metronic-Shop-UI-master/theme/MyPageEdit.jsp");
//                forward.setRedirect(false);
                return "/Metronic-Shop-UI-master/theme/MyPageEdit";
            }

        } catch (Exception e) {
            System.out.println("UpdateProfile Action 로그: 예외 발생 - " + e.getMessage());
            e.printStackTrace();

            // 예외 발생 시 에러 메시지와 함께 수정 페이지로 돌아감
//            request.setAttribute("errorMessage", "프로필 정보 처리 중 오류가 발생했습니다: " + e.getMessage());
//            forward.setPath("/Metronic-Shop-UI-master/theme/MyPageEdit.jsp");
            model.addAttribute("errorMessage", "프로필 정보 처리 중 오류가 발생했습니다: " + e.getMessage());

            //forward.setRedirect(false);
            return "/Metronic-Shop-UI-master/theme/MyPageEdit";
        }

       // return forward;
    }

    //마이페이지 이동
    @GetMapping("/myPage.do")
    public String myPage(UserVO userVO, PreferenceVO preferenceVO, PaymentVO paymentVO,
                         ParticipantVO participantVO, HttpSession session, Model model) {
        //전달받을거: userEmail만?
        System.out.println("CTRL 로그: MyPageAction");
        String userEmail = (String) session.getAttribute("userEmail");

        if (userEmail == null) {
            // 로그인되지 않은 경우
            System.out.println("Mypage Action Log: userEmail is null");
            return "redirct:login.jsp";
        }

        userVO.setCondition("SELECTONE_USERINFO"); // 조건 설정 추가

        preferenceVO.setCondition("SELECTONE");    // 조건 설정 추가

        paymentVO.setCondition("SELECTALL_PRODUCTLIST");// 조건 설정 추가

        participantVO.setCondition("SELECTALL_EVENTPRINT");// 조건 설정 추가

        UserVO getUserVO = userService.getUser(userVO);//selectOne
        //PreferenceDTO preference = preferenceDAO.selectOne(preferenceDTO);
        PreferenceVO getPreferenceVO = preferenceService.getPreference(preferenceVO);
        List<PaymentVO> paymentList = paymentService.getPaymentList(paymentVO);
        System.out.println("paymentList: ["+paymentList+"]");
        List<ParticipantVO> participantList = participantService.getParticipantList(participantVO);
        for(ParticipantVO p : participantList) {
            System.out.println(p);
        }

        if (userService.getUser(userVO) != null) {
            //request.setAttribute("paymentList", paymentList);
            model.addAttribute("paymentList", paymentList);
            //request.setAttribute("userDTO", getUserVO); // 첫 번째 사용자 정보
            model.addAttribute("userVO", getUserVO);
            //request.setAttribute("preferenceDTO", getPreferenceVO);
            model.addAttribute("preferenceVO", getPreferenceVO);
            //request.setAttribute("participantList", participantList);
            model.addAttribute("participantList", participantList);
            //forward.setPath("/Metronic-Shop-UI-master/theme/MyPage.jsp");
            //forward.setRedirect(false);
            return "/Metronic-Shop-UI-master/theme/MyPage.jsp";
        } else {
            System.out.println("마이페이지 로그[사용자 정보 없음]");
            // 사용자 정보가 없을 때 처리
//            forward.setPath("loginPage.do");
//            forward.setRedirect(true);
            return "redirect:/login.do";
        }
    }
}

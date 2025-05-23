//package com.example.common.view.myPage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.example.common.biz.user.UserService;
//import com.example.common.biz.user.UserVO;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import java.io.File;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//
//@Controller
//public class MyPageUpdateProfileImageRestController {
//    @Autowired
//    private UserService userService;
//
//     //프로필 이미지 수정
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
package com.example.common.view.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;


@Controller
public class MyPageUpdateProfileImageController {
    @Autowired
    private UserService userService;

    //프로필 이미지 수정
    @PostMapping("/updateProfileImage.do")
    public String updateProfileImage(
            @RequestParam("profileImage") MultipartFile profileImage,
            HttpSession session, Model model) {

        String result = "/Metronic-Shop-UI-master/theme/Alert";
        String userEmail = (String) session.getAttribute("userEmail");
        model.addAttribute("url", "myPage.do");
        if (userEmail == null) {
            model.addAttribute("flag", false);
            model.addAttribute("msg", "로그인이 필요합니다.");
            return result;
        }

        if (profileImage == null || profileImage.isEmpty()) {
            model.addAttribute("flag", false);
            model.addAttribute("msg", "파일이 선택되지 않았습니다.");
            return result;
        }

        if (profileImage.getSize() > 5 * 1024 * 1024) {
            model.addAttribute("flag", false);
            model.addAttribute("msg", "파일 크기는 5MB 이하여야 합니다.");
            return result;
        }

        if (!profileImage.getContentType().startsWith("image/")) {
            model.addAttribute("flag", false);
            model.addAttribute("msg", "이미지 파일만 업로드 가능합니다.");
            return result;
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

            profileImage.transferTo(new File(fullPath)); // 파일 저장

            String webPath = "/uploads/profiles/" + newFileName;

            // DB 업데이트
            UserVO userVO = new UserVO();
            userVO.setUserEmail(userEmail);
            userVO.setUserProfile(webPath);
            userVO.setCondition("UPDATE_PROFILE_IMAGE");

            boolean updateResult = userService.update(userVO);

            if (updateResult) {
                session.setAttribute("userProfile", webPath);
                model.addAttribute("flag", true);
                model.addAttribute("msg", "프로필 이미지가 성공적으로 업데이트되었습니다.");
            } else {
                model.addAttribute("flag", false);
                model.addAttribute("msg", "프로필 이미지 업데이트에 실패했습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("flag", false);
            model.addAttribute("msg", "프로필 이미지 처리 중 오류 발생: " + e.getMessage());
        }

        return result;
    }
}

package com.example.view.mainPage;

import com.example.biz.alert.AlertService;
import com.example.biz.alert.AlertVO;
import com.example.biz.preference.PreferenceService;
import com.example.biz.preference.PreferenceVO;
import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageAction{
	@Autowired
	private UserService userService;
	@Autowired
	private PreferenceService preferenceService;
	@Autowired
	private AlertService alertService;

	@GetMapping("main")
	public String mainPage(HttpSession session, PreferenceVO preferenceVO, AlertVO alertVO, UserVO userVO , Model model) {
		System.out.println("CONT 로그: MAINPAGE ACTION 도착");
		String userEmail = (String) session.getAttribute("userEmail");
		int userRole = (int) session.getAttribute("userRole");
		String path = "/Metronic-Shop-UI-master/theme/Alert";
		// 선호취향 입력 안한 상태라면
		preferenceVO.setUserEmail(userEmail);
		preferenceVO = preferenceService.getPreference(preferenceVO);
		System.out.println("preferenceVO = " + preferenceVO);
		if (preferenceVO == null && userRole == 0) {
			model.addAttribute("msg", "먼저 선호 취향을 입력하시길 바랍니다.");
			model.addAttribute("url", "userPreferencePage.do");
			model.addAttribute("flag", true);
			return path;
		}

		// 알림 목록 mainpage로
		alertVO.setUserEmail(userEmail);
		List<AlertVO> alertDatas = alertService.getAlertList(alertVO);

		// 디버깅용 출력
		for (AlertVO alertVO1 : alertDatas) {
			System.out.println(alertVO1);
		}

		// JSON 변환
		JSONArray jsonArray = new JSONArray();

		if (alertDatas != null) {
			for (AlertVO data : alertDatas) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("alertNumber", data.getAlertNumber());
				jsonObject.put("userEmail", data.getUserEmail());
				jsonObject.put("alertContent", data.getAlertContent());
				jsonObject.put("alertDate", data.getAlertDate().toString()); // Date를 문자열로 변환
				jsonObject.put("alertIsWatch", data.isAlertIsWatch());
				jsonArray.add(jsonObject);
			}
		}
		// 세션에 JSON 문자열 저장
		session.setAttribute("alertDatasJson", jsonArray.toJSONString());
		// 기존 alertDatas도 유지 (임시)
		session.setAttribute("alertDatas", alertDatas);


		userVO.setUserEmail(userEmail);
		userVO.setCondition("SELECTALL");
		List<UserVO> userDatas = userService.getUserList(userVO);

		JSONArray userDatasJsonArray = new JSONArray();
		for (UserVO user : userDatas) {
			JSONObject userJson = new JSONObject();
			userJson.put("userEmail", user.getUserEmail());
			userJson.put("userNickname", user.getUserNickname());
			userJson.put("userRegdate", user.getUserRegdate() != null ? user.getUserRegdate().toString() : null);
			userJson.put("userGender", user.isUserGender()); // boolean
			userJson.put("userBirth", user.getUserBirth()); // String
			userJson.put("userHeight", user.getUserHeight()); // int
			userJson.put("userProfile", user.getUserProfile());
			userJson.put("userReligion", user.getUserReligion());
			userJson.put("userSmoke", user.isUserSmoke()); // boolean
			userJson.put("userRole", user.getUserRole());
			userJson.put("userRegion", user.getUserRegion());
			userJson.put("userDescription", user.getUserDescription());
			userJson.put("userName", user.getUserName());
			userJson.put("userLatitude", user.getUserLatitude()); // double
			userJson.put("userLongitude", user.getUserLongitude()); // double
			userDatasJsonArray.add(userJson);
		}

		String jsonUserDatas = userDatasJsonArray.toJSONString();
		model.addAttribute("userDatas", jsonUserDatas);

		path = "/Metronic-Shop-UI-master/theme/MainPage";
		return path;
	}
}

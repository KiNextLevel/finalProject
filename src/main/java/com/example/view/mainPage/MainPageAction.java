//package com.example.view.mainPage;
//
//import com.example.biz.preference.PreferenceService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.ArrayList;
//
//@Controller
//public class MainPageAction{
//
//	@Autowired
//	private PreferenceService preferenceService;
//
//	@Autowired
//	private
//
//	@GetMapping("main")
//	public String mainPage(HttpSession session, PreferenceVO preferenceVO) {
//		System.out.println("CONT 로그: MAINPAGE ACTION 도착");
//		String userEmail = (String) session.getAttribute("userEmail");
//		int userRole = (int) session.getAttribute("userRole");
//		// 선호취향 입력 안한 상태라면
//		preferenceVO.setUserEmail(userEmail);
//		preferenceDTO = preferenceDAO.selectOne(preferenceDTO);
//		System.out.println("preferenceDTO = " + preferenceDTO);
//		if (preferenceDTO == null && userRole == 0) {
//			request.setAttribute("msg", "먼저 선호 취향을 입력하시길 바랍니다.");
//			request.setAttribute("url", "userPreferencePage.do");
//			request.setAttribute("flag", true);
//			forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
//			forward.setRedirect(false);
//			return forward;
//		}
//
//		// 알림 목록 mainpage로
//		AlertDAO alertDAO = new AlertDAO();
//		AlertDTO alertDTO = new AlertDTO();
//		alertDTO.setUserEmail(userEmail);
//		ArrayList<AlertDTO> alertDatas = alertDAO.selectAll(alertDTO);
//
//		// 디버깅용 출력
//		for (AlertDTO alertDTO1 : alertDatas) {
//			System.out.println(alertDTO1);
//		}
//
//		// JSON 변환
//		JSONArray jsonArray = new JSONArray();
//
//		if (alertDatas != null) {
//			for (AlertDTO data : alertDatas) {
//				JSONObject jsonObject = new JSONObject();
//				jsonObject.put("alertNumber", data.getAlertNumber());
//				jsonObject.put("userEmail", data.getUserEmail());
//				jsonObject.put("alertContent", data.getAlertContent());
//				jsonObject.put("alertDate", data.getAlertDate().toString()); // Date를 문자열로 변환
//				jsonObject.put("alertIsWatch", data.isAlertIsWatch());
//				jsonArray.add(jsonObject);
//			}
//		}
//		// 세션에 JSON 문자열 저장
//		session.setAttribute("alertDatasJson", jsonArray.toJSONString());
//
//		// 기존 alertDatas도 유지 (임시)
//		session.setAttribute("alertDatas", alertDatas);
//
//		UserDAO userDAO = new UserDAO();
//		UserDTO userDTO = new UserDTO();
//		userDTO.setUserEmail(userEmail);
//		userDTO.setCondition("SELECTALL");
//		ArrayList<UserDTO> userDatas = userDAO.selectAll(userDTO);
//
//		JSONArray userDatasJsonArray = new JSONArray();
//		for (UserDTO user : userDatas) {
//			JSONObject userJson = new JSONObject();
//			userJson.put("userEmail", user.getUserEmail());
//			userJson.put("userNickname", user.getUserNickname());
//			userJson.put("userRegdate", user.getUserRegdate() != null ? user.getUserRegdate().toString() : null);
//			userJson.put("userGender", user.getUserGender()); // boolean
//			userJson.put("userBirth", user.getUserBirth()); // String
//			userJson.put("userHeight", user.getUserHeight()); // int
//			userJson.put("userProfile", user.getUserProfile());
//			userJson.put("userReligion", user.getUserReligion());
//			userJson.put("userSmoke", user.isUserSmoke()); // boolean
//			userJson.put("userRole", user.getUserRole());
//			userJson.put("userRegion", user.getUserRegion());
//			userJson.put("userDescription", user.getUserDescription());
//			userJson.put("userName", user.getUserName());
//			userJson.put("userLatitude", user.getUserLatitude()); // double
//			userJson.put("userLongitude", user.getUserLongitude()); // double
//			userDatasJsonArray.add(userJson);
//		}
//
//		String jsonUserDatas = userDatasJsonArray.toJSONString();
//		request.setAttribute("userDatas", jsonUserDatas);
//
//		forward.setPath("/Metronic-Shop-UI-master/theme/MainPage.jsp");
//		forward.setRedirect(false);
//		return forward;
//	}
//}

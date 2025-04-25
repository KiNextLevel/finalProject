package com.example.common.view.mainPage;

import com.example.common.biz.preference.PreferenceService;
import com.example.common.biz.preference.PreferenceVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
	@Autowired
	private PreferenceService preferenceService;

	@GetMapping("/mainPage.do")
	public String mainPage(HttpSession session, PreferenceVO preferenceVO, Model model) {
		// 선호취향 입력 안한 상태라면
		preferenceVO.setUserEmail((String) session.getAttribute("userEmail"));
		preferenceVO = preferenceService.getPreference(preferenceVO);
		System.out.println("CONT 로그: MAINPAGE ACTION 도착");
		System.out.println("preferenceVO = " + preferenceVO);
		String path = "/Metronic-Shop-UI-master/theme/Alert";
		if (preferenceVO == null && (int) session.getAttribute("userRole") == 0) {
			model.addAttribute("msg", "먼저 선호 취향을 입력하시길 바랍니다.");
			model.addAttribute("url", "userPreferencePage.do");
			model.addAttribute("flag", true);
		} else {
			path = "/Metronic-Shop-UI-master/theme/MainPage";
		}
		return path;
	}
}

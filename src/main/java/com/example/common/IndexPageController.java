package com.example.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class IndexPageController {
    @GetMapping("/index.do")
    public String index() {
        System.out.println("index");
        return "Metronic-Shop-UI-master/theme/Index"; // /Metronic-Shop-UI-master/theme/index.jsp를 렌더링
    }
}

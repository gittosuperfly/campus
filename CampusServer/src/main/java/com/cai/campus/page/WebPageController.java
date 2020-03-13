package com.cai.campus.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebPageController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }
}

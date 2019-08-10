package com.yczx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    // Login form
    @RequestMapping("pages/common/login.html")
    public String login() {
        return "pages/common/login";
    }

    // Login form with error
    @RequestMapping("pages/common/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "pages/common/login.html";
    }

}

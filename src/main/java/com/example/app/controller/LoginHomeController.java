package com.example.app.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.dto.AdminLoginForm;

@Controller
public class LoginHomeController {

    @GetMapping("/")
    public String loginHome() {
        return "redirect:/loginhome";
    }

    @GetMapping({"/loginhome", "/loginHome"})
    public String showLoginHome(HttpSession session, Model model) {
        // 管理者ログイン済なら管理者ホームへ
        if (session.getAttribute("loginId") != null) {
            return "redirect:/admins/club/home";
        }

        model.addAttribute("admin", new AdminLoginForm());
        return "loginhome";
    }
}
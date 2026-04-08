package com.example.app.controller;

import jakarta.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.domain.Member;
import com.example.app.domain.PasswordResetToken;
import com.example.app.dto.PasswordSetupForm;
import com.example.app.service.MemberService;
import com.example.app.service.PasswordResetService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members/password")
public class PasswordSetupController {

    private final PasswordResetService passwordResetService;
    private final MemberService memberService;

    @GetMapping("/setup")
    public String showForm(@RequestParam String token, Model model) {

        PasswordResetToken resetToken = passwordResetService.findValidToken(token);

        if (resetToken == null) {
            return "error/invalid-token";
        }

        PasswordSetupForm form = new PasswordSetupForm();
        form.setToken(token);

        model.addAttribute("form", form);
        return "members/setup-password";
    }

    @PostMapping("/setup")
    public String submit(
            @ModelAttribute("form") @Valid PasswordSetupForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "members/setup-password";
        }

        if (!form.getPassword().equals(form.getConfirmPassword())) {
            result.rejectValue(
                    "confirmPassword",
                    "error.confirmPassword",
                    "確認用パスワードが一致しません"
            );
            return "members/setup-password";
        }

        PasswordResetToken resetToken = passwordResetService.findValidToken(form.getToken());

        if (resetToken == null) {
            return "error/invalid-token";
        }

        Member member = memberService.getMemberById(resetToken.getMemberId());

        if (member == null) {
            return "error/invalid-token";
        }

        member.setEmail(form.getEmail());
        member.setLoginPass(BCrypt.hashpw(form.getPassword(), BCrypt.gensalt()));

        memberService.updateCredentials(member);

        passwordResetService.deleteToken(form.getToken());

        return "redirect:/members/club/home";
    }
}
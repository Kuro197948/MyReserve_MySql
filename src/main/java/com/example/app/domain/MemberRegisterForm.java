package com.example.app.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MemberRegisterForm {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;
}
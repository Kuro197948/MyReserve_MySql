package com.example.app.service;

import com.example.app.domain.Member;

public interface MemberAuthService {
    Member login(String email, String loginPass);
}
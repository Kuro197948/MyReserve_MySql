package com.example.app.service;




import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.domain.Admin;
import com.example.app.mapper.AdminMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Admin authenticate(String loginId, String loginPass) {
        Admin admin = adminMapper.selectByLoginId(loginId);

        if (admin == null) {
            return null;
        }

        if (!passwordEncoder.matches(loginPass, admin.getLoginPass())) {
            return null;
        }

        return admin;
    }
}
package com.example.app.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Member;
import com.example.app.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class MemberAuthServiceImpl implements MemberAuthService {

    private final MemberMapper memberMapper;

    @Override
    public Member login(String email, String loginPass) {

        Member member = memberMapper.selectByEmail(email);
        if (member == null) {
            return null;
        }

        if (member.getLoginPass() == null) {
            return null;
        }

        if (!BCrypt.checkpw(loginPass, member.getLoginPass())) {
            return null;
        }

        return member;
    }
}
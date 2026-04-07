package com.example.app.service;

import com.example.app.domain.Admin;

public interface AdminService {

    Admin authenticate(String loginId, String loginPass);

}
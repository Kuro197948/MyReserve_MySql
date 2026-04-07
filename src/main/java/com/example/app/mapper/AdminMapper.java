package com.example.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.domain.Admin;

@Mapper
public interface AdminMapper {

    Admin selectByLoginId(@Param("loginId") String loginId);
}
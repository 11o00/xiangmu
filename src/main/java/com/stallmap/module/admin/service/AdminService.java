package com.stallmap.module.admin.service;

import com.stallmap.module.admin.dto.AdminLoginRequest;
import com.stallmap.module.admin.entity.Admin;

import java.util.Map;

public interface AdminService {
    /**
     * 管理员登录
     */
    Map<String, Object> login(AdminLoginRequest request);
    
    /**
     * 管理员登出
     */
    void logout(String token);
    
    /**
     * 根据用户名获取管理员信息
     */
    Admin getAdminByUsername(String username);
}

package com.stallmap.module.admin.service.impl;

import com.stallmap.common.utils.JwtUtil;
import com.stallmap.module.admin.dto.AdminLoginRequest;
import com.stallmap.module.admin.entity.Admin;
import com.stallmap.module.admin.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    
    @Override
    public Map<String, Object> login(AdminLoginRequest request) {
        // 模拟管理员登录验证
        // 实际项目中应该从数据库查询并验证密码
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> adminInfo = new HashMap<>();
        
        // 模拟管理员数据
        adminInfo.put("id", 1L);
        adminInfo.put("username", "admin");
        adminInfo.put("name", "管理员");
        adminInfo.put("phone", "13800138000");
        
        // 生成JWT token
        String token = JwtUtil.generateToken(1L, "admin");
        
        result.put("token", token);
        result.put("adminInfo", adminInfo);
        
        return result;
    }
    
    @Override
    public void logout(String token) {
        // 模拟登出操作
        // 实际项目中应该将token加入黑名单
    }
    
    @Override
    public Admin getAdminByUsername(String username) {
        // 模拟获取管理员信息
        // 实际项目中应该从数据库查询
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setUsername(username);
        admin.setName("管理员");
        admin.setPhone("13800138000");
        admin.setStatus(1);
        return admin;
    }
}

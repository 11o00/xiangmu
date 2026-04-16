package com.stallmap.module.admin.service.impl;

import com.stallmap.module.admin.dto.UserQueryRequest;
import com.stallmap.module.admin.service.UserManageService;
import com.stallmap.module.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserManageServiceImpl implements UserManageService {
    
    @Override
    public Map<String, Object> getUserList(UserQueryRequest request) {
        // 模拟获取用户列表
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> users = new ArrayList<>();
        
        // 模拟数据
        for (int i = 0; i < request.getPageSize(); i++) {
            Map<String, Object> user = new HashMap<>();
            user.put("id", (request.getPage() - 1) * request.getPageSize() + i + 1);
            user.put("nickname", "用户" + i);
            user.put("phone", "138001380" + i);
            user.put("status", 1);
            user.put("createdAt", "2026-03-27 12:00:00");
            users.add(user);
        }
        
        result.put("list", users);
        result.put("total", 100);
        result.put("page", request.getPage());
        result.put("pageSize", request.getPageSize());
        
        return result;
    }
    
    @Override
    public void updateUserStatus(Long userId, Integer status) {
        // 模拟更新用户状态
        // 实际项目中应该更新数据库
    }
    
    @Override
    public void deleteUser(Long userId) {
        // 模拟删除用户
        // 实际项目中应该从数据库删除
    }
    
    @Override
    public byte[] exportUsers(UserQueryRequest request) {
        // 模拟导出用户数据
        // 实际项目中应该使用Apache POI生成Excel文件
        return new byte[0];
    }
}

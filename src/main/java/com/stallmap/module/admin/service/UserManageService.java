package com.stallmap.module.admin.service;

import com.stallmap.module.admin.dto.UserQueryRequest;
import com.stallmap.module.user.entity.User;

import java.util.List;
import java.util.Map;

public interface UserManageService {
    /**
     * 获取用户列表
     */
    Map<String, Object> getUserList(UserQueryRequest request);
    
    /**
     * 更新用户状态
     */
    void updateUserStatus(Long userId, Integer status);
    
    /**
     * 删除用户
     */
    void deleteUser(Long userId);
    
    /**
     * 导出用户数据
     */
    byte[] exportUsers(UserQueryRequest request);
}

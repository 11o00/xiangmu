package com.stallmap.module.admin.service;

import com.stallmap.module.admin.dto.SystemSettingsRequest;

import java.util.Map;

public interface SystemSettingsService {
    /**
     * 获取系统设置
     */
    Map<String, Object> getSystemSettings();
    
    /**
     * 更新系统设置
     */
    void updateSystemSettings(SystemSettingsRequest request);
}

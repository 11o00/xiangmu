package com.stallmap.module.admin.service.impl;

import com.stallmap.module.admin.dto.SystemSettingsRequest;
import com.stallmap.module.admin.service.SystemSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SystemSettingsServiceImpl implements SystemSettingsService {
    
    @Override
    public Map<String, Object> getSystemSettings() {
        // 模拟获取系统设置
        Map<String, Object> result = new HashMap<>();
        
        // 基础设置
        Map<String, Object> basicSettings = new HashMap<>();
        basicSettings.put("siteName", "流动摊位共享平台");
        basicSettings.put("siteLogo", "https://example.com/logo.png");
        basicSettings.put("contactEmail", "contact@stallmap.com");
        basicSettings.put("contactPhone", "13800138000");
        
        // 安全设置
        Map<String, Object> securitySettings = new HashMap<>();
        securitySettings.put("tokenExpireTime", 24); // 小时
        securitySettings.put("passwordMinLength", 6);
        securitySettings.put("enableCaptcha", true);
        
        // 通知设置
        Map<String, Object> notificationSettings = new HashMap<>();
        notificationSettings.put("enableEmailNotification", true);
        notificationSettings.put("enableSmsNotification", true);
        
        result.put("basicSettings", basicSettings);
        result.put("securitySettings", securitySettings);
        result.put("notificationSettings", notificationSettings);
        
        return result;
    }
    
    @Override
    public void updateSystemSettings(SystemSettingsRequest request) {
        // 模拟更新系统设置
        // 实际项目中应该更新数据库
    }
}

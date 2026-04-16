package com.stallmap.module.admin.dto;

import lombok.Data;

import java.util.Map;

@Data
public class SystemSettingsRequest {
    private Map<String, Object> basicSettings; // 基础设置
    private Map<String, Object> securitySettings; // 安全设置
    private Map<String, Object> notificationSettings; // 通知设置
}

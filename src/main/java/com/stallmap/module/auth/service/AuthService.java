package com.stallmap.module.auth.service;

import com.stallmap.module.auth.dto.*;
import com.stallmap.module.user.entity.User;
import java.util.Map;

public interface AuthService {
    /**
     * 账号密码登录
     */
    Map<String, Object> login(LoginRequest req);
    
    /**
     * 微信登录
     */
    Map<String, Object> wxLogin(WxLoginRequest req);
    
    /**
     * 用户注册
     */
    Long register(RegisterRequest req);
    
    /**
     * 发送短信验证码
     */
    Integer sendSms(SendSmsRequest req);
    
    /**
     * 重置密码
     */
    void resetPassword(ResetPasswordRequest req);
    
    /**
     * 退出登录
     */
    void logout(String token);
    
    /**
     * 刷新Token
     */
    String refreshToken(String oldToken);
    
    /**
     * 根据Token获取用户信息
     */
    User getUserByToken(String token);
}
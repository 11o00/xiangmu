package com.stallmap.module.auth.controller;

import com.stallmap.common.api.Result;
import com.stallmap.module.auth.dto.*;
import com.stallmap.module.auth.service.AuthService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 账号密码登录
     */
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@Valid @RequestBody LoginRequest req) {
        Map<String, Object> result = authService.login(req);
        return Result.ok(result).setMessage("登录成功");
    }

    /**
     * 微信登录
     */
    @PostMapping("/wx/login")
    public Result<Map<String, Object>> wxLogin(@Valid @RequestBody WxLoginRequest req) {
        Map<String, Object> result = authService.wxLogin(req);
        return Result.ok(result).setMessage("登录成功");
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<Map<String, Object>> register(@Valid @RequestBody RegisterRequest req) {
        Long userId = authService.register(req);
        Map<String, Object> data = Map.of("userId", userId);
        return Result.ok(data).setMessage("注册成功");
    }

    /**
     * 发送短信验证码
     */
    @PostMapping("/sms/send")
    public Result<Map<String, Object>> sendSms(@Valid @RequestBody SendSmsRequest req) {
        Integer expireTime = authService.sendSms(req);
        Map<String, Object> data = Map.of("expireTime", expireTime);
        return Result.ok(data).setMessage("发送成功");
    }

    /**
     * 重置密码
     */
    @PostMapping("/password/reset")
    public Result<Void> resetPassword(@Valid @RequestBody ResetPasswordRequest req) {
        authService.resetPassword(req);
        return Result.<Void>ok().setMessage("密码重置成功");
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public Result<Void> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            authService.logout(token);
        }
        return Result.<Void>ok().setMessage("退出成功");
    }

    /**
     * 刷新Token
     */
    @PostMapping("/token/refresh")
    public Result<Map<String, Object>> refreshToken(HttpServletRequest request) {
        String oldToken = request.getHeader("Authorization");
        if (oldToken != null && oldToken.startsWith("Bearer ")) {
            oldToken = oldToken.substring(7);
            String newToken = authService.refreshToken(oldToken);
            Map<String, Object> data = Map.of("token", newToken);
            return Result.ok(data).setMessage("刷新成功");
        }
        return Result.fail(401, "Token无效");
    }
}


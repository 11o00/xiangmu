package com.stallmap.module.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.stallmap.common.exception.BizException;
import com.stallmap.common.utils.JwtUtil;
import com.stallmap.module.auth.dto.*;
import com.stallmap.module.auth.service.AuthService;
import com.stallmap.module.user.entity.User;
import com.stallmap.module.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    public Map<String, Object> login(LoginRequest req) {
        // 根据用户名/手机号查询用户
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, req.getUsername())
               .or()
               .eq(User::getPhone, req.getUsername());
        User user = userMapper.selectOne(wrapper);
        
        if (user == null) {
            throw new BizException(401, "用户名或密码错误");
        }
        
        if (user.getStatus() == 0) {
            throw new BizException(403, "账号已被禁用");
        }
        
        // 验证密码
        String encryptedPassword = DigestUtils.md5DigestAsHex(req.getPassword().getBytes());
        if (!encryptedPassword.equals(user.getPassword())) {
            throw new BizException(401, "用户名或密码错误");
        }
        
        // 生成token
        String token = jwtUtil.generateToken(user.getId());
        
        // 构建返回数据
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", user.getId());
        userInfo.put("username", user.getUsername());
        userInfo.put("phone", user.getPhone());
        userInfo.put("nickname", user.getNickname());
        userInfo.put("avatar", user.getAvatarUrl());
        userInfo.put("createTime", user.getCreatedAt());
        result.put("userInfo", userInfo);
        
        return result;
    }
    
    @Override
    public Map<String, Object> wxLogin(WxLoginRequest req) {
        // TODO: 对接微信小程序 code2session
        // 这里暂时模拟微信登录
        String openid = "o" + UUID.randomUUID().toString().substring(0, 10);
        
        // 根据openid查询用户
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getWxOpenid, openid);
        User user = userMapper.selectOne(wrapper);
        
        if (user == null) {
            // 新用户，创建账号
            user = new User();
            user.setWxOpenid(openid);
            user.setNickname("微信用户");
            user.setAvatarUrl("https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKbY6ibD1yRvNib0iaV7sFbFj4icjDibI3fZ4Jia/0");
            user.setStatus(1);
            userMapper.insert(user);
        }
        
        // 生成token
        String token = jwtUtil.generateToken(user.getId());
        
        // 构建返回数据
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", user.getId());
        userInfo.put("openid", user.getWxOpenid());
        userInfo.put("nickname", user.getNickname());
        userInfo.put("avatar", user.getAvatarUrl());
        userInfo.put("createTime", user.getCreatedAt());
        result.put("userInfo", userInfo);
        
        return result;
    }
    
    @Override
    public Long register(RegisterRequest req) {
        // 检查手机号是否已注册
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone, req.getPhone());
        User existingUser = userMapper.selectOne(wrapper);
        if (existingUser != null) {
            throw new BizException(409, "手机号已注册");
        }
        
        // TODO: 验证验证码
        
        // 创建新用户
        User user = new User();
        user.setPhone(req.getPhone());
        user.setUsername(req.getPhone()); // 用户名默认为手机号
        user.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        user.setNickname(req.getNickname() != null ? req.getNickname() : "用户" + req.getPhone().substring(7));
        user.setStatus(1); // 状态默认为正常
        
        userMapper.insert(user);
        return user.getId();
    }
    
    @Override
    public Integer sendSms(SendSmsRequest req) {
        // TODO: 实现短信发送逻辑
        // 这里暂时模拟发送
        log.info("发送短信验证码到: {}", req.getPhone());
        return 300; // 验证码有效期300秒
    }
    
    @Override
    public void resetPassword(ResetPasswordRequest req) {
        // 检查手机号是否存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone, req.getPhone());
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new BizException(404, "手机号未注册");
        }
        
        // TODO: 验证验证码
        
        // 更新密码
        user.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        userMapper.updateById(user);
    }
    
    @Override
    public void logout(String token) {
        // TODO: 实现退出登录逻辑，如将token加入黑名单
        log.info("用户退出登录，token: {}", token);
    }
    
    @Override
    public String refreshToken(String oldToken) {
        // 验证旧token
        if (!jwtUtil.validateToken(oldToken)) {
            throw new BizException(401, "Token无效或已过期");
        }
        
        // 获取用户ID
        Long userId = jwtUtil.getUserIdFromToken(oldToken);
        
        // 生成新token
        return jwtUtil.generateToken(userId);
    }
    
    @Override
    public User getUserByToken(String token) {
        Long userId = jwtUtil.getUserIdFromToken(token);
        return userMapper.selectById(userId);
    }
}
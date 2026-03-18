package com.stallmap.module.user.service.impl;

import com.stallmap.module.user.entity.User;
import com.stallmap.module.user.mapper.UserMapper;
import com.stallmap.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }
}


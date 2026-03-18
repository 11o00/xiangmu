package com.stallmap.module.user.mapper;

import com.stallmap.mapper.BaseMapperPlus;
import com.stallmap.module.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapperPlus<User> {
}


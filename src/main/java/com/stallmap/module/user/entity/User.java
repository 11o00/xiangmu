package com.stallmap.module.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.stallmap.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_user")
public class User extends BaseEntity {
    @TableId
    private Long id;

    /**
     * 微信 openId（小程序用户唯一标识）
     */
    private String wxOpenid;

    private String nickname;
    private String avatarUrl;
}


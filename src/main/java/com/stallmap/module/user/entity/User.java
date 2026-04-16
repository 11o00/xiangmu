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
     * 用户名/手机号
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 手机号
     */
    private String phone;

    /**
     * 微信 openId（小程序用户唯一标识）
     */
    private String wxOpenid;

    private String nickname;
    private String avatarUrl;
    
    /**
     * 性别：0-未知，1-男，2-女
     */
    private Integer gender;
    
    /**
     * 生日
     */
    private String birthday;
    
    /**
     * 个性签名
     */
    private String signature;
    
    /**
     * 状态：0-禁用，1-正常
     */
    private Integer status;
}


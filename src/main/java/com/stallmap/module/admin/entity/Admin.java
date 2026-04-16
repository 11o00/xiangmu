package com.stallmap.module.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.stallmap.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_admin")
public class Admin extends BaseEntity {
    @TableId
    private Long id;
    
    private String username;
    private String password;
    private String name;
    private String phone;
    private Integer status; // 0-禁用，1-启用
}

package com.stallmap.module.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.stallmap.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_system_settings")
public class SystemSettings extends BaseEntity {
    @TableId
    private Long id;

    /**
     * 设置键
     */
    private String key;

    /**
     * 设置值
     */
    private String value;

    /**
     * 设置描述
     */
    private String description;
}
package com.stallmap.module.follow.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.stallmap.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_follow")
public class Follow extends BaseEntity {
    @TableId
    private Long id;

    private Long userId;
    private Long stallId;
}


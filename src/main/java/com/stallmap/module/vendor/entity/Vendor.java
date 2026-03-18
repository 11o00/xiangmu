package com.stallmap.module.vendor.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.stallmap.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_vendor")
public class Vendor extends BaseEntity {
    @TableId
    private Long id;

    /**
     * 对应用户 ID（摊主也是用户的一种角色）
     */
    private Long userId;

    private String displayName;
    private String phone;
}


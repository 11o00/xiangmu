package com.stallmap.module.stall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.stallmap.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_stall")
public class Stall extends BaseEntity {
    @TableId
    private Long id;

    /**
     * 摊主 ID
     */
    private Long vendorId;

    private String name;
    private String category;

    /**
     * WGS84：纬度/经度
     */
    private BigDecimal lat;
    private BigDecimal lng;

    /**
     * 是否营业中
     */
    private Boolean online;
}


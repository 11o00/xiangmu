package com.stallmap.module.stall.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class NearbyStallQuery {
    @NotNull
    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    private BigDecimal lat;

    @NotNull
    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    private BigDecimal lng;

    /**
     * 搜索半径（米）
     */
    @NotNull
    private Integer radiusMeters;
}


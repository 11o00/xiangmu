package com.stallmap.module.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VendorStatusUpdateRequest {
    @NotNull(message = "状态不能为空")
    private Integer status; // 0-不活跃，1-活跃，2-封禁，3-待审核
    private String remark; // 审核备注
}

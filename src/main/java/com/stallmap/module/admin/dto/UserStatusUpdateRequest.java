package com.stallmap.module.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserStatusUpdateRequest {
    @NotNull(message = "状态不能为空")
    private Integer status; // 0-不活跃，1-活跃，2-封禁
}

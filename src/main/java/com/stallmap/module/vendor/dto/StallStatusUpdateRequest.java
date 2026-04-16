package com.stallmap.module.vendor.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StallStatusUpdateRequest {
    @NotBlank(message = "状态不能为空")
    private String status; // open/closed
}

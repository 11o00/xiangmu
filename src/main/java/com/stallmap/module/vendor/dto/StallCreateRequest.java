package com.stallmap.module.vendor.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StallCreateRequest {
    @NotBlank(message = "摊位名称不能为空")
    private String name;
    
    @NotBlank(message = "位置不能为空")
    private String location;
    
    @NotBlank(message = "营业时间不能为空")
    private String openingHours;
}

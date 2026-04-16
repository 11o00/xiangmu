package com.stallmap.module.vendor.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class VendorLoginRequest {
    @NotBlank(message = "用户名不能为空")
    private String username; // 手机号/邮箱
    
    @NotBlank(message = "密码不能为空")
    private String password;
}

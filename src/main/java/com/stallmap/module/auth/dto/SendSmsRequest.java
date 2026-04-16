package com.stallmap.module.auth.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class SendSmsRequest {
    @NotBlank(message = "手机号不能为空")
    @Size(min = 11, max = 11, message = "手机号必须为11位")
    private String phone;
    
    @NotBlank(message = "类型不能为空")
    private String type; // register-注册，reset-重置密码
}
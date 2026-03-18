package com.stallmap.module.auth.controller;

import com.stallmap.common.api.Result;
import com.stallmap.module.auth.dto.WxLoginRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/wx/login")
    public Result<String> wxLogin(@Valid @RequestBody WxLoginRequest req) {
        // TODO：对接微信小程序 code2session，生成你自己的 token
        return Result.ok("TODO_TOKEN");
    }
}


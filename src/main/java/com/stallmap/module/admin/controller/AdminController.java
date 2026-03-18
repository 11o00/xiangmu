package com.stallmap.module.admin.controller;

import com.stallmap.common.api.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @GetMapping("/ping")
    public Result<String> ping() {
        return Result.ok("pong");
    }
}


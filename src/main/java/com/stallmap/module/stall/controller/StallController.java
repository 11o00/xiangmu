package com.stallmap.module.stall.controller;

import com.stallmap.common.api.Result;
import com.stallmap.module.stall.dto.NearbyStallQuery;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stall")
public class StallController {

    /**
     * 附近摊位查询（占位）
     * 后续建议：MySQL 空间索引 / H3 / GeoHash / ES 地理查询 等实现。
     */
    @PostMapping("/nearby")
    public Result<Object> nearby(@Valid @RequestBody NearbyStallQuery query) {
        return Result.ok(null);
    }
}


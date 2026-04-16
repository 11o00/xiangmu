package com.stallmap.module.admin.dto;

import lombok.Data;

@Data
public class VendorQueryRequest {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String keyword; // 搜索关键词（摊主名称、手机号）
    private Integer status; // 状态：0-不活跃，1-活跃，2-封禁，3-待审核
    private String timeRange; // 时间范围：7d, 30d, 90d, 1y
}

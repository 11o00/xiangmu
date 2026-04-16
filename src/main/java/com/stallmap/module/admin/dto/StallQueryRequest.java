package com.stallmap.module.admin.dto;

import lombok.Data;

@Data
public class StallQueryRequest {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String keyword; // 搜索关键词（摊位名称、地址）
    private Integer status; // 状态：0-不活跃，1-活跃，2-封禁，3-待审核
    private Integer category; // 分类：1-美食小吃，2-水果生鲜，3-日用百货，4-其他
    private String timeRange; // 时间范围：7d, 30d, 90d, 1y
}

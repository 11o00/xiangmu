package com.stallmap.module.admin.dto;

import lombok.Data;

@Data
public class ReviewQueryRequest {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String keyword; // 搜索关键词（评价内容）
    private Integer rating; // 评分：1-5星
    private Integer status; // 状态：0-正常，1-已举报，2-已删除
    private String timeRange; // 时间范围：7d, 30d, 90d, 1y
}

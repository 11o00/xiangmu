package com.stallmap.module.admin.service;

import java.util.Map;

public interface StatsService {
    /**
     * 获取统计数据
     */
    Map<String, Object> getStats();
    
    /**
     * 获取用户增长趋势
     */
    Map<String, Object> getUserGrowth(String timeRange); // timeRange: week, month, year
}

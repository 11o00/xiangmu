package com.stallmap.module.admin.service.impl;

import com.stallmap.module.admin.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    
    @Override
    public Map<String, Object> getStats() {
        // 模拟获取统计数据
        Map<String, Object> result = new HashMap<>();
        
        result.put("totalUsers", 1000);
        result.put("totalVendors", 200);
        result.put("totalStalls", 300);
        result.put("pendingCount", 50);
        result.put("todayUsers", 50);
        result.put("todayVendors", 10);
        result.put("todayStalls", 15);
        
        return result;
    }
    
    @Override
    public Map<String, Object> getUserGrowth(String timeRange) {
        // 模拟获取用户增长趋势
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> data = new ArrayList<>();
        
        // 模拟数据
        if ("week".equals(timeRange)) {
            for (int i = 0; i < 7; i++) {
                Map<String, Object> item = new HashMap<>();
                item.put("date", "2026-03-" + (21 + i));
                item.put("users", 30 + i * 5);
                item.put("vendors", 5 + i);
                data.add(item);
            }
        } else if ("month".equals(timeRange)) {
            for (int i = 0; i < 4; i++) {
                Map<String, Object> item = new HashMap<>();
                item.put("date", "2026-03-" + (1 + i * 7));
                item.put("users", 200 + i * 50);
                item.put("vendors", 30 + i * 10);
                data.add(item);
            }
        } else if ("year".equals(timeRange)) {
            for (int i = 0; i < 12; i++) {
                Map<String, Object> item = new HashMap<>();
                item.put("date", "2026-" + (i + 1));
                item.put("users", 500 + i * 100);
                item.put("vendors", 100 + i * 20);
                data.add(item);
            }
        }
        
        result.put("data", data);
        result.put("timeRange", timeRange);
        
        return result;
    }
}

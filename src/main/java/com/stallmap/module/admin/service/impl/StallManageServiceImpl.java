package com.stallmap.module.admin.service.impl;

import com.stallmap.module.admin.dto.StallQueryRequest;
import com.stallmap.module.admin.service.StallManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StallManageServiceImpl implements StallManageService {
    
    @Override
    public Map<String, Object> getStallList(StallQueryRequest request) {
        // 模拟获取摊位列表
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> stalls = new ArrayList<>();
        
        // 模拟数据
        for (int i = 0; i < request.getPageSize(); i++) {
            Map<String, Object> stall = new HashMap<>();
            stall.put("id", (request.getPage() - 1) * request.getPageSize() + i + 1);
            stall.put("name", "摊位" + i);
            stall.put("address", "地址" + i);
            stall.put("category", 1);
            stall.put("status", 1);
            stall.put("vendorId", 1L);
            stall.put("vendorName", "摊主1");
            stall.put("createdAt", "2026-03-27 12:00:00");
            stalls.add(stall);
        }
        
        result.put("list", stalls);
        result.put("total", 80);
        result.put("page", request.getPage());
        result.put("pageSize", request.getPageSize());
        
        return result;
    }
    
    @Override
    public void updateStallStatus(Long stallId, Integer status, String remark) {
        // 模拟审核摊位
        // 实际项目中应该更新数据库
    }
    
    @Override
    public Map<String, Object> getStallDetail(Long stallId) {
        // 模拟获取摊位详情
        Map<String, Object> result = new HashMap<>();
        
        // 摊位信息
        Map<String, Object> stallInfo = new HashMap<>();
        stallInfo.put("id", stallId);
        stallInfo.put("name", "摊位" + stallId);
        stallInfo.put("address", "地址" + stallId);
        stallInfo.put("category", 1);
        stallInfo.put("status", 1);
        stallInfo.put("vendorId", 1L);
        stallInfo.put("vendorName", "摊主1");
        stallInfo.put("createdAt", "2026-03-27 12:00:00");
        
        // 图片列表
        List<String> images = new ArrayList<>();
        images.add("https://example.com/image1.jpg");
        images.add("https://example.com/image2.jpg");
        
        result.put("stallInfo", stallInfo);
        result.put("images", images);
        
        return result;
    }
    
    @Override
    public void deleteStall(Long stallId) {
        // 模拟删除摊位
        // 实际项目中应该从数据库删除
    }
    
    @Override
    public byte[] exportStalls(StallQueryRequest request) {
        // 模拟导出摊位数据
        // 实际项目中应该使用Apache POI生成Excel文件
        return new byte[0];
    }
}

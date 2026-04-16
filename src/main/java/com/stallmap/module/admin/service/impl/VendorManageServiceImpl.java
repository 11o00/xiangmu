package com.stallmap.module.admin.service.impl;

import com.stallmap.module.admin.dto.VendorQueryRequest;
import com.stallmap.module.admin.service.VendorManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VendorManageServiceImpl implements VendorManageService {
    
    @Override
    public Map<String, Object> getVendorList(VendorQueryRequest request) {
        // 模拟获取摊主列表
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> vendors = new ArrayList<>();
        
        // 模拟数据
        for (int i = 0; i < request.getPageSize(); i++) {
            Map<String, Object> vendor = new HashMap<>();
            vendor.put("id", (request.getPage() - 1) * request.getPageSize() + i + 1);
            vendor.put("name", "摊主" + i);
            vendor.put("phone", "138001381" + i);
            vendor.put("status", 1);
            vendor.put("createdAt", "2026-03-27 12:00:00");
            vendor.put("stallCount", i + 1);
            vendors.add(vendor);
        }
        
        result.put("list", vendors);
        result.put("total", 50);
        result.put("page", request.getPage());
        result.put("pageSize", request.getPageSize());
        
        return result;
    }
    
    @Override
    public void updateVendorStatus(Long vendorId, Integer status, String remark) {
        // 模拟审核摊主
        // 实际项目中应该更新数据库
    }
    
    @Override
    public Map<String, Object> getVendorDetail(Long vendorId) {
        // 模拟获取摊主详情
        Map<String, Object> result = new HashMap<>();
        
        // 摊主信息
        Map<String, Object> vendorInfo = new HashMap<>();
        vendorInfo.put("id", vendorId);
        vendorInfo.put("name", "摊主" + vendorId);
        vendorInfo.put("phone", "138001381" + vendorId);
        vendorInfo.put("status", 1);
        vendorInfo.put("createdAt", "2026-03-27 12:00:00");
        
        // 摊位列表
        List<Map<String, Object>> stalls = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> stall = new HashMap<>();
            stall.put("id", vendorId * 10 + i);
            stall.put("name", "摊位" + i);
            stall.put("address", "地址" + i);
            stall.put("status", 1);
            stalls.add(stall);
        }
        
        result.put("vendorInfo", vendorInfo);
        result.put("stalls", stalls);
        
        return result;
    }
    
    @Override
    public void deleteVendor(Long vendorId) {
        // 模拟删除摊主
        // 实际项目中应该从数据库删除
    }
    
    @Override
    public byte[] exportVendors(VendorQueryRequest request) {
        // 模拟导出摊主数据
        // 实际项目中应该使用Apache POI生成Excel文件
        return new byte[0];
    }
}

package com.stallmap.module.vendor.service.impl;

import com.stallmap.common.utils.JwtUtil;
import com.stallmap.module.vendor.dto.*;
import com.stallmap.module.vendor.entity.Vendor;
import com.stallmap.module.vendor.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {
    
    @Override
    public Map<String, Object> login(VendorLoginRequest request) {
        // 模拟登录验证
        // 实际项目中应该从数据库查询并验证密码
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> userInfo = new HashMap<>();
        
        // 模拟商家数据
        userInfo.put("id", 1L);
        userInfo.put("name", "老王烧烤");
        userInfo.put("phone", "13800138000");
        userInfo.put("avatar", "https://example.com/avatar.jpg");
        userInfo.put("stallCount", 1);
        userInfo.put("rating", 4.8);
        
        // 生成JWT token
        String token = JwtUtil.generateToken(1L, "vendor");
        
        result.put("token", token);
        result.put("userInfo", userInfo);
        
        return result;
    }
    
    @Override
    public Map<String, Object> getVendorInfo(Long vendorId) {
        // 模拟获取商家信息
        Map<String, Object> info = new HashMap<>();
        info.put("id", vendorId);
        info.put("name", "老王烧烤");
        info.put("phone", "13800138000");
        info.put("avatar", "https://example.com/avatar.jpg");
        info.put("stallCount", 1);
        info.put("rating", 4.8);
        return info;
    }
    
    @Override
    public List<Map<String, Object>> getStallList(Long vendorId) {
        // 模拟获取摊位列表
        List<Map<String, Object>> stalls = new ArrayList<>();
        Map<String, Object> stall = new HashMap<>();
        stall.put("id", 1L);
        stall.put("name", "老王烧烤");
        stall.put("location", "泰山学院小吃街");
        stall.put("openingHours", "10:00-22:00");
        stall.put("status", "open");
        stall.put("rating", 4.8);
        stalls.add(stall);
        return stalls;
    }
    
    @Override
    public Map<String, Object> getStallDetail(Long stallId, Long vendorId) {
        // 模拟获取摊位详情
        Map<String, Object> stall = new HashMap<>();
        stall.put("id", stallId);
        stall.put("name", "老王烧烤");
        stall.put("location", "泰山学院小吃街");
        stall.put("openingHours", "10:00-22:00");
        stall.put("status", "open");
        stall.put("rating", 4.8);
        return stall;
    }
    
    @Override
    public Map<String, Object> createStall(StallCreateRequest request, Long vendorId) {
        // 模拟创建摊位
        Map<String, Object> stall = new HashMap<>();
        stall.put("id", 2L);
        stall.put("name", request.getName());
        stall.put("location", request.getLocation());
        stall.put("openingHours", request.getOpeningHours());
        stall.put("status", "open");
        stall.put("rating", 0.0);
        return stall;
    }
    
    @Override
    public Map<String, Object> updateStall(Long stallId, StallCreateRequest request, Long vendorId) {
        // 模拟更新摊位
        Map<String, Object> stall = new HashMap<>();
        stall.put("id", stallId);
        stall.put("name", request.getName());
        stall.put("location", request.getLocation());
        stall.put("openingHours", request.getOpeningHours());
        stall.put("status", "open");
        stall.put("rating", 4.8);
        return stall;
    }
    
    @Override
    public void deleteStall(Long stallId, Long vendorId) {
        // 模拟删除摊位
        // 实际项目中应该从数据库删除
    }
    
    @Override
    public Map<String, Object> updateStallStatus(Long stallId, StallStatusUpdateRequest request, Long vendorId) {
        // 模拟更新摊位状态
        Map<String, Object> stall = new HashMap<>();
        stall.put("id", stallId);
        stall.put("status", request.getStatus());
        stall.put("name", "老王烧烤");
        stall.put("location", "泰山学院小吃街");
        stall.put("openingHours", "10:00-22:00");
        stall.put("rating", 4.8);
        return stall;
    }
    
    @Override
    public List<Map<String, Object>> getOrderList(Long vendorId, String status) {
        // 模拟获取订单列表
        List<Map<String, Object>> orders = new ArrayList<>();
        Map<String, Object> order1 = new HashMap<>();
        order1.put("id", "20260326001");
        order1.put("time", "2026-03-26 10:30");
        order1.put("items", "羊肉串×2, 烤鸡翅×1");
        order1.put("amount", 45);
        order1.put("status", "completed");
        orders.add(order1);
        
        Map<String, Object> order2 = new HashMap<>();
        order2.put("id", "20260326002");
        order2.put("time", "2026-03-26 11:00");
        order2.put("items", "烤肠×1, 可乐×1");
        order2.put("amount", 15);
        order2.put("status", "processing");
        orders.add(order2);
        
        return orders;
    }
    
    @Override
    public Map<String, Object> getOrderDetail(String orderId, Long vendorId) {
        // 模拟获取订单详情
        Map<String, Object> order = new HashMap<>();
        order.put("id", orderId);
        order.put("time", "2026-03-26 10:30");
        order.put("items", "羊肉串×2, 烤鸡翅×1");
        order.put("amount", 45);
        order.put("status", "completed");
        return order;
    }
    
    @Override
    public Map<String, Object> updateOrderStatus(String orderId, OrderStatusUpdateRequest request, Long vendorId) {
        // 模拟更新订单状态
        Map<String, Object> order = new HashMap<>();
        order.put("id", orderId);
        order.put("status", request.getStatus());
        order.put("time", "2026-03-26 10:30");
        order.put("items", "羊肉串×2, 烤鸡翅×1");
        order.put("amount", 45);
        return order;
    }
    
    @Override
    public Map<String, Object> getStatistics(Long vendorId, String period) {
        // 模拟获取统计数据
        Map<String, Object> statistics = new HashMap<>();
        
        // 关键指标
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("orders", 12);
        metrics.put("revenue", 388);
        metrics.put("average", 32);
        statistics.put("metrics", metrics);
        
        // 销售趋势
        Map<String, Object> salesTrend = new HashMap<>();
        List<String> labels = List.of("09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00");
        List<Integer> data = List.of(2, 5, 8, 12, 7, 3, 2, 4, 6, 9, 11, 8);
        salesTrend.put("labels", labels);
        salesTrend.put("data", data);
        statistics.put("salesTrend", salesTrend);
        
        // 热门商品
        List<Map<String, Object>> popularItems = new ArrayList<>();
        Map<String, Object> item1 = new HashMap<>();
        item1.put("id", 1);
        item1.put("name", "羊肉串");
        item1.put("sales", 25);
        item1.put("revenue", 150);
        popularItems.add(item1);
        
        Map<String, Object> item2 = new HashMap<>();
        item2.put("id", 2);
        item2.put("name", "烤鸡翅");
        item2.put("sales", 18);
        item2.put("revenue", 108);
        popularItems.add(item2);
        
        statistics.put("popularItems", popularItems);
        
        return statistics;
    }
}

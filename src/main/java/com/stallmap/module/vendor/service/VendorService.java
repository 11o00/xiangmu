package com.stallmap.module.vendor.service;

import com.stallmap.module.vendor.dto.*;
import com.stallmap.module.vendor.entity.Vendor;

import java.util.List;
import java.util.Map;

public interface VendorService {
    /**
     * 摊主登录
     */
    Map<String, Object> login(VendorLoginRequest request);
    
    /**
     * 获取商家信息
     */
    Map<String, Object> getVendorInfo(Long vendorId);
    
    /**
     * 获取摊位列表
     */
    List<Map<String, Object>> getStallList(Long vendorId);
    
    /**
     * 获取摊位详情
     */
    Map<String, Object> getStallDetail(Long stallId, Long vendorId);
    
    /**
     * 创建摊位
     */
    Map<String, Object> createStall(StallCreateRequest request, Long vendorId);
    
    /**
     * 更新摊位
     */
    Map<String, Object> updateStall(Long stallId, StallCreateRequest request, Long vendorId);
    
    /**
     * 删除摊位
     */
    void deleteStall(Long stallId, Long vendorId);
    
    /**
     * 更新摊位状态
     */
    Map<String, Object> updateStallStatus(Long stallId, StallStatusUpdateRequest request, Long vendorId);
    
    /**
     * 获取订单列表
     */
    List<Map<String, Object>> getOrderList(Long vendorId, String status);
    
    /**
     * 获取订单详情
     */
    Map<String, Object> getOrderDetail(String orderId, Long vendorId);
    
    /**
     * 更新订单状态
     */
    Map<String, Object> updateOrderStatus(String orderId, OrderStatusUpdateRequest request, Long vendorId);
    
    /**
     * 获取统计数据
     */
    Map<String, Object> getStatistics(Long vendorId, String period);
}

package com.stallmap.module.admin.service;

import com.stallmap.module.admin.dto.VendorQueryRequest;
import com.stallmap.module.vendor.entity.Vendor;

import java.util.Map;

public interface VendorManageService {
    /**
     * 获取摊主列表
     */
    Map<String, Object> getVendorList(VendorQueryRequest request);
    
    /**
     * 审核摊主
     */
    void updateVendorStatus(Long vendorId, Integer status, String remark);
    
    /**
     * 查看摊主详情
     */
    Map<String, Object> getVendorDetail(Long vendorId);
    
    /**
     * 删除摊主
     */
    void deleteVendor(Long vendorId);
    
    /**
     * 导出摊主数据
     */
    byte[] exportVendors(VendorQueryRequest request);
}

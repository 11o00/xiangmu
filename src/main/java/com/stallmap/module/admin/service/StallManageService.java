package com.stallmap.module.admin.service;

import com.stallmap.module.admin.dto.StallQueryRequest;

import java.util.Map;

public interface StallManageService {
    /**
     * 获取摊位列表
     */
    Map<String, Object> getStallList(StallQueryRequest request);
    
    /**
     * 审核摊位
     */
    void updateStallStatus(Long stallId, Integer status, String remark);
    
    /**
     * 查看摊位详情
     */
    Map<String, Object> getStallDetail(Long stallId);
    
    /**
     * 删除摊位
     */
    void deleteStall(Long stallId);
    
    /**
     * 导出摊位数据
     */
    byte[] exportStalls(StallQueryRequest request);
}

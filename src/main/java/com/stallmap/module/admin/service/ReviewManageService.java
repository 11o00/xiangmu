package com.stallmap.module.admin.service;

import com.stallmap.module.admin.dto.ReviewQueryRequest;

import java.util.List;
import java.util.Map;

public interface ReviewManageService {
    /**
     * 获取评价列表
     */
    Map<String, Object> getReviewList(ReviewQueryRequest request);
    
    /**
     * 删除评价
     */
    void deleteReview(Long reviewId);
    
    /**
     * 批量删除评价
     */
    void batchDeleteReviews(List<Long> reviewIds);
    
    /**
     * 导出评价数据
     */
    byte[] exportReviews(ReviewQueryRequest request);
}

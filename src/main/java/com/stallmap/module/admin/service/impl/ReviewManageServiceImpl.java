package com.stallmap.module.admin.service.impl;

import com.stallmap.module.admin.dto.ReviewQueryRequest;
import com.stallmap.module.admin.service.ReviewManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReviewManageServiceImpl implements ReviewManageService {
    
    @Override
    public Map<String, Object> getReviewList(ReviewQueryRequest request) {
        // 模拟获取评价列表
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> reviews = new ArrayList<>();
        
        // 模拟数据
        for (int i = 0; i < request.getPageSize(); i++) {
            Map<String, Object> review = new HashMap<>();
            review.put("id", (request.getPage() - 1) * request.getPageSize() + i + 1);
            review.put("userId", 1L);
            review.put("userName", "用户" + i);
            review.put("stallId", 1L);
            review.put("stallName", "摊位" + i);
            review.put("rating", (i % 5) + 1);
            review.put("content", "评价内容" + i);
            review.put("status", 0);
            review.put("createdAt", "2026-03-27 12:00:00");
            reviews.add(review);
        }
        
        result.put("list", reviews);
        result.put("total", 120);
        result.put("page", request.getPage());
        result.put("pageSize", request.getPageSize());
        
        return result;
    }
    
    @Override
    public void deleteReview(Long reviewId) {
        // 模拟删除评价
        // 实际项目中应该从数据库删除
    }
    
    @Override
    public void batchDeleteReviews(List<Long> reviewIds) {
        // 模拟批量删除评价
        // 实际项目中应该从数据库批量删除
    }
    
    @Override
    public byte[] exportReviews(ReviewQueryRequest request) {
        // 模拟导出评价数据
        // 实际项目中应该使用Apache POI生成Excel文件
        return new byte[0];
    }
}

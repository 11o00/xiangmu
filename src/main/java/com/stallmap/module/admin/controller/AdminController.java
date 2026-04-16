package com.stallmap.module.admin.controller;

import com.stallmap.common.api.Result;
import com.stallmap.common.utils.JwtUtil;
import com.stallmap.module.admin.dto.*;
import com.stallmap.module.admin.service.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    private final UserManageService userManageService;
    private final VendorManageService vendorManageService;
    private final StallManageService stallManageService;
    private final ReviewManageService reviewManageService;
    private final StatsService statsService;
    private final SystemSettingsService systemSettingsService;
    
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@Valid @RequestBody AdminLoginRequest request) {
        Map<String, Object> result = adminService.login(request);
        return Result.ok(result).setMessage("登录成功");
    }
    
    @PostMapping("/logout")
    public Result<Void> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            adminService.logout(token);
        }
        return Result.<Void>ok().setMessage("登出成功");
    }
    
    // 用户管理接口
    @GetMapping("/users")
    public Result<Map<String, Object>> getUserList(UserQueryRequest request) {
        Map<String, Object> result = userManageService.getUserList(request);
        return Result.ok(result);
    }
    
    @PutMapping("/user/{id}/status")
    public Result<Void> updateUserStatus(@PathVariable Long id, @Valid @RequestBody UserStatusUpdateRequest request) {
        userManageService.updateUserStatus(id, request.getStatus());
        return Result.<Void>ok().setMessage("更新成功");
    }
    
    @DeleteMapping("/user/{id}")
    public Result<Void> deleteUser(@PathVariable Long id) {
        userManageService.deleteUser(id);
        return Result.<Void>ok().setMessage("删除成功");
    }
    
    @GetMapping("/users/export")
    public void exportUsers(UserQueryRequest request, HttpServletResponse response) throws IOException {
        byte[] data = userManageService.exportUsers(request);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=users.xlsx");
        response.getOutputStream().write(data);
        response.getOutputStream().flush();
    }
    
    // 摊主管理接口
    @GetMapping("/vendors")
    public Result<Map<String, Object>> getVendorList(VendorQueryRequest request) {
        Map<String, Object> result = vendorManageService.getVendorList(request);
        return Result.ok(result);
    }
    
    @PutMapping("/vendor/{id}/status")
    public Result<Void> updateVendorStatus(@PathVariable Long id, @Valid @RequestBody VendorStatusUpdateRequest request) {
        vendorManageService.updateVendorStatus(id, request.getStatus(), request.getRemark());
        return Result.<Void>ok().setMessage("审核成功");
    }
    
    @GetMapping("/vendor/{id}")
    public Result<Map<String, Object>> getVendorDetail(@PathVariable Long id) {
        Map<String, Object> result = vendorManageService.getVendorDetail(id);
        return Result.ok(result);
    }
    
    @DeleteMapping("/vendor/{id}")
    public Result<Void> deleteVendor(@PathVariable Long id) {
        vendorManageService.deleteVendor(id);
        return Result.<Void>ok().setMessage("删除成功");
    }
    
    @GetMapping("/vendors/export")
    public void exportVendors(VendorQueryRequest request, HttpServletResponse response) throws IOException {
        byte[] data = vendorManageService.exportVendors(request);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=vendors.xlsx");
        response.getOutputStream().write(data);
        response.getOutputStream().flush();
    }
    
    // 摊位管理接口
    @GetMapping("/stalls")
    public Result<Map<String, Object>> getStallList(StallQueryRequest request) {
        Map<String, Object> result = stallManageService.getStallList(request);
        return Result.ok(result);
    }
    
    @PutMapping("/stall/{id}/status")
    public Result<Void> updateStallStatus(@PathVariable Long id, @Valid @RequestBody StallStatusUpdateRequest request) {
        stallManageService.updateStallStatus(id, request.getStatus(), request.getRemark());
        return Result.<Void>ok().setMessage("审核成功");
    }
    
    @GetMapping("/stall/{id}")
    public Result<Map<String, Object>> getStallDetail(@PathVariable Long id) {
        Map<String, Object> result = stallManageService.getStallDetail(id);
        return Result.ok(result);
    }
    
    @DeleteMapping("/stall/{id}")
    public Result<Void> deleteStall(@PathVariable Long id) {
        stallManageService.deleteStall(id);
        return Result.<Void>ok().setMessage("删除成功");
    }
    
    @GetMapping("/stalls/export")
    public void exportStalls(StallQueryRequest request, HttpServletResponse response) throws IOException {
        byte[] data = stallManageService.exportStalls(request);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=stalls.xlsx");
        response.getOutputStream().write(data);
        response.getOutputStream().flush();
    }
    
    // 评价管理接口
    @GetMapping("/reviews")
    public Result<Map<String, Object>> getReviewList(ReviewQueryRequest request) {
        Map<String, Object> result = reviewManageService.getReviewList(request);
        return Result.ok(result);
    }
    
    @DeleteMapping("/review/{id}")
    public Result<Void> deleteReview(@PathVariable Long id) {
        reviewManageService.deleteReview(id);
        return Result.<Void>ok().setMessage("删除成功");
    }
    
    @DeleteMapping("/reviews/batch")
    public Result<Void> batchDeleteReviews(@Valid @RequestBody BatchDeleteRequest request) {
        reviewManageService.batchDeleteReviews(request.getIds());
        return Result.<Void>ok().setMessage("批量删除成功");
    }
    
    @GetMapping("/reviews/export")
    public void exportReviews(ReviewQueryRequest request, HttpServletResponse response) throws IOException {
        byte[] data = reviewManageService.exportReviews(request);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=reviews.xlsx");
        response.getOutputStream().write(data);
        response.getOutputStream().flush();
    }
    
    // 统计数据接口
    @GetMapping("/stats")
    public Result<Map<String, Object>> getStats() {
        Map<String, Object> result = statsService.getStats();
        return Result.ok(result);
    }
    
    @GetMapping("/stats/user-growth")
    public Result<Map<String, Object>> getUserGrowth(@RequestParam(defaultValue = "week") String timeRange) {
        Map<String, Object> result = statsService.getUserGrowth(timeRange);
        return Result.ok(result);
    }
    
    // 系统设置接口
    @GetMapping("/settings")
    public Result<Map<String, Object>> getSystemSettings() {
        Map<String, Object> result = systemSettingsService.getSystemSettings();
        return Result.ok(result);
    }
    
    @PutMapping("/settings")
    public Result<Void> updateSystemSettings(@Valid @RequestBody SystemSettingsRequest request) {
        systemSettingsService.updateSystemSettings(request);
        return Result.<Void>ok().setMessage("更新成功");
    }
    
    @RequestMapping("/ping")
    public Result<String> ping() {
        return Result.ok("pong");
    }
}


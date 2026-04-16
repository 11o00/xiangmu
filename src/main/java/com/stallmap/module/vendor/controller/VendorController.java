package com.stallmap.module.vendor.controller;

import com.stallmap.common.api.Result;
import com.stallmap.common.utils.JwtUtil;
import com.stallmap.module.vendor.dto.*;
import com.stallmap.module.vendor.service.VendorService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vendor")
public class VendorController {
    private final VendorService vendorService;
    
    /**
     * 摊主登录
     */
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@Valid @RequestBody VendorLoginRequest request) {
        Map<String, Object> result = vendorService.login(request);
        return Result.ok(result).setMessage("登录成功");
    }
    
    /**
     * 获取商家信息
     */
    @GetMapping("/info")
    public Result<Map<String, Object>> getVendorInfo(HttpServletRequest request) {
        Long vendorId = getVendorIdFromToken(request);
        Map<String, Object> info = vendorService.getVendorInfo(vendorId);
        return Result.ok(info).setMessage("获取成功");
    }
    
    /**
     * 获取摊位列表
     */
    @GetMapping("/stalls")
    public Result<List<Map<String, Object>>> getStallList(HttpServletRequest request) {
        Long vendorId = getVendorIdFromToken(request);
        List<Map<String, Object>> stalls = vendorService.getStallList(vendorId);
        return Result.ok(stalls).setMessage("获取成功");
    }
    
    /**
     * 获取摊位详情
     */
    @GetMapping("/stalls/{id}")
    public Result<Map<String, Object>> getStallDetail(@PathVariable Long id, HttpServletRequest request) {
        Long vendorId = getVendorIdFromToken(request);
        Map<String, Object> stall = vendorService.getStallDetail(id, vendorId);
        return Result.ok(stall).setMessage("获取成功");
    }
    
    /**
     * 创建摊位
     */
    @PostMapping("/stalls")
    public Result<Map<String, Object>> createStall(@Valid @RequestBody StallCreateRequest request, HttpServletRequest httpRequest) {
        Long vendorId = getVendorIdFromToken(httpRequest);
        Map<String, Object> stall = vendorService.createStall(request, vendorId);
        return Result.ok(stall).setMessage("创建成功");
    }
    
    /**
     * 更新摊位
     */
    @PutMapping("/stalls/{id}")
    public Result<Map<String, Object>> updateStall(@PathVariable Long id, @Valid @RequestBody StallCreateRequest request, HttpServletRequest httpRequest) {
        Long vendorId = getVendorIdFromToken(httpRequest);
        Map<String, Object> stall = vendorService.updateStall(id, request, vendorId);
        return Result.ok(stall).setMessage("更新成功");
    }
    
    /**
     * 删除摊位
     */
    @DeleteMapping("/stalls/{id}")
    public Result<Void> deleteStall(@PathVariable Long id, HttpServletRequest request) {
        Long vendorId = getVendorIdFromToken(request);
        vendorService.deleteStall(id, vendorId);
        return Result.<Void>ok().setMessage("摊位删除成功");
    }
    
    /**
     * 更新摊位状态
     */
    @PutMapping("/stalls/{id}/status")
    public Result<Map<String, Object>> updateStallStatus(@PathVariable Long id, @Valid @RequestBody StallStatusUpdateRequest request, HttpServletRequest httpRequest) {
        Long vendorId = getVendorIdFromToken(httpRequest);
        Map<String, Object> stall = vendorService.updateStallStatus(id, request, vendorId);
        return Result.ok(stall).setMessage("状态更新成功");
    }
    
    /**
     * 获取订单列表
     */
    @GetMapping("/orders")
    public Result<List<Map<String, Object>>> getOrderList(@RequestParam(required = false) String status, HttpServletRequest request) {
        Long vendorId = getVendorIdFromToken(request);
        List<Map<String, Object>> orders = vendorService.getOrderList(vendorId, status);
        return Result.ok(orders).setMessage("获取成功");
    }
    
    /**
     * 获取订单详情
     */
    @GetMapping("/orders/{id}")
    public Result<Map<String, Object>> getOrderDetail(@PathVariable String id, HttpServletRequest request) {
        Long vendorId = getVendorIdFromToken(request);
        Map<String, Object> order = vendorService.getOrderDetail(id, vendorId);
        return Result.ok(order).setMessage("获取成功");
    }
    
    /**
     * 更新订单状态
     */
    @PutMapping("/orders/{id}/status")
    public Result<Map<String, Object>> updateOrderStatus(@PathVariable String id, @Valid @RequestBody OrderStatusUpdateRequest request, HttpServletRequest httpRequest) {
        Long vendorId = getVendorIdFromToken(httpRequest);
        Map<String, Object> order = vendorService.updateOrderStatus(id, request, vendorId);
        return Result.ok(order).setMessage("状态更新成功");
    }
    
    /**
     * 获取统计数据
     */
    @GetMapping("/statistics")
    public Result<Map<String, Object>> getStatistics(@RequestParam String period, HttpServletRequest request) {
        Long vendorId = getVendorIdFromToken(request);
        Map<String, Object> statistics = vendorService.getStatistics(vendorId, period);
        return Result.ok(statistics).setMessage("获取成功");
    }
    
    /**
     * 从Token中获取商家ID
     */
    private Long getVendorIdFromToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            return JwtUtil.getUserIdFromToken(token);
        }
        throw new RuntimeException("Token无效");
    }
}

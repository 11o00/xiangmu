package com.stallmap.module.review.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.stallmap.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_review")
public class Review extends BaseEntity {
    @TableId
    private Long id;

    /**
     * 用户 ID
     */
    private Long userId;

    /**
     * 摊位 ID
     */
    private Long stallId;

    /**
     * 评分：1-5星
     */
    private Integer rating;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 评价图片（多个图片以逗号分隔）
     */
    private String images;

    /**
     * 状态：0-待审核，1-已发布，2-已删除
     */
    private Integer status;
}
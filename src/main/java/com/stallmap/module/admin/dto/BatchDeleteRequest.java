package com.stallmap.module.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class BatchDeleteRequest {
    @NotEmpty(message = "ID列表不能为空")
    private List<Long> ids;
}

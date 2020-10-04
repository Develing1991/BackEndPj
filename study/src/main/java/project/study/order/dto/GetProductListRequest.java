package project.study.order.dto;

import lombok.Data;

@Data
public class GetProductListRequest {
    private Long productId;
    private int count;
}

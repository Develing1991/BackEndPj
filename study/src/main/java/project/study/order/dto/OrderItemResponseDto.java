package project.study.order.dto;

import lombok.Data;
import project.study.order.domain.OrderItem;

@Data
public class OrderItemResponseDto {
    private String productName;
    private int orderPrice;
    private int count;
    public OrderItemResponseDto(OrderItem orderItem) {
        productName = orderItem.getProduct().getName();
        orderPrice = orderItem.getTotalPrice();
        count = orderItem.getCount();
    }
}

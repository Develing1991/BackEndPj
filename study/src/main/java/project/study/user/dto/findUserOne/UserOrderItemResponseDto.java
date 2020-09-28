package project.study.user.dto.findUserOne;

import lombok.Data;
import project.study.order.domain.OrderItem;

@Data
public class UserOrderItemResponseDto {
    private String productName;
    private int orderPrice;
    private int count;
    public UserOrderItemResponseDto(OrderItem orderItem) {
        productName = orderItem.getProduct().getName();
        orderPrice = orderItem.getOrderPrice();
        count = orderItem.getCount();
    }
}

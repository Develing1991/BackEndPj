package project.study.user.dto.findUserOne;

import lombok.Data;
import project.study.order.domain.Order;
import project.study.order.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserOrderResponseDto {
    private OrderStatus orderStatus;
    private LocalDateTime orderDate;
    private List<UserOrderItemResponseDto> userOrderItemResponseDtos;

    public UserOrderResponseDto(Order order) {
        orderStatus = order.getOrderStatus();
        orderDate = order.getOrderDate();
        userOrderItemResponseDtos = order.getOrderItems().stream()
                .map(orderItem -> new UserOrderItemResponseDto(orderItem))
                .collect(Collectors.toList());
    }
}

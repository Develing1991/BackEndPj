package project.study.order.dto;

import lombok.Data;
import project.study.common.domain.Address;
import project.study.order.domain.Order;
import project.study.order.domain.OrderItem;
import project.study.order.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderResponseDto {
    private Long id;
    private String name;
    private LocalDateTime createdDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemResponseDto> orderItems;

    public OrderResponseDto(Order order) {
        this.id = order.getId();
        this.name = order.getUser().getName();
        this.createdDate = order.getCreatedDate();
        this.orderStatus = order.getOrderStatus();
        this.address = order.getDelivery().getAddress();
        this.orderItems = order.getOrderItems().stream().map(orderItem -> new OrderItemResponseDto(orderItem)).collect(Collectors.toList());
    }
}

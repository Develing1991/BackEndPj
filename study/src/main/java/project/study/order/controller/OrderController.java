package project.study.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import project.study.order.dto.CreateProductIdListRequestDto;
import project.study.order.dto.GetProductListRequest;
import project.study.order.dto.OrderResponseDto;
import project.study.order.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    //세션 유저아이디 - 주문할 유저  //서비스에서
    //상품 아이디 - 상품조회List로 받아야 할것
    //상품 카운트 - 해당 상품 갯수 주문
    @PostMapping("/order")
    public Long createOrder(@RequestBody CreateProductIdListRequestDto<GetProductListRequest> createProductIdListRequestDto) {
        return orderService.createOrder(createProductIdListRequestDto);
    }

    @GetMapping("/orders")
    public Page<OrderResponseDto> orderList(Pageable pageable) {
        return orderService.findOrderList(pageable);
    }

    @DeleteMapping("/order/{id}")
    public String cancelOrder(@PathVariable("id") Long id) {
        orderService.cancelOrder(id);
        return "redirect:/orders";
    }
}

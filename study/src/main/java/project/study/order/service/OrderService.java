package project.study.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study.common.domain.Address;
import project.study.order.domain.Delivery;
import project.study.order.domain.Order;
import project.study.order.domain.OrderItem;
import project.study.order.domain.OrderStatus;
import project.study.order.dto.CreateProductIdListRequestDto;
import project.study.order.dto.GetProductListRequest;
import project.study.order.dto.OrderResponseDto;
import project.study.order.repository.OrderRepository;
import project.study.order.repository.OrderRepositoryCustom;
import project.study.projuct.domain.Product;
import project.study.projuct.repository.ProductRepository;
import project.study.user.domain.User;
import project.study.user.repository.UserRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepositoryCustom orderRepositoryCustom;

    @Resource(name = "loginUserBean")
    private User loginUserBean;

    @Transactional
    public Long createOrder(CreateProductIdListRequestDto<GetProductListRequest> createProductIdListRequestDto){
        if(loginUserBean.getId() == null){
            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
        }
        User findUser = userRepository.findById(loginUserBean.getId()).get();
        Address address = findUser.getAddress();
        Delivery delivery = new Delivery();
        delivery.setAddress(address);

        int orderListCount = createProductIdListRequestDto.getProductIds().size();
        List<OrderItem> orderItems = new ArrayList<>();
        for (int i=0; i<orderListCount; i++){
            Product product = productRepository.findById(createProductIdListRequestDto.getProductIds().get(i).getProductId()).get();
            orderItems.add(OrderItem.createOrderItem(product, product.getPrice(), createProductIdListRequestDto.getProductIds().get(i).getCount()));
        }
        Order order = Order.createOrder(findUser, delivery, orderItems);
        orderRepository.save(order);
        return order.getId();
    }

    public Page<OrderResponseDto> findOrderList(Pageable pageable) {
        Page<Order> orderList = orderRepository.findAll(pageable);
        Page<OrderResponseDto> map = orderList.map(order -> new OrderResponseDto(order));
        return map;

    }

    @Transactional
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id).get();
        order.cancel();
    }
}

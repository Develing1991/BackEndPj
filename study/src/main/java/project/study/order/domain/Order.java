package project.study.order.domain;

import project.study.user.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@SequenceGenerator(name = "ORDERS_SEQ_GENERATOR"
//        , sequenceName = "ORDERS_SEQ"
//        , initialValue = 1, allocationSize = 1)
//@Table(name = "ORDERS")
public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDERS_SEQ_GENERATOR")
//    @Column(name = "order_id")
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private List<OrderItem> orderItems = new ArrayList<>();
//
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "delivery_id")
//    private Delivery delivery;
//
}

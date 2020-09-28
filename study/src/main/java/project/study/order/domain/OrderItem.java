package project.study.order.domain;

import lombok.Getter;
import lombok.Setter;
import project.study.projuct.domain.Product;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "ORDER_ITEM_SEQ_GENERATOR", sequenceName = "ORDER_ITEM_SEQ"
        , initialValue = 1, allocationSize = 1)
@Getter @Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ITEM_SEQ_GENERATOR")
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    private int orderPrice;
    private int count;

    public static OrderItem createOrderItem(Product product, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        product.removeStock(count);
        return orderItem;
    }

    public void cancel(){
        getProduct().addStock(count);
    }

    public int getTotalPrice(){
        return getOrderPrice() * getCount();
    }

}

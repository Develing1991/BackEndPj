package project.study.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study.common.domain.Address;
import project.study.user.domain.User;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "DELIVERY_SEQ_GENERATOR",sequenceName = "DELIVERY_SEQ"
        ,initialValue = 1, allocationSize = 1)
@NoArgsConstructor
@Getter
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "DELIVERY_SEQ_GENERATOR")
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    public void setOrder(Order order) {
        this.order = order;
        deliveryStatus = DeliveryStatus.READY;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

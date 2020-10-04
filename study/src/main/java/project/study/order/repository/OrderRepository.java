package project.study.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.study.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}

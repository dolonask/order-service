package kg.easy.orderservice.dao;

import kg.easy.orderservice.models.entity.Address;
import kg.easy.orderservice.models.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}

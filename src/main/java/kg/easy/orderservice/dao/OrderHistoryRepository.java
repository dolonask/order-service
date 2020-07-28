package kg.easy.orderservice.dao;

import kg.easy.orderservice.models.entity.Order;
import kg.easy.orderservice.models.entity.OrderHistory;
import kg.easy.orderservice.models.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

    OrderHistory findTopByOrderOrderByIdDesc(Order order);
    List<OrderHistory> findAllByStatusAndEndDateIsNull(OrderStatus status);
    List<OrderHistory> findAllByEndDateIsNull();


}

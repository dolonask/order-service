package kg.easy.orderservice.services;

import kg.easy.orderservice.models.dto.OrderDto;
import kg.easy.orderservice.models.enums.OrderStatus;

import java.util.List;

public interface OrderService {


    OrderDto createOrder(OrderDto orderDto, Long userId);

    List<OrderDto> findOrdersByStatus(Integer pageNo, Integer pageSize, String sortBy, OrderStatus orderStatus);

    OrderDto findOrderById(Long orderId);


    List<OrderDto> findAllOrders(Integer pageNo, Integer pageSize, String sortBy);

    OrderDto updateOrder(OrderDto orderDto, Long userId);

    boolean changeOrderStatus(Long orderId, OrderStatus newStatus, Long userId);

    List<OrderDto> findOrdersByUserAndShifts(Long userId, Long shiftId, Integer pageNo, Integer pageSize, String sortBy);
}

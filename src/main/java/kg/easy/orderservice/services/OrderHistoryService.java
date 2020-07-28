package kg.easy.orderservice.services;

import kg.easy.orderservice.models.dto.OrderDto;
import kg.easy.orderservice.models.dto.OrderHistoryDto;
import kg.easy.orderservice.models.dto.UserDto;
import kg.easy.orderservice.models.enums.OrderStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderHistoryService {

    OrderHistoryDto setOrderHistory(OrderDto orderDto, OrderStatus newStatus, UserDto userDto);

    List<OrderDto> findOrdersByStatus(OrderStatus orderStatus, Pageable paging);

    OrderHistoryDto findHistoryByOrder(OrderDto orderDto);


}

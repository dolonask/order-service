package kg.easy.orderservice.services;

import kg.easy.orderservice.models.dto.OrderDetailDto;
import kg.easy.orderservice.models.dto.OrderDto;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetailDto> saveOrderDetails(OrderDto orderDto);

    List<OrderDetailDto> findOrderDetails(OrderDto orderDto);

}


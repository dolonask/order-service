package kg.easy.orderservice.mappers;

import kg.easy.orderservice.models.dto.OrderDto;
import kg.easy.orderservice.models.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order orderDtoToOrder(OrderDto orderDto);
    OrderDto orderToOrderDto(Order order);



}

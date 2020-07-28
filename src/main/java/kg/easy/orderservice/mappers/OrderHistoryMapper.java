package kg.easy.orderservice.mappers;

import kg.easy.orderservice.models.dto.OrderHistoryDto;
import kg.easy.orderservice.models.entity.OrderHistory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderHistoryMapper {

    OrderHistoryMapper INSTANCE = Mappers.getMapper(OrderHistoryMapper.class);

    OrderHistory orderHistoryDtoToOrderHistory(OrderHistoryDto orderHistoryDto);
    OrderHistoryDto orderHistoryToOrderHistoryDto(OrderHistory orderHistory);
}

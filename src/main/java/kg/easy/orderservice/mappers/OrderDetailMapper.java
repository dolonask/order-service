package kg.easy.orderservice.mappers;

import kg.easy.orderservice.models.dto.OrderDetailDto;
import kg.easy.orderservice.models.dto.OrderDto;
import kg.easy.orderservice.models.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);


    OrderDetail orderDetailDtoToOrderDetail(OrderDetailDto orderDetailDto);
    List<OrderDetail> orderDetailDtosToOrderDetails(List<OrderDetailDto> orderDetailDtos);
    List<OrderDetailDto> orderDetailsToOrderDetailDtos(List<OrderDetail> orderDetails);


    default List<OrderDetail> orderDtoToOrderDetails(OrderDto orderDto){
        List<OrderDetail> orderDetails = orderDetailDtosToOrderDetails(orderDto.getOrderDetails());

        orderDetails.stream()
                .forEach(x->x.setOrder(OrderMapper.INSTANCE.orderDtoToOrder(orderDto)));

        return orderDetails;
    }

}

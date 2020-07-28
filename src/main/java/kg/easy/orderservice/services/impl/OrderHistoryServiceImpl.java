package kg.easy.orderservice.services.impl;

import kg.easy.orderservice.dao.OrderHistoryRepository;
import kg.easy.orderservice.mappers.OrderHistoryMapper;
import kg.easy.orderservice.mappers.OrderMapper;
import kg.easy.orderservice.mappers.UserMapper;
import kg.easy.orderservice.models.dto.OrderDto;
import kg.easy.orderservice.models.dto.OrderHistoryDto;
import kg.easy.orderservice.models.dto.UserDto;
import kg.easy.orderservice.models.entity.OrderHistory;
import kg.easy.orderservice.models.enums.OrderStatus;
import kg.easy.orderservice.services.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;


    @Override
    public OrderHistoryDto setOrderHistory(OrderDto orderDto, OrderStatus newStatus, UserDto userDto) {

        OrderHistory orderHistory = orderHistoryRepository.findTopByOrderOrderByIdDesc(OrderMapper.INSTANCE.orderDtoToOrder(orderDto));

        if (orderHistory != null){
            if (!orderHistory.getStatus().equals(newStatus)){
                orderHistory.setEndDate(new Date());
                orderHistory.setUser(UserMapper.INSTANCE.userDtoToUser(userDto));
                orderHistory = orderHistoryRepository.save(orderHistory);
            }else{
                return OrderHistoryMapper.INSTANCE.orderHistoryToOrderHistoryDto(orderHistory);
            }
        }

        OrderHistory newOrderHistory = new OrderHistory();
        newOrderHistory.setUser(UserMapper.INSTANCE.userDtoToUser(userDto));
        newOrderHistory.setStartDate(new Date());
        newOrderHistory.setStatus(newStatus);
        newOrderHistory.setOrder(OrderMapper.INSTANCE.orderDtoToOrder(orderDto));

        newOrderHistory = orderHistoryRepository.save(newOrderHistory);

        return OrderHistoryMapper.INSTANCE.orderHistoryToOrderHistoryDto(newOrderHistory);
    }

    @Override
    public List<OrderDto> findOrdersByStatus(OrderStatus orderStatus, Pageable paging) {
        List<OrderHistory> orderHistories;

        if (orderStatus.equals(OrderStatus.ALL)){
            orderHistories = orderHistoryRepository.findAllByEndDateIsNull();
        }else{
            orderHistories = orderHistoryRepository.findAllByStatusAndEndDateIsNull(orderStatus);
        }

        List<OrderDto> orderDtos = orderHistories.stream()
                .map(x->{
                    OrderDto orderDto = OrderMapper.INSTANCE.orderToOrderDto(x.getOrder());
                    orderDto.setOrderHistory(OrderHistoryMapper.INSTANCE.orderHistoryToOrderHistoryDto(x));
                    return orderDto;
                })
                .collect(Collectors.toList());


        return orderDtos;
    }

    @Override
    public OrderHistoryDto findHistoryByOrder(OrderDto orderDto) {
        OrderHistory orderHistory = orderHistoryRepository.findTopByOrderOrderByIdDesc(OrderMapper.INSTANCE.orderDtoToOrder(orderDto));
        return OrderHistoryMapper.INSTANCE.orderHistoryToOrderHistoryDto(orderHistory);
    }


}

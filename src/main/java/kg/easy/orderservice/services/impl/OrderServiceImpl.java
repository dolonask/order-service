package kg.easy.orderservice.services.impl;

import kg.easy.orderservice.dao.OrderRepository;
import kg.easy.orderservice.exceptions.IncorrectStatus;
import kg.easy.orderservice.exceptions.ResourceNotFoundException;
import kg.easy.orderservice.mappers.OrderMapper;
import kg.easy.orderservice.models.dto.OrderDetailDto;
import kg.easy.orderservice.models.dto.OrderDto;
import kg.easy.orderservice.models.dto.OrderHistoryDto;
import kg.easy.orderservice.models.dto.UserDto;
import kg.easy.orderservice.models.entity.Order;
import kg.easy.orderservice.models.enums.OrderStatus;
import kg.easy.orderservice.services.OrderDetailService;
import kg.easy.orderservice.services.OrderHistoryService;
import kg.easy.orderservice.services.OrderService;
import kg.easy.orderservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final UserService userService;
    private final OrderHistoryService orderHistoryService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderDetailService orderDetailService,
                            UserService userService,
                            OrderHistoryService orderHistoryService) {
        this.orderRepository = orderRepository;
        this.orderDetailService = orderDetailService;
        this.userService = userService;
        this.orderHistoryService = orderHistoryService;
    }

    private OrderDto getOrderDto(OrderDto orderDto){

        orderDto.setOrderDetails(orderDetailService.findOrderDetails(orderDto));

        orderDto.setOrderHistory(orderHistoryService.findHistoryByOrder(orderDto));

        return orderDto;
    }

    private OrderDto saveOrder(OrderDto orderDto, UserDto userDto){
        Order order = orderRepository.save(OrderMapper.INSTANCE.orderDtoToOrder(orderDto));
        orderDto.setId(order.getId());

        List<OrderDetailDto> orderDetailDtos = orderDetailService.saveOrderDetails(orderDto);
        orderDto.setOrderDetails(orderDetailDtos);

        OrderHistoryDto orderHistoryDto = orderHistoryService.setOrderHistory(orderDto, OrderStatus.NEW,userDto);
        orderDto.setOrderHistory(orderHistoryDto);

        return orderDto;
    }
    @Override
    public OrderDto createOrder(OrderDto orderDto, Long userId) {

        UserDto userDto = userService.findUserById(userId);

        orderDto.setAddDate(new Date());

        return saveOrder(orderDto, userDto);
    }

    @Override
    public List<OrderDto> findOrdersByStatus(Integer pageNo, Integer pageSize, String sortBy, OrderStatus orderStatus) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));


        List<OrderDto> orderDtos = orderHistoryService.findOrdersByStatus(orderStatus, paging);

        orderDtos.stream()
                .forEach(x->x.setOrderDetails(orderDetailService.findOrderDetails(x)));

        return orderDtos;
    }

    @Override
    public OrderDto findOrderById(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Заявка не найдена"));

        OrderDto orderDto = OrderMapper.INSTANCE.orderToOrderDto(order);

        orderDto = getOrderDto(orderDto);

        return orderDto;
    }

    @Override
    public List<OrderDto> findAllOrders(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Order> pagedResult = orderRepository.findAll(paging);

        List<OrderDto> orderDtos = pagedResult.get()
                .map(x-> getOrderDto(OrderMapper.INSTANCE.orderToOrderDto(x)))
                .collect(Collectors.toList());

        return orderDtos;
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto, Long userId) {
        OrderDto currOrderDto = findOrderById(orderDto.getId());
        UserDto userDto = userService.findUserById(userId);
        return saveOrder(orderDto,userDto);
    }

    @Override
    public boolean changeOrderStatus(Long orderId, OrderStatus newStatus, Long userId) {

        UserDto userDto = userService.findUserById(userId);
        OrderDto orderDto = findOrderById(orderId);

        if (orderDto.getOrderHistory().getStatus().equals(newStatus)){
            throw new IncorrectStatus("Статус уже изменен!");
        }

        OrderHistoryDto orderHistoryDto = orderHistoryService.setOrderHistory(orderDto,newStatus, userDto );

        return orderHistoryDto.getId() != null;
    }

    @Override
    public List<OrderDto> findOrdersByUserAndShifts(Long userId, Long shiftId, Integer pageNo, Integer pageSize, String sortBy) {
        return null;
    }
}

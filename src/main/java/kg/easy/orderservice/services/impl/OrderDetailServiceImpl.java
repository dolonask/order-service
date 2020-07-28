package kg.easy.orderservice.services.impl;

import kg.easy.orderservice.dao.OrderDetailRepository;
import kg.easy.orderservice.mappers.OrderDetailMapper;
import kg.easy.orderservice.models.dto.OrderDetailDto;
import kg.easy.orderservice.models.dto.OrderDto;
import kg.easy.orderservice.models.entity.OrderDetail;
import kg.easy.orderservice.services.ClientService;
import kg.easy.orderservice.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ClientService clientService;


    @Override
    public List<OrderDetailDto> saveOrderDetails(OrderDto orderDto) {

        orderDto.getOrderDetails()
                .stream()
                .forEach(x->{
                    x.setRecipient(clientService.saveClient(x.getRecipient()));
                    x.setSender(clientService.saveClient(x.getSender()));
                });

        List<OrderDetail> orderDetails = OrderDetailMapper.INSTANCE.orderDtoToOrderDetails(orderDto);

        orderDetails = orderDetailRepository.saveAll(orderDetails);
        return OrderDetailMapper.INSTANCE.orderDetailsToOrderDetailDtos(orderDetails);
    }

    @Override
    public List<OrderDetailDto> findOrderDetails(OrderDto orderDto) {

        List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderId(orderDto.getId());

        return OrderDetailMapper.INSTANCE.orderDetailsToOrderDetailDtos(orderDetails);
    }
}

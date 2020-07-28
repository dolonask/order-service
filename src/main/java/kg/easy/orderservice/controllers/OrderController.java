package kg.easy.orderservice.controllers;

import kg.easy.orderservice.models.dto.OrderDto;
import kg.easy.orderservice.models.entity.User;
import kg.easy.orderservice.models.enums.OrderStatus;
import kg.easy.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public OrderDto saveOrder(@RequestBody OrderDto orderDto, @RequestParam Long userId){
        return orderService.createOrder(orderDto, userId);
    }

    @PutMapping("/update")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto, @RequestParam Long userId){
        return orderService.updateOrder(orderDto, userId);
    }

    @GetMapping("/status")
    public boolean changeOrderStatus(@RequestParam Long orderId, @RequestParam OrderStatus status, @RequestParam Long userId){
         return orderService.changeOrderStatus(orderId,status,userId);
    }

    @GetMapping("/all/{userId}/{shiftId}")
    public List<OrderDto> findOrdersByUser(@PathVariable Long userId,
                                        @PathVariable Long shiftId,
                                        @RequestParam(defaultValue = "0") Integer pageNo,
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        @RequestParam(defaultValue = "id") String sortBy){
        return orderService.findOrdersByUserAndShifts(userId,shiftId,pageNo,pageSize,sortBy);
    }

    @GetMapping("/by/status")
    public List<OrderDto> findOrdersByStatus(@RequestParam OrderStatus orderStatus,
                                             @RequestParam(defaultValue = "0") Integer pageNo,
                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                             @RequestParam(defaultValue = "id") String sortBy){
        return orderService.findOrdersByStatus(pageNo, pageSize, sortBy, orderStatus);
    }

    @GetMapping("/all")
    public List<OrderDto> findOrders(@RequestParam(defaultValue = "0") Integer pageNo,
                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                     @RequestParam(defaultValue = "id") String sortBy){
        return orderService.findAllOrders(pageNo, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    public OrderDto findOrderById(@PathVariable Long id){
        return orderService.findOrderById(id);
    }





}

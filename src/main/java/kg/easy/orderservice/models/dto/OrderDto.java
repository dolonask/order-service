package kg.easy.orderservice.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.easy.orderservice.models.entity.OrderDetail;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm", timezone = "Asia/Bishkek")
    private Date addDate;
    private double ransomSum;

    private OrderHistoryDto orderHistory;
    private List<OrderDetailDto> orderDetails;




}

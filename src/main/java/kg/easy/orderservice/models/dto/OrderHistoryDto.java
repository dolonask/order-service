package kg.easy.orderservice.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.easy.orderservice.models.enums.OrderStatus;
import lombok.Data;

import java.util.Date;

@Data
public class OrderHistoryDto {

    private Long id;

    private UserDto user;

    private OrderStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss", timezone = "Asia/Bishkek")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss", timezone = "Asia/Bishkek")
    private Date endDate;
}

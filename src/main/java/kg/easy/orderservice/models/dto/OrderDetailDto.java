package kg.easy.orderservice.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDetailDto {

    private Long id;

    private ClientDto recipient;
    private ClientDto sender;

    private AddressDto fromAddress;

    private AddressDto toAddress;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss", timezone = "Asia/Bishkek")
    private Date estimatedDate;
}

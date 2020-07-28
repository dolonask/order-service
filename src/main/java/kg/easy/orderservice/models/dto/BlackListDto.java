package kg.easy.orderservice.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BlackListDto {

    private Long id;

    private ClientDto clientDto;
    private UserDto user;

    private BlockReasonDto reason;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss", timezone = "Asia/Bishkek")
    private Date addDate;

    private boolean active;
}

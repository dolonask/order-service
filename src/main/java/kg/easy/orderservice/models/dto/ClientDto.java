package kg.easy.orderservice.models.dto;

import kg.easy.orderservice.models.enums.ClientStatus;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ClientDto {

    private Long id;

    private String name;
    private List<PhoneDto> phones;
    private List<AddressDto> addresses;
    private ClientStatus status;


}

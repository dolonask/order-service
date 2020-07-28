package kg.easy.orderservice.services;

import kg.easy.orderservice.models.dto.AddressDto;
import kg.easy.orderservice.models.dto.ClientDto;

import java.util.List;

public interface AddressService {
    List<AddressDto> saveClientAddresses(ClientDto clientDto);
}

package kg.easy.orderservice.services;

import kg.easy.orderservice.models.dto.ClientDto;
import kg.easy.orderservice.models.dto.PhoneDto;
import kg.easy.orderservice.models.entity.Client;
import kg.easy.orderservice.models.entity.Phone;

import java.util.List;

public interface PhoneService {

    PhoneDto savePhone(PhoneDto phoneDto, ClientDto clientDto);

    List<PhoneDto> saveClientPhones(ClientDto clientDto);
    List<PhoneDto> updateClientPhones(ClientDto clientDto);

    List<Phone> findClientPhones(Client client);
}

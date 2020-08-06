package kg.easy.orderservice.services;

import kg.easy.orderservice.models.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto findClientByMsisdn(String msisdn);

    ClientDto saveClient(ClientDto clientDto);
    ClientDto findClientById(Long id);

    ClientDto updateClient(ClientDto clientDto);

    List<ClientDto> findAllClients();

    List<ClientDto> findClientsByPhoneOrName(String value);
}

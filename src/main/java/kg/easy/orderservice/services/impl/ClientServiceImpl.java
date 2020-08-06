package kg.easy.orderservice.services.impl;

import kg.easy.orderservice.dao.ClientRepository;
import kg.easy.orderservice.exceptions.ResourceNotFoundException;
import kg.easy.orderservice.mappers.ClientMapper;
import kg.easy.orderservice.mappers.PhoneMapper;
import kg.easy.orderservice.models.dto.AddressDto;
import kg.easy.orderservice.models.dto.ClientDto;
import kg.easy.orderservice.models.dto.PhoneDto;
import kg.easy.orderservice.models.entity.Client;
import kg.easy.orderservice.models.entity.Phone;
import kg.easy.orderservice.services.AddressService;
import kg.easy.orderservice.services.ClientService;
import kg.easy.orderservice.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PhoneService phoneService;
    @Autowired
    private AddressService addressService;

    @Override
    public ClientDto findClientByMsisdn(String msisdn) {

        Client client = clientRepository.findClientByMsisdn(msisdn);

        if (client == null){
            throw new ResourceNotFoundException("Клиент не найден! - " + msisdn);
        }

        List<Phone> phones = phoneService.findClientPhones(client);

        return ClientMapper.INSTANCE.clientToClientDto(client, phones);

    }




    @Override
    public ClientDto saveClient(ClientDto clientDto) {

        Client client = ClientMapper.INSTANCE.clientDtoToClient(clientDto);
        client = clientRepository.save(client);


        clientDto.setId(client.getId());

        clientDto.setPhones(
                phoneService.saveClientPhones(clientDto)
        );

        clientDto.setAddresses(
                addressService.saveClientAddresses(clientDto)
        );


//        ClientDto finalClientDto = clientDto;
//        List<Phone> phones = phoneDtos.stream()
//                .map(x->PhoneMapper.INSTANCE.phoneDtoToPhone(x, finalClientDto))
//                .collect(Collectors.toList());
//
//        clientDto = ClientMapper.INSTANCE.clientToClientDto(client, phones);
//
//        phoneDtos = phoneService.saveClientPhones(clientDto);
//
//        clientDto.setPhones(phoneDtos);

        return clientDto;
    }

    @Override
    public ClientDto findClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Клиент не найден"));
        ClientDto clientDto = ClientMapper.INSTANCE.clientToClientDto(client,phoneService.findClientPhones(client));
        return clientDto;
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {

        Client client = clientRepository.findById(clientDto.getId()).orElseThrow(()->new ResourceNotFoundException("Клиент не найден!"));

        client.setName(clientDto.getName());
        client.setStatus(clientDto.getStatus());

        clientRepository.save(client);

        List<PhoneDto> phoneDtos = phoneService.updateClientPhones(clientDto);

        clientDto.setPhones(phoneDtos);
        clientDto.setAddresses(addressService.saveClientAddresses(clientDto));

        return clientDto;
    }

    @Override
    public List<ClientDto> findAllClients() {

        List<ClientDto> clientDtos;
        List<Client> clients = clientRepository.findAll();

        clientDtos = clients.stream()
                .map(x-> ClientMapper.INSTANCE.clientToClientDto(x, phoneService.findClientPhones(x)))
                .collect(Collectors.toList());

        return clientDtos;
    }

    @Override
    public List<ClientDto> findClientsByPhoneOrName(String value) {

        List<Phone> phones = phoneService.findByClientNameOrPhone(value);

        List<ClientDto> clientDtos =  ClientMapper.INSTANCE.phonesToClientDtos(phones);

        return clientDtos;
    }
}

package kg.easy.orderservice.mappers;

import kg.easy.orderservice.models.dto.ClientDto;
import kg.easy.orderservice.models.dto.PhoneDto;
import kg.easy.orderservice.models.entity.Client;
import kg.easy.orderservice.models.entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client clientDtoToClient(ClientDto clientDto);

    default ClientDto clientToClientDto(Client client, List<Phone> phones){
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setStatus(client.getStatus());

        clientDto.setPhones(phones.stream()
                .map(x->PhoneMapper.INSTANCE.phoneToPhoneDto(x))
                .collect(Collectors.toList()));

        return clientDto;
    }
}

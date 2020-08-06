package kg.easy.orderservice.mappers;

import kg.easy.orderservice.models.dto.ClientDto;
import kg.easy.orderservice.models.dto.PhoneDto;
import kg.easy.orderservice.models.entity.Client;
import kg.easy.orderservice.models.entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client clientDtoToClient(ClientDto clientDto);
    ClientDto clientToClientDto(Client client);

    static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

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

    default List<ClientDto> phonesToClientDtos(List<Phone> phones){

        List<ClientDto> clientDtos = phones.stream()
                .map(x-> ClientMapper.INSTANCE.clientToClientDto(x.getClient()))
                .filter(distinctByKey(ClientDto::getId))
                .collect(Collectors.toList());

        clientDtos.stream()
                .forEach(x->{
                    x.setPhones(phones.stream().filter(y->y.getClient().getId() == x.getId())
                            .map(y-> PhoneMapper.INSTANCE.phoneToPhoneDto(y))
                            .collect(Collectors.toList()));
                });
        return clientDtos;

    }


}

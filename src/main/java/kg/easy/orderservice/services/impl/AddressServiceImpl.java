package kg.easy.orderservice.services.impl;

import kg.easy.orderservice.dao.AddressRepository;
import kg.easy.orderservice.mappers.AddressMapper;
import kg.easy.orderservice.mappers.ClientMapper;
import kg.easy.orderservice.models.dto.AddressDto;
import kg.easy.orderservice.models.dto.ClientDto;
import kg.easy.orderservice.models.entity.Address;
import kg.easy.orderservice.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Override
    public List<AddressDto> saveClientAddresses(ClientDto clientDto) {

        List<AddressDto> addressDtos = clientDto.getAddresses();

        List<Address> addresses = AddressMapper.INSTANCE.addressDtosToAddresses(addressDtos);

        addresses.stream()
                .forEach(x->x.setClient(ClientMapper.INSTANCE.clientDtoToClient(clientDto)));

        addresses = addressRepository.saveAll(addresses);

        return AddressMapper.INSTANCE.addressesToAddressDtos(addresses);
    }
}

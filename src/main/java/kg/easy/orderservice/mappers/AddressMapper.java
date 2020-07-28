package kg.easy.orderservice.mappers;

import kg.easy.orderservice.models.dto.AddressDto;
import kg.easy.orderservice.models.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address addressDtoToAddress(AddressDto addressDto);
    List<Address> addressDtosToAddresses(List<AddressDto> addressDtos);
    AddressDto addressToAddressDto(Address address);
    List<AddressDto> addressesToAddressDtos(List<Address> addresses);


}

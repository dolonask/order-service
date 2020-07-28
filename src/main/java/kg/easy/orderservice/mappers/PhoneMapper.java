package kg.easy.orderservice.mappers;

import kg.easy.orderservice.models.dto.ClientDto;
import kg.easy.orderservice.models.dto.PhoneDto;
import kg.easy.orderservice.models.entity.Phone;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface PhoneMapper {
    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

    @Mappings({
            @Mapping(source = "phoneDto.id", target = "id"),
            @Mapping(source = "phoneDto.msisdn", target = "msisdn"),
            @Mapping(source = "phoneDto.active", target = "active"),
            @Mapping(source = "clientDto", target = "client")
    }
    )
    Phone phoneDtoToPhone(PhoneDto phoneDto, ClientDto clientDto);
    @InheritInverseConfiguration
    PhoneDto phoneToPhoneDto(Phone phone);


    default List<Phone> phoneDtosToPhones(List<PhoneDto> phoneDtos, ClientDto clientDto){
        List<Phone> phones = phoneDtos.stream()
                .map(x->phoneDtoToPhone(x,clientDto))
                .collect(Collectors.toList());
        return phones;
    }

    List<PhoneDto> phonesToPhoneDtos(List<Phone> newPhones);
}

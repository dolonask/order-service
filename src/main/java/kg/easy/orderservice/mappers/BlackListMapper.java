package kg.easy.orderservice.mappers;

import kg.easy.orderservice.models.dto.BlackListDto;
import kg.easy.orderservice.models.entity.BlackList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlackListMapper {

    BlackListMapper INSTANCE = Mappers.getMapper(BlackListMapper.class);

    BlackList blackListDtoToBlackList(BlackListDto blackListDto);
    BlackListDto blackListToBlackListDto(BlackList blackList);


}

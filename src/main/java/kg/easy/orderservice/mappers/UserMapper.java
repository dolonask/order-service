package kg.easy.orderservice.mappers;

import kg.easy.orderservice.models.dto.UserDto;
import kg.easy.orderservice.models.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
}

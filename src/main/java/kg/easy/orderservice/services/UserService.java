package kg.easy.orderservice.services;

import kg.easy.orderservice.models.dto.UserDto;

public interface UserService {

    UserDto save(UserDto userDto);

    UserDto findUserById(Long id);
}

package kg.easy.orderservice.services.impl;

import kg.easy.orderservice.dao.UserRepository;
import kg.easy.orderservice.exceptions.ResourceNotFoundException;
import kg.easy.orderservice.mappers.UserMapper;
import kg.easy.orderservice.models.dto.UserDto;
import kg.easy.orderservice.models.entity.User;
import kg.easy.orderservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto save(UserDto userDto) {
        User user = userRepository.save(UserMapper.INSTANCE.userDtoToUser(userDto));
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Override
    public UserDto findUserById(Long id) {

        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Пользователь не найден"));
        return UserMapper.INSTANCE.userToUserDto(user);
    }


}

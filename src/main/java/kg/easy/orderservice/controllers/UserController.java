package kg.easy.orderservice.controllers;

import kg.easy.orderservice.models.dto.UserDto;
import kg.easy.orderservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping
    public UserDto save(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }



}

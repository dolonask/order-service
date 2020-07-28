package kg.easy.orderservice.controllers;

import kg.easy.orderservice.services.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/blacklist")
public class BlackListController {


    @Autowired
    private BlackListService blackListService;
    @GetMapping("/check/{msisdn}")
    public boolean checkInBlackList(@PathVariable String msisdn){
        return blackListService.clientExistsInBlackList(msisdn);
    }


}

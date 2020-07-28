package kg.easy.orderservice.controllers;

import kg.easy.orderservice.models.dto.ClientDto;
import kg.easy.orderservice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public ClientDto findClientById(@PathVariable Long id){
        return clientService.findClientById(id);
    }

    @PostMapping("/create")
    public ClientDto saveClient(@RequestBody ClientDto clientDto){
        return clientService.saveClient(clientDto);
    }

    @PutMapping("/update")
    public ClientDto updateClient(@RequestBody ClientDto clientDto){
        return clientService.updateClient(clientDto);

    }

    @GetMapping("/msisdn/{msisdn}")
    public ClientDto findClientByMsisdn(@PathVariable String msisdn){
        return clientService.findClientByMsisdn(msisdn);
    }

}

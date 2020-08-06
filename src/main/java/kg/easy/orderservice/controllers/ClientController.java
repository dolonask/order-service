package kg.easy.orderservice.controllers;

import kg.easy.orderservice.models.dto.ClientDto;
import kg.easy.orderservice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public ClientDto findClientById(@PathVariable Long id){
        return clientService.findClientById(id);
    }

    @GetMapping("/find")
    public List<ClientDto> findClientsByPhoneOrName(@RequestParam String value){
        return clientService.findClientsByPhoneOrName(value);
    }

    @PostMapping("/create")
    public ClientDto saveClient(@RequestBody ClientDto clientDto){
        return clientService.saveClient(clientDto);
    }

    @PutMapping("/update")
    public ClientDto updateClient(@RequestBody ClientDto clientDto){
        return clientService.updateClient(clientDto);
    }

    @GetMapping("/list")
    public List<ClientDto> findAllClients(){
        return clientService.findAllClients();
    }


    @GetMapping("/msisdn/{msisdn}")
    public ClientDto findClientByMsisdn(@PathVariable String msisdn){
        return clientService.findClientByMsisdn(msisdn);
    }

}

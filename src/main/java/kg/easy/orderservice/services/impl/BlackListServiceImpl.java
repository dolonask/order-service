package kg.easy.orderservice.services.impl;

import kg.easy.orderservice.dao.BlackListRepository;
import kg.easy.orderservice.exceptions.ResourceNotFoundException;
import kg.easy.orderservice.mappers.BlackListMapper;
import kg.easy.orderservice.mappers.ClientMapper;
import kg.easy.orderservice.models.dto.BlackListDto;
import kg.easy.orderservice.models.dto.ClientDto;
import kg.easy.orderservice.models.entity.BlackList;
import kg.easy.orderservice.models.entity.Client;
import kg.easy.orderservice.services.BlackListService;
import kg.easy.orderservice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlackListServiceImpl implements BlackListService {

    @Autowired
    private ClientService clientService;
    @Autowired
    private BlackListRepository blackListRepository;


    @Override
    public BlackListDto findClientInBlackListByMsisdn(String msisdn) {

        ClientDto clientDto = clientService.findClientByMsisdn(msisdn);

        BlackList blackList = blackListRepository.findByClientAndActiveIsTrue(ClientMapper.INSTANCE.clientDtoToClient(clientDto));

        if (blackList == null){
            throw new ResourceNotFoundException("Клиент не найден в черном списке!");
        }

        return BlackListMapper.INSTANCE.blackListToBlackListDto(blackList);
    }

    @Override
    public boolean clientExistsInBlackList(String msisdn) {
        ClientDto clientDto = clientService.findClientByMsisdn(msisdn);
        return blackListRepository.existsByClientAndActiveIsTrue(ClientMapper.INSTANCE.clientDtoToClient(clientDto));
    }

    @Override
    public boolean clientExistsInBlackList(ClientDto clientDto) {
        return blackListRepository.existsByClientAndActiveIsTrue(ClientMapper.INSTANCE.clientDtoToClient(clientDto));
    }
}

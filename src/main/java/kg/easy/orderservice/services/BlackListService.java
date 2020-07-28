package kg.easy.orderservice.services;

import kg.easy.orderservice.models.dto.BlackListDto;
import kg.easy.orderservice.models.dto.ClientDto;
import kg.easy.orderservice.models.entity.Client;

public interface BlackListService {

    BlackListDto findClientInBlackListByMsisdn(String msisdn);

    boolean clientExistsInBlackList(String msisdn);
    boolean clientExistsInBlackList(ClientDto clientDto);

}

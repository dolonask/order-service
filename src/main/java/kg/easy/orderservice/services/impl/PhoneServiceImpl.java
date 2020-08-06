package kg.easy.orderservice.services.impl;

import kg.easy.orderservice.dao.PhoneRepository;
import kg.easy.orderservice.mappers.ClientMapper;
import kg.easy.orderservice.mappers.PhoneMapper;
import kg.easy.orderservice.models.dto.ClientDto;
import kg.easy.orderservice.models.dto.PhoneDto;
import kg.easy.orderservice.models.entity.Client;
import kg.easy.orderservice.models.entity.Phone;
import kg.easy.orderservice.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public PhoneDto savePhone(PhoneDto phoneDto, ClientDto clientDto) {
        Phone phone = PhoneMapper.INSTANCE.phoneDtoToPhone(phoneDto, clientDto);
        phone = phoneRepository.save(phone);
        return PhoneMapper.INSTANCE.phoneToPhoneDto(phone);
    }


    @Override
    public List<PhoneDto> saveClientPhones(ClientDto clientDto) {

        List<PhoneDto> phoneDtos = clientDto.getPhones().stream()
                .map(x->savePhone(x,clientDto))
                .collect(Collectors.toList());

        phoneDtos = phoneDtos
                .stream()
                .map(x->savePhone(x, clientDto))
                .collect(Collectors.toList());


        return phoneDtos;
    }

    @Override
    public List<PhoneDto> updateClientPhones(ClientDto clientDto) {
        List<PhoneDto> phoneDtos = clientDto.getPhones().stream()
                .map(x->savePhone(x,clientDto))
                .collect(Collectors.toList());

        List<Phone> currPhones = findClientPhones(ClientMapper.INSTANCE.clientDtoToClient(clientDto));

        currPhones = currPhones.stream()
                .filter(x->x.isActive())
                .filter(x->phoneDtos.stream().filter(y->y.getMsisdn().equals(x.getMsisdn())).count() == 0)
                .collect(Collectors.toList());

        currPhones.stream()
                .forEach(x->x.setActive(false));

        currPhones = phoneRepository.saveAll(currPhones);

        List<Phone> newPhones = PhoneMapper.INSTANCE.phoneDtosToPhones(phoneDtos, clientDto);
        newPhones = phoneRepository.saveAll(newPhones);


        return PhoneMapper.INSTANCE.phonesToPhoneDtos(newPhones);
    }

    @Override
    public List<Phone> findClientPhones(Client client) {
        return phoneRepository.findAllByClient(client);

    }

    @Override
    public List<Phone> findByClientNameOrPhone(String value) {
        return phoneRepository.findAllByClientNameOrPhone(value, value);
    }
}

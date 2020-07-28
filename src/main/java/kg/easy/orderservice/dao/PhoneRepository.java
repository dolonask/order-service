package kg.easy.orderservice.dao;

import kg.easy.orderservice.models.entity.Address;
import kg.easy.orderservice.models.entity.Client;
import kg.easy.orderservice.models.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    List<Phone> findAllByClient(Client client);
}

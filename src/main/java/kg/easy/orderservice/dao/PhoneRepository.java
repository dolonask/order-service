package kg.easy.orderservice.dao;

import kg.easy.orderservice.models.entity.Address;
import kg.easy.orderservice.models.entity.Client;
import kg.easy.orderservice.models.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    List<Phone> findAllByClient(Client client);

    @Query("select u from Phone u where u.client.name like %:name% or u.msisdn like %:msisdn%")
    List<Phone> findAllByClientNameOrPhone(String name, String msisdn);
}

package kg.easy.orderservice.dao;

import kg.easy.orderservice.models.entity.Address;
import kg.easy.orderservice.models.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select u from Client u join Phone p on u.id = p.client where p.msisdn = ?1")
    List<Client> findClientsByMsisdn(String msisdn);

    @Query("select u from Client u join Phone p on u.id = p.client.id where p.msisdn = ?1")
    Client findClientByMsisdn(String msisdn);



}

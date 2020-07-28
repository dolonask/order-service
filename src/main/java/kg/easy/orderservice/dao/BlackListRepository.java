package kg.easy.orderservice.dao;

import kg.easy.orderservice.models.entity.Address;
import kg.easy.orderservice.models.entity.BlackList;
import kg.easy.orderservice.models.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Long> {

    BlackList findByClientAndActiveIsTrue(Client client);
    boolean existsByClientAndActiveIsTrue(Client client);
}

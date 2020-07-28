package kg.easy.orderservice.dao;

import kg.easy.orderservice.models.entity.Address;
import kg.easy.orderservice.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

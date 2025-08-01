package lk.ijse.gdse72.o13_spring_secyrity_with_jwt.repository;

import lk.ijse.gdse72.o13_spring_secyrity_with_jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

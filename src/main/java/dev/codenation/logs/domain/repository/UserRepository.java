package dev.codenation.logs.domain.repository;

import dev.codenation.logs.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
//    UserAuth findByEmail(String userLogin);
    User findByEmail(String userLogin);
}

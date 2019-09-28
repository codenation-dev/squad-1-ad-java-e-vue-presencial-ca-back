package dev.codenation.logs.repository;

import dev.codenation.logs.domain.entity.Log;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LogRepository extends JpaRepository<Log, UUID> {
}

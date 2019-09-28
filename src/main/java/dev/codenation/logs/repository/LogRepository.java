package dev.codenation.logs.repository;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.domain.enums.Environment;
import dev.codenation.logs.domain.enums.Severity;
import dev.codenation.logs.dto.response.LogSumaryResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LogRepository extends JpaRepository<Log, UUID> {

    @Query(value = "SELECT DISTINCT ON (l.hash) l.hash, " +
            "l.id, " +
            "l.message," +
            "l.details," +
            "l.severity," +
            "l.environment," +
            "l.origin," +
            "l.archived," +
            "l.reported_by as reportedBy," +
            "l.created_at as createdAt," +
            "CAST(l2.total AS INTEGER)" +
            "FROM logs l " +
            "INNER JOIN " +
            "  (SELECT count(hash) total, hash FROM logs GROUP BY hash) AS l2 " +
            "   ON l2.hash = l.hash " +
            "ORDER BY l.hash, l.created_at DESC " +
            "\n-- #pageable\n",
            nativeQuery = true )
    Page<LogSumaryResponseDTO> findAllSumarized(@Param("hash") Integer hash,
                                                @Param("message") String message,
                                                @Param("details") String details,
                                                @Param("severity") Severity severity,
                                                @Param("environment") Environment environment,
                                                @Param("origin") String origin,
                                                @Param("reportedBy") UUID reportedBy,
                                                Pageable pageable);
}

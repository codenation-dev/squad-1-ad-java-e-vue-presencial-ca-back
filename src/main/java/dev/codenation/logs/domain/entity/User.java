package dev.codenation.logs.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.codenation.logs.authentication.WebSecurityConfig;
import dev.codenation.logs.domain.vo.UserAuth;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    private UUID id;

    @NotNull
    @Size(max = 50)
    private String firstName;

    @NotNull
    @Size(max = 50)
    private String lastName;

    @NotNull
    @Email
    @Size(max = 250)
    @Column(unique=true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "reportedBy")
    private List<Log> logs;

    @OneToMany(mappedBy = "archivedBy")
    private List<Log> archivedLogs;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    public void setPassword(String value){
        this.password = new WebSecurityConfig().cryptPasswordEncoder().encode(value);
    }
}

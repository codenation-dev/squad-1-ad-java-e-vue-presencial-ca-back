package dev.codenation.logs.mapper;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.dto.UserFindFilterDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password")
    })

    User map(UserFindFilterDTO filter);

    List<UserFindFilterDTO> map(List all);
}

package dev.codenation.logs.core.mapper;

import dev.codenation.logs.domain.entity.User;
import dev.codenation.logs.domain.valueObject.UserInformation;
import dev.codenation.logs.core.dto.request.UserFilterRequestDTO;
import dev.codenation.logs.core.dto.request.UserRequestDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mappings({
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "email", target = "email")
    })
    User map(UserFilterRequestDTO filter);

    @Mappings({
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password")
    })
    User map(UserRequestDTO request);

    @Mappings({
            @Mapping(source = "id" ,target = "id"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "createdAt",target = "createdAt"),
            @Mapping(source = "updatedAt",target = "updatedAt")
    })
    UserInformation mapInf(User request);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "firstName",target = "firstName"),
            @Mapping(source = "lastName",target = "lastName"),
            @Mapping(source = "email",target = "email"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt",target = "updatedAt")
    })
    @Named("toDto")
    UserInformation map(User user);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "firstName",target = "firstName"),
            @Mapping(source = "lastName",target = "lastName"),
            @Mapping(source = "email",target = "email"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "updatedAt",target = "updatedAt")
    })
    UserInformation map(@MappingTarget UserInformation dto, User parent);

    @IterableMapping(qualifiedByName = "toDto")
    List<UserInformation> map(List<User> children);

    static List<UserInformation> map(List<User> children, User parent) {
        throw new UnsupportedOperationException("Not implemented");
    }
}

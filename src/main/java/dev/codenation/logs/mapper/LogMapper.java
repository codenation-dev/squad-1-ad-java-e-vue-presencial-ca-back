package dev.codenation.logs.mapper;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.parameter.LogFilter;
import org.mapstruct.*;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface LogMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "hash", target = "hash"),
            @Mapping(source = "message", target = "logDetail.message"),
            @Mapping(source = "details", target = "logDetail.details"),
            @Mapping(source = "severity", target = "logDetail.severity"),
            @Mapping(source = "environment", target = "origin.environment"),
            @Mapping(source = "origin", target = "origin.origin"),
            @Mapping(source = "archived", target = "archived"),
            @Mapping(source = "archivedBy", target = "archivedBy.id"),
            @Mapping(source = "reportedBy", target = "reportedBy.id")
    })
    Log map(LogFilter filter);

//    @Named("optionalWrapper")
//    default <T> T optionalWrapper(Optional<T> optional) {
//        return optional.orElse(null);
//    }
}

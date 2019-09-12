package dev.codenation.logs.mapper;

import dev.codenation.logs.domain.entity.Log;
import dev.codenation.logs.filter.LogFilter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface LogMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "hash", target = "hash"),
            @Mapping(source = "message", target = "message.logDetail"),
            @Mapping(source = "details", target = "details.logDetail"),
            @Mapping(source = "severity", target = "severity.logDetail"),
            @Mapping(source = "environment", target = "environment.origin"),
            @Mapping(source = "origin", target = "origin.origin"),
            @Mapping(source = "archived", target = "archived"),
            @Mapping(source = "archivedBy", target = "archivedBy.id"),
            @Mapping(source = "reportedBy", target = "reportedBy.id")
    })

    Log map(LogFilter filter);
}

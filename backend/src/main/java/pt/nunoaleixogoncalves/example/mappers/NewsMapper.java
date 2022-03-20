package pt.nunoaleixogoncalves.example.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import pt.nunoaleixogoncalves.example.dto.NewsDto;
import pt.nunoaleixogoncalves.example.model.News;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = NewsMapperUtil.class)
public interface NewsMapper {

    @Mapping(target = "authorUuid", source = "author", qualifiedByName = "getAuthorUuid")
    NewsDto toDto(News news);

    @Mapping(target = "author", source = "authorUuid", qualifiedByName = "getAuthor")
    News toEntity(NewsDto newsDto);

    @Mapping(target = "author", source = "authorUuid", qualifiedByName = "getAuthor")
    void updateToEntityFromDto(NewsDto dto, @MappingTarget News entity);
}

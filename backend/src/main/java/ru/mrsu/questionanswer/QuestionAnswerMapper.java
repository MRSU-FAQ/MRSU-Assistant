package ru.mrsu.questionanswer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.mrsu.questionanswer.dto.CreateUpdateQuestionAnswerRequestDto;
import ru.mrsu.questionanswer.dto.QuestionAnswerResponseDto;

@Mapper(componentModel = "spring")
public interface QuestionAnswerMapper {

  @Mapping(target = "id", ignore = true)
  QuestionAnswer toEntity(CreateUpdateQuestionAnswerRequestDto dto);

  QuestionAnswerResponseDto toResponseDto(QuestionAnswer entity);

  void updateEntityFromDto(
    CreateUpdateQuestionAnswerRequestDto dto, @MappingTarget QuestionAnswer entity);
}

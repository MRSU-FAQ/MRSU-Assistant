package ru.mrsu.questionanswer;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface QuestionAnswerMapper {

  QuestionAnswer toEntity(CreateUpdateQuestionAnswerRequestDto dto);

  QuestionAnswerResponseDto toResponseDto(QuestionAnswer entity);

  void updateEntityFromDto(
    CreateUpdateQuestionAnswerRequestDto dto, @MappingTarget QuestionAnswer entity);
}

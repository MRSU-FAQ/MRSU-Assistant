package ru.mrsu.questionanswer.dto;


public record QuestionAnswerResponseDto (
  Long id,
  String question,
  String answer
) {}

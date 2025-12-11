package ru.mrsu.questionanswer;


public record QuestionAnswerResponseDto (
  Long id,
  String question,
  String answer
) {}

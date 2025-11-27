package ru.mrsu.questionanswer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUpdateQuestionAnswerRequestDto (

  @NotBlank(message = "Question cannot be blank")
  @Size(max = 1000, message = "Question cannot exceed 1000 characters")
  String question,

  @NotBlank(message = "Answer cannot be blank")
  @Size(max = 5000, message = "Answer cannot exceed 5000 characters")
  String answer
) {}

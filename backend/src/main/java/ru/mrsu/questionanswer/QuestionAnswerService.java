package ru.mrsu.questionanswer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionAnswerService {

  Page<QuestionAnswerResponseDto> getAllQuestionAnswers(Pageable pageable);

  QuestionAnswerResponseDto getQuestionAnswerById(Long id);

  QuestionAnswerResponseDto createQuestionAnswer(
      CreateUpdateQuestionAnswerRequestDto request);

  QuestionAnswerResponseDto updateQuestionAnswer(
      Long id, CreateUpdateQuestionAnswerRequestDto request);

  QuestionAnswerResponseDto findBestMatchByQuestionText(String questionText);

  void deleteQuestionAnswer(Long id);
}

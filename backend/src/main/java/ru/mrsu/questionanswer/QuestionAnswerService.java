package ru.mrsu.questionanswer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.mrsu.questionanswer.dto.CreateUpdateQuestionAnswerRequestDto;
import ru.mrsu.questionanswer.dto.QuestionAnswerResponseDto;

public interface QuestionAnswerService {

  Page<QuestionAnswerResponseDto> getAllQuestionAnswers(Pageable pageable);

  QuestionAnswerResponseDto getQuestionAnswerById(Long id);

  QuestionAnswerResponseDto createQuestionAnswer(
      CreateUpdateQuestionAnswerRequestDto request);

  QuestionAnswerResponseDto updateQuestionAnswer(
      Long id, CreateUpdateQuestionAnswerRequestDto request);

  void deleteQuestionAnswer(Long id);
}

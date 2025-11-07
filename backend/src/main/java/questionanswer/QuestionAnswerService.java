package questionanswer;

import java.util.List;
import questionanswer.dto.CreateUpdateQuestionAnswerRequestDto;
import questionanswer.dto.QuestionAnswerResponseDto;

public interface QuestionAnswerService {

  List<QuestionAnswerResponseDto> getAllQuestionAnswers();

  QuestionAnswerResponseDto getQuestionAnswerById(Long id);

  QuestionAnswerResponseDto createQuestionAnswer(
      CreateUpdateQuestionAnswerRequestDto request);

  QuestionAnswerResponseDto updateQuestionAnswer(
      Long id, CreateUpdateQuestionAnswerRequestDto request);

  void deleteQuestionAnswer(Long id);
}

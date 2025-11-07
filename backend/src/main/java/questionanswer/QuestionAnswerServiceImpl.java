package questionanswer;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import questionanswer.dto.CreateUpdateQuestionAnswerRequestDto;
import questionanswer.dto.QuestionAnswerResponseDto;

@Service
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

  private final QuestionAnswerRepository repository;

  @Autowired
  public QuestionAnswerServiceImpl(QuestionAnswerRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<QuestionAnswerResponseDto> getAllQuestionAnswers() {
    return repository.findAll().stream()
        .map(this::convertToResponse)
        .collect(Collectors.toList());
  }

  @Override
  public QuestionAnswerResponseDto getQuestionAnswerById(Long id) {
    QuestionAnswer entity = repository.findById(id)
        .orElseThrow(() -> new NotFoundException(id));
    return convertToResponse(entity);
  }

  @Override
  public QuestionAnswerResponseDto createQuestionAnswer(
      CreateUpdateQuestionAnswerRequestDto request) {
    QuestionAnswer entity = new QuestionAnswer();
    entity.setQuestion(request.getQuestion());
    entity.setAnswer(request.getAnswer());

    QuestionAnswer saved = repository.save(entity);
    return convertToResponse(saved);
  }

  @Override
  public QuestionAnswerResponseDto updateQuestionAnswer(Long id,
      CreateUpdateQuestionAnswerRequestDto request) {
    QuestionAnswer existing = repository.findById(id)
        .orElseThrow(() -> new NotFoundException(id));

    existing.setQuestion(request.getQuestion());
    existing.setAnswer(request.getAnswer());

    QuestionAnswer updated = repository.save(existing);
    return convertToResponse(updated);
  }

  @Override
  public void deleteQuestionAnswer(Long id) {
    if (!repository.existsById(id)) {
      throw new NotFoundException(id);
    }
    repository.deleteById(id);
  }

  private QuestionAnswerResponseDto convertToResponse(QuestionAnswer entity) {
    return new QuestionAnswerResponseDto(
        entity.getId(),
        entity.getQuestion(),
        entity.getAnswer()
    );
  }

}
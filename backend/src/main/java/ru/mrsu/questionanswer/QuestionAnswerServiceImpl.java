package ru.mrsu.questionanswer;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.mrsu.questionanswer.dto.CreateUpdateQuestionAnswerRequestDto;
import ru.mrsu.questionanswer.dto.QuestionAnswerResponseDto;
import ru.mrsu.questionanswer.util.exception.NotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

  QuestionAnswerRepository repository;
  QuestionAnswerMapper mapper;

  @Override
  public Page<QuestionAnswerResponseDto> getAllQuestionAnswers(Pageable pageable) {
    return repository.findAll(pageable)
        .map(mapper::toResponseDto);
  }

  @Override
  public QuestionAnswerResponseDto getQuestionAnswerById(Long id) {
    QuestionAnswer entity = repository.findById(id)
        .orElseThrow(() -> new NotFoundException("QuestionAnswer", id));
    return mapper.toResponseDto(entity);
  }

  @Override
  public QuestionAnswerResponseDto createQuestionAnswer(
      CreateUpdateQuestionAnswerRequestDto request) {
    QuestionAnswer entity = mapper.toEntity(request);

    log.info("QuestionAnswer with id {} created successfully", entity.getId());

    QuestionAnswer saved = repository.save(entity);
    return mapper.toResponseDto(saved);
  }

  @Override
  public QuestionAnswerResponseDto updateQuestionAnswer(Long id,
      CreateUpdateQuestionAnswerRequestDto request) {
    QuestionAnswer existing = repository.findById(id)
        .orElseThrow(() -> new NotFoundException("QuestionAnswer", id));

    mapper.updateEntityFromDto(request, existing);

    log.info("QuestionAnswer with ID: {} updated successfully", id);

    QuestionAnswer updated = repository.save(existing);
    return mapper.toResponseDto(updated);
  }

  @Override
  public void deleteQuestionAnswer(Long id) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("QuestionAnswer", id);
    }
    log.info("QuestionAnswer with ID: {} deleted successfully", id);
    repository.deleteById(id);
  }
}

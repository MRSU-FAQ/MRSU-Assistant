package ru.mrsu.questionanswer;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.mrsu.questionanswer.dto.CreateUpdateQuestionAnswerRequestDto;
import ru.mrsu.questionanswer.dto.QuestionAnswerResponseDto;

@Validated
@RestController
@RequestMapping("v1/questions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionAnswerController {

  QuestionAnswerServiceImpl service;

  @GetMapping
  public Page<QuestionAnswerResponseDto> getAllQuestionAnswers(Pageable pageable) {
    return service.getAllQuestionAnswers(pageable);
  }

  @GetMapping("/{id}")
  public QuestionAnswerResponseDto getQuestionAnswerById(@PathVariable("id") Long id) {
    return service.getQuestionAnswerById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public QuestionAnswerResponseDto createQuestionAnswer(
      @RequestBody @Valid CreateUpdateQuestionAnswerRequestDto request) {
    return service.createQuestionAnswer(request);
  }

  @PutMapping("/{id}")
  public QuestionAnswerResponseDto updateQuestionAnswer(@PathVariable("id") Long id,
      @RequestBody @Valid CreateUpdateQuestionAnswerRequestDto request) {
    return service.updateQuestionAnswer(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteQuestionAnswer(@PathVariable("id") Long id) {
    service.deleteQuestionAnswer(id);
  }
}

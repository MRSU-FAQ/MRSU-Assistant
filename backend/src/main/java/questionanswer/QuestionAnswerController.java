package questionanswer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import questionanswer.dto.CreateUpdateQuestionAnswerRequestDto;
import questionanswer.dto.QuestionAnswerResponseDto;

@RestController
@RequestMapping("/questions")
public class QuestionAnswerController {

  private final QuestionAnswerServiceImpl service;

  @Autowired
  public QuestionAnswerController(QuestionAnswerServiceImpl service) {
    this.service = service;
  }

  @GetMapping("/")
  public List<QuestionAnswerResponseDto> getAllQuestionAnswers() {
    return service.getAllQuestionAnswers();
  }

  @GetMapping("/{id}")
  public QuestionAnswerResponseDto getQuestionAnswerById(@PathVariable("id") Long id) {
    return service.getQuestionAnswerById(id);
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public QuestionAnswerResponseDto createQuestionAnswer(
      @RequestBody CreateUpdateQuestionAnswerRequestDto request) {
    return service.createQuestionAnswer(request);
  }

  @PutMapping("/{id}")
  public QuestionAnswerResponseDto updateQuestionAnswer(@PathVariable("id") Long id,
      @RequestBody CreateUpdateQuestionAnswerRequestDto request) {
    return service.updateQuestionAnswer(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteQuestionAnswer(@PathVariable("id") Long id) {
    service.deleteQuestionAnswer(id);
  }
}
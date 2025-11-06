package questionanswer.web;

import questionanswer.dto.QuestionAnswerRequest;
import questionanswer.dto.QuestionAnswerResponse;
import questionanswer.service.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionAnswerController {

    private final QuestionAnswerService service;

    @Autowired
    public QuestionAnswerController(QuestionAnswerService service) {
        this.service = service;
    }

    @GetMapping("/questions")
    public List<QuestionAnswerResponse> getAllQuestionAnswers() {
        return service.getAllQuestionAnswers();
    }

    @GetMapping("/questions/{question_id}")
    public QuestionAnswerResponse getQuestionAnswerById(@PathVariable("question_id") Long id) {
        return service.getQuestionAnswerById(id);
    }

    @PostMapping("/questions")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionAnswerResponse createQuestionAnswer(@RequestBody QuestionAnswerRequest request) {
        return service.createQuestionAnswer(request);
    }

    @PutMapping("/questions/{question_id}")
    public QuestionAnswerResponse updateQuestionAnswer(@PathVariable("question_id") Long id, @RequestBody QuestionAnswerRequest request) {
        return service.updateQuestionAnswer(id, request);
    }

    @DeleteMapping("/questions/{question_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestionAnswer(@PathVariable("question_id") Long id) {
        service.deleteQuestionAnswer(id);
    }
}
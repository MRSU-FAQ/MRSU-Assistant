package questionanswer.service;

import questionanswer.QuestionAnswer;
import questionanswer.dto.QuestionAnswerRequest;
import questionanswer.dto.QuestionAnswerResponse;
import questionanswer.repository.QuestionAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionAnswerService {

    private final QuestionAnswerRepository repository;

    @Autowired
    public QuestionAnswerService(QuestionAnswerRepository repository) {
        this.repository = repository;
    }

    public List<QuestionAnswerResponse> getAllQuestionAnswers() {
        return repository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public QuestionAnswerResponse getQuestionAnswerById(Long id) {
        QuestionAnswer entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return convertToResponse(entity);
    }

    public QuestionAnswerResponse createQuestionAnswer(QuestionAnswerRequest request) {
        QuestionAnswer entity = new QuestionAnswer();
        entity.setQuestion(request.getQuestion());
        entity.setAnswer(request.getAnswer());

        QuestionAnswer saved = repository.save(entity);
        return convertToResponse(saved);
    }

    public QuestionAnswerResponse updateQuestionAnswer(Long id, QuestionAnswerRequest request) {
        QuestionAnswer existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        existing.setQuestion(request.getQuestion());
        existing.setAnswer(request.getAnswer());

        QuestionAnswer updated = repository.save(existing);
        return convertToResponse(updated);
    }

    public void deleteQuestionAnswer(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(id);
        }
        repository.deleteById(id);
    }

    private QuestionAnswerResponse convertToResponse(QuestionAnswer entity) {
        return new QuestionAnswerResponse(
                entity.getId(),
                entity.getQuestion(),
                entity.getAnswer()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NotFoundException extends RuntimeException {
        public NotFoundException(Long id) {
            super("Question with id=" + id + " not found");
        }
    }
}
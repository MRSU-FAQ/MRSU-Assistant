package questionanswer.service;

import questionanswer.QuestionAnswer;
import questionanswer.dto.QuestionAnswerRequest;
import questionanswer.dto.QuestionAnswerResponse;
import questionanswer.repository.QuestionAnswerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionAnswerServiceTest {

    @Mock
    private QuestionAnswerRepository repository;

    private QuestionAnswerService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new QuestionAnswerService(repository);
    }

    @Test
    @DisplayName("Should return all questions")
    void testGetAllQuestions() {
        // Arrange
        QuestionAnswer qa1 = new QuestionAnswer("Q1", "A1");
        qa1.setId(1L);
        QuestionAnswer qa2 = new QuestionAnswer("Q2", "A2");
        qa2.setId(2L);

        when(repository.findAll()).thenReturn(Arrays.asList(qa1, qa2));

        // Act
        List<QuestionAnswerResponse> result = service.getAllQuestionAnswers();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Q1", result.get(0).getQuestion());
        assertEquals("A1", result.get(0).getAnswer());
    }

    @Test
    @DisplayName("Should return question by id")
    void testGetQuestionById() {
        // Arrange
        Long id = 1L;
        QuestionAnswer qa = new QuestionAnswer("Test Question", "Test Answer");
        qa.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(qa));

        // Act
        QuestionAnswerResponse result = service.getQuestionAnswerById(id);

        // Assert
        assertNotNull(result);
        assertEquals("Test Question", result.getQuestion());
        assertEquals("Test Answer", result.getAnswer());
    }

    @Test
    @DisplayName("Should throw exception when question not found")
    void testGetQuestionByIdNotFound() {
        // Arrange
        Long id = 999L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(QuestionAnswerService.NotFoundException.class,
                () -> service.getQuestionAnswerById(id));
    }

    @Test
    @DisplayName("Should create new question")
    void testCreateQuestion() {
        // Arrange
        QuestionAnswerRequest request = new QuestionAnswerRequest("New Question", "New Answer");
        QuestionAnswer saved = new QuestionAnswer("New Question", "New Answer");
        saved.setId(1L);

        when(repository.save(any(QuestionAnswer.class))).thenReturn(saved);

        // Act
        QuestionAnswerResponse result = service.createQuestionAnswer(request);

        // Assert
        assertNotNull(result);
        assertEquals("New Question", result.getQuestion());
        assertEquals(1L, result.getId());
    }

    @Test
    @DisplayName("Should update question")
    void testUpdateQuestion() {
        // Arrange
        Long id = 1L;
        QuestionAnswerRequest request = new QuestionAnswerRequest("Updated Question", "Updated Answer");
        QuestionAnswer existing = new QuestionAnswer("Old Question", "Old Answer");
        existing.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(existing));
        when(repository.save(any(QuestionAnswer.class))).thenReturn(existing);

        // Act
        QuestionAnswerResponse result = service.updateQuestionAnswer(id, request);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Question", result.getQuestion());
    }

    @Test
    @DisplayName("Should delete question")
    void testDeleteQuestion() {
        // Arrange
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);

        // Act
        service.deleteQuestionAnswer(id);

        // Assert
        verify(repository, times(1)).deleteById(id);
    }
}
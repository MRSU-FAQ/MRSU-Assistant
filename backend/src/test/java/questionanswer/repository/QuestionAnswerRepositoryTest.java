package questionanswer.repository;

import questionanswer.QuestionAnswer;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// Простой тест репозитория - проверяем только Entity
class QuestionAnswerRepositoryTest {

    @Test
    void testQuestionAnswerEntity() {
        // Arrange
        QuestionAnswer qa = new QuestionAnswer("Test Question", "Test Answer");

        // Act
        qa.setId(1L);

        // Assert
        assertNotNull(qa);
        assertEquals("Test Question", qa.getQuestion());
        assertEquals("Test Answer", qa.getAnswer());
        assertEquals(1L, qa.getId());
    }

    @Test
    void testQuestionAnswerConstructor() {
        // Arrange & Act
        QuestionAnswer qa = new QuestionAnswer("Q", "A");

        // Assert
        assertEquals("Q", qa.getQuestion());
        assertEquals("A", qa.getAnswer());
        assertNull(qa.getId()); // ID еще не установлен
    }

    @Test
    void testQuestionAnswerSetters() {
        // Arrange
        QuestionAnswer qa = new QuestionAnswer();

        // Act
        qa.setId(5L);
        qa.setQuestion("New Question");
        qa.setAnswer("New Answer");

        // Assert
        assertEquals(5L, qa.getId());
        assertEquals("New Question", qa.getQuestion());
        assertEquals("New Answer", qa.getAnswer());
    }
}
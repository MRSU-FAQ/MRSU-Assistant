package questionanswer.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DtoTest {

    @Test
    void testQuestionAnswerRequest() {
        // Arrange & Act
        QuestionAnswerRequest request = new QuestionAnswerRequest("Question?", "Answer!");

        // Assert
        assertEquals("Question?", request.getQuestion());
        assertEquals("Answer!", request.getAnswer());
    }

    @Test
    void testQuestionAnswerResponse() {
        // Arrange & Act
        QuestionAnswerResponse response = new QuestionAnswerResponse(1L, "Question?", "Answer!");

        // Assert
        assertEquals(1L, response.getId());
        assertEquals("Question?", response.getQuestion());
        assertEquals("Answer!", response.getAnswer());
    }

    @Test
    void testRequestSetters() {
        // Arrange
        QuestionAnswerRequest request = new QuestionAnswerRequest();

        // Act
        request.setQuestion("New Q");
        request.setAnswer("New A");

        // Assert
        assertEquals("New Q", request.getQuestion());
        assertEquals("New A", request.getAnswer());
    }

    @Test
    void testResponseSetters() {
        // Arrange
        QuestionAnswerResponse response = new QuestionAnswerResponse();

        // Act
        response.setId(10L);
        response.setQuestion("Q");
        response.setAnswer("A");

        // Assert
        assertEquals(10L, response.getId());
        assertEquals("Q", response.getQuestion());
        assertEquals("A", response.getAnswer());
    }
}
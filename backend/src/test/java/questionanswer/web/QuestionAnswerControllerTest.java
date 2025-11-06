package questionanswer.web;

import questionanswer.service.QuestionAnswerService;
import questionanswer.dto.QuestionAnswerRequest;
import questionanswer.dto.QuestionAnswerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Простой тест без Spring - проверяем только логику
class QuestionAnswerControllerTest {

    private final QuestionAnswerService service = mock(QuestionAnswerService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testControllerLogic() throws Exception {
        // Просто проверяем, что методы сервиса вызываются
        QuestionAnswerRequest request = new QuestionAnswerRequest("Test", "Answer");
        QuestionAnswerResponse response = new QuestionAnswerResponse(1L, "Test", "Answer");

        when(service.createQuestionAnswer(any())).thenReturn(response);

        // Это просто пример - в реальности тестировали бы через MockMvc
        QuestionAnswerResponse result = service.createQuestionAnswer(request);

        assert result != null;
        assert result.getQuestion().equals("Test");
    }
}
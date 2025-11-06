package questionanswer.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class QuestionAnswerRequest {

    @NotBlank(message = "Question cannot be blank")
    @Size(max = 1000, message = "Question cannot exceed 1000 characters")
    private String question;

    @NotBlank(message = "Answer cannot be blank")
    @Size(max = 5000, message = "Answer cannot exceed 5000 characters")
    private String answer;

    public QuestionAnswerRequest() {}

    public QuestionAnswerRequest(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
package questionanswer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUpdateQuestionAnswerRequestDto {

  @NotBlank(message = "Question cannot be blank")
  @Size(max = 1000, message = "Question cannot exceed 1000 characters")
  private String question;

  @NotBlank(message = "Answer cannot be blank")
  @Size(max = 5000, message = "Answer cannot exceed 5000 characters")
  private String answer;

  public CreateUpdateQuestionAnswerRequestDto(String question, String answer) {
    this.question = question;
    this.answer = answer;
  }
}
package questionanswer.dto;

import lombok.Data;

@Data
public class QuestionAnswerResponseDto {

  private Long id;
  private String question;
  private String answer;

  public QuestionAnswerResponseDto(Long id, String question, String answer) {
    this.id = id;
    this.question = question;
    this.answer = answer;
  }
}
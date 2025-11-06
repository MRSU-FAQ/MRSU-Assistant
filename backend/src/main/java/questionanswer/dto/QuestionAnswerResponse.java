package questionanswer.dto;

import lombok.Data;

@Data
public class QuestionAnswerResponse {
    private long id;
    private String question;
    private String answer;

    public QuestionAnswerResponse() {}

    public QuestionAnswerResponse(long id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }
}
package questionanswer;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "question_answers")
@Data
public class QuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Question cannot be blank")
    @Size(max = 1000, message = "Question cannot exceed 1000 characters")
    private String question;

    @NotBlank(message = "Answer cannot be blank")
    @Size(max = 5000, message = "Answer cannot exceed 5000 characters")
    private String answer;

    public QuestionAnswer() {}

    public QuestionAnswer(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
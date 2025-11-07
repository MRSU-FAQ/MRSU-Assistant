package questionanswer;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "question_answers")
@Data
public class QuestionAnswer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "question", nullable = false, length = 1000)
  private String question;

  @Column(name = "answer", nullable = false, length = 5000)
  private String answer;

  public QuestionAnswer() {

  }

  public QuestionAnswer(String question, String answer) {
    this.question = question;
    this.answer = answer;
  }
}
package ru.mrsu.questionanswer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
  List<QuestionAnswer> findByQuestionContainingIgnoreCase(String questionPart);
}

package ru.mrsu.chat;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mrsu.questionanswer.QuestionAnswerService;
import ru.mrsu.questionanswer.QuestionAnswerResponseDto;

@Validated
@RestController
@RequestMapping("v1/chat")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatController {

  QuestionAnswerService questionAnswerService;

  @PostMapping("/ask")
  public QuestionAnswerResponseDto askQuestion(
    @RequestBody ChatRequestDto request
  ) {
    return questionAnswerService.findBestMatchByQuestionText(request.questionText());
  }

}

package ru.mrsu.util.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({
    NotFoundException.class,
    NoResourceFoundException.class
  })
  public ErrorResponse handleNotFoundException(Exception ex, WebRequest request) {
    log.info("Entity Not Found: {}, Request details {}", ex, request);
    String userFriendlyMessage = "Resource not found";
    return new ErrorResponse(userFriendlyMessage, Instant.now());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
      BadRequestException.class,
      MultipartException.class,
      HttpRequestMethodNotSupportedException.class,
      DataIntegrityViolationException.class
  })
  public ErrorResponse handleBadRequestException(Exception ex, WebRequest request) {
    log.info("Bad Request: {}, Request details {}", ex, request);
    String userFriendlyMessage = "Bad request";
    return new ErrorResponse(userFriendlyMessage, Instant.now());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ErrorResponse handleGlobalException(Exception ex, WebRequest request) {
    log.error("""
        Unexpected error.
        Message: {},
        Error: {},
        Request: {}
        """, ex.getMessage(), ex, request);
    return new ErrorResponse("An unexpected error occurred", Instant.now());
  }
}

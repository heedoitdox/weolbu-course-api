package io.heedoitdox.courseapi.exception;

import io.heedoitdox.courseapi.exception.ErrorResponse.ValidationError;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    log.warn("handleMethodArgumentNotValid: {}", e);
    return makeErrorResponse(e, ErrorCode.INVALID_PARAMETER);
  }

  private ErrorResponse makeErrorResponse(BindException e, ErrorCode errorCode) {
    List<ValidationError> validationErrorList = e.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(ErrorResponse.ValidationError::of)
        .toList();

    return ErrorResponse.of(errorCode.name(), errorCode.getMessage(), validationErrorList);
  }
}

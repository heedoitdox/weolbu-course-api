package io.heedoitdox.courseapi.exception;

import io.heedoitdox.courseapi.exception.ErrorResponse.ValidationError;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleBadRequestException(BadRequestException e) {
    log.warn("handleBadRequestException: ", e);
    return makeErrorResponse(e.getErrorCode());
  }

  @ExceptionHandler(UnauthorizedException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorResponse handleUnauthorizedException(UnauthorizedException e) {
    log.warn("UnauthorizedException: ", e);
    return makeErrorResponse(e.getErrorCode());
  }

  @ExceptionHandler(ApiErrorException.class)
  public ResponseEntity<Object> handleIllegalArgumentException(ApiErrorException e) {
    log.warn("ApiErrorException: ", e);
    return handleExceptionInternal(e.getErrorCode(), e.getErrorMessage());
  }

  @ExceptionHandler(UnprocessableException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ErrorResponse handleUnprocessableException(UnprocessableException e) {
    log.error("UnprocessableException: ", e);
    return makeErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleRuntimeException(RuntimeException e) {
    log.error("RuntimeException: ", e);
    return makeErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR);
  }

  private ErrorResponse makeErrorResponse(BindException e, ErrorCode errorCode) {
    List<ValidationError> validationErrorList = e.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(ErrorResponse.ValidationError::of)
        .toList();

    return ErrorResponse.of(errorCode.name(), errorCode.getMessage(), validationErrorList);
  }

  private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode, String errorMessage) {
    return ResponseEntity.status(errorCode.getHttpStatus())
        .body(makeErrorResponse(errorCode, errorMessage));
  }

  private ErrorResponse makeErrorResponse(ErrorCode errorCode, String errorMessage) {
    return ErrorResponse.of(errorCode.name(), errorMessage);
  }

  private ErrorResponse makeErrorResponse(ErrorCode errorCode) {
    return ErrorResponse.of(errorCode.name(), errorCode.getMessage());
  }
}

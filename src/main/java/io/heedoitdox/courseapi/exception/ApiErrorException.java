package io.heedoitdox.courseapi.exception;

import lombok.Getter;

@Getter
public class ApiErrorException extends RuntimeException {
  private final ErrorCode errorCode;
  private final String errorMessage;

  public ApiErrorException(ErrorCode errorCode, String errorMessage) {
    super(errorMessage);
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }
}
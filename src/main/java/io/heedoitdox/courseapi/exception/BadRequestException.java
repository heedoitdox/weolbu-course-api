package io.heedoitdox.courseapi.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException{
  private final ErrorCode errorCode;
  private final String message;

  public BadRequestException(String message) {
    super(message);
    this.errorCode = ErrorCode.INVALID_PARAMETER;
    this.message = message;
  }
}

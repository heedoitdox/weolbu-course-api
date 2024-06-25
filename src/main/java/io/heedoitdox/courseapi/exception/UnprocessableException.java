package io.heedoitdox.courseapi.exception;

import lombok.Getter;

@Getter
public class UnprocessableException extends RuntimeException{
  private final ErrorCode errorCode;
  private final String message;

  public UnprocessableException() {
    this.errorCode = ErrorCode.UNPROCESSABLE_ENTITY;
    this.message = ErrorCode.UNPROCESSABLE_ENTITY.getMessage();
  }

  public UnprocessableException(String message) {
    super(message);
    this.errorCode = ErrorCode.UNPROCESSABLE_ENTITY;
    this.message = message;
  }
}

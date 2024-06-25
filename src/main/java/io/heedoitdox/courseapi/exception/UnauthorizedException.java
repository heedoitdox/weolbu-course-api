package io.heedoitdox.courseapi.exception;

import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException {
  private final ErrorCode errorCode;
  private final String message;

  public UnauthorizedException(String message) {
    super(message);
    this.errorCode = ErrorCode.UNAUTHORIZED;
    this.message = message;
  }
}

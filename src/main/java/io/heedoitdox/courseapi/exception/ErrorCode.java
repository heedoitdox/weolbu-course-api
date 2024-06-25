package io.heedoitdox.courseapi.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "요청 값을 확인해 주세요"),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러가 발생했어요"),
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "잘못된 인증 정보 입니다"),
  NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 데이터입니다"),
  DUPLICATED(HttpStatus.CONFLICT, "이미 존재하는 데이터입니다")
  ;

  private final HttpStatus httpStatus;
  private final String message;
}
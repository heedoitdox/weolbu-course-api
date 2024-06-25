package io.heedoitdox.courseapi.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "요청 값을 확인해 주세요"),
  BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청이에요"),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러가 발생했어요"),
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "잘못된 인증 정보에요"),
  NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 데이터에요"),
  CONFLICT(HttpStatus.CONFLICT, "이미 존재하는 데이터에요"),
  UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY, "처리할 수 없는 요청이에요")
  ;

  private final HttpStatus httpStatus;
  private final String message;
}
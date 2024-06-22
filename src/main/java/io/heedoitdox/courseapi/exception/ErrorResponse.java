package io.heedoitdox.courseapi.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AccessLevel;
import lombok.Builder;
import org.springframework.validation.FieldError;

import java.util.List;

public record ErrorResponse(
    String code,
    String message,

    @JsonInclude(Include.NON_EMPTY)
    List<ValidationError> errors
) {

  @Builder(access = AccessLevel.PRIVATE)
  public record ValidationError(String field, String message) {
    public static ValidationError of(final FieldError fieldError) {
      return ValidationError.builder()
          .field(fieldError.getField())
          .message(fieldError.getDefaultMessage())
          .build();
    }
}

  public static ErrorResponse of(final String code, final String message) {
    return new ErrorResponse(code, message, null);
  }

  public static ErrorResponse of(final String code, final String message, final List<ValidationError> errors) {
    return new ErrorResponse(code, message, errors);
  }
}

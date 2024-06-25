package io.heedoitdox.courseapi.application.course;

import io.heedoitdox.courseapi.exception.ApiErrorException;
import io.heedoitdox.courseapi.exception.ErrorCode;
import java.util.List;

public record CourseRegisterRequest(
    List<Long> courseIds
) {

  public CourseRegisterRequest {
    if(courseIds == null || courseIds.isEmpty()) {
      throw new ApiErrorException(ErrorCode.BAD_REQUEST, "강의 ID 는 최소 1개 이상 존재해야 해요");
    }
  }
}

package io.heedoitdox.courseapi.application.course;

import java.util.List;

public record CourseRegisterRequest(
    List<Long> courseIds
) {

  public CourseRegisterRequest {
    if(courseIds == null || courseIds.isEmpty()) {
      throw new IllegalArgumentException("courseIds cannot be null or empty");
    }

    if(courseIds.size() > 20) {
      throw new IllegalArgumentException("courseIds cannot contain more than 20 elements");
    }
  }
}

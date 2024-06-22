package io.heedoitdox.courseapi.application;

import io.heedoitdox.courseapi.domain.course.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CreateCourseRequest(
    @NotBlank String title,
    String description,
    @NotNull int capacity,
    @NotNull BigDecimal price
) {

  public Course toEntity() {
    return Course.create(title, description, capacity, price);
  }

}

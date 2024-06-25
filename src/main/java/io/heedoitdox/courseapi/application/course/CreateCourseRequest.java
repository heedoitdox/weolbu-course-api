package io.heedoitdox.courseapi.application.course;

import io.heedoitdox.courseapi.domain.course.Course;
import io.heedoitdox.courseapi.support.StringUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CreateCourseRequest(
    @NotBlank String title,
    @NotNull int capacity,
    @NotBlank String price
) {

  public Course toEntity() {
    BigDecimal priceBigDecimal = new BigDecimal(StringUtil.removeCommas(price));

    return Course.create(title, capacity, priceBigDecimal);
  }
}

package io.heedoitdox.courseapi.application.course;

import io.heedoitdox.courseapi.domain.course.Course;
import io.heedoitdox.courseapi.support.BigDecimalUtil;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;

/**
 * 강의 정보
 * @param id
 * @param title
 * @param capacity
 * @param price
 * @param registeredCount
 * @param registeredRate
 * @param instructorName
 * @param createdAt
 */
@Builder(access = AccessLevel.PRIVATE)
public record CourseListResponse(
    Long id,
    String title,
    Integer capacity,
    String price,
    Integer registeredCount,
    BigDecimal registeredRate,
    String instructorName,
    LocalDateTime createdAt
) {

  public static CourseListResponse from(CourseSummary courseSummary) {
    return CourseListResponse.builder()
        .id(courseSummary.id())
        .title(courseSummary.title())
        .capacity(courseSummary.capacity())
        .price(BigDecimalUtil.formatWithCommas(courseSummary.price()))
        .registeredCount(courseSummary.registeredCount())
        .registeredRate(courseSummary.registeredRate())
        .instructorName(courseSummary.instructorName())
        .createdAt(courseSummary.createdAt())
        .build();
  }
}

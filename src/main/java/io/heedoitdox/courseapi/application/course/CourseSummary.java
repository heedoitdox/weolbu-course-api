package io.heedoitdox.courseapi.application.course;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 강의 정보
 * @param id
 * @param title 강의명
 * @param capacity 최대 수강인원
 * @param price 가격
 * @param registeredCount 신청인원
 * @param registeredRate 신청률
 * @param instructorName 강사명
 * @param createdAt
 */
public record CourseSummary(
    Long id,
    String title,
    Integer capacity,
    BigDecimal price,
    Integer registeredCount,
    BigDecimal registeredRate,
    String instructorName,
    LocalDateTime createdAt
) {

}

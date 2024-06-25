package io.heedoitdox.courseapi.application.course;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 강의 목록
 *
 * @param title 강의명
 * @param capacity 최대 신청 가능 인원
 * @param price 가격
 */
public record CourseListResponse(
    Long id,
    String title,
    Integer capacity,
    BigDecimal price, // BigDecimal + money comma
    Integer registeredCount,
    LocalDateTime createdAt
//    Integer registeredStudents,
//    Double registrationRate,
//    String instructorName,
//    Boolean registered // 이거 할 수가 있나?
) {

}

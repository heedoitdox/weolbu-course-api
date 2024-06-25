package io.heedoitdox.courseapi.ui;

import io.heedoitdox.courseapi.application.course.CourseListResponse;
import io.heedoitdox.courseapi.config.FixtureMonkeyUtil;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class CourseFixtures {

  public static Page<CourseListResponse> getPageCourseListResponse() {
    List<CourseListResponse> list = List.of(
        FixtureMonkeyUtil.monkey()
            .giveMeBuilder(CourseListResponse.class)
            .set("id", 3L)
            .set("title", "최소 5억 이상 오를곳! 분당판교 지역분석 & 입성 전략 노하우")
            .set("capacity", 10)
            .set("price", "425,000")
            .set("registeredCount", 7)
            .set("registeredRate", new BigDecimal("0.70"))
            .set("instructorName", "강사3")
            .set("createdAt", LocalDateTime.now())
            .sample(),
        FixtureMonkeyUtil.monkey()
            .giveMeBuilder(CourseListResponse.class)
            .set("id", 2L)
            .set("title", "너나위의 내집마련 기초반")
            .set("capacity", 30)
            .set("price", "425,000")
            .set("registeredCount", 12)
            .set("registeredRate", new BigDecimal("0.40"))
            .set("instructorName", "강사2")
            .set("createdAt", LocalDateTime.now())
            .sample(),
        FixtureMonkeyUtil.monkey()
            .giveMeBuilder(CourseListResponse.class)
            .set("id", 1L)
            .set("title", "월 100만원 버는 블로그 부업 완벽 세팅하기!")
            .set("capacity", 30)
            .set("price", "425,000")
            .set("registeredCount", 9)
            .set("registeredRate", new BigDecimal("0.30"))
            .set("instructorName", "강사1")
            .set("createdAt", LocalDateTime.now())
            .sample());

    return new PageImpl<>(list, PageRequest.of(0, 20), 3);
  }
}

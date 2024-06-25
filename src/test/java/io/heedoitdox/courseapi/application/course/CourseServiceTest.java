package io.heedoitdox.courseapi.application.course;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import io.heedoitdox.courseapi.config.FixtureMonkeyUtil;
import io.heedoitdox.courseapi.domain.course.Course;
import io.heedoitdox.courseapi.domain.course.CourseRepository;
import io.heedoitdox.courseapi.domain.member.Member;
import io.heedoitdox.courseapi.exception.ApiErrorException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

  @InjectMocks
  private CourseService sut;

  @Mock
  private CourseRepository courseRepository;

  @Mock
  private CourseRegistrationService courseRegistrationService;

  @Nested
  @DisplayName("강의 신청시")
  class Register {

    @Test
    void 이미_신청한_강의라면_에러륿_반환한다() {
      // given
      Member applicant = FixtureMonkeyUtil.monkey().giveMeOne(Member.class);
      given(courseRegistrationService.existRegistration(any(), any())).willReturn(true);

      // when
      // then
      assertThrows(ApiErrorException.class,
          () -> sut.register(List.of(1L, 2L, 3L), applicant));
    }

    @Test
    void 존재하지_않는_강의라면_에러를_반환한다() {
      // given
      Member applicant = FixtureMonkeyUtil.monkey().giveMeOne(Member.class);
      given(courseRegistrationService.existRegistration(any(), any())).willReturn(false);
      given(courseRepository.findByIdWithPessimisticLock(any())).willReturn(Optional.empty());

      // when
      // then
      assertThrows(ApiErrorException.class,
          () -> sut.register(List.of(1L, 2L, 3L), applicant));
    }

    @Test
    void 정상적으로_신청된다() {
      // given
      Member applicant = FixtureMonkeyUtil.monkey().giveMeOne(Member.class);
      given(courseRegistrationService.existRegistration(any(), any())).willReturn(false);
      Course course = Course.create("test title", 5, new BigDecimal("1000"));
      Integer expectedRegisteredCount = course.getRegisteredCount() + 1;
      given(courseRepository.findByIdWithPessimisticLock(any())).willReturn(Optional.of(course));

      // when
      sut.register(List.of(1L), applicant);

      // then
      assertThat(course.getRegisteredCount()).isEqualTo(expectedRegisteredCount);
    }
  }

}
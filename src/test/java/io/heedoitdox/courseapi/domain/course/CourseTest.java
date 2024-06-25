package io.heedoitdox.courseapi.domain.course;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.heedoitdox.courseapi.exception.UnprocessableException;
import io.heedoitdox.courseapi.support.BigDecimalUtil;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CourseTest {

  @Nested
  @DisplayName("강의 신청 시")
  class Register {

    @Test
    void 신청_가능한_인원을_초과하면_에러가_발생한다() {
      // given
      final int CAPACITY = 5;
      Course course = Course.create("test", CAPACITY, null);

      // when
      for (int i = 0; i < CAPACITY + 3; i++) {
        try {
          course.register();
        } catch (UnprocessableException e) {
          assertEquals("요청 가능한 수를 초과했어요", e.getMessage());
        }
      }
    }

    @Test
    void 신청_가능한_인원이라면_신청인원_카운트가_1_증가한다() {
      // given
      final int CAPACITY = 5;
      Course course = Course.create("test", CAPACITY, null);

      // when
      course.register();

      // then
      assertThat(course.getRegisteredCount()).isEqualTo(1);
    }

    @Test
    void 신청_가능한_인원이라면_추가된_인원으로_신청률을_다시_계산한다() {
      // given
      final int CAPACITY = 5;
      Course course = Course.create("test", CAPACITY, null);
      BigDecimal expected = BigDecimalUtil.RoundToTwoDecimalPlace(new BigDecimal(1 / (double)course.getCapacity()));

      // when
      course.register();

      // then
      assertThat(course.getRegisteredRate()).isEqualTo(expected);
    }
  }
}
package io.heedoitdox.courseapi.application.course;

import static org.junit.jupiter.api.Assertions.assertThrows;

import io.heedoitdox.courseapi.exception.ApiErrorException;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CourseRegisterRequestTest {

  @Test
  void 요청_값이_없는_경우_에러가_발생한다() {
    assertThrows(ApiErrorException.class,
        () -> new CourseRegisterRequest(Collections.EMPTY_LIST));
  }

}
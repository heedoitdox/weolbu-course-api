package io.heedoitdox.courseapi.ui;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.heedoitdox.courseapi.application.CourseService;
import io.heedoitdox.courseapi.application.CreateCourseRequest;
import io.heedoitdox.courseapi.config.FixtureMonkeyUtil;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(value = CourseController.class)
class CourseControllerTest extends AbstractControllerTest {

  @MockBean
  private CourseService courseService;

  @Nested
  @DisplayName("강의 등록 요청시")
  class CreateCourses {

    @Test
    void 정상적으로_성공한다() throws Exception {
      // given
      CreateCourseRequest request = FixtureMonkeyUtil.monkey().giveMeOne(CreateCourseRequest.class);

      // when
      ResultActions result = mockMvc.perform(
          post("/api/v1/courses")
              .characterEncoding("UTF-8")
              .content(objectMapper.writeValueAsString(request))
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON));

      // then
      result.andExpect(status().isOk());
    }

    @Test
    void 필수_요청값이_없을시_에러를_반환한다() throws Exception {
      // given
      CreateCourseRequest request = FixtureMonkeyUtil.monkey().giveMeBuilder(CreateCourseRequest.class)
          .setNull("title")
          .set("capacity", 5)
          .set("price", new BigDecimal("10000"))
          .sample();

      // when
      ResultActions result = mockMvc.perform(
          post("/api/v1/courses")
              .characterEncoding("UTF-8")
              .content(objectMapper.writeValueAsString(request))
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON));

      // then
      result.andExpect(status().isBadRequest());
    }
  }
}
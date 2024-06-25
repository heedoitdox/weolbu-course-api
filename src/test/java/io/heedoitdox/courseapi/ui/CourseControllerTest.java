package io.heedoitdox.courseapi.ui;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.heedoitdox.courseapi.application.course.CourseService;
import io.heedoitdox.courseapi.application.course.CreateCourseRequest;
import io.heedoitdox.courseapi.config.FixtureMonkeyUtil;
import io.heedoitdox.courseapi.support.security.CustomWithMockUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(value = CourseController.class)
class CourseControllerTest extends AbstractControllerTest {

  @MockBean
  private CourseService courseService;

  @Nested
  @DisplayName("강의 개설 요청시")
  class CreateCourses {

    @Test
    @CustomWithMockUser
    void 정상적으로_성공한다() throws Exception {
      // given
      CreateCourseRequest request = FixtureMonkeyUtil.monkey().giveMeBuilder(CreateCourseRequest.class)
          .set("title", "너나위의 내집마련 기초반")
          .set("capacity", 10)
          .set("price", "30,000")
          .sample();

      // when
      ResultActions result = mockMvc.perform(
          post("/api/v1/courses")
              .characterEncoding("UTF-8")
              .content(objectMapper.writeValueAsString(request))
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON));

      // then
      result.andExpect(status().isOk())
          .andDo(print())
          .andDo(document("courses-create-success",
              requestFields(
                  attributes(key("title").value("Request Fields")),
                  fieldWithPath("title").type(JsonFieldType.STRING).description("강의명"),
                  fieldWithPath("capacity").type(JsonFieldType.NUMBER).description("최대 수강 인원"),
                  fieldWithPath("price").type(JsonFieldType.STRING).description("강의 가격")
              )
          ));
    }

    @Test
    @CustomWithMockUser
    void 필수_요청값이_없을시_에러를_반환한다() throws Exception {
      // given
      CreateCourseRequest request = FixtureMonkeyUtil.monkey().giveMeBuilder(CreateCourseRequest.class)
          .setNull("title")
          .set("capacity", 5)
          .set("price", "10,000")
          .sample();

      // when
      ResultActions result = mockMvc.perform(
          post("/api/v1/courses")
              .characterEncoding("UTF-8")
              .content(objectMapper.writeValueAsString(request))
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON));

      // then
      result.andExpect(status().isBadRequest())
          .andDo(print())
          .andDo(document("courses-create-success",
              requestFields(
                  attributes(key("title").value("Request Fields")),
                  fieldWithPath("title").type(JsonFieldType.STRING).description("강의명").optional(),
                  fieldWithPath("capacity").type(JsonFieldType.NUMBER).description("최대 수강 인원"),
                  fieldWithPath("price").type(JsonFieldType.STRING).description("강의 가격")
              )
          ));
    }
  }
}
package io.heedoitdox.courseapi.ui;

import static io.heedoitdox.courseapi.ui.CourseFixtures.getPageCourseListResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.heedoitdox.courseapi.application.course.CourseListRequest;
import io.heedoitdox.courseapi.application.course.CourseRegisterRequest;
import io.heedoitdox.courseapi.application.course.CourseService;
import io.heedoitdox.courseapi.application.course.CreateCourseRequest;
import io.heedoitdox.courseapi.application.course.OrderCondition;
import io.heedoitdox.courseapi.config.FixtureMonkeyUtil;
import io.heedoitdox.courseapi.domain.member.Member;
import io.heedoitdox.courseapi.support.security.SecurityUtil;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(value = CourseController.class)
class CourseControllerTest extends AbstractControllerTest {

  @MockBean
  private CourseService courseService;

  private static MockedStatic<SecurityUtil> securityUtil;

  @BeforeAll
  public static void beforeALl() {
    securityUtil = mockStatic(SecurityUtil.class);
  }

  @AfterAll
  public static void afterAll() {
    securityUtil.close();
  }

  @Nested
  @DisplayName("강의 개설 요청시")
  class CreateCourses {

    @Test
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
              .header("Authorization", "Bearer " + "accessToken")
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
    @WithMockUser(authorities = {"ROLE_INSTRUCTOR"})
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

  @Nested
  @DisplayName("강의 신청시")
  class Register {

    @Test
    @WithMockUser(authorities = {"ROLE_INSTRUCTOR"}) // 강사 본인도 가능
    void 정상적으로_성공한다() throws Exception {
      // given
      Member member = FixtureMonkeyUtil.monkey().giveMeOne(Member.class);
      when(SecurityUtil.getMember()).thenReturn(Optional.of(member));
      doNothing().when(courseService).register(any(), any());

      CourseRegisterRequest request = new CourseRegisterRequest(List.of(1L, 2L));

      // when
      ResultActions result = mockMvc.perform(
          post("/api/v1/courses/register")
              .characterEncoding("UTF-8")
              .content(objectMapper.writeValueAsString(request))
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON));

      // then
      result.andExpect(status().isOk())
          .andDo(print())
          .andDo(document("courses-register-success",
              requestFields(
                  attributes(key("title").value("Request Fields")),
                  fieldWithPath("courseIds").type(JsonFieldType.ARRAY).description("강의 ID 리스트")
              )
          ));
    }
  }

  @Nested
  @DisplayName("강의 목록 조회시")
  class GetList {

    @Test
    @WithMockUser(authorities = {"ROLE_INSTRUCTOR"})
    void 정상적으로_성공한다() throws Exception {
      // given
      CourseListRequest request = new CourseListRequest(20, 0, List.of(OrderCondition.CREATED_AT_DESC));
      given(courseService.getList(request)).willReturn(getPageCourseListResponse());

      // when
      ResultActions result = mockMvc.perform(
          get("/api/v1/courses")
              .characterEncoding("UTF-8")
              .params(convertQueryParams(request))
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON));

      // then
      result.andExpect(status().isOk())
          .andDo(print())
          .andDo(document("courses-list-success",
              queryParameters(
                  attributes(key("title").value("Request Fields")),
                  parameterWithName("pageNo").description("페이지 번호"),
                  parameterWithName("pageSize").description("페이지 사이즈"),
                  parameterWithName("orderConditions").description("정렬 조건")
              ),
              responseFields(
                  attributes(key("title").value("Request Fields")),
                  fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 개수"),
                  fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("전체 데이터 개수"),
                  fieldWithPath("first").type(JsonFieldType.BOOLEAN).description("first"),
                  fieldWithPath("last").type(JsonFieldType.BOOLEAN).description("last"),
                  fieldWithPath("size").type(JsonFieldType.NUMBER).description("현재 데이터 사이즈"),
                  fieldWithPath("content").type(JsonFieldType.ARRAY).description("데이터"),

                  fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("강의 ID"),
                  fieldWithPath("content[].title").type(JsonFieldType.STRING).description("강의명"),
                  fieldWithPath("content[].capacity").type(JsonFieldType.NUMBER).description("최대 수용 인원"),
                  fieldWithPath("content[].price").type(JsonFieldType.STRING).description("가격"),
                  fieldWithPath("content[].registeredCount").type(JsonFieldType.NUMBER).description("신청 인원"),
                  fieldWithPath("content[].registeredRate").type(JsonFieldType.NUMBER).description("신청률 (소수점 둘째자리에서 반올림)"),
                  fieldWithPath("content[].instructorName").type(JsonFieldType.STRING).description("강사명"),
                  fieldWithPath("content[].createdAt").type(JsonFieldType.STRING).description("강의 개설일시"),

                  fieldWithPath("number").type(JsonFieldType.NUMBER).description("number"),
                  fieldWithPath("sort").type(JsonFieldType.OBJECT).description("정렬"),
                  fieldWithPath("sort.empty").type(JsonFieldType.BOOLEAN).description("empty"),
                  fieldWithPath("sort.sorted").type(JsonFieldType.BOOLEAN).description("sorted"),
                  fieldWithPath("sort.unsorted").type(JsonFieldType.BOOLEAN).description("unsorted"),
                  fieldWithPath("numberOfElements").type(JsonFieldType.NUMBER).description("numberOfElements"),
                  fieldWithPath("pageable").type(JsonFieldType.OBJECT).description("pageable"),
                  fieldWithPath("pageable.pageNumber").type(JsonFieldType.NUMBER).description("pageNumber"),
                  fieldWithPath("pageable.pageSize").type(JsonFieldType.NUMBER).description("pageSize"),
                  fieldWithPath("pageable.sort").type(JsonFieldType.OBJECT).description("sort"),
                  fieldWithPath("pageable.sort.empty").type(JsonFieldType.BOOLEAN).description("empty"),
                  fieldWithPath("pageable.sort.sorted").type(JsonFieldType.BOOLEAN).description("sorted"),
                  fieldWithPath("pageable.sort.unsorted").type(JsonFieldType.BOOLEAN).description("unsorted"),
                  fieldWithPath("pageable.offset").type(JsonFieldType.NUMBER).description("offset"),
                  fieldWithPath("pageable.paged").type(JsonFieldType.BOOLEAN).description("paged"),
                  fieldWithPath("pageable.unpaged").type(JsonFieldType.BOOLEAN).description("unpaged"),
                  fieldWithPath("empty").type(JsonFieldType.BOOLEAN).description("empty")
              )
          ));
    }
  }
}
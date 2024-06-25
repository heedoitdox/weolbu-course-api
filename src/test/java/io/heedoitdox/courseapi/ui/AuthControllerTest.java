package io.heedoitdox.courseapi.ui;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.heedoitdox.courseapi.application.auth.AuthService;
import io.heedoitdox.courseapi.application.auth.JwtAuthenticationResponse;
import io.heedoitdox.courseapi.application.auth.LoginRequest;
import io.heedoitdox.courseapi.application.auth.SignupRequest;
import io.heedoitdox.courseapi.config.FixtureMonkeyUtil;
import io.heedoitdox.courseapi.domain.member.MemberType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(value = AuthController.class)
class AuthControllerTest extends AbstractControllerTest {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @MockBean
  private AuthService authService;


  @Nested
  @DisplayName("회원 가입 요청시")
  class Signup{

    @Test
    void 정상적으로_성공한다() throws Exception {
      // given
      SignupRequest request = FixtureMonkeyUtil.monkey().giveMeBuilder(SignupRequest.class)
          .set("email", "binzzang810@gmail.com")
          .set("password", "1234")
          .set("name", "윤영빈")
          .set("phone", "010-4560-9218")
          .set("memberType", MemberType.INSTRUCTOR)
          .sample();

      // when
      ResultActions result = mockMvc.perform(
          post("/api/v1/auth/signup")
              .characterEncoding("UTF-8")
              .content(objectMapper.writeValueAsString(request))
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON));

      // then
      result.andExpect(status().isOk())
          .andDo(print())
          .andDo(document("member-create-success",
              requestFields(
                  attributes(key("title").value("Request Fields")),
                  fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                  fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                  fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                  fieldWithPath("phone").type(JsonFieldType.STRING).description("연락처"),
                  fieldWithPath("memberType").type(JsonFieldType.STRING).description("회원 타입 (강사, 학생)")
              )
          ));
    }
  }

  @Nested
  @DisplayName("로그인 요청시")
  class Login{

    @Test
    void 정상적으로_토큰을_발급한다() throws Exception {
      // given
      LoginRequest request = FixtureMonkeyUtil.monkey().giveMeBuilder(LoginRequest.class)
          .set("email", "binzzang810@gmail.com")
          .set("password", "1234")
          .sample();
      JwtAuthenticationResponse response = new JwtAuthenticationResponse("access-token", "bearer");
      given(authService.login(any())).willReturn(response);

      // when
      ResultActions result = mockMvc.perform(
          post("/api/v1/auth/login")
              .characterEncoding("UTF-8")
              .content(objectMapper.writeValueAsString(request))
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON));

      // then
      result.andExpect(status().isOk())
          .andDo(print())
          .andDo(document("login-success",
              requestFields(
                  attributes(key("title").value("Request Fields")),
                  fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                  fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
              ),
              responseFields(
                  attributes(key("title").value("Request Fields")),
                  fieldWithPath("accessToken").type(JsonFieldType.STRING).description("인증 토큰"),
                  fieldWithPath("tokenType").type(JsonFieldType.STRING).description("토큰 타입 (Bearer)")
              )
          ));
    }
  }

}
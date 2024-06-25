package io.heedoitdox.courseapi.application.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import io.heedoitdox.courseapi.config.FixtureMonkeyUtil;
import io.heedoitdox.courseapi.domain.member.Member;
import io.heedoitdox.courseapi.domain.member.MemberRepository;
import io.heedoitdox.courseapi.exception.DuplicatedKeyException;
import io.heedoitdox.courseapi.exception.UnauthorizedException;
import io.heedoitdox.courseapi.support.security.JwtService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

  @InjectMocks
  private AuthService authService;

  @Mock
  private MemberRepository memberRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @Mock
  private JwtService jwtService;

  @Nested
  @DisplayName("회원가입시")
  class Signup{

    @Test
    void 중복된_이메일인_경우_에러를_반환한다() {
      // given
      SignupRequest request = FixtureMonkeyUtil.monkey().giveMeOne(SignupRequest.class);
      Member member = FixtureMonkeyUtil.monkey().giveMeOne(Member.class);
      given(memberRepository.findByEmail(anyString())).willReturn(Optional.of(member));

      // when then
      assertThrows(DuplicatedKeyException.class,
          () -> authService.signup(request));
    }

    @Test
    void 정상적으로_회원정보를_저장한다() {
      // given
      SignupRequest request = FixtureMonkeyUtil.monkey().giveMeOne(SignupRequest.class);
      given(memberRepository.findByEmail(anyString())).willReturn(Optional.empty());

      // when
      authService.signup(request);

      // then
      verify(memberRepository, times(1)).save(any());
    }

  }

  @Nested
  @DisplayName("로그인 요청시")
  class Login {

    @Test
    void 검증에_성공하여_액세스_토큰을_발급한다() {
      // given
      LoginRequest request = new LoginRequest("binzzang810@gmail.com", "1234");
      Member member = FixtureMonkeyUtil.monkey().giveMeOne(Member.class);
      given(memberRepository.findByEmail(anyString())).willReturn(Optional.of(member));
      given(passwordEncoder.matches(anyString(), anyString())).willReturn(true);
      given(jwtService.generateToken(any())).willReturn("accessToken");

      // when
      JwtAuthenticationResponse result = authService.login(request);

      // then
      assertThat(result.accessToken()).isEqualTo("accessToken");
      assertThat(result.tokenType()).isEqualTo("Bearer");
    }

    @Test
    void 잘못된_이메일_정보를_입력했을_경우_에러를_반환한다() {
      // given
      LoginRequest request = new LoginRequest("binzzang810@gmail.com", "1234");
      given(memberRepository.findByEmail(anyString())).willReturn(Optional.empty());

      // when then
      assertThrows(UnauthorizedException.class,
          () -> authService.login(request));
    }

    @Test
    void 잘못된_비밀번회를_입력했을_경우_에러를_반환한다() {
      // given
      LoginRequest request = new LoginRequest("binzzang810@gmail.com", "1234");
      Member member = FixtureMonkeyUtil.monkey().giveMeOne(Member.class);
      given(memberRepository.findByEmail(anyString())).willReturn(Optional.of(member));
      given(passwordEncoder.matches(anyString(), anyString())).willReturn(false);

      // when then
      assertThrows(UnauthorizedException.class,
          () -> authService.login(request));
    }
  }

}
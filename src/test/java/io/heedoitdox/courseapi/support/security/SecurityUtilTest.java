package io.heedoitdox.courseapi.support.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import io.heedoitdox.courseapi.domain.member.Member;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

class SecurityUtilTest {

  @Mock
  private SecurityContext securityContext;

  @Mock
  private Authentication authentication;

  @Mock
  private Member member;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this); // 모의 객체 초기화
    SecurityContextHolder.setContext(securityContext);
  }

  @Test
  void 인증된_객체가_존재한다면_회원정보를_반환한다() {
    // given
    when(securityContext.getAuthentication()).thenReturn(authentication);
    when(authentication.isAuthenticated()).thenReturn(true);
    when(authentication.getPrincipal()).thenReturn(member);

    // when
    Optional<Member> result = SecurityUtil.getMember();

    // then
    assertTrue(result.isPresent());
    assertEquals(member, result.get());
  }

  @Test
  void 인증된_객체가_존재하지_않는다면_null을_반환한다() {
    // given
    when(securityContext.getAuthentication()).thenReturn(authentication);
    when(authentication.isAuthenticated()).thenReturn(false);

    // when
    Optional<Member> result = SecurityUtil.getMember();

    // then
    assertTrue(result.isEmpty());
  }

  @Test
  void 인증정보_자체가_존재하지_않는다면_null을_반환한다() {
    // given
    when(securityContext.getAuthentication()).thenReturn(null);

    // when
    Optional<Member> result = SecurityUtil.getMember();

    // then
    assertTrue(result.isEmpty());
  }
}
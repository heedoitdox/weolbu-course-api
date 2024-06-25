package io.heedoitdox.courseapi.support.security;

import io.heedoitdox.courseapi.domain.member.Member;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

  public static Optional<Member> getMember() {
    return Optional.ofNullable(SecurityContextHolder.getContext())
        .map(SecurityContext::getAuthentication)
        .filter(Authentication::isAuthenticated)
        .map(Authentication::getPrincipal)
        .filter(principal -> principal instanceof Member)
        .map(Member.class::cast);
  }

}

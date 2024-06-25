package io.heedoitdox.courseapi.support.security;

import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<CustomWithMockUser> {
  @Override
  public SecurityContext createSecurityContext(CustomWithMockUser annotation) {
    final SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

    final UsernamePasswordAuthenticationToken authenticationToken
        = new UsernamePasswordAuthenticationToken(annotation.userName(), null,
        List.of(new SimpleGrantedAuthority(annotation.role())));
    securityContext.setAuthentication(authenticationToken);
    return securityContext;
  }
}

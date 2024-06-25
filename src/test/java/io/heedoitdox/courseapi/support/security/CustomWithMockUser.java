package io.heedoitdox.courseapi.support.security;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.security.test.context.support.WithSecurityContext;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface CustomWithMockUser {
  String userName() default "heedoitdox";
  String role() default "STUDENT";
}

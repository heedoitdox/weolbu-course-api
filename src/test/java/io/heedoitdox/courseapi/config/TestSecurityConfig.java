package io.heedoitdox.courseapi.config;

import io.heedoitdox.courseapi.application.auth.UserService;
import io.heedoitdox.courseapi.support.security.JwtAuthenticationFilter;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

@TestConfiguration
public class TestSecurityConfig extends SecurityConfig {

  @MockBean
  private UserDetailsService userDetailsService;

  public TestSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, UserService userService) {
    super(jwtAuthenticationFilter, userService);
  }

  @Override
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }
}
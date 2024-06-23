package io.heedoitdox.courseapi.application.auth;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
  UserDetailsService userDetailsService();
}

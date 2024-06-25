package io.heedoitdox.courseapi.ui;

import io.heedoitdox.courseapi.application.auth.AuthService;
import io.heedoitdox.courseapi.application.auth.JwtAuthenticationResponse;
import io.heedoitdox.courseapi.application.auth.LoginRequest;
import io.heedoitdox.courseapi.application.auth.SignupRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public JwtAuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest) {
    return authService.login(loginRequest);
  }

  @PostMapping("/signup")
  public void signup(@Valid @RequestBody SignupRequest request) {
    authService.signup(request);
  }
}

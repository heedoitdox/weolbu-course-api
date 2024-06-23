package io.heedoitdox.courseapi.application.auth;

public record JwtAuthenticationResponse(
    String accessToken,
    String tokenType
) {

  public static JwtAuthenticationResponse of(String jwt) {
    return new JwtAuthenticationResponse(jwt, "Bearer");
  }
}

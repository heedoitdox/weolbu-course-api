package io.heedoitdox.courseapi.application.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank String email,
    @NotBlank String password
) {

}

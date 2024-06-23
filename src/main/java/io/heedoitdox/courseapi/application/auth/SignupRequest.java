package io.heedoitdox.courseapi.application.auth;

import io.heedoitdox.courseapi.domain.member.MemberType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignupRequest(
    @NotBlank String email, // TODO: email validation
    @NotBlank String name,
    @NotBlank String phone, // TODO: @Pattern
    @NotBlank String password, // TODO: validation
    @NotNull MemberType memberType
) {

}

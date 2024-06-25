package io.heedoitdox.courseapi.application.auth;

import io.heedoitdox.courseapi.domain.member.MemberType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SignupRequest(
    @Email
    String email,

    @NotBlank
    String name,

    @Pattern(regexp = "010-\\d{4}-\\d{4}", message = "올바른 전화번호 형식이 아니에요")
    String phone,

    String password,

    @NotNull
    MemberType memberType
) {

}

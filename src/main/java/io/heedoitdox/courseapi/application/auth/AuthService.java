package io.heedoitdox.courseapi.application.auth;

import io.heedoitdox.courseapi.domain.member.Member;
import io.heedoitdox.courseapi.domain.member.MemberRepository;
import io.heedoitdox.courseapi.exception.DuplicatedKeyException;
import io.heedoitdox.courseapi.exception.UnauthorizedException;
import io.heedoitdox.courseapi.support.security.JwtService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public void signup(SignupRequest signupRequest) {
    Optional<Member> memberOptional = memberRepository.findByEmail(signupRequest.email());
    if (memberOptional.isPresent()) {
      throw new DuplicatedKeyException("사용할 수 없는 이메일이에요");
    }

    Member member = Member.create(
        signupRequest.email(),
        passwordEncoder.encode(signupRequest.password()),
        signupRequest.name(),
        signupRequest.phone(),
        signupRequest.memberType()
    );
    memberRepository.save(member);
  }

  public JwtAuthenticationResponse login(LoginRequest request) {
    Member member = memberRepository.findByEmail(request.email())
        .orElseThrow(() -> new UnauthorizedException("올바르지 않은 사용자 정보에요"));

    if(!passwordEncoder.matches(request.password(), member.getPassword())) {
      throw new UnauthorizedException("올바르지 않은 사용자 정보에요");
    }

    return JwtAuthenticationResponse.of(jwtService.generateToken(member));
  }
}

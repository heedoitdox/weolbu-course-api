package io.heedoitdox.courseapi.application.auth;

import io.heedoitdox.courseapi.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetailsService userDetailsService() {
    return username -> memberRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}
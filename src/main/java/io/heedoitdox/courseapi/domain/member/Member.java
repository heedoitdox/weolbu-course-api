package io.heedoitdox.courseapi.domain.member;

import io.heedoitdox.courseapi.domain.BaseTimeEntity;
import io.heedoitdox.courseapi.domain.CryptoStringConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.Collections;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "members")
public class Member extends BaseTimeEntity implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;

  @Convert(converter = CryptoStringConverter.class)
  @Column(nullable = false)
  private String phone;

  @Enumerated(EnumType.STRING)
  @Column(name = "member_type")
  private MemberType memberType;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.memberType.name()));
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  public static Member create(String email, String password, String name, String phone, MemberType memberType) {
    return Member.builder()
        .email(email)
        .password(password)
        .name(name)
        .phone(phone)
        .memberType(memberType)
        .build();
  }
}

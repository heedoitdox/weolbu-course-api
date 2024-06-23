package io.heedoitdox.courseapi.domain.course;

import io.heedoitdox.courseapi.domain.BaseTimeEntity;
import io.heedoitdox.courseapi.domain.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "courses")
public class Course extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private String title;

  private String description;

  private int capacity;

  private BigDecimal price;

  @ManyToOne
  @JoinColumn
  private Member instructorId; // TODO: 네이밍 고민 & @CreatedBy 사용할지 생각

  public static Course create(String title, String description, int capacity, BigDecimal price) {
    return Course.builder()
        .title(title)
        .description(description)
        .capacity(capacity)
        .price(price)
        .build();
  }
}

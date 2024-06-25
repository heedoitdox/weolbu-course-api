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
import jakarta.persistence.Version;
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

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private Integer capacity;

  private Integer registeredCount;

  private Double registeredRate;

  @Column(nullable = false)
  private BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "instructor_id", nullable = false)
  private Member instructor;

  @Version
  private Long version;

  public static Course create(String title, int capacity, BigDecimal price) {
    return Course.builder()
        .title(title)
        .capacity(capacity)
        .registeredCount(0)
        .registeredRate(0.0)
        .price(price)
        .build();
  }

  public void addInstructor(Member instructor) {
    this.instructor = instructor;
  }

  public void register() {
    if (this.registeredCount + 1 > this.capacity) {
      throw new RuntimeException();
    }

    this.registeredCount = this.registeredCount + 1;
  }
}

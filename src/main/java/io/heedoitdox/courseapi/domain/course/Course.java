package io.heedoitdox.courseapi.domain.course;

import io.heedoitdox.courseapi.domain.BaseTimeEntity;
import io.heedoitdox.courseapi.domain.member.Member;
import io.heedoitdox.courseapi.exception.UnprocessableException;
import io.heedoitdox.courseapi.support.BigDecimalUtil;
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

  private BigDecimal registeredRate;

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
        .registeredRate(BigDecimal.ZERO)
        .price(price)
        .build();
  }

  public void addInstructor(Member instructor) {
    this.instructor = instructor;
  }

  public void register() {
    if (!canRegister()) {
      throw new UnprocessableException("요청 가능한 수를 초과했어요");
    }

    this.registeredCount = this.registeredCount + 1;
    this.registeredRate =
        BigDecimalUtil.RoundToTwoDecimalPlace(new BigDecimal(this.registeredCount / (double) this.capacity));
  }

  private boolean canRegister() {
    return this.registeredCount + 1 <= this.capacity;
  }
}

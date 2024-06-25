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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "course_registrations")
public class CourseRegistration extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "member_id", nullable = false)
  private Member applicant;

  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  public static CourseRegistration create(Course course, Member applicant) {
    return CourseRegistration.builder()
        .course(course)
        .applicant(applicant)
        .build();
  }
}

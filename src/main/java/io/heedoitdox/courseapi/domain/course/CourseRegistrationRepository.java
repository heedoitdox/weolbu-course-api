package io.heedoitdox.courseapi.domain.course;

import io.heedoitdox.courseapi.domain.member.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
  List<CourseRegistration> findByCourseId(Long courseId);

  boolean existsByCourseIdInAndApplicant(List<Long> courseId, Member applicant);
}


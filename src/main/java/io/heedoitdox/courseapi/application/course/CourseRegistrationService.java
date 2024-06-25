package io.heedoitdox.courseapi.application.course;

import io.heedoitdox.courseapi.domain.course.Course;
import io.heedoitdox.courseapi.domain.course.CourseRegistration;
import io.heedoitdox.courseapi.domain.course.CourseRegistrationRepository;
import io.heedoitdox.courseapi.domain.member.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourseRegistrationService {

  private final CourseRegistrationRepository courseRegistrationRepository;

  public void register(Course course, Member applicant) {
    CourseRegistration courseRegistration = CourseRegistration.create(course, applicant);
    courseRegistrationRepository.save(courseRegistration);
  }

  public boolean existRegistration(List<Long> courseIds, Member applicant) {
    return courseRegistrationRepository.existsByCourseIdInAndApplicant(courseIds, applicant);
  }

}

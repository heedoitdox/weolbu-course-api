package io.heedoitdox.courseapi.application;

import io.heedoitdox.courseapi.domain.course.Course;
import io.heedoitdox.courseapi.domain.course.CourseRepository;
import io.heedoitdox.courseapi.support.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CourseService {

  private final CourseRepository courseRepository;

  public void create(CreateCourseRequest request) {
    Course course = request.toEntity();
    course.addInstructor(SecurityUtil.getMember().get());

    courseRepository.save(course);
  }

}

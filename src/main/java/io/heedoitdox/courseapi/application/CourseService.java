package io.heedoitdox.courseapi.application;

import io.heedoitdox.courseapi.domain.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CourseService {

  private final CourseRepository courseRepository;

  public void create(CreateCourseRequest request) {
    courseRepository.save(request.toEntity());
  }

}

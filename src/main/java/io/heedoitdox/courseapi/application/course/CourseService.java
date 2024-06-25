package io.heedoitdox.courseapi.application.course;

import io.heedoitdox.courseapi.domain.course.Course;
import io.heedoitdox.courseapi.domain.course.CourseRepository;
import io.heedoitdox.courseapi.domain.member.Member;
import io.heedoitdox.courseapi.support.security.SecurityUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class CourseService {

  private final CourseRepository courseRepository;
  private final CourseRegistrationService courseRegistrationService;

  public void create(CreateCourseRequest request) {
    Course course = request.toEntity();
    course.addInstructor(SecurityUtil.getMember().get());

    courseRepository.save(course);
  }

  @Transactional
  public void register(List<Long> courseIds, Member applicant) {
    if(courseRegistrationService.existRegistration(courseIds, applicant)) {
      throw new IllegalArgumentException("이미 신청한 강의가 있어요");
    }

    for(Long courseId : courseIds) {
      Course course = courseRepository.findByIdWithPessimisticLock(courseId)
          .orElseThrow(IllegalArgumentException::new);

      course.register();
      courseRegistrationService.register(course, applicant);
    }
  }

  @Transactional(readOnly = true)
  public Page<CourseListResponse> getList(CourseListRequest request) {
    Pageable pageable = PageRequest.of(request.pageNo(), request.pageSize());
    return courseRepository.findAllBySort(request.orderConditions(), pageable);
  }

}

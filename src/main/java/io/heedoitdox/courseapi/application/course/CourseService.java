package io.heedoitdox.courseapi.application.course;

import io.heedoitdox.courseapi.domain.course.Course;
import io.heedoitdox.courseapi.domain.course.CourseRepository;
import io.heedoitdox.courseapi.domain.member.Member;
import io.heedoitdox.courseapi.exception.ApiErrorException;
import io.heedoitdox.courseapi.exception.ErrorCode;
import io.heedoitdox.courseapi.exception.UnprocessableException;
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
public class CourseService {

  private final CourseRepository courseRepository;
  private final CourseRegistrationService courseRegistrationService;

  @Transactional
  public void create(CreateCourseRequest request) {
    Course course = request.toEntity();
    Member instructor = SecurityUtil.getMember().orElseThrow(UnprocessableException::new);
    course.addInstructor(instructor);

    courseRepository.save(course);
  }

  @Transactional
  public void register(List<Long> courseIds, Member applicant) {
    if(courseRegistrationService.existRegistration(courseIds, applicant)) {
      throw new ApiErrorException(ErrorCode.BAD_REQUEST, "이미 신청한 강의가 있어요");
    }

    for(Long courseId : courseIds) {
      Course course = courseRepository.findByIdWithPessimisticLock(courseId)
          .orElseThrow(() -> new ApiErrorException(ErrorCode.BAD_REQUEST, "존재하지 않는 강의에요: " + courseId));

      course.register();
      courseRegistrationService.register(course, applicant);
    }
  }

  @Transactional(readOnly = true)
  public Page<CourseListResponse> getList(CourseListRequest request) {
    Pageable pageable = PageRequest.of(request.pageNo(), request.pageSize());
    Page<CourseSummary> courseSummary = courseRepository.findAllBySort(request.orderConditions(), pageable);

    return courseSummary.map(CourseListResponse::from);
  }

}

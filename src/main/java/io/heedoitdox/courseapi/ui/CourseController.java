package io.heedoitdox.courseapi.ui;

import io.heedoitdox.courseapi.application.course.CourseListRequest;
import io.heedoitdox.courseapi.application.course.CourseListResponse;
import io.heedoitdox.courseapi.application.course.CourseRegisterRequest;
import io.heedoitdox.courseapi.application.course.CourseService;
import io.heedoitdox.courseapi.application.course.CreateCourseRequest;
import io.heedoitdox.courseapi.domain.member.Member;
import io.heedoitdox.courseapi.support.security.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

  private final CourseService courseService;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("(hasAuthority('ROLE_INSTRUCTOR'))")
  public ResponseEntity<Void> create(@Valid @RequestBody CreateCourseRequest request) {
    courseService.create(request);

    return ResponseEntity.ok().build();
  }

  @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<CourseListResponse>> getList(@ModelAttribute CourseListRequest request) {
    Page<CourseListResponse> response = courseService.getList(request);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<Void> register(@RequestBody CourseRegisterRequest request) {
    Member member = SecurityUtil.getMember().orElseThrow(IllegalArgumentException::new);
    courseService.register(request.courseIds(), member);

    return ResponseEntity.ok().build();
  }

}

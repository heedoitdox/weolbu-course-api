package io.heedoitdox.courseapi.ui;

import io.heedoitdox.courseapi.application.CourseService;
import io.heedoitdox.courseapi.application.CreateCourseRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Void> create(@Valid @RequestBody CreateCourseRequest request) {
    courseService.create(request);
    return ResponseEntity.ok().build();
  }

}

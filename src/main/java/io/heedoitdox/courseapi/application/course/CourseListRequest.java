package io.heedoitdox.courseapi.application.course;

import java.util.List;

public record CourseListRequest (
  Integer pageSize,
  Integer pageNo,
  List<OrderCondition> orderConditions
) {

  public CourseListRequest {
    pageSize = 20;
    pageNo = 0;
  }
}
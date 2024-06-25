package io.heedoitdox.courseapi.domain.course;

import io.heedoitdox.courseapi.application.course.CourseListResponse;
import io.heedoitdox.courseapi.application.course.OrderCondition;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseRepositoryCustom {
  Page<CourseListResponse> findAllBySort(List<OrderCondition> orderConditionList, Pageable pageable);

}

package io.heedoitdox.courseapi.domain.course;

import static io.heedoitdox.courseapi.domain.course.QCourse.course;
import static io.heedoitdox.courseapi.domain.course.QCourseRegistration.courseRegistration;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.heedoitdox.courseapi.application.course.CourseListResponse;
import io.heedoitdox.courseapi.application.course.OrderCondition;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CourseRepositoryImpl implements CourseRepositoryCustom {

  private final JPAQueryFactory factory;

  @Override
  public Page<CourseListResponse> findAllBySort(List<OrderCondition> orderConditionList, Pageable pageable) {

    List<CourseListResponse> fetch = factory
        .select(Projections.constructor(
            CourseListResponse.class,
            course.id,
            course.title,
            course.capacity,
            course.price,
            course.registeredCount,
            course.createdAt
        )).from(course)
        .leftJoin(course).on(courseRegistration.course.id.eq(course.id))
        .orderBy(getOrderSpecifiers(orderConditionList))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    JPAQuery<Long> count = factory.select(course.count()).from(course);

    return PageableExecutionUtils.getPage(fetch, pageable, count::fetchOne);
  }

  private OrderSpecifier[] getOrderSpecifiers(List<OrderCondition> orderConditionList) {
    List<OrderSpecifier> orderSpecifiers = new ArrayList<>();

    if(ObjectUtils.isEmpty(orderConditionList)) {
      orderSpecifiers.add(new OrderSpecifier(Order.DESC, course.createdAt));
      return orderSpecifiers.toArray(new OrderSpecifier[0]);
    }

    for(OrderCondition type : orderConditionList) {
      switch(type) {
        // 최신순
        case CREATED_AT_DESC -> orderSpecifiers.add(new OrderSpecifier(Order.DESC, course.createdAt));
        // 신청자 많은 순
        case REGISTERED_COUNT_DESC -> orderSpecifiers.add(new OrderSpecifier(Order.DESC, course.registeredCount));
        // 신청률 높은 순
        case REGISTRATION_RATE_DESC -> {
          NumberExpression<Double> registeredRateDividedCapacity =
              Expressions.numberTemplate(Double.class, "({0} / {1})", course.registeredCount, course.capacity);
          orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, registeredRateDividedCapacity));
        }
      }
    }

    return orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]);
  }
}

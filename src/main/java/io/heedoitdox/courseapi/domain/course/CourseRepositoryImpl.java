package io.heedoitdox.courseapi.domain.course;

import static io.heedoitdox.courseapi.domain.course.QCourse.course;
import static io.heedoitdox.courseapi.domain.member.QMember.member;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.heedoitdox.courseapi.application.course.CourseSummary;
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
  public Page<CourseSummary> findAllBySort(List<OrderCondition> orderConditionList, Pageable pageable) {

    List<CourseSummary> fetch = factory
        .select(Projections.constructor(
            CourseSummary.class,
            course.id,
            course.title,
            course.capacity,
            course.price,
            course.registeredCount,
            course.registeredRate,
            member.name,
            course.createdAt
        )).from(course)
        .leftJoin(course.instructor, member)
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
        case REGISTRATION_RATE_DESC -> orderSpecifiers.add(new OrderSpecifier(Order.DESC, course.registeredRate));
      }
    }

    return orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]);
  }
}

package io.heedoitdox.courseapi.application.course;

import static org.assertj.core.api.Assertions.assertThat;

import io.heedoitdox.courseapi.config.FixtureMonkeyUtil;
import io.heedoitdox.courseapi.domain.course.Course;
import io.heedoitdox.courseapi.domain.course.CourseRegistration;
import io.heedoitdox.courseapi.domain.course.CourseRegistrationRepository;
import io.heedoitdox.courseapi.domain.course.CourseRepository;
import io.heedoitdox.courseapi.domain.member.Member;
import io.heedoitdox.courseapi.domain.member.MemberRepository;
import io.heedoitdox.courseapi.domain.member.MemberType;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class CourseServiceConcurrencyTest {

  @Autowired
  CourseService courseService;

  @Autowired
  CourseRepository courseRepository;
  @Autowired
  CourseRegistrationRepository courseRegistrationRepository;

  @Autowired
  MemberRepository memberRepository;

  Course savedCourse;
  Member savedMember;

  @BeforeEach
  void 강의_세팅() {
    Member instructor =
        Member.create("테스터@test.com", "1234", "테스터", "01012341234", MemberType.INSTRUCTOR);
    savedMember = memberRepository.save(instructor);

    Course course = FixtureMonkeyUtil.monkey().giveMeBuilder(Course.class)
        .set("title", "너나위의 부동산 투자법")
        .set("capacity", 10)
        .set("registeredCount", 0)
        .set("price", new BigDecimal("452000"))
        .set("instructor", instructor)
        .set("version", 1L)
        .sample();
    savedCourse = courseRepository.save(course);
  }

  @Test
  @Order(1)
  void 강의_생성_확인() {
    Optional<Course> courseOptional = courseRepository.findById(savedCourse.getId());

    assertThat(courseOptional.isPresent()).isTrue();
  }

  @Test
  @Order(2)
  void 수용인원이_10명인_강의에_100명이_신청해도_10명만_신청된다() throws InterruptedException {
    //given
    final int PARTICIPATION_PEOPLE = 100;

    ExecutorService executorService = Executors.newFixedThreadPool(32);
    CountDownLatch latch = new CountDownLatch(PARTICIPATION_PEOPLE);

    //when
    for(int i = 0; i < PARTICIPATION_PEOPLE; i++) {
      executorService.submit(() -> {
        try {
          courseService.register(List.of(savedCourse.getId()), savedMember);
        } finally {
          latch.countDown();
        }
      });
    }
    latch.await();

    //then
    Optional<Course> courseOptional = courseRepository.findById(savedCourse.getId());
    Course course = courseOptional.get();
    List<CourseRegistration> courseRegistrations = courseRegistrationRepository.findByCourseId(savedCourse.getId());

    assertThat(course.getRegisteredCount()).isEqualTo(savedCourse.getCapacity());
    assertThat(courseRegistrations.size()).isEqualTo(savedCourse.getCapacity());
  }
}
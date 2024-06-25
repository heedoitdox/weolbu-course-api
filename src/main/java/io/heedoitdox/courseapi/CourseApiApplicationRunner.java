package io.heedoitdox.courseapi;

import io.heedoitdox.courseapi.domain.course.Course;
import io.heedoitdox.courseapi.domain.course.CourseRegistration;
import io.heedoitdox.courseapi.domain.course.CourseRegistrationRepository;
import io.heedoitdox.courseapi.domain.course.CourseRepository;
import io.heedoitdox.courseapi.domain.member.Member;
import io.heedoitdox.courseapi.domain.member.MemberRepository;
import io.heedoitdox.courseapi.domain.member.MemberType;
import java.math.BigDecimal;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CourseApiApplicationRunner implements ApplicationRunner {

  private final MemberRepository memberRepository;
  private final CourseRepository courseRepository;
  private final CourseRegistrationRepository courseRegistrationRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void run(ApplicationArguments args) throws Exception {
    /* 30명 회원 등록 */
    ArrayList<Member> members = new ArrayList<>();
    Member member1 = Member.create("student1@weolbu.com", passwordEncoder.encode("1234"), "윤영빈1", "0109999999", MemberType.STUDENT);
    members.add(member1);
    Member member2 = Member.create("student2@weolbu.com", passwordEncoder.encode("1234"), "윤영빈2", "0109999999", MemberType.STUDENT);
    members.add(member2);
    Member member3 = Member.create("student3@weolbu.com", passwordEncoder.encode("1234"), "윤영빈3", "0109999999", MemberType.STUDENT);
    members.add(member3);
    Member member4 = Member.create("student4@weolbu.com", passwordEncoder.encode("1234"), "윤영빈4", "0109999999", MemberType.STUDENT);
    members.add(member4);
    Member member5 = Member.create("student5@weolbu.com", passwordEncoder.encode("1234"), "윤영빈5", "0109999999", MemberType.STUDENT);
    members.add(member5);
    Member member6 = Member.create("student6@weolbu.com", passwordEncoder.encode("1234"), "윤영빈6", "0109999999", MemberType.STUDENT);
    members.add(member6);
    Member member7 = Member.create("student7@weolbu.com", passwordEncoder.encode("1234"), "윤영빈7", "0109999999", MemberType.STUDENT);
    members.add(member7);
    Member member8 = Member.create("student8@weolbu.com", passwordEncoder.encode("1234"), "윤영빈8", "0109999999", MemberType.STUDENT);
    members.add(member8);
    Member member9 = Member.create("student9@weolbu.com", passwordEncoder.encode("1234"), "윤영빈9", "0109999999", MemberType.STUDENT);
    members.add(member9);
    Member member10 = Member.create("student10@weolbu.com", passwordEncoder.encode("1234"), "윤영빈10", "0109999999", MemberType.STUDENT);
    members.add(member10);
    Member member11 = Member.create("student11@weolbu.com", passwordEncoder.encode("1234"), "윤영빈11", "0109999999", MemberType.STUDENT);
    members.add(member11);
    Member member12 = Member.create("student12@weolbu.com", passwordEncoder.encode("1234"), "윤영빈12", "0109999999", MemberType.STUDENT);
    members.add(member12);
    Member member13 = Member.create("student13@weolbu.com", passwordEncoder.encode("1234"), "윤영빈13", "0109999999", MemberType.STUDENT);
    members.add(member13);
    Member member14 = Member.create("student14@weolbu.com", passwordEncoder.encode("1234"), "윤영빈14", "0109999999", MemberType.STUDENT);
    members.add(member14);
    Member member15 = Member.create("student15@weolbu.com", passwordEncoder.encode("1234"), "윤영빈15", "0109999999", MemberType.STUDENT);
    members.add(member15);
    Member member16 = Member.create("student16@weolbu.com", passwordEncoder.encode("1234"), "윤영빈16", "0109999999", MemberType.STUDENT);
    members.add(member16);
    Member member17 = Member.create("student17@weolbu.com", passwordEncoder.encode("1234"), "윤영빈17", "0109999999", MemberType.STUDENT);
    members.add(member17);
    Member member18 = Member.create("student18@weolbu.com", passwordEncoder.encode("1234"), "윤영빈18", "0109999999", MemberType.STUDENT);
    members.add(member18);
    Member member19 = Member.create("student19@weolbu.com", passwordEncoder.encode("1234"), "윤영빈19", "0109999999", MemberType.STUDENT);
    members.add(member19);
    Member member20 = Member.create("student20@weolbu.com", passwordEncoder.encode("1234"), "윤영빈20", "0109999999", MemberType.STUDENT);
    members.add(member20);
    Member member21 = Member.create("instructor1@weolbu.com", passwordEncoder.encode("1234"), "강사1", "0109999999", MemberType.INSTRUCTOR);
    members.add(member21);
    Member member22 = Member.create("instructor2@weolbu.com", passwordEncoder.encode("1234"), "강사2", "0109999999", MemberType.INSTRUCTOR);
    members.add(member22);
    Member member23 = Member.create("instructor3@weolbu.com", passwordEncoder.encode("1234"), "강사3", "0109999999", MemberType.INSTRUCTOR);
    members.add(member23);
    Member member24 = Member.create("instructor4@weolbu.com", passwordEncoder.encode("1234"), "강사4", "0109999999", MemberType.INSTRUCTOR);
    members.add(member24);
    Member member25 = Member.create("instructor5@weolbu.com", passwordEncoder.encode("1234"), "강사5", "0109999999", MemberType.INSTRUCTOR);
    members.add(member25);
    Member member26 = Member.create("instructor6@weolbu.com", passwordEncoder.encode("1234"), "강사6", "0109999999", MemberType.INSTRUCTOR);
    members.add(member26);
    Member member27 = Member.create("instructor7@weolbu.com", passwordEncoder.encode("1234"), "강사7", "0109999999", MemberType.INSTRUCTOR);
    members.add(member27);
    Member member28 = Member.create("instructor8@weolbu.com", passwordEncoder.encode("1234"), "강사8", "0109999999", MemberType.INSTRUCTOR);
    members.add(member28);
    Member member29 = Member.create("instructor9@weolbu.com", passwordEncoder.encode("1234"), "강사9", "0109999999", MemberType.INSTRUCTOR);
    members.add(member29);
    Member member30 = Member.create("instructor10@weolbu.com", passwordEncoder.encode("1234"), "강사10", "0109999999", MemberType.INSTRUCTOR);
    members.add(member30);
    memberRepository.saveAll(members);

    /* 30개의 강의 등록 */
    ArrayList<Course> courses = new ArrayList<>();
    Course course1 = Course.create("너나위의 내집마련 기초반", 30, new BigDecimal("425000"));
    courses.add(course1);
    Course course2 = Course.create("[SPECIAL] 따박따박 자동수익 만들기", 30, new BigDecimal("425000"));
    courses.add(course2);
    Course course3 = Course.create("서울 가장 좋은 곳에 내집마련 하는 법", 30, new BigDecimal("425000"));
    courses.add(course3);
    Course course4 = Course.create("소액투자로 1억버는 지방광역시 4곳 분석", 30, new BigDecimal("425000"));
    courses.add(course4);
    Course course5 = Course.create("서울 아파트 1억으로 갖는법 지금 싸고, 계속 오를곳 최초공개!", 30, new BigDecimal("425000"));
    courses.add(course5);
    Course course6 = Course.create("사고 후회 말고 사기 전에 반드시! 월부멘토 매물코칭", 30, new BigDecimal("425000"));
    courses.add(course6);
    Course course7 = Course.create("부를 이루어 내는 성공할 수 밖에 없는 법칙", 30, new BigDecimal("425000"));
    courses.add(course7);
    Course course8 = Course.create("손실나면 100% 환불! 예적금보다 5배 빠른 무손실 투자 수익화반", 30, new BigDecimal("425000"));
    courses.add(course8);
    Course course9 = Course.create("1000만원으로 시작하는 아파트 투자법", 30, new BigDecimal("425000"));
    courses.add(course9);
    Course course10 = Course.create("나도 호스트! 월 300 에어비앤비 실전 창업반", 30, new BigDecimal("425000"));
    courses.add(course10);
    Course course11 = Course.create("2천만원으로 시작하는 서울 부동산 소액 투자법", 30, new BigDecimal("425000"));
    courses.add(course11);
    Course course12 = Course.create("월 100만원 버는 블로그 부업 완벽 세팅하기!", 15, new BigDecimal("425000"));
    courses.add(course12);
    Course course13 = Course.create("최소 5억 이상 오를곳! 분당판교 지역분석 & 입성 전략 노하우", 30, new BigDecimal("425000"));
    courses.add(course13);
    Course course14 = Course.create("7년만에 왕초보에게도 찾아온 기회! 수익률 145% 투자법", 30, new BigDecimal("425000"));
    courses.add(course14);
    Course course15 = Course.create("전자책 딱 한 권으로 2024년 자동수익 만들기", 30, new BigDecimal("425000"));
    courses.add(course15);
    Course course16 = Course.create("김현준의 주식으로 부자되는 공식", 30, new BigDecimal("425000"));
    courses.add(course16);
    Course course17 = Course.create("권동우의 미국주식 성공 투자원칙", 30, new BigDecimal("425000"));
    courses.add(course17);
    Course course18 = Course.create("종잣돈 없어도 가능! 평생 월 300만원 받는 투자 공식", 30, new BigDecimal("425000"));
    courses.add(course18);
    Course course19 = Course.create("종목까지 싹 다 알려주는 퀀트투자 공식", 30, new BigDecimal("425000"));
    courses.add(course19);
    Course course20 = Course.create("억대 수익 가능한 유일한 1등 지방! 부산 유망단지 찾는법", 10, new BigDecimal("425000"));
    courses.add(course20);
    Course course21 = Course.create("2024 서울 임장보고서 작성하고 투자처 받는 법", 30, new BigDecimal("425000"));
    courses.add(course21);
    Course course22 = Course.create("데이터 분석으로 짜릿하게 상승하는 저평가 입지 찾기", 30, new BigDecimal("425000"));
    courses.add(course22);
    Course course23 = Course.create("매출 20배 만드는 전설의 마케팅 성공공식!", 30, new BigDecimal("425000"));
    courses.add(course23);
    Course course24 = Course.create("빛난다의 네이버 블로그 수익화 기초반", 30, new BigDecimal("425000"));
    courses.add(course24);
    Course course25 = Course.create("2024 한 달에 하나씩 늘리는 손품 발품 임보 노하우", 30, new BigDecimal("425000"));
    courses.add(course25);
    Course course26 = Course.create("대구 지역분석 및 유망단지 찾는법", 30, new BigDecimal("425000"));
    courses.add(course26);
    Course course27 = Course.create("서울 수도권 아파트 하락장 소액 투자법", 30, new BigDecimal("425000"));
    courses.add(course27);
    Course course28 = Course.create("2024 한 달에 하나씩 늘리는 손품 발품 임보 노하우", 30, new BigDecimal("425000"));
    courses.add(course28);
    Course course29 = Course.create("임보 결론 단하루만에! 비교평가와 1등뽑기 노하우", 30, new BigDecimal("425000"));
    courses.add(course29);
    Course course30 = Course.create("5만원으로 월 300만원 만드는 스마트스토어", 30, new BigDecimal("425000"));
    courses.add(course30);
    for(Course course : courses) {
      course.addInstructor(member21);
    }
    courseRepository.saveAll(courses);

    for(int i = 0; i < 11; i++) {
      course1.register();
    }
    ArrayList<CourseRegistration> courseRegistrations = new ArrayList<>();
    // course1 / 신청자 11명 / 신청률 0.37
    courseRegistrations.add(CourseRegistration.create(course1, member1));
    courseRegistrations.add(CourseRegistration.create(course1, member2));
    courseRegistrations.add(CourseRegistration.create(course1, member3));
    courseRegistrations.add(CourseRegistration.create(course1, member4));
    courseRegistrations.add(CourseRegistration.create(course1, member5));
    courseRegistrations.add(CourseRegistration.create(course1, member6));
    courseRegistrations.add(CourseRegistration.create(course1, member7));
    courseRegistrations.add(CourseRegistration.create(course1, member8));
    courseRegistrations.add(CourseRegistration.create(course1, member9));
    courseRegistrations.add(CourseRegistration.create(course1, member10));
    courseRegistrations.add(CourseRegistration.create(course1, member11));

    // course6 / 신청자 1명 / 신청률 : 0.03
    course6.register();
    courseRegistrations.add(CourseRegistration.create(course6, member16));

    // course12 / 신청자 5명 / 신청률: 0.33
    for(int i = 0; i < 5; i++) {
      course12.register();
    }
    courseRegistrations.add(CourseRegistration.create(course12, member11));
    courseRegistrations.add(CourseRegistration.create(course12, member12));
    courseRegistrations.add(CourseRegistration.create(course12, member13));
    courseRegistrations.add(CourseRegistration.create(course12, member14));
    courseRegistrations.add(CourseRegistration.create(course12, member15));

    // course13 / 신청자: 9명 / 신청률: 0.3
    for(int i = 0; i < 9; i++) {
      course13.register();
    }
    courseRegistrations.add(CourseRegistration.create(course13, member5));
    courseRegistrations.add(CourseRegistration.create(course13, member6));
    courseRegistrations.add(CourseRegistration.create(course13, member7));
    courseRegistrations.add(CourseRegistration.create(course13, member8));
    courseRegistrations.add(CourseRegistration.create(course13, member9));
    courseRegistrations.add(CourseRegistration.create(course13, member10));
    courseRegistrations.add(CourseRegistration.create(course13, member11));
    courseRegistrations.add(CourseRegistration.create(course13, member12));
    courseRegistrations.add(CourseRegistration.create(course13, member13));

    // course20 / 신청자 7명 / 신청률: 0.7
    for(int i = 0; i < 7; i++) {
      course20.register();
    }
    courseRegistrations.add(CourseRegistration.create(course20, member13));
    courseRegistrations.add(CourseRegistration.create(course20, member14));
    courseRegistrations.add(CourseRegistration.create(course20, member15));
    courseRegistrations.add(CourseRegistration.create(course20, member16));
    courseRegistrations.add(CourseRegistration.create(course20, member17));
    courseRegistrations.add(CourseRegistration.create(course20, member18));
    courseRegistrations.add(CourseRegistration.create(course20, member19));

    courseRegistrationRepository.saveAll(courseRegistrations);
  }

}



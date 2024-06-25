# weolbu-couse-api

## 1. 개요
- 회원가입 API
- 로그인 API
- 강의 개설 API
- 강의 목록 조회 API
- 강의 신청 API
- [API 문서](localhost:8080/docs/docs.html) (애플리케이션 실행 후 확인가능)

## 2. 개발 환경
- IDE: Intellij IDEA
- gradle: `Gradle 8.7`
- java: `Java 17`
- spring boot: `3.3.1`

## 3. 실행 절차
1. git clone
```bash
git clone <repository_url>
```
2. project 로 이동
```bash 
cd <project-name>
```
3. gradle 빌드
```bash
./gradlew build
```
4. 애플리케이션 실행 
```bash
./gradlew bootRun
```
5. [빠른 api 호출](https://github.com/heedoitdox/weolbu-course-api/blob/main/src/http/api.http)
```bash
intellij IDE 에서 /src/http/api.http 파일 오픈 후 호출 
```

## 4. 사용 라이브러리
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-security`
- `spring-boot-starter-validation` : controller request 객체 검증
- `querydsl`
- `restdocs`
- `jsonwebtoken`
- database: h2 인메모리
### 테스트
- [fixture monkey](https://naver.github.io/fixture-monkey/v1-0-0/)
### 로그
- `logback-json-classic:0.1.5`
- `logback-jackson:0.1.5`


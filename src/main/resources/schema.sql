DROP TABLE IF EXISTS members;
CREATE TABLE IF NOT EXISTS members
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    email    VARCHAR(100) NOT NULL,
    password VARCHAR(10)  NOT NULL,
    name     VARCHAR(100) NOT NULL,
    role     VARCHAR(100) NOT NULL,
    UNIQUE (email)
);

DROP TABLE IF EXISTS courses;
CREATE TABLE IF NOT EXISTS courses
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(100) NOT NULL,
    description VARCHAR(100) NOT NULL,
    capacity    int          NOT NULL,
    price       decimal      NOT NULL,
    created_by  bigint
);

DROP TABLE IF EXISTS course_registrations;
CREATE TABLE IF NOT EXISTS course_registrations
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id       bigint NOT NULL,
    course_id       bigint NOT NULL,
    registration_at date,
    UNIQUE (member_id, course_id)
);
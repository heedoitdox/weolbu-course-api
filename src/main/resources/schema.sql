DROP TABLE IF EXISTS members;
CREATE TABLE IF NOT EXISTS members
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    email       VARCHAR(100) NOT NULL,
    password    VARCHAR(256) NOT NULL,
    name        VARCHAR(100) NOT NULL,
    phone       VARCHAR(100) NOT NULL,
    member_type VARCHAR(100) NOT NULL,
    created_at  timestamp    not null,
    updated_at  timestamp,
    UNIQUE (email)
);

DROP TABLE IF EXISTS courses;
CREATE TABLE IF NOT EXISTS courses
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    title            VARCHAR(100)   NOT NULL,
    capacity         int            NOT NULL,
    registered_count int            default 0,
    registered_rate  decimal(19, 2) default 0,
    price            decimal(19, 2) NOT NULL,
    instructor_id    bigint         not null,
    version          int            default 1,
    created_at       timestamp      not null,
    updated_at       timestamp
);

DROP TABLE IF EXISTS course_registrations;
CREATE TABLE IF NOT EXISTS course_registrations
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id       bigint    NOT NULL,
    course_id       bigint    NOT NULL,
    registration_at date,
    created_at      timestamp not null,
    updated_at      timestamp,
    UNIQUE (member_id, course_id)
);
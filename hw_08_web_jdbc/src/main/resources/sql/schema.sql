-- Creation database

-- DROP DATABASE IF EXISTS "nix-lms";
--
-- CREATE DATABASE "nix-lms"
--     WITH
--     OWNER = postgres
--     ENCODING = 'UTF8'
--     LC_COLLATE = 'Russian_Ukraine.1251'
--     LC_CTYPE = 'Russian_Ukraine.1251'
--     CONNECTION LIMIT = -1;

DROP TABLE IF EXISTS student_group_student;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS student_group;
DROP INDEX IF EXISTS idx_student_uuid;
DROP INDEX IF EXISTS idx_student_group_uuid;
DROP SEQUENCE IF EXISTS student_id;
DROP SEQUENCE IF EXISTS student_group_id;

-- Creation tables

CREATE SEQUENCE IF NOT EXISTS student_id
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS student
(
    id         bigint                      NOT NULL DEFAULT nextval('student_id'),
    uuid       uuid                        NOT NULL,
    created    timestamp without time zone NOT NULL,
    updated    timestamp without time zone NOT NULL,
    first_name character varying(255),
    last_name  character varying(255),
    birth_day  date,
    CONSTRAINT student_pkey PRIMARY KEY (id),
    CONSTRAINT ux_student_uuid UNIQUE (uuid)
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_student_uuid
    ON student USING btree
        (uuid ASC NULLS LAST);

CREATE SEQUENCE IF NOT EXISTS student_group_id
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS student_group
(
    id          bigint                      NOT NULL DEFAULT nextval('student_group_id'),
    uuid        uuid                        NOT NULL,
    created     timestamp without time zone NOT NULL,
    updated     timestamp without time zone NOT NULL,
    name        character varying(255),
    description character varying(400),
    group_type  character varying(255),
    CONSTRAINT student_group_pkey PRIMARY KEY (id),
    CONSTRAINT ux_student_group_uuid UNIQUE (uuid)
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_student_group_uuid
    ON student_group USING btree
        (uuid ASC NULLS LAST);

CREATE TABLE IF NOT EXISTS student_group_student
(
    student_id       bigint NOT NULL,
    student_group_id bigint NOT NULL,
    CONSTRAINT student_group_student_pkey PRIMARY KEY (student_group_id, student_id),
    CONSTRAINT fk_student_group_student_student_group_id FOREIGN KEY (student_group_id)
        REFERENCES student_group (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_student_group_student_students_id FOREIGN KEY (student_id)
        REFERENCES student (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

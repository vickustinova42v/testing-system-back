create table roles (
    id serial primary key,
    name varchar(100) not null
);

create table users (
    id serial primary key,
    last_name varchar(50) not null,
    first_name varchar(50) not null,
    fathers_name varchar(50),
    email varchar(50) not null,
    password varchar(255) not null,
    role_id integer references roles (id)
);

create table subjects (
    id serial primary key,
    name varchar(100) not null,
    teacher_id integer references users (id),
    student_id integer references users (id)
);

create table questions (
    id serial primary key,
    type varchar(20) not null,
    name varchar(500) not null,
    subject_id integer references subjects (id) not null
);

create table answers (
    id serial primary key,
    name varchar(200) not null,
    is_right_answer bool not null,
    question_id integer references questions (id) not null
);

create table tests (
    id serial primary key,
    time integer not null,
    name varchar(100) not null,
    subject_id integer references subjects (id) not null
);

create table test_question (
    id serial primary key,
    test_id integer references tests (id) not null,
    question_id integer references questions (id) not null
);

create table student_test (
    id serial primary key,
    mark integer not null,
    student_id integer references users (id) not null,
    test_id integer references tests (id) not null
);

create table subject_student (
    id serial primary key,
    subject_id integer references subjects(id) not null,
    student_id integer references users(id) not null
);

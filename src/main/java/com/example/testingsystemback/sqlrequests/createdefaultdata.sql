insert into roles (name) values
     ('Администратор'),
     ('Преподаватель'),
     ('Студент'),
     ('Гость');

insert into users (last_name, first_name, fathers_name, email, password, role_id) values
      ('Админов', 'Админ', 'Админович','ivan@ivan.ru',
       '$2b$12$woiMG2qIeijoeEMduhZ4/.d4SyxViILEUp5oJDuZ3OPZ0yHPLFwES', 1),

      ('Ivanov', 'Pavel', 'Olegovich', 'admin2@example.com',
       '$2a$10$0GJPD7Wv/MManIeZDV4SSuYcyJMccwVnZq70MkUj7JHwI6snvqOdi', 1),

      ('Иванов', 'Иван', 'Иванович', 'teacher@test.com',
       '$2b$12$aXYfHRjxsYZkjQ65f4k.kOrM1RuCZUDAWw3PWfqtth3TfBOUBK0V2', 2),

      ('Petrov', 'Ivan', 'Sergeevich', 'teacher2@example.com',
       '$2a$10$0GJPD7Wv/MManIeZDV4SSuYcyJMccwVnZq70MkUj7JHwI6snvqOdi', 2),

      ('Петрова', 'Анна', 'Сергеевна', 'student@test.com',
       '$2b$12$fbvbx3hlRY3I9EqMEWNaF.s.sIv7MqCvUQb30O/MUKKX/.E31csxm', 3),

      ('Sidorova', 'Olga', 'Nikolaevna', 'student2@example.com',
       '$2a$10$0GJPD7Wv/MManIeZDV4SSuYcyJMccwVnZq70MkUj7JHwI6snvqOdi', 3);

insert into subjects (name, teacher_id) values
    ('Математика', 2),
    ('Физика', 2),
    ('Информатика', 2);

insert into subject_student (subject_id, student_id) values
     (1, 5),
     (2, 5),
     (3, 5);

insert into tests (name, subject_id, time) values
       ('Тест по математике', 1, 30),
       ('Тест по физике', 2, 30),
       ('Тест по информатике', 3, 30);

insert into questions (name, type, subject_id) values
       ('Чему равна производная x^2?', 'single', 1),
       ('Сколько корней имеет уравнение x^2 = 4?', 'single', 1),
       ('Выберите простые числа', 'multi', 1),
       ('Чему равно 5! ?', 'single', 1),
       ('Какие числа являются четными?', 'multi', 1),
       ('Корень из 49?', 'single', 1),
       ('Что является значением π (пи)?', 'single', 1),

       ('Единица измерения силы?', 'single', 2),
       ('Какие из перечисленных являются видами энергии?', 'multi', 2),
       ('Скорость света?', 'single', 2),
       ('Что является единицей измерения напряжения?', 'single', 2),
       ('Какие частицы входят в состав атома?', 'multi', 2),
       ('Что является видом движения?', 'multi', 2),
       ('Первый закон Ньютона описывает…?', 'single', 2),

       ('Что такое переменная?', 'single', 3),
       ('Какие типы данных существуют?', 'multi', 3),
       ('Что означает аббревиатура CPU?', 'single', 3),
       ('Какие устройства являются входными?', 'multi', 3),
       ('Что такое алгоритм?', 'single', 3),
       ('Какие языки являются языками программирования?', 'multi', 3);

insert into test_question (test_id, question_id) values
     (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),
     (2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14),
     (3,15),(3,16),(3,17),(3,18),(3,19),(3,20);

insert into student_test (mark, student_id, test_id) values
     (null, 5, 1),
     (null, 5, 2),
     (null, 5, 3);

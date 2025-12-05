insert into roles (name) values
                             ('Администратор'),
                             ('Преподаватель'),
                             ('Студент');

insert into users (last_name, first_name, fathers_name, email, password, role_id) values
    ('Админов', 'Админ', 'Админович','ivan@ivan.ru', 'admin', 1);

insert into users (last_name, first_name, fathers_name, email, password, role_id) values
    ('Иванов', 'Иван', 'Иванович', 'teacher@test.com', '12345', 2);

insert into users (last_name, first_name, fathers_name, email, password, role_id) values
    ('Petrova', 'Anna', 'Sergeevna', 'student@test.com', '12345', 3);

insert into subjects (name, teacher_id, student_id) values
                                                        ('Математика', 2, 3),
                                                        ('Физика',     2, 3),
                                                        ('Информатика', 2, 3);

insert into tests (name, subject_id, time) values
                                               ('Тест по математике', 1, 30),
                                               ('Тест по физике', 2, 30),
                                               ('Тест по информатике', 3, 30);

insert into questions (name, type, subject_id) values
                                                   ('Чему равна производная x^2?', 'single', 1),
                                                   ('Сколько корней имеет уравнение x^2 = 4?', 'single', 1),
                                                   ('Выберите простые числа', 'multiple', 1),
                                                   ('Чему равно 5! ?', 'single', 1),
                                                   ('Какие числа являются четными?', 'multiple', 1),
                                                   ('Корень из 49?', 'single', 1),
                                                   ('Что является значением π (пи)?', 'single', 1),
                                                   ('Единица измерения силы?', 'single', 2),
                                                   ('Какие из перечисленных являются видами энергии?', 'multiple', 2),
                                                   ('Скорость света?', 'single', 2),
                                                   ('Что является единицей измерения напряжения?', 'single', 2),
                                                   ('Какие частицы входят в состав атома?', 'multiple', 2),
                                                   ('Что является видом движения?', 'multiple', 2),
                                                   ('Первый закон Ньютона описывает…?', 'single', 2),
                                                   ('Что такое переменная?', 'single', 3),
                                                   ('Какие типы данных существуют?', 'multiple', 3),
                                                   ('Что означает аббревиатура CPU?', 'single', 3),
                                                   ('Какие устройства являются входными?', 'multiple', 3),
                                                   ('Что такое алгоритм?', 'single', 3),
                                                   ('Какие языки являются языками программирования?', 'multiple', 3);

insert into answers (name, is_right_answer, question_id) values
                                                             ('2x', true, 1),
                                                             ('x', false, 1),
                                                             ('x^2', false, 1);

insert into answers (name, is_right_answer, question_id) values
                                                             ('0', false, 2),
                                                             ('1', false, 2),
                                                             ('2', true, 2);

insert into answers (name, is_right_answer, question_id) values
                                                             ('2', true, 3),
                                                             ('3', true, 3),
                                                             ('9', false, 3);

insert into answers (name, is_right_answer, question_id) values
                                                             ('120', true, 4),
                                                             ('25', false, 4),
                                                             ('720', false, 4);

insert into answers (name, is_right_answer, question_id) values
                                                             ('2', true, 5),
                                                             ('5', false, 5),
                                                             ('8', true, 5);

insert into answers (name, is_right_answer, question_id) values
                                                             ('7', true, 6),
                                                             ('14', false, 6),
                                                             ('√9', false, 6);

insert into answers (name, is_right_answer, question_id) values
                                                             ('3.14', true, 7),
                                                             ('2.71', false, 7),
                                                             ('1.41', false, 7);

insert into answers(name, is_right_answer, question_id) values
                                                            ('Ньютон', true, 8),
                                                            ('Паскаль', false, 8),
                                                            ('Джоуль', false, 8);

insert into answers(name, is_right_answer, question_id) values
                                                            ('Тепловая', true, 9),
                                                            ('Электрическая', true, 9),
                                                            ('Звуковая', false, 9);

insert into answers(name, is_right_answer, question_id) values
                                                            ('300 000 км/с', true, 10),
                                                            ('150 000 км/с', false, 10);

insert into answers(name, is_right_answer, question_id) values
                                                            ('Вольт', true, 11),
                                                            ('Ампер', false, 11);

insert into answers(name, is_right_answer, question_id) values
                                                            ('Протон', true, 12),
                                                            ('Электрон', true, 12),
                                                            ('Фотон', false, 12);

insert into answers(name, is_right_answer, question_id) values
                                                            ('Поступательное', true, 13),
                                                            ('Колебательное', true, 13),
                                                            ('Случайное', false, 13);

insert into answers(name, is_right_answer, question_id) values
                                                            ('Закон инерции', true, 14),
                                                            ('Закон тяготения', false, 14);

insert into answers(name, is_right_answer, question_id) values
                                                            ('Именованная область памяти', true, 15),
                                                            ('Программный код', false, 15);

insert into answers(name, is_right_answer, question_id) values
                                                            ('int', true, 16),
                                                            ('boolean', true, 16),
                                                            ('stringify', false, 16);

insert into answers(name, is_right_answer, question_id) values
                                                            ('Central Processing Unit', true, 17),
                                                            ('Computer Power Unit', false, 17);

insert into answers(name, is_right_answer, question_id) values
                                                            ('Клавиатура', true, 18),
                                                            ('Мышь', true, 18),
                                                            ('Принтер', false, 18);

insert into answers(name, is_right_answer, question_id) values
                                                            ('Последовательность действий', true, 19),
                                                            ('Инструкции компьютера', false, 19);

insert into answers(name, is_right_answer, question_id) values
                                                            ('Java', true, 20),
                                                            ('Python', true, 20),
                                                            ('HTML', false, 20);

insert into test_question (test_id, question_id) values
                                                     (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7);

insert into test_question (test_id, question_id) values
                                                     (2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14);

insert into test_question (test_id, question_id) values
                                                     (3,15),(3,16),(3,17),(3,18),(3,19),(3,20);

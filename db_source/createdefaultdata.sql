insert into roles (name) values
    ('admin'),
    ('teacher'),
    ('student');

insert into users (first_name, last_name, email, password, role_id) values
    ('Иван', 'Иванов', 'ivan@ivan.ru', 'admin', 1);
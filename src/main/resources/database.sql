create table User
(
    id            int          not null auto_increment,
    lastname      varchar(255) default 'Фамилия',
    midname       varchar(255) default 'Отчество',
    firstname     varchar(255) default 'Имя',
    username      varchar(255) not null,
    password      varchar(255) not null,
    date_of_birth date         default '2004-04-04',
    rating        int          default 0,
    photo         varchar(255) default 'пока пусто',
    primary key (id)
);

create table Role
(
    id   int          not null auto_increment primary key,
    name varchar(255) not null
);

create table User_role
(
    users_id int not null,
    roles_id int not null,
    foreign key (users_id) references user (id),
    foreign key (roles_id) references role (id),
    UNIQUE (users_id, roles_id)
);

create table Course
(
    id          int not null auto_increment primary key,
    name        varchar(255) default 'Безымянный',
    description varchar(255) default 'Нет описания',
    price       int          default 0,
    photo       varchar(255) default 'пока пусто',
    teacher_id  int not null,
    foreign key (teacher_id) references user (id)
);

create table Test
(
    id          int          not null auto_increment primary key,
    name        varchar(255) not null,
    description varchar(255) default 'Нет описания',
    start       datetime     not null,
    stop        datetime     not null
);

create table Question
(
    id      int          not null auto_increment primary key,
    text    varchar(255) not null,
    test_id int          not null,
    foreign key (test_id) references Test (id)
);

create table Answer
(
    id          int          not null auto_increment primary key,
    text        varchar(255) not null,
    is_right    boolean      not null,
    question_id int          not null,
    foreign key (question_id) references Question (id)
);

create table Attempt
(
    id      int not null primary key auto_increment,
    number  int not null,
    grade   int not null,
    test_id int not null,
    user_id int not null,
    foreign key (test_id) references Test (id),
    foreign key (user_id) references User (id)
);

create table TestGrade
(
    id      int not null primary key auto_increment,
    grade   int not null,
    test_id int not null,
    user_id int not null,
    foreign key (test_id) references Test (id),
    foreign key (user_id) references User (id)
);

create table CourseGrade
(
    id        int not null primary key auto_increment,
    grade     int not null,
    course_id int not null,
    user_id   int not null,
    foreign key (course_id) references Course (id),
    foreign key (user_id) references User (id)
);

insert into User(username, password)
values ('killmepalas', 'qwerty'),
       ('proselyte', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG');

insert into Role(name)
values ('student'),
       ('teacher'),
       ('admin');

insert into User_role
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 1);
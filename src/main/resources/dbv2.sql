create table user_course
(
    user_id   int not null,
    course_id int not null,
    foreign key (user_id) references User (id),
    foreign key (course_id) references Course (id) ON DELETE CASCADE,
    unique (user_id, course_id)
);

insert into user_course
values (1, 1),
       (1, 3),
       (2, 2);

create table status
(
    id   int          not null primary key auto_increment,
    name varchar(255) not null
);

alter table user
    add status_id int,
    add foreign key (status_id) references status (id);

insert into status (id, name)
values (1, 'active'),
       (2, 'block'),
       (3, 'wait');

create table category
(
    id   int          not null primary key auto_increment,
    name varchar(255) not null
);

alter table course
    add category_id int,
    add foreign key (category_id) references category (id),
    add status_id   int,
    add foreign key (status_id) references status (id);

create table topic
(
    id          int          not null primary key auto_increment,
    name        varchar(255) not null,
    description varchar(255) not null,
    course_id   int          not null,
    status_id   int          not null,
    foreign key (course_id) references course (id),
    foreign key (status_id) references status (id)
);

alter table test
    add topic_id int,
    add foreign key (topic_id) references topic (id);

alter table test
    add status_id int,
    add foreign key (status_id) references status (id);

create table lecture
(
    id          int          not null primary key auto_increment,
    name        varchar(255) not null,
    description varchar(255) not null,
    text        varchar(255) not null,
    topic_id    int          not null,
    foreign key (topic_id) references topic (id)
);

insert into category(name)
values ('Программирование'),
       ('Аналитика'),
       ('Маркетинг и продажи'),
       ('Дизайн'),
       ('Фотография');

create table TopicGrade
(
    id       int not null primary key auto_increment,
    grade    int not null,
    topic_id int not null,
    user_id  int not null,
    foreign key (topic_id) references Topic (id),
    foreign key (user_id) references User (id)
);

alter table test
    add mix   boolean,
    add count int;

alter table user
    add rating int;

create table over_course
(
    id        int     not null primary key auto_increment,
    user_id   int     not null,
    course_id int     not null,
    overing   boolean not null,
    foreign key (user_id) references User (id),
    foreign key (course_id) references Course (id)
);

alter table over_course
    add UNIQUE (user_id, course_id);

alter table over_course
    drop overing;

alter table over_course
    add status_id int,
    add foreign key (status_id) references status (id);

insert into status(id,name)
values (6, 'in process'),
       (7, 'final testing'),
       (8,'over');

alter table coursegrade
add final_test int;

alter table user
add email varchar(255);
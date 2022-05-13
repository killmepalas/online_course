create table user_course(
    user_id int not null,
    course_id int not null,
    foreign key (user_id) references User(id),
    foreign key (course_id) references Course(id),
    unique (user_id,course_id)
);

insert into user_course values (1,1), (1,3),(2,2);
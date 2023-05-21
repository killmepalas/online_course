create table User_course
(
    user_id   int not null,
    course_id int not null,
    foreign key (user_id) references User (id),
    foreign key (course_id) references Course (id) ON DELETE CASCADE,
    unique (user_id, course_id)
);

create table Status
(
    id   int          not null primary key auto_increment,
    name varchar(255) not null
);

alter table User
    add status_id int,
    add foreign key (status_id) references Status (id);

insert into Status (id, name)
values (1, 'active'),
       (2, 'block'),
       (3, 'wait');

create table Category
(
    id   int          not null primary key auto_increment,
    name varchar(255) not null
);

alter table Course
    add category_id int,
    add foreign key (category_id) references Category (id),
    add status_id   int,
    add foreign key (status_id) references Status (id);

create table Topic
(
    id          int          not null primary key auto_increment,
    name        varchar(255) not null,
    description varchar(255) not null,
    course_id   int          not null,
    status_id   int          not null,
    foreign key (course_id) references Course (id),
    foreign key (status_id) references Status (id)
);

alter table Test
    add topic_id int,
    add foreign key (topic_id) references Topic (id);

alter table Test
    add status_id int,
    add foreign key (status_id) references Status (id);

create table lecture
(
    id          int          not null primary key auto_increment,
    name        varchar(255) not null,
    description varchar(255) not null,
    text        varchar(255) not null,
    topic_id    int          not null,
    foreign key (topic_id) references Topic (id)
);

create table TopicGrade
(
    id       int not null primary key auto_increment,
    grade    int not null,
    topic_id int not null,
    user_id  int not null,
    foreign key (topic_id) references Topic (id),
    foreign key (user_id) references User (id)
);

alter table Test
    add mix boolean,
    add count int;


alter table Test
drop column start;

alter table Test
    drop column stop;

create table Over_course
(
    id        int     not null primary key auto_increment,
    user_id   int     not null,
    course_id int     not null,
    overing   boolean not null,
    foreign key (user_id) references User (id),
    foreign key (course_id) references Course (id)
);

alter table Over_course
    add UNIQUE (user_id, course_id);

alter table Over_course
drop
overing;

alter table Over_course
    add status_id int,
    add foreign key (status_id) references Status (id);

insert into Status(id, name)
values (6, 'in process'),
       (7, 'final testing'),
       (8, 'over');

alter table CourseGrade
    add final_test int not null;

alter table User
    add email varchar(255);

create table Career_guidance_test_question
(
    id   int          not null primary key auto_increment,
    text varchar(255) not null
);

create table Career_guidance_test_answer
(
    id          int          not null primary key auto_increment,
    question_id int          not null,
    text        varchar(255) not null,
    category_id int          not null,
    foreign key (category_id) references Category (id),
    foreign key (question_id) references Career_guidance_test_question (id)
);

alter table Category
    add column description varchar(255) not null;

insert into Category(name, description)
values ('Frontend-разработка',
        'Это создание клиентской части сайта. Front-end разработчик занимается версткой шаблона сайта и созданием пользовательского интерфейса. '),
       ('Аналитика',
        'Системный аналитик — профессия на стыке разработки, аналитики, менеджмента. Функция специалиста в IT-компании — анализировать потребности заказчика и формулировать требования к программной системе, которая должна закрыть эти потребности.'),
       ('Маркетинг и продажи',
        'Интернет-маркетологи планируют стратегию онлайн-продвижения, продвигают сайты в поисковых системах, запускают контекстную и таргетированную рекламу, анализируют эффективность рекламных кампаний.'),
       ('Дизайн',
        'Веб-дизайнеры работают над внешним видом сайтов: выбирают, что видит пользователь на экране, в каком порядке и как это будет оформлено. Они могут верстать новые страницы или менять логику на уже существующих.'),
       ('Системное администрирование',
        'Это процесс создания, настройки, управления, обслуживания и проведения других технических и административных мероприятий, направленных на поддержание информационных систем обработки и передачи данных и разграниченного доступа к ним в рабочем состоянии.'),
       ('Тестирование',
        'Тестировщики занимаются тестированием всего продукта в целом или же отдельных компонентов. Тестирование играет важнейшую роль в обеспечении качества продукта.'),
       ('Backend-разработка',
        'Это создание серверной части в веб-приложениях. То есть backend разработчики имеют дело со всем, что относится к базам данных, архитектуре, программной логике — в общем, со всем, что обычный пользователь не видит.');

insert into Career_guidance_test_question(text)
values ('Выберите утверждение, которое вам ближе'),
       ('Больше всего вам нравится работать'),
       ('А как у вас с математикой?'),
       ('Какой hard skill хотите приобрести?'),
       ('Выберите проект'),
       ('Вам необходимо организовать праздник. Какую задачу возьмете на себя?'),
       ('Вы умеете быстро'),
       ('Выберите игру'),
       ('В рамках совместного проекта вам больше нравится'),
       ('Выбирая фильм, вы скорее обратите внимание на');

insert into Career_guidance_test_answer(question_id, text, category_id)
values (1, 'Создавать плавные переходы и интересные анимации в мобильном приложении', 4),
       (1, 'Придумывать новые и нетривиальные способы поиска ошибок', 6),
       (1, 'Программировать сайты так, чтобы на любых устройствах они отображались корректно', 1),
       (1, 'Разносторонне продумывать логику и внутреннюю структуру будущей программы', 7),
       (1, 'Формулировать требования к программной системе', 2),
       (1, 'Осуществлять поиск целевой аудитории и продумывать стратегию онлайн-продвижения приложения', 3),
       (1, 'Обеспечение штатной работы парка компьютерной техники', 5),
       (2, 'С изображениями', 4),
       (2, 'С ошибками, которые нужно исправить', 6),
       (2, 'С графической составляющей приложения', 1),
       (2, 'С математическими формулами', 7),
       (2, 'С архитектурой будущей программной системы', 2),
       (2, 'С людьми', 3),
       (2, 'Со схемами, "железом", техникой', 5),
       (3, 'Ничего не понимаю и понимать не хочу', 4),
       (3, 'Надеюсь, что она не пригодится в моей деятельности', 6),
       (3, 'Плохо разбираюсь, но готов учиться', 1),
       (3, 'Мой уровень выше среднего, я могу оперировать математическими формулами и алгоритмами', 7),
       (3, 'Чуть ниже среднего', 2),
       (3, 'Не умею считать ничего, кроме денег', 3),
       (3, 'У меня есть понимание математических формул и алгоритмов, могу применять их', 5),
       (4, 'Создавать 3D-модели и уметь проектировать', 4),
       (4, 'Умение адеекватно описывать обнаруженные проблемы', 6),
       (4, 'Визуализировать большие массивы данных', 1),
       (4, 'Писать сложный код', 7),
       (4, 'Чётко формулировать мысли и превращать их в текст', 2),
       (4, 'Уметь коммуницировать с различными людьми, искать подход к каждому', 3),
       (4, 'Понимать принципы работы и настройки операционных систем', 5),
       (5, 'Создать 3D-модель здания', 4),
       (5, 'Контроль качества новостного канала', 6),
       (5, 'Разработка удобного интерфейса банковского приложения', 1),
       (5, 'Создать программу, которая преобразовывает чёрно-белый фильм в цветной', 7),
       (5, 'Создание концептуальных схем', 2),
       (5, 'Прогнозирование развития эпидемии', 3),
       (5, 'Защита локальных сетей, инфраструктуры приложения магазина от взлома', 5),
       (6, 'Займусь декором и оформлением пространства', 4),
       (6, 'Проверка соответствия содержимого праздника задумке', 6),
       (6, 'Удобно разложу столовые приборы и расставлю блюда', 1),
       (6, 'Продумаю все развлечения', 7),
       (6, 'Проанализирую все цены в продуктовых магазинах и выберу самые выгодные варианты', 2),
       (6, 'Соберу данные обо всех гостях и их предпочтениях, систематизирую их и составлю список продуктов', 3),
       (6, 'Настройка светомузыки', 5),
       (7, 'Визуализировать идеи', 4),
       (7, 'Всё ломать', 6),
       (7, 'Продумывать навигацию', 1),
       (7, 'Разрабатывать алгоритмы действий', 7),
       (7, 'Формулировать мысли', 2),
       (7, 'Собирать и систематизировать инфомацию', 3),
       (7, 'Настраивать Linux с закрытыми глазами', 5),
       (8, 'Активити', 4),
       (8, 'Морской бой', 6),
       (8, 'Имаджинариум', 1),
       (8, 'Судоку', 7),
       (8, 'Крокодил', 2),
       (8, 'Мафия', 3),
       (8, 'Конструктор лего', 5),
       (9, 'Рисовать, строить различные модели', 4),
       (9, 'Искать ошибки и баги', 6),
       (9, 'Продумывать навигацию', 1),
       (9, 'Писать много сложного кода', 7),
       (9, 'Ставить задачи', 2),
       (9, 'Писать тексты, делать презентации', 3),
       (9, 'Настраивать оборудование', 5),
       (10, 'Локации фильма', 4),
       (10, 'Ляпы', 6),
       (10, 'Цветокоррекцию - главное, чтобы он был приятен визуально', 1),
       (10, 'Наличие спецэффектов и применение современных IT-технологий', 7),
       (10, 'Сюжет', 2),
       (10, 'Рейтинги и отзывы', 3),
       (10, 'Качество изображения и звука', 5);

drop table Attempt;

alter table Course
add column chat varchar(255);

create table Comment(
    id int not null primary key auto_increment,
    text varchar(255) not null,
    topic_id int not null,
    date datetime not null,
    user_id int not null,
    foreign key (user_id) references User(id),
    foreign key (topic_id) references Topic(id)
);
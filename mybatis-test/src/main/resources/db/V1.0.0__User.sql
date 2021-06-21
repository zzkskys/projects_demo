create table user
(
    id          varchar(36) primary key not null,
    name        varchar(10),
    create_time datetime
);

insert into user(id, name, create_time)
values ('1', '张三', '2021-06-21');
insert into user(id, name, create_time)
values ('2', '李四', '2021-06-23');
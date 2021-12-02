create table if not exists user
(
    id          varchar(36) primary key not null,
    create_time datetime                not null default now(),
    name        varchar(10)             not null default ''
);

create table if not exists role
(
    id   varchar(36) primary key not null,
    name varchar(20)             null default null
);
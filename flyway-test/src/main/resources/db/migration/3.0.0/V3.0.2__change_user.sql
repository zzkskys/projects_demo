-- 增加 id_card 字段
alter table user
    add column id_card varchar(255) not null default '';


alter table user
    add column dtype varchar(255) null;

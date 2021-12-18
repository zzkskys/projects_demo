create table if not exists contact
(
    phone   varchar(20) not null,
    email   varchar(20) null,
    user_id varchar(40) null
)
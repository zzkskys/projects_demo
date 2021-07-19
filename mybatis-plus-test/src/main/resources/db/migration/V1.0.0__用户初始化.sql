CREATE TABLE user
(
    id          BIGINT(20)  NOT NULL COMMENT '主键ID' primary key,
    name        VARCHAR(30) NULL     DEFAULT NULL COMMENT '姓名',
    age         INT(11)     NULL     DEFAULT NULL COMMENT '年龄',
    email       VARCHAR(50) NULL     DEFAULT NULL COMMENT '邮箱',
    role        int(2)      not null default 1 comment '角色',
    contact     json        null     default null comment '联系方式',

    create_time datetime    null comment '创建时间',
    update_time datetime    null comment '更新时间',
    version     int(8)      not null default 0 comment '乐观锁',
    deleted     bit(1)      not null default false comment '逻辑删除'
);
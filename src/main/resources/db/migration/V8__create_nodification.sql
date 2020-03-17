create table notification
(
    id bigint auto_increment primary key,
    notifier bigint not null,
    receiver bigint not null,
    outerId bigint not null comment '外键id',
    type int not null,
    gmt_create bigint not null,
    status int default 0 not null comment '0：未读，1：已读',
    notifier_name varchar(255) null ,
    outer_title varchar(255) null
)
    comment '消息通知表';
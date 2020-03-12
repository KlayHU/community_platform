create table comment
(
    id BIGINT auto_increment primary key,
    parent_id BIGINT not null comment '父类ID',
    type int not null comment '父类类型',
    comment_author int not null comment '评论人ID',
    column_5 int null,
    gmt_create BIGINT not null,
    gmt_modify BIGINT not null,
    follow_count BIGINT default 0 null comment '点赞数
'
);


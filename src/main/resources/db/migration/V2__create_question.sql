-- auto-generated definition
create table question
(
    id            int auto_increment
        primary key,
    title         varchar(255)  null,
    description   text          null,
    gmt_create    bigint        null,
    gmt_modify    bigint        null,
    creator       int           null,
    comment_count int default 0 null,
    view_count    int default 0 null,
    follow_count  int default 0 null,
    tag          varchar(255)  null
);


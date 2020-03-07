-- auto-generated definition
create table user
(
    id         int auto_increment
        primary key,
    account_id varchar(255)              null,
    name       varchar(255) charset utf8 null,
    token      varchar(255)              null,
    gmt_create bigint                    null,
    gmt_modify bigint                    null,
    bio        varchar(255)              null,
    avatar_url varchar (255)             null
)
    charset = latin1;

create table ads
(
    id int auto_increment primary key,
    title varchar(255) DEFAULT null,
    url varchar(512)DEFAULT null,
    image varchar(255) DEFAULT null,
    gmt_start bigint,
    gmt_end bigint,
    gmt_create bigint,
    gmt_modify bigint,
    status int null,
    local_area varchar(20) not null
)
    comment '接入广告表';

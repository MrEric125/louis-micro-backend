create table backend_todo_list
(
    id bigint auto_increment
        primary key,
    todo_title varchar(50) not null comment '待办列表标题',
    todo_description varchar(255) null comment '待办事件描述',
    todo_status tinyint default 1 not null comment '待办事件状态',
    created_by varchar(50) not null comment '创建者',
    created_date timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    modified_by varchar(50) not null comment '修改人',
    modified_date timestamp default CURRENT_TIMESTAMP not null comment '修改时间',
    KEY backend_todo_list_todo_title_index(todo_title)
)  ENGINE=InnoDB  comment '待办列表';


-- auto-generated definition
create table sys_login_info
(
    id                  bigint auto_increment primary key,
    ip                  varchar(255) null comment 'ip地址',
    last_login          datetime     null comment '最近登录时间',
    user_id             bigint       null comment '用户id',
    username            varchar(255) null comment '用户名',
    os                  varchar(255) null comment '操作系统',
    request_url         varchar(255) null comment '请求地址',
    last_login_location varchar(255) null comment '用户归属地',
    browser             varchar(255) null comment '使用的是哪个浏览器',
    KEY sys_login_info_user_tm (user_id, last_login)

) comment '用户登录统计' ENGINE=InnoDB ;
CREATE SCHEMA jarvis;

use jarvis;

create table `role`
(
    role_id bigint primary key not null auto_increment,
    role varchar(255) default '',
    role_status varchar(32) default 'ACTIVE',
    description text null,
    created_at datetime null,
    created_by int null,
    updated_at datetime null,
    updated_by int null
);

create table `user`
(
	user_id bigint primary key not null auto_increment,
    username varchar(30) not null,
    full_name nvarchar(50) default '',
    age int default 0,
    email varchar(255) default '',
    fcm_token text null,
    created_at datetime null,
    created_by bigint null,
    updated_at datetime null,
    updated_by bigint null,
    fk_role_id bigint null,
    foreign key(fk_role_id) references role(role_id)
);

create table tag
(
	tag_id bigint primary key not null auto_increment,
    title nvarchar(255) default '',
    link varchar(9999) default '',
    description text null,
    created_at datetime null,
    created_by bigint null,
    updated_at datetime null,
    updated_by bigint null,
    fk_user_id bigint null,
    foreign key(fk_user_id) references user(user_id)
);

create table note
(
	note_id bigint primary key not null auto_increment,
    title nvarchar(255) default '',
    body nvarchar(9999) default '',
    note_type varchar(32) default 'NORMAL',
    description text null,
    created_at datetime null,
    created_by bigint null,
    updated_at datetime null,
    updated_by bigint null,
    fk_user_id bigint null,
    foreign key(fk_user_id) references user(user_id)
);

create table event
(
	event_id bigint primary key not null auto_increment,
    name nvarchar(9999) default '',
    day_alert int null,
    month_alert int null,
    description text null,
    created_at datetime null,
    created_by bigint null,
    updated_at datetime null,
    updated_by bigint null,
    fk_user_id bigint null,
    foreign key(fk_user_id) references user(user_id)
);






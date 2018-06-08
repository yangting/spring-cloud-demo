create database if not exists demo default character set utf8;

drop table if exists ex_user;
create table if not exists ex_user (
id int not null AUTO_INCREMENT comment '用户名',
username varchar(32) not null,
nickname varchar(32) not null,
age tinyint(3) not null,
balance decimal(10,2) not null,
primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into ex_user values(1,'account1','zhao',20,100.00);
insert into ex_user values(2,'account2','wang',28,180.00);
insert into ex_user values(3,'account3','zhang',25,120.00);
insert into ex_user values(4,'account4','li',18,20.00);


select * from ex_user;

drop table if exists movie_info;
CREATE TABLE `movie_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_name` varchar(32) NOT NULL,
  `ticket_price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

insert into movie_info values(1,'复仇者3',60.00);
insert into movie_info values(2,'漫威电影全',180.00);

select * from movie_info;


drop table if exists order_info;
create table `order_info` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `user_id` int(11) not null comment 'fk -> ex_user.pk',
    `user_name` varchar(32) not null comment 'ex_user.username',
    `movie_id` int(11) not null comment 'fk -> movie_info.pk',
    `movie_name` varchar(32) not null comment 'movie_info.movie_name',
    `lock_seats` varchar(32) not null comment 'lock seats',
    `ticket_nums` tinyint(2) not null comment 'ticket nums',
    `ticket_price` decimal(10,2) not null comment 'movie_info.ticket_price',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


drop table if exists order_flow;
create table `order_flow` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `order_id` int(11) not null comment 'fk > order_info.pk',
    `order_status` tinyint not null comment 'current status',
    `order_chain` tinyint not null default 0 comment 'order chain',
    `create_time` datetime not null,
    PRIMARY KEY (`id`)
 )ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

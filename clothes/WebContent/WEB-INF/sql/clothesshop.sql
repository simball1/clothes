create table manager(
 managerId varchar(50) not null primary key,
 managerPasswd varchar(60) not null
);

insert into manager(managerId,managerPasswd)
values('clothesmaster@shop.com','123456');

create table clothes(
  clothes_id int not null primary key auto_increment,
  clothes_kind varchar(3) not null,
  clothes_title varchar(100) not null,
  clothes_price int not null,
  clothes_count smallint not null,
  clothes_size varchar(3) not null,
  clothes_image varchar(16) default 'nothing.jpg',
  clothes_content text not null,
  reg_date datetime not null
);

create table member(
  id varchar(50) not null primary key,
  passwd varchar(60) not null,
  name varchar(10) not null,
  reg_date datetime not null,
  address varchar(100) not null,
  tel varchar(20) not null
);

create table qna(
  qna_id int not null primary key auto_increment,
  clothes_id int not null,
  clothes_title varchar(100) not null,
  qna_writer varchar(50) not null,
  qna_content text not null,
  group_id int not null,
  qora tinyint not null,
  reply tinyint default 0,
  reg_date datetime not null
);

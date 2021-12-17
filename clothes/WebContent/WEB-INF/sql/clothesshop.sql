/*	clothesshop 데이터베이스
 * 
 * 데이터베이스 아이디: clothesid
 * 데이터베이스 암호 : clothespwd
 * 
 */


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
  tel varchar(20) not null,
  birth varchar(8) not null
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

create table board(
	num int not null primary key auto_increment,
	writer varchar(50) not null,
	subject varchar(50) not null,
	content text not null,
	passwd varchar(60) not null,
	reg_date datetime not null,
	readcount int default 0,
	ref int not null,
	re_step smallint not null,
	re_level smallint not null
);

create table cart(
  cart_id int not null primary key auto_increment,
  buyer varchar(50) not null,
  clothes_id int not null,
  clothes_title varchar(100) not null,
  buy_price int not null,
  buy_count tinyint not null,
  clothes_size varchar(3) not null,
  clothes_image varchar(16) default 'nothing.jpg'
); 

create table buy(
  buy_id bigint not null,
  buyer varchar(50) not null,
  clothes_id varchar(12) not null,
  clothes_title varchar(100) not null,
  buy_price int not null,
  buy_count tinyint not null,
  clothes_size varchar(3) not null,
  clothes_image varchar(16) default 'nothing.jpg',
  buy_date datetime not null,
  deliveryName varchar(10) not null,
  deliveryTel varchar(20) not null,
  deliveryAddress varchar(100) not null
);


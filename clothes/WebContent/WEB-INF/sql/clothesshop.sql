create table manager(
 managerId varchar(50) not null primary key,
 managerPasswd varchar(60) not null
);

insert into manager(managerId,managerPasswd)
values('clothesmaster@shop.com','123456');
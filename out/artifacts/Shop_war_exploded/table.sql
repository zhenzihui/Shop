
# mysql数据库

CREATE database shop;
use shop;

create TABLE cart(id int PRIMARY KEY auto_increment,
userid int,pid int);


CREATE TABLE history(id int PRIMARY key auto_increment,uid int,pid int,see_at datetime  DEFAULT now());
CREATE TABLE products(id int primary key auto_increment,name VARCHAR(20),city varchar(10),number int,price FLOAT ,img text);

create table users(id int primary key auto_increment,name VARCHAR(50),password text,role text);
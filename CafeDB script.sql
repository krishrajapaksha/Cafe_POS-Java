create database Cafe;
use Cafe;

create table foods(
fdID int auto_increment primary key,
fdName varchar (100),
fdPrice double
);

create table beverage(
bvID int auto_increment primary key,
bvName varchar (100),
bvPrice double
);

create table custbills(
billNo int auto_increment primary key,
date varchar (100),
items varchar (255),
amount double
);

create table user(
username varchar (100) primary key,
fName  varchar (100),
lName  varchar (100),
password varchar (100)
);

INSERT into beverage(bvName,bvPrice) values
("Milo",250.00),
("Nescafe",250.00),
("Orange Juice",250.00),
("Iced Coffee",450.00),
("Blue Mojito",400.00),
("Black Mojito",400.00),
("Black Currant Mojito",450.00),
("Chocolate Milk Shake",450.00);

INSERT into foods(fdName,fdPrice) values 
("Chicken Hot Dog",300.00),
("Egg Wrapped Chicken Hot Dog",400.00),
("Monster Double Hot Dog",450.00),
("Cheesy Chicken Hot Dog",500.00),
("Vegetable Burger",300.00),
("Cheesy Vegetable Burger",400.00),
("Spicy Chicken Burger",500.00),
("Cheesy Crispy Chicken Burger",650.00),
("Vegetable Submarine",300.00),
("Spicy Submarine",400.00),
("Cheesy Crispy Chicken Submarine",600.00);

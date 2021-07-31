create database bdpoo;

use bdpoo;


create table pecas(
id int primary key not null auto_increment,
codigo varchar(50) not null unique,
nome varchar(50) not null,
quantidade double not null,
valor double not null, 
desconto double
);

create table geladeira(
id int primary key not null auto_increment,
marca varchar(50) not null,
modelo varchar(50) not null unique,
quantidade double not null,
valor double not null,
tipodegelo varchar(50) not null,
desconto double
);

describe geladeira;

create table maquinalavar(
id int primary key not null auto_increment,
marca varchar(50) not null,
modelo varchar(50) not null unique,
quantidade double not null,
valor double not null,
kgs double not null,
desconto double
);

describe maquinalavar;

create table fogao(
id int primary key not null auto_increment,
marca varchar(50) not null,
modelo varchar(50) not null unique,
quantidade double not null,
valor double not null,
qtdbocas int not null,
desconto double
);



select * from pecas



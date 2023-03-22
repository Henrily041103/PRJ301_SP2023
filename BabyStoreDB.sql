if exists (select * from sysdatabases where name='BabyStore')
begin
  raiserror('Dropping existing BabyStore database ....',0,1)
  DROP database BabyStore
end
GO

CHECKPOINT
go

raiserror('Creating BabyStore database....',0,1)
go
   CREATE DATABASE BabyStore
GO

/*Creating table*/
raiserror('Creating Table Product....',0,1)
create table Product

(
	ProId nvarchar(4) primary key not null,
	ProBrand nvarchar(10) not null,
	ProType nvarchar(20) not null,
	Price float not null,
	Sale float,
	Stock float,
	AgeGroup nvarchar(10),
	Size nvarchar(10),
	Color nvarchar(10),
)
go

raiserror('Creating Table User....',0,1)
create table tblUser

(
	UserID nvarchar(10) primary key,
	username nvarchar(20) not null,
	password char(60) not null,
	role nvarchar(2) not null,
)
go

/*
raiserror('Creating Table Order....',0,1)
create table ProOrder

(
	OrderId int primary key,
	OrderDate date,
	UserId char,
)
go

raiserror('Creating Table Detail....',0,1)
create table OrderDetail

(
	OrderId int,
	ProductId nvarchar(4) not null,
	Amount float not null,
	foreign key(OrderId) references ProOrder(OrderId),
	primary key(OrderId),
)
go
*/
drop table Product
drop table tblUser
drop table ProOrder
/*****************************************************/
-- Populating data into the tables
/********************/
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

raiserror('Creating Table Cart....',0,1)
create table Cart

(
	CartId char primary key not null,
	Account varchar not null,
	ProId varchar(4),
	Amount int,
	foreign key(ProId) references Product(ProId),
)
go

raiserror('Creating Table Order....',0,1)
create table ProOrder

(
	CartId char,
	OrderDate date,
	foreign key(CartId) references Cart(CartId),
	primary key(CartId),
)
go

raiserror('Creating Table User....',0,1)
create table ProUser

(
	UserId char primary key,
	Username varchar not null,
	UserPassword char(60) not null,
	ProUser varchar not null,
)
go

drop table Product
drop table Cart
drop table ProUser
drop table ProOrder
/*****************************************************/
-- Populating data into the tables
/********************/
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
	ProId char primary key not null,
	ProBrand varchar not null,
	ProType varchar not null,
	Sale money not null,
	Stock int,
	AgeGroup varchar,
	Size varchar(1),
	Color varchar,
	Picture image,
)
go

raiserror('Creating Table Cart....',0,1)
create table Cart
(
	CartId char primary key not null,
	Account varchar not null,
	ProId char,
	Amount int not null,
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

/*****************************************************/
-- Populating data into the tables
/********************/
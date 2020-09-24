#Single line comment
/*
multiline comment
	SEE
*/

#create database Borchelt_Test_Server;
#create database inventoryFile;
#use Borchelt_Test_Server;
#use inventoryFile;
/*create table NT(
	accuracy int,
    gpsAltitude int,
    latitude Decimal(9,6),
    longitude Decimal(8,6),
    pressure int,
    locationName varchar(20)
);

drop table NT;
truncate table NT;
*/

#desc NT;

#select * from NT;

#insert into NT (accuracy,gpsAltitude,latitude,Longitude,pressure,locationName)
#		values (3,104534,37.4589,-87.9834,103478,"school");
drop table invTbl;
create table invTbl(
	id1 int,
    snack Text,
    quanity int,
    cost Decimal(5,2),
    url1 Text,
    foodType char
);

#insert into invTbl(id1,snack,quantity,cost,url1,foodType);

desc invTbl
select * invTbl
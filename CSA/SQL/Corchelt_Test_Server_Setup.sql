#Single line comment
/*
multiline comment
	SEE
*/

#create database Borchelt_Test_Server;

use Borchelt_Test_Server;

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

desc NT;

select * from NT;

insert into NT (accuracy,gpsAltitude,latitude,Longitude,pressure,locationName)
		values (3,104534,37.4589,-87.9834,103478,"school");

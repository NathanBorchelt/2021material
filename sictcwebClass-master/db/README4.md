
# Putting it all together

## Table of Contents
 - Overview
 - Systems Analysis
 - Identify Tables
 - Identify Fields - Data Types
 - Entity Diagram
 - Create Accounts Table
 - Verify Accounts Table
 - Insert Account Record
 - Create Users Table
 - Insert User Record
 - Verify User Record
 - Challenge
 - Tips
 - Shortcuts Cheatsheet

## Overview
In this tutorial we will begin creating relating tables for our Internet of Things (IoT) application. As always, the scripts used in this tutorial can be used to create other querys by simply modifing the syntax.

## Systems Analysis
The first step to designing our database is to ask what are the data requirements of our application? Conducting a thorough ```Systems Analysis Design```, or ```(SA)``` for short, identifies all entities and attributes which identify our tables and columns. The ```(SA)``` reveals the relationship between tables commonly known as an ```Entity Relationship Diagram (ERD)```. To keep this tutorial simple we will use bulleted lists and tables to illustrate. Since we are creating an IoT tracking system we need to store information about each IoT device. During the ```(SA)``` we discovered the need to track the following items: Accounts, Users, Iot, Groups. We also discovered that each IoT device can belong to multiple groups which changes the IoT to Groups relationship from a ```1-1``` to a ```1-N``` relationship. This requirement is solved by adding table ```IoTGroups``` to house this relationship. Next we identify the attributes we want to track about each entity/table. This gives us a simple entity relationship diagram listed below.

## Identify Tables
- Accounts - store account level information for the assets. This will allow us to bring on more than one organization to use the system.
- Users - store the User login information. The Users belong to an Account.
- IoT - store the device information along with current location. IoT devices belong to an Account.
- Groups - store logical or geographical grouping informtion about an IoT(s). IoT(s) can be assigned to multiple groups. Think of departments within a company. A maintenance company may have Groups for: Electrical, Plumbing, HVAC, etc.
 - IoTGroups - store the relationship(s) between IoT and Group. IoT(s) can be assigned to multiple Groups thereby defining a ```1``` to ```N``` relationship between IoT(s) and Groups.

## Identify Fields - Data Types
The majority of this system is going to be managed by a few simple data types. Our ```Id``` fields will be of type integer and will ``` AUTO_INCREMENT```. The strings are of type ```VARCHAR(n)``` with ```n``` defining max number of characters. Dates are stored in ```DATETIME``` format and capture ```Universal Time Coordinated (UTC)```. The application calculates time based upon the user's local timezone on their computer or device.

## Entity Diagram
```console
Tables:
Accounts
 - Id (int)
 - Name (string)
 - Address (string)
 - City (string)
 - State (string)
 - Zip (string)
 - Phone (string)
 - LastUpdate
 - CreatedDate

Users
 - Id
 - AccountId
 - Email
 - LastName
 - FirstName
 - Password
 - LastLogin
 - LastUpdate
 - CreatedDate

IoT
 - Id
 - AccountId
 - Name
 - TypeId
 - Lat
 - Lng
 - LastUpdate
 - CreatedDate

 Groups
  - Id
  - AccountId
  - Name
  - CreatedDate
  - LastUpdate

 IoTGroups
  - Id
  - IoTId
  - GroupId
  - CreatedDate
```

## Create Database
Ok, we need to issue the following commands to get the database created and assigned to our user. This topic was covered previously so I will just be listing the commands in sequential order.

- Logout of MySQL ```exit;```
- Login as root ```sudo mysql -u root -p```
- Create Database ```CREATE DATABASE iot;```
- Grant Privileges ```GRANT ALL PRIVILEGES ON iot.* TO 'sictc'@'localhost';```
- Flush Privileges ```FLUSH PRIVILEGES```
- Logout ```exit;```
- Login with sictc ```sudo mysql -u sictc -p```
- Show Databases (optional) ```SHOW DATABASES```
- Select Database ```USE iot;```

## Create Accounts Table
```console
CREATE TABLE Accounts(
    Id int NOT NULL AUTO_INCREMENT, 
    Name varchar(50),
    Address varchar(50),
    City varchar(50),
    StateId int,
    Zip varchar(10),
    LastUpdate timestamp DEFAULT CURRENT_TIMESTAMP,
    CreatedDate timestamp DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (Id)
);
```

## Verify Accounts Table
Now that we created the new table we can verify its existence by issuing the ```SHOW TABLES;``` command. After comfirming the existence we will ensure all fields attributes were created successfully by issuing the ```DESCRIBE Accounts``` command.
```console
SHOW TABLES;
```
Query Results:
```console
+------------------+
| Tables_in_iot    |
+------------------+
| Accounts         |
+------------------+
1 row in set (0.001 sec)
```

```console
DESCRIBE Accounts;
```

Query Results:
```console
+-------------+-------------+------+-----+---------------------+----------------+
| Field       | Type        | Null | Key | Default             | Extra          |
+-------------+-------------+------+-----+---------------------+----------------+
| Id          | int(11)     | NO   | PRI | NULL                | auto_increment |
| Name        | varchar(50) | YES  |     | NULL                |                |
| Address     | varchar(50) | YES  |     | NULL                |                |
| City        | varchar(50) | YES  |     | NULL                |                |
| StateId     | int(11)     | YES  |     | NULL                |                |
| Zip         | varchar(10) | YES  |     | NULL                |                |
| LastUpdate  | timestamp   | NO   |     | current_timestamp() |                |
| CreatedDate | timestamp   | NO   |     | current_timestamp() |                |
+-------------+-------------+------+-----+---------------------+----------------+
7 rows in set (0.006 sec)
```

## Insert Account Record
Now that we verified the existence of our new ```Accounts``` table we can add new records via the ```INSERT``` command. For this example I will add two accounts.

```console
INSERT INTO Accounts (Name, Address, City, StateId, Zip) VALUES ('SICTC', '1901 Lynch Rd', 'Evansville', 1, '47711');
```

Query Results:
```console
Query OK, 1 row affected (0.010 sec)
```

## Verify Account Record
```console
SELECT * FROM Accounts;
```

Query Results:
```console
+----+-------+---------------+------------+---------+-------+---------------------+---------------------+
| Id | Name  | Address       | City       | StateId | Zip   | LastUpdate          | CreatedDate         |
+----+-------+---------------+------------+---------+-------+---------------------+---------------------+
|  1 | SICTC | 1901 Lynch Rd | Evansville |       1 | 47711 | 2020-09-28 17:04:08 | 2020-09-28 17:04:08 |
+----+-------+---------------+------------+---------+-------+---------------------+---------------------+
1 row in set (0.001 sec)
```



## Create Users Table
Before we cut loose and build out the remaining tables I want to walk through creating the ```Users``` table. In particular, we have a column that requires special attention when designing. You never want to store a password in plane text format.  This example stores the ```Password``` field as a ```Secure Hash Algorithm (SHA)``` checksum. MySQL provides a ```SHA1()``` function to hash values into 160 bit checksums. MySQL's ```SHA1()``` function returns these values in ```hexadecimal tuple``` format.  To calculate the amount of storage of the ```SHA``` we use the following equation: ```160 / 8 * 2 = 40```. The result tells us we need 40 characters of storage.  Since ```SHA1()``` consistently returns 160 bit values our ```Password``` field will have a datatype of ```CHAR(40)``` to store the ```salted``` password. 

```console
CREATE TABLE Users(
    Id int NOT NULL AUTO_INCREMENT, 
    AccountId int NOT NULL,
    Email varchar(200) NOT NULL,
    LastName varchar(50),
    Firstname varchar(50),
    Password CHAR(40),
    LastLogin timestamp NULL,
    LastUpdate timestamp NULL, 
    CreatedDate timestamp DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (Id),
    UNIQUE KEY (Email)
);
```

## Insert User Record
```console
INSERT INTO Users (AccountId, Email, LastName, FirstName, Password) VALUES(1, 'johnc@sictc.edu', 'Cobb', 'John', SHA1('Pencil1'));
```

## Verify User Record

```console
SELECT * FROM Users;
```

Query Results:
```console
+----+-----------+-----------------+----------+-----------+------------------------------------------+-----------+------------+---------------------+
| Id | AccountId | Email           | LastName | Firstname | Password                                 | LastLogin | LastUpdate | CreatedDate         |
+----+-----------+-----------------+----------+-----------+------------------------------------------+-----------+------------+---------------------+
|  1 |         1 | johnc@sictc.edu | Cobb     | John      | 9e0259a2ef6cf6399c9a9a78072f1d79ce7f2f16 | NULL      | NULL       | 2020-09-28 19:32:43 |
+----+-----------+-----------------+----------+-----------+------------------------------------------+-----------+------------+---------------------+
1 row in set (0.000 sec)
```

Note the ```Password``` field is scrambled. This prevents unwated password snooping by database admins. There are other more secure authentication schemes but this will get you started.


There you have it, we successfully created the Accounts table and inserted our first record. Moving forward, other tutorials require you to create the remaining tables for our application. Hint, our (ERD) is the key to building the remaining tables. 

## Challenges:
### Challenge 1
Our first challenge is to create the lookup table for ```States``` for use with the ```StateId``` column in the ```Accounts``` table. You will also want to update your (ERD) to reflect this change. This is an example of adapting the design on the fly as you discover omissions or enhancements to the design. 

### Challenge 2
The next challenge is to change the ```Password``` field from ```SHA1 160 bit``` salt to ```SHA2 512 bit```. This will require a change to the ```SHA``` function and the ```CHAR(n)``` data type.


## Tips:
### Altering Fields:
While creating this tutorial I accidently pasted an incorrect datatype for the Address and City fields. These fields were created with ```varchar(2)``` data types. This limits the number of characters allowed in the fields to just two.  Not enough to capture the full Address and City. Instead of dropping the table, I was able to issue the ```ALTER TABLE``` command to change each field's data type. See example below:

```console
ALTER TABLE Accounts MODIFY Address varchar(50);
ALTER TABLE Accounts MODIFY City varchar(50);
```

### Time Stamp Fields;
When updating records it is a good idea to log the last time the record was udated. We typically record this in our ```LastUpdate``` column. When designing your ```UPDATE``` statesments you can use the builtin ```NOW()``` function to date/time stamp this field. See the example below:

```console
UPDATE Accounts SET StateId = 2, LastUpdate = NOW();
```

Before moving on to the next section be sure to logoff of MySQL (mariadb).
```console
quit;
```

## Shortcuts Cheatsheet
 * ```ssh pi@10.0.0.1``` Login to Raspberry Pi.
 * ```raspberry``` Raspbery Pi default password.
 * ```whoami``` Linux currnent logged in user.
 * ```sudo``` Super user do.
 * ```sudo mysql -u sictc -p``` Login to MySQL console.
 * ```quit;``` or ```CTRL+D``` Exit MySQL console.
 * ```exampledb``` MySQL example database.
 * ```Password1``` MySQL root password.
 * ```sictc``` MySQL exampledb user.
 * ```Pencil1``` MySQL exampledb password.
 * ```SELECT CURRENT_USER();``` MySQL current logged in user.
 * ```SHOW DATABASES;``` MySQL list all databases.
 * ```USE sictc;``` MySQL use or choose a database.
 * ```DESCRIBE Contacts;``` MySQL describe a table.

## References
 - https://www.w3schools.com/sql
 - https://www.howtogeek.com/410442/how-to-display-the-date-and-time-in-the-linux-terminal-and-use-it-in-bash-scripts/
 - https://phoenixnap.com/kb/change-mysql-time-zone
 - https://www.geeksforgeeks.org/mysql-sha1-function/



## [Table of Contents](../README.md)
#  Tables, Querys, and SQL.

## Table of Contents
 - Overview
 - Structured Query Language (SQL)
 - Creating Tables
 - Inserting Records
 - Querying Records
 - Updating Records
 - Deleting Records
 - Altering Tables
 - Challenge
 - A Word on Optimization
 - Shortcuts Cheatsheet

## Overview
In this tutorial we will begin creating tables from the MySQL console. The script used in this tutorial can be used to create other tables by simply modifing the table, field names, and datatypes.

## Structured Query Language (SQL)
Going into the depths of SQL is beyond the scope of this tutorial but I will list some basic SQL commands below. This will get you started on your journey of becoming a great database designer or administrator.

### Table Operations
```console
SHOW
DESCRIBE
CREATE
ALTER
DROP
```
### Query Operations
Query operations are SQL comands issued agains a SQL database to Create Read Update Delete records in a particular table. As the acronym spells out these are known as CRUD operations.
```console
INSERT
SELECT 
UPDATE
DELETE
```

## Creating Tables
First things first, login to your Raspberry Pi as in the previous tutorial and connect to the MySQL console. Hint: ```mysql -u sictc -p``` will get you logged in. List the databases using the ```SHOW DATABASES;``` to ensure our ```exampledb``` is present. Once confirmed, connect to the ```exampledb``` database by issuing ```use exampledb;``` from the MySQL console. This will switch the selected database to ```exampledb```.

### Pro Tip:
If you forget who you logged in as in MySQL, issue the ```SELECT CURRENT_USER();``` command. MySQL responds with the currently logged in user. 

Let's start by creating our first table for our contacts database. The first thing we're going to need is a table to store the contacts. From the MySQL console copy and paste the ```CREATE TABLE``` command below.
```console
CREATE TABLE Contacts(
    Id int, 
    LastName varchar(100),
    FirstName varchar(100)
);
```

When the command completes we are notified with a successful response. You should see something to the effect:
```console
Query OK, 0 rows affected (0.115 sec)
```

Next we are going to make sure the table exists by issuing the ```DESCRIBE``` command. this will return the structure of the table including field names, data types, as well as other information beyond the scope of this tutorial.

```console
DESCRIBE Contacts;
```
Below are the results of our ```DESCRIBE``` command. We can see that each field and data type are the same compared to the ```CREATE TABLE``` command we issued previously.
```console
+-----------+--------------+------+-----+---------+-------+
| Field     | Type         | Null | Key | Default | Extra |
+-----------+--------------+------+-----+---------+-------+
| Id        | int(11)      | YES  |     | NULL    |       |
| LastName  | varchar(100) | YES  |     | NULL    |       |
| FirstName | varchar(100) | YES  |     | NULL    |       |
+-----------+--------------+------+-----+---------+-------+
3 rows in set (0.005 sec)
```

## Inserting Records
TODO:
Now that we have our table ready we can insert some example records. In this case we will be adding two contacts, Jane Doe and Max Payne.
```console
INSERT INTO Contacts VALUES(1, 'Doe', 'Jane');
INSERT INTO Contacts VALUES(2, 'Payne', 'Max');
```
Barring any errors you should see something similar to the message listed below for each command:
```console
Query OK, 1 row affected (0.069 sec)
```

## Querying Records
Now for the fun part. Let's query all of the records in our new table. There should only be one.
```console
SELECT Id, LastName, FirstName FROM Contacts;
```
Query Results:
```console
+------+----------+-----------+
| Id   | LastName | FirstName |
+------+----------+-----------+
|    1 | Doe      | Jane      |
|    2 | Payne    | Max       |
+------+----------+-----------+
2 rows in set (0.001 sec)
```

## Updating Records
```console
UPDATE Contacts SET FirstName = 'Sweet', LastName='Caroline' WHERE Id = 1;
```
Query Results:
```console
Query OK, 1 row affected (0.010 sec)
Rows matched: 1  Changed: 1  Warnings: 0
```

## Deleting Records
There are times when your application will need to delete records from the database. The ```DELETE``` command allows us to do just that. Cascading deletes and orphaning records is beyond the scope of this tutorial but I highly recommend researching these topics before taking your system to production.

```console
DELETE FROM Contacts WHERE Id = 2;
```
Query Results:
```console
Query OK, 1 row affected (0.009 sec)
```
Now if we query the records in the database we can see Max Payne has been removed and Sweet Caroline is all alone in the Contacts table.

## Altering Tables
There will be times when your database tables require changes to columns. The ```ALTER``` command allows you to add/remove or make changes to existing columns. The following command will add the new Email column to the Contacts table. Once the command completes you will be notified of the number of rows set.

```console
ALTER TABLE Contacts ADD Email varchar(255);
```
Query Results:
```console
Query OK, 0 rows affected (0.029 sec)
Records: 0  Duplicates: 0  Warnings: 0
```

## Challenge
For an extra challenge use the ```UPDATE``` example above to add an email address to each user. If you forgot the ```Id``` of the Contact reference the ```SELECT``` example above. 

## A Word on Optimization
The rabbit hole of normalization and optimization burrows to infinity. Many times just the right level is all that is needed. In many cases certain optimizations made can begin to create bottlenecks in other areas of performance.  I leave you with these words. Build the system you need today, optimize for today, forecast based on what you know today, adapt the system based on what you learned up to today, rinse... repeat.

## Summary
By now you are starting build up some basic ```SQL``` skills.  There are many purpouse driven simplifications that are being made in this tutorial so that you experience the entire build up of skills. If I were to teach you all of the shortcuts you would lack the fundamental understanding and appreciation of why the shortcuts exist.  

## Shortcuts Cheatsheet
 * ```ssh pi@10.0.0.1``` Login to Raspberry Pi.
 * ```raspberry``` Raspbery Pi default password.
 * ```whoami``` Linux current logged in user.
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

### References
 - https://www.w3schools.com/sql

## Continue to [Part 3](README3.md)

#  Working with Relations

## Table of Contents
 - Overview
 - Normalization
 - Coffee Break
 - Relating Tables and Foreign Keys
 - Joins
 - Challenge
 - Shortcuts Cheatsheet

## Overview
In this tutorial we will begin creating relating tables from the MySQL console. Storing all of the information in one table is not optimal and can repeat a lot of data thus taking up more storage space. Normalizing the data into a relational structures reduces the amount of table space taken up by records. As always, the scripts used in this tutorial can be used to create other querys by simply modifing the syntax.

## Normalization
Our Contacts database needs three additional fields City, State, and Zip. City and Zip are pretty straight forward but the State column can be stored in a relating table. Furthermore, if there are additional tables that require a state we already have States table there for our convenience.

Below we will use the ```CREATE``` and ```ALTER``` commands to achieve our goal. We will then followup with an update command to populate the the newly created fields. Take note of the additional features of the ```CREATE``` command. We are including an ```AUTO_INCREMENT``` field for ```Id```. This allows us to just create the records as we need them and allow the database to take care of the numbering. Another interesting field attribute is the ```PRIMARY KEY``` meaning the Id field is constrained to always provide a unique value to represent the record. The ```UNIQUE KEY``` constraint placed on the```State``` column prevents duplicate values. These are all very convenient features of Relational Database Management Sytems (RDBMS).

```console
CREATE TABLE States(
    Id int NOT NULL AUTO_INCREMENT, 
    State varchar(2),
    PRIMARY KEY (Id),
    UNIQUE KEY (State)
);
```

Query Results:
```console
Query OK, 0 rows affected (0.183 sec)
```

Another handy command to have around is ```SHOW TABLES```. This will list all tables residing in a database.

```console
SHOW TABLES;
```

Query Results:
```console
+---------------------+
| Tables_in_exampledb |
+---------------------+
| Contacts            |
| States              |
+---------------------+
2 rows in set (0.001 sec)
```

Now that we verified the existence of our new ```States``` table we can add new records via the ```INSERT``` command. For this example I would like to add Indiana and Ohio. You will notice a subtle difference between the previous ```INSERT``` commands and this one. This command includes a field listing at the beginning. Since the ```Id``` column is an ```AUTO_INCREMENT``` field we can ommit the parameter from the ```INSERT``` command. Anytime you are passing in fewer values than what the ```TABLE``` has columns you need to explicitly pass in the fields you want to update. The ```VALUES``` need to be in the same order as the field list.

```console
INSERT INTO States (State) VALUES ('IN');
INSERT INTO States (State) VALUES ('OH');
```

We can verify the ```AUTO_INCREMENT``` field is working by querying the ```States``` table. We should see a record for ```IN``` and ```OH```.

```console
SELECT * FROM States;
```

Query Results:
```console
+----+-------+
| Id | State |
+----+-------+
|  1 | IN    |
|  2 | OH    |
+----+-------+
2 rows in set (0.001 sec)
```

## Coffee Break
Wow, we're moving right along and you are becoming a better database developer by the minute. Take a break, grab some coffee or go on a walk. Good time to let the difuse mode of the brain to do it's work. We are covering a lot of material here and the number of relating research items that could branch off of each topic are too many to enumerate. So let's take 15-20 minutes and then come back.

## Relating Tables and Foreign Keys
Now we can take the normalized ```States``` table and put it to use. To do so, we will need to add a new column to our ```Contacts``` table. We are going to issue the ```ALTER TABLE``` command to add a new field namesd ```StateId``` with a data type of ```int```. This field will store the ``Id`` from the ```States``` table.

```console
ALTER TABLE Contacts ADD StateId int;
```
Query Results:
```console
Query OK, 0 rows affected (0.016 sec)
Records: 0  Duplicates: 0  Warnings: 0
```

We can now verify the table changes by issuing the ```DESCRIBE``` command on the ```Contacts``` table. Doing so should list all fields including the new ```StateId``` field.

```console
DESCRIBE Contacts;
```
Query Results:
```console
+-----------+--------------+------+-----+---------+-------+
| Field     | Type         | Null | Key | Default | Extra |
+-----------+--------------+------+-----+---------+-------+
| Id        | int(11)      | YES  |     | NULL    |       |
| LastName  | varchar(100) | YES  |     | NULL    |       |
| FirstName | varchar(100) | YES  |     | NULL    |       |
| Email     | varchar(255) | YES  |     | NULL    |       |
| StateId   | int(11)      | YES  |     | NULL    |       |
+-----------+--------------+------+-----+---------+-------+
5 rows in set (0.004 sec)
```

Note the ```StateId``` column is of data type  ```int``` to store the foreign key ```Id``` from the ```States``` table. Exactly what we need to store the desired ```State``` for each record. We can now use our ```UPDATE``` command to set the correct ```State``` for each record in our ```Contacts``` table. This example does not fully illustrate the power of reltional tables but certainly drives the concept we are after. Imagine storing millions of records of relating types where the relating table is storing more than one field to represent the selection.

Before we update the ```Contacts``` table's ```StateId``` column with the foreign key value let's take a peek at what what records we have and the value currently stored in the ```StateId``` column.

```console
SELECT * FROM Contacts;
```
Query Results:
```console
+------+----------+-----------+--------------------------------------+---------+
| Id   | LastName | FirstName | Email                                | StateId |
+------+----------+-----------+--------------------------------------+---------+
|    1 | Doe      | Jane      | sweetcaroline@neildiamondfanclub.com |       1 |
+------+----------+-----------+--------------------------------------+---------+
1 row in set (0.001 sec)
```

Oh yes, there's Sweet Caroline all alone in our contacts table. The last time I checked she lives in good old Indiana. Let's complete this entry by placing Caroline in her home state.

## Joins
Relating tables are great but we need to ```JOIN``` ```Contacts``` and ```States``` to make sense of our information. For the most part our data is complete but we would like to see which state the contact is in.

```console
SELECT C.Id, C.LastName, C.FirstName, C.Email, S.State FROM Contacts C 
LEFT JOIN States S
ON
C.StateId = S.Id;
```

Query Results:
```console
+------+----------+-----------+--------------------------------------+-------+
| Id   | LastName | FirstName | Email                                | State |
+------+----------+-----------+--------------------------------------+-------+
|    1 | Doe      | Jane      | sweetcaroline@nieldiamondfanclub.com | IN    |
+------+----------+-----------+--------------------------------------+-------+
1 row in set (0.002 sec)
```
Ok, there we go, our data is now complete. Note the ```SELECT``` command is only pulling the necessary fields. This is more verbose but the preffered method of writing querys. This keeps the number of fields and dataset size to a minimum. This is a good practice to get accustomed to as optimization, tuning, and code readability become more important.

## Challenge
Add the additional fields ```City``` and ```Zip``` to the ```Contacts``` table. ```UPDATE``` the fields with example data. Modify the ```JOIN``` query to include the new fields. Use various parts of this tutorial to complete this challenge.

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


## Continue to [Part 4](README4.md)
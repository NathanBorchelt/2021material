
# Database (MySQL).

## Table of Contents
 - Overview
 - Preparing the Raspberry Pi
 - Install and Configure MySQL
 - Shortcuts Cheatsheet

## Overview
In this tutorial we will setup MySQL on the Raspberry Pi.  MySQL will host the database used by our web application and accompanying APIs. In this example I am using the Raspberry Pi 3 Model B v1.2.


## Preparing Raspberry Pi
Before we begin we want to ensure the Raspberry Pi is up to date to avoid any complications.  Login to the Raspberry Pi by issuing the ssh command below to establish a new connection. When prompted enter the Raspberry Pi's default password 
```raspberry```.
```console
ssh pi@your_ip_here
```
Now that we are connected, issue the commands below to begin the update and upgrade process.
```console
sudo apt update; sudo apt upgrade
```

## Install and Configure MySQL
Install MySQL server by issuing the following comand from your terminal.
```console
sudo apt install mariadb-server
```

### Secure the mysql database
The following command will start the securing process. For this example we will use ```Password1``` as the ```root``` level password. Answering ```Y``` to all of the prompts ensures the most secure installation.
```console
sudo mysql_secure_installation
```

### Login to the datbase
Login to MySQL using root user. You will be prompted with the password we created above. For this example we created used the password Password1. This password is very week and I do not recommend this for productions systems.
```console
sudo mysql -u root -p
```

### Create exampledb
```console
CREATE DATABASE exampledb;
```
### Create a user
This example user will be called "sictc" and the password will be "Pencil1"
```console
CREATE USER 'sictc'@'localhost' IDENTIFIED BY 'Pencil1';
```
### Granting proper access to our new user
```console
GRANT ALL PRIVILEGES ON exampledb.* TO 'sictc'@'localhost';
```
### Flush the privilege table.
This will ensure the newly created user can access the database. 
```console
FLUSH PRIVILEGES;
```

### Try out the new user.
Let's see if we can login with our new user. First we will need to exit the MySQL console.
The console can be exited by issuing ```quit;``` or the key sequence ```CTRL+D```.

Now attempt to login using the new user ```sictc```. Reminder the password is ```Pencil1```.

```console
sudo mysql -u sictc -p
```

### Verify MySQL User
We can verify that we are logged in as the sictc user by issuing the following command.
```console
SELECT CURRENT_USER();
```
Query Results:
```console
+-----------------+
| current_user()  |
+-----------------+
| sictc@localhost |
+-----------------+
1 row in set (0.001 sec)
```

## Shortcuts Commands and Cheatsheet

| Commands | Definition |
|---|---|
|`ssh pi@ipaddress`| Command to login to Raspberry Pi.|
|`raspberry`| Default password for Raspberry Pi. |
|`touch filename`| Command to create a file. |
|`ls`| Command to list contents in a directory. Think of the contents you see on your desktop icons. |
|`pwd`| Command that prints the current directly, called print working directory|
|`whoami`| Command that displays the current logged in user. |
|`sudo`| Command to allow admin executions, short for `super user do`|
|`apt update`| Command to update repository. |
|`apt upgrade`| Command to upgrade the installed packages. |
|`apt install packagename`| Command to install a package. |
|`arp`| Command displaying the IPv4 network neighbor cache.  |
|`grep`| Command that search for a pattern matching an expression. |
|`awk`| Command that search a file or text containing a pattern. |
|`cat`| Display contents of a file, think of it like `call at attention`. |

For MySQL

|`sudo mysql -u sictc -p`| Login to MySQL console. |
|`quit;` or `CTRL+D` | Command to exit MySQL console. |
|`exampledb`| MySQL example database. |
|`Password1`| MySQL root password. |
|`sictc`| MySQL exampledb user. |
|`Pencil`| MySQL exampledb password. |


### References
 - https://pimylifeup.com/raspberry-pi-mysql/
 - https://www.raspberrypi.org/documentation/remote-access/ssh/
 - https://raspberrytips.com/mac-address-on-raspberry-pi/
 - https://en.wikipedia.org/wiki/MAC_address


## Continue to [Part 2](README2.md)



# (Extras) Setting MySQL Timezone on Raspberry Pi.

## Table of Contents
 - Overview
 - Setting MySQL Timezone


## Overview
This tutorial guides you through the process of setting the timezone for MySQL. Setting the timezone to Universal Time Coordinated (UTC) is a good practice to get into. This allows the clients connecting to your MySQL server to use their local timezome when viewing time sensitive information. This also, keeps your from having to calculate timezone offsets in your application.  Less is more, let the operating system do the work.

## Setting MySQL Timezone
From the Raspberry Pi terminal issue the ```datetime``` comand to display the current date and time on the Raspberry Pi. By default this is the system datetime that MySQL uses to timestamp information in ```DATIME``` columns.

### Get Raspberry Pi date and time
Type the following command in your terminal to request the local date and time from the Raspberry Pi.
```console
date
```
Example Terminal Result:
```console
Fri 25 Sep 22:19:09 BST 2020
```

### Query MySQL Timezone
```console
sudo mysql –e “SELECT @@global.time_zone;”
```
You should see the following result.

```console
+--------------------+
| @@global.time_zone |
+--------------------+
| SYSTEM             |
+--------------------+
```

### Edit MySQL Configuration File
In order to set the GMT timezone we need to edit the local configuration file. Fire up your favorite editory from the terminal and paste the following configuration information at the end of the file. I personally use Vim but you can use nano or another editor of choice. The file is located here: ```sudo vim /etc/mysql/my.cnf```.

Paste settings below in ```/etc/mysql/my.cnf```.
```console
[mysqld]
default-time-zone = "+00:00"
```

### Restart MySQL
```console
sudo service mysql restart
```

Issue the command described in Query MySQL Timezone and you should receive the following results:
```console
sudo mysql –e “SELECT @@global.time_zone;”
+--------------------+
| @@global.time_zone |
+--------------------+
| +00:00             |
+--------------------+
```

Now that we have the correct timezone set, let's query MySQL for the ```DATETIME```.
```console
sudo mysql -e "SELECT NOW();"
```


## References
 - https://phoenixnap.com/kb/change-mysql-time-zone


## [Table of Contents](../README.md)
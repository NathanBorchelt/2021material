
# Operating System (Linux).

## Table of Contents
 - Overview
 - Enabling SSH on Raspberry Pi (headless)
 - Finding Raspberry Pi on the network
 - Package Managers
 - Command-line Text Editors
 - Introduction to Secure Copy (SCP)
 - MAC Address Spoofing
 - Shortcuts Cheatsheet

## Overview
In this tutorial we configure the Raspberry Pi and prepare it for installtion.  MySQL host the database used by our web application and accompanying APIs. In this example I am using the Raspberry Pi 3 Model B v1.2.

### Windows (Windows Subsystem for Linux)
Windows users must install Windows Subsystem for Linux (WSL) before proceeding.  Listed below is a link to resources explaining how to get WSL up and running on your machine.
 - https://docs.microsoft.com/en-us/windows/wsl/install-win10#step-6---install-your-linux-distribution-of-choice

 <b>Note</b>: Getting everything for WSL can take you a bit of time so be prepared before getting started below.

## Enable SSH on Raspberry Pi (headless)
SSH can be enabled by creating an empty file named `ssh` on the boot partition of the SD card from another computer. For this section, I am using a Mac and the volumes are listed under `/Volumes`. 

Issuing the `touch` command places an empty file ssh file on the boot partition of the Raspbery Pi.

```touch /Volumes/boot/ssh```

## Finding Raspberry Pi on the network
Now that we have the Raspberry Pi Operating System installed its time to boot up the pi. Once the Pi has booted up, we will need to find the device on the network. We can find the address on the netwrok by doing a couple of commands, starting with `arp`.

The `arp` command uses the <b>Address Resolution Protocol (ARP)</b> to enumerate all attached network devices on a particular network segment. The ```ARP``` protocol gathers all ```Network Interface Controllers (NIC)```s on a network segment. The controllers on the newtwork will respond with ```MAC``` and ```IP``` address among other things.

Every ```NIC``` is assigned a unique identifier called a ```Media Access Control (MAC) Address```. This provides a network address for use within a network segment.  

MAC addresses are assigned by manufacturers and you can consider "burned-in addresses" tatooed on the device's firmware. The Raspberry Pi Foundation designates ```b8:27:eb``` as the first three octects "tuples" to identify a Raspberry Pi MAC address. The next section outlines the details of searching for the Raspbery Pi on your network and printing out both the `MAC` and `IP` address. This will allow us to connect to the Pi via SSH.

### List Raspberry Pi(s) on your local network
Now that we know how to find the pi on our network, we need to connect to the device for installing MySQL. You can find all devices connected to your network by issuing the ```arp -a``` from the terminal wiht a couple of other piped-in commands.

 The command below will filter out only devices connected to the network with the first two octets ```b8:27:eb``` by using the `grep` command. From your terminal issue the following command (may need to explain more of this in detail). 
```console
arp -a | grep b8:27:eb | awk -v OFS='\t' '{print $4, $2}'
```

<b>Note</b>: Be patient as the resolution of the device can take a moment.

You should see something like the following. 
```console
b8:27:eb:44:fe:ff	(169.254.178.239)
```

As you can see our command discovered and displayed the MAC and IP address of our Raspberry Pi. We will use this IP address to connect to our Raspberry Pi via ```Secure Shell``` also known as ```SSH```.

### Loging into the Raspberry Pi.
Once you discover the mac and IP address of your Raspberry Pi you are ready to connect to it. Issue the ssh command below to establish a new connection. 

```console
ssh pi@your_ip_here
```

When prompted enter the Raspberry Pi's default password `raspberry`.

## Package Managers
The Raspberry Pi comes pre installed with an a package manager called ```APT``` or Advanced Packaging Tool.  Package managers maintain software packages downloaded from a repository. Software can be installed, upgraded and removed with a package manager. Common package managers for Linux are called```APT```, ```DPKG```, and ```YUM```.  Other operating systems like Mac include ```Home Brew``` and ```MacPorts``` among others. 

<b>Note</b>: For the remainder of this tutorial we will be using `APT`.

Before we proceed we want to make sure the Raspberry Pi is up to date with the latest updates.

```console
sudo apt update 
sudo apt upgrade
```

## Command-line Text Editors
```Nano``` is just one of many editors available to you on Linux that would be just fine for the tutorial. I personally use ```Vim```, which is a bit difficult to learn at first due to its unintuitiveness, but powerful once you get used to it. I'd encourage you to research and choose an editor that best fits you. Many of the Linux text editors can be downloaded and maintained from the package manager. 

<b>Note:</b> If you would like to learn how to use Vim's general nvaigation and basic searching, you can [follow this tutorial](https://www.cpht.gitlab.io/docs/wiki/docs/docVi)

The majority of this tutorial involves command line acceess to the Raspberry Pi to get things setup. The source code provided in the simpler examples can be managed using a simple editor like ```Vim``` or ```Nano```. Larger code blocks in subsequent tutorials require you to upload files to the Raspberry Pi from your local machine. Using this workflow will accelerate the development process.

 - https://en.wikipedia.org/wiki/GNU_nano
 - https://en.wikipedia.org/wiki/Vim_(text_editor)


## Introduction to Secure Copy (SCP)
Editing files on the Raspberry Pi using ```Vim``` or ```Nano``` can be very inefficient. Let's move our source editing to our local machine and copy files to the Raspberry Pi for testing.  

To automate the transfer of files in this tutorial we will be using ```SCP```.  ```SCP``` is used to securely copy files between a local client and remote host or between two remote hosts. ```SCP``` uses ```Secure Shell Protocol (SSH)``` to``` transfer files.

Below is an example of transferring a single file to the Raspberry Pi. The command copies the ```droids.dat``` file to the home directory on the Raspberry Pi. The ```~/``` shortcut tells the terminal to place the file in the home directory. ```SCP``` will prompt you for the Raspberry Pi's password before beginning the transfer.
```console
scp droids.dat pi@10.0.0.242:~/
```
Confirm transfer on the Raspberry Pi by issuing the ```ls -al``` command from the Raspberry Pi terminal. Navigate to your home directory on the Raspberry Pi and issue the command below.
```console
ls -al
```
You should see a directory listing and the ```droids.data``` file.

## MAC Address Spoofing
There are times when you will want to make it more convenient to find your Raspbery Pi on the network. This tutorial teaches you how to change the last three octects of the MAC address on the Raspberry Pi.

If you are not already, log into your Raspberry Pi check the contents of the ```/boot/cmdline.txt``` by issuing the following command. 
```console
cat /boot/cmdline.txt
```
You should see something like this:
```console
console=serial0,115200 console=tty1 root=PARTUUID=4d59a030-02 rootfstype=ext4 elevator=deadline fsck.repair=yes rootwait quiet splash plymouth.ignore-serial-consoles
```

The ```cat``` command displays the contents of the file. Fire up your favorite edtitor and add the following lines to the end of the file if not already present. Be sure to put a space between the last entry and the new ```MAC``` address entry. ```smsc95xx.macaddr=b8:27:eb:xx:xx:xx```. Be sure to replace ```xx:xx:xx``` with your own octects. 

<b>Note</b>: ```octects``` are hexadecimal format so the characters should in the range from ```0``` to ```9``` and ```a``` through ```f```. You will need issue the ```Nano``` or  ```Vim``` command to edit the file using ```sudo```. Once you complete the process issue the reboot command to restart your Raspberry Pi. ```sudo reboot```

The reboot command terminates the terminal session and places you back at the local command line. Give the ```Pi``` some time to boot and repeat the ```ARP``` command above to discover your Raspberry Pi on the network. If everything goes as planned, you will see the Raspberry Pi listed with the newly assigned ```MAC``` address.

## Shortcuts and Commands Cheatsheet

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
|`arp`| Command displaying the IPv4 network neighbor cache.  |
|`grep`| Command that search for a pattern matching an expression. |
|`awk`| Command that search a file or text containing a pattern. |
|`cat`| Display contents of a file. |


### References
 - https://pimylifeup.com/raspberry-pi-mac-address-spoofing/
 - https://www.raspberrypi.org/documentation/remote-access/ssh/
 - https://raspberrytips.com/mac-address-on-raspberry-pi/
 - https://en.wikipedia.org/wiki/MAC_address

### [Table of Contents](../README.md)


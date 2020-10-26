
# Getting Started with Node

## Table of Contents
 - Overview
 - Installing Node
 - Hello Node Example
 - Challenge
 - Tips

## Overview
In this tutorial we begin exploring the `Javascript` based language `Node`. Before we can do this we need to install Node and required dependencies called modules. Follow the steps below to begin prearing your installation of the Node stack. As always, the code used in this tutorial can be used to create other programs by simply modifing the syntax.

## Installing Node.
Enable NodeSource repository.
```console
curl -sL https://deb.nodesource.com/setup_10.x | sudo bash -
```

Now that the NodeSource repository is enabled we can install ```Node```. NodesSource is a company focusing on the management and distribution of ```Node``` for enterprise deployments. The NodeSource repository gives us the flexibility of downloading and maintaining multiple versions of ```Node```. This comes in handy when hosting more than one application that may be dependent on different versions of ```Node```.

Ok... ok, enough on that, let's keep moving forward. Issue the command below to start the ```Node``` installation process. Be sure to answer the prompts with as the package manager prompts for input.

```console
sudo apt install nodejs
```
Now that ```Node``` is successfully installed we can very installation by ussing the following command.
```console
node --version
```
The console will reply with the current ```Node``` version if successful. ```Note:``` the version you receive may differ based upon the age of this article and versions available.
```console
v10.21.0
```

Now that ```Node``` installation is confirmed, we need to install the ```Node``` packages we will be using to build our application and ```API(s)```. Similar to ```APT``` ```Node``` has its own package manager called ```Node Package Manager (NPM)``` used to manage packages that our application uses. The ```Node``` developer comunity is rich with prebuilt packages that can be deployed for use in your application.

Our application requires a package to manage our connection to ```MySQL```, interface with ```Environment```, and a minimal web application framework to test our app called ```Express```.

Install the modules by issuing the commands below individually:
```console
npm install dotenv
npm install mysql2
npm install express
```

## Hello Node Example
Now that we have all the prerequisites install let's create an example application. In order to edit code directly on the Raspberry Pi we need to choose an editory. The Pi comes preinstalled with ```Nano```, a simple text editor that allows editing files from the command line. Paste the code below into a new file named ```app_hello.js```. This is our first ```Hello Node``` application written in ```Node```.

Fire up ```Nano``` by issuing the command below:
```console
nano app_hello.js
```
Type or paste the example code below.
```javascript
console.log("Hello Node!");
```
Save the file by issuing ```CTRL+O```, and then exit ```Nano``` by issuing ```CTRL+X```. Now we can test our new ```app_hello.js``` script by issuing the command below.
```console
node app_hello.js
```
You should see ```Hello Node!``` printed to the console.

## Challenges:
TODO:

## References
 - https://www.tecmint.com/linux-package-managers/
 - https://linuxize.com/post/how-to-install-node-js-on-raspberry-pi/
 - https://www.geeksforgeeks.org/scp-command-in-linux-with-examples/


## Continue to [Part 2](README2.md)
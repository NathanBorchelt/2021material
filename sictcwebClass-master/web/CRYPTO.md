
# Cryptography

## Table of Contents
 - Overview
 - Hashing Algorithm
 - Breaking Weak Cryptography
 - Challenge

## Overview
In this tutorial we will begin exploring the hashing function to verify the user's password. The level of encryption we want to use determine the complexity of the hash. 

## Hashing Algorithm
The first module we want to create is going to exercise the SHA2 512 hashing algorithm we need to verify the User's password.  This module can be run independently by running the ```Node``` file ```web/api/js/src/exp/crypto/app_hash.js``` from the command line. Take some time to study the contents of the file and determine where you can make changes.

### File Structure
The first line of code imports the ```Node``` cryptography library. The ```crypto``` library  is used to generate SHA2 512 bit checksums of values passed as a parameters to it's update method. Line 2 uses the ```crypto``` library to create a ```SHA 512``` bit hash. The type of hash created can be changed by passing different values into the ```createHash``` method. We will be using 512 in our application.

The variable ```userPassword``` is a constant used to generate different checksums as we explore the functionality of the library. This ```Password``` was also used in the MySQL database example so you might want to take note of the resulting checksum.

```javascript
var crypto = require('crypto');
const hash_sha512 = crypto.createHash('512');

const userPassword = 'Pencil1';
```
### Custom Methods
The ```genSHA512``` is a user defined method that takes a single argument. The given argument name ```userPassword``` is the variable that is used to calculate the checksum. The method completes by returning the hashed result.

We call this user defined method by assigning a return variable to the method call. The argument we pass in is the ```userPassword``` constant we defined above. The ```console.log``` method simply outputs the result to the terminal.

```javascript
function genSHA512(userPassword) {

    result = hash_sha512.update(userPassword, 'utf-8');

    return result.digest('hex');
}

result = genSHA512(userPasword);

console.log('hash: ', result);
```

Give it a try. Login to the Raspberry Pi and navigate to the ```sictcweb/web/api/js/src/exp``` direcotry and issue the ```node``` command on file ```app_hash.js```.

## Breaking Weak Cryptography
TODO:

Build script to brute force crack 40 bit hash. Provide insturmentation.

## Challenges:
TODO:


## References
 - https://www.tecmint.com/linux-package-managers/
 - https://linuxize.com/post/how-to-install-node-js-on-raspberry-pi/
 - https://www.geeksforgeeks.org/scp-command-in-linux-with-examples/

### [Table of Contents](../README.md)
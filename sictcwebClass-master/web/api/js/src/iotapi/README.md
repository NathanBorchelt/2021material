
# Web API (Node)

## Table of Contents
 - Overview
 - Initialize Node Project
 - Hello Node Example
 - Challenge
 - Tips

## Overview
In this tutorial we will begin creating our API that talks to the database. Before we can do this we need to install Node and required dependencies called modules. Follow the steps below to begin prearing your installation of the Node stack. As always, the code used in this tutorial can be used to create other programs by simply modifing the syntax.

## Initialize Node Project.
Initialize our new project by issuing npm command listed below. Answer the prompts according to the table below. Fields like `author` and `description` fill in with your preferences. Click Enter for the fields that are left blank.
```console
npm init
```

| Prompt | Answer |
|---|---|
|`package name:`| iotapi|
|`version:`| 1.0.0|
|`description:`| IoT REST API |
|`entry point:`| index.js |
|`test command:`|  |
|`git repository:`|  |
|`keywords:`|  |
|`author:`| John Cobb |
|`license:`|  |
|`Is this OK? (yes):`| yes |

This creates a new file named `package.json`.  This file is used to store information about the `Node` project as well as dependencies. Listed below are the contents of the example file that was created. Note the error message under scripts test section. This section is used for unit testing which will be covered in future topics. 
```javascript
{
  "name": "iotapi",
  "version": "1.0.0",
  "description": "IoT REST API",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "author": "John Cobb",
  "license": "ISC"
}
```

Great, now we need a way to run our application so that we can interface with the API(s) we create in our code. Express is a lightweight `Node` application server that does just that. Install `Express` by issuing the commands below.

```console
npm install express --save
```

Great, let's get started by creating our first lines of code. The code below creates an instance of express and called app that listens to REST requests on port 3000. Type the code below into the new `index.js` file.

```javascript
const express = require('express');

const app = express();
const PORT = 3000;

app.listen(PORT, () => console.log('IoT REST API running on port 3000'));

app.get('/', (request, response) => {
    console.log(request.url);
    response.send('Hello, World');
});
```

We can test the code above by running the command below. Once the console shows that the application is running launch a browser on your desktop computer and navigate to `http://your_ip_address:3000`. You should see the message `Hello, World`.

Great, now that the node server is up and running we can begin building our our API calls. The remainder of this tutorial covers basic examples of exercising the HTTP methods mentioned in previous tutorials.


Our First `POST` Method
In order for node to accept `POST` requests we need to install the `body-parser` module.  The `body-parser` parses information transmitted from the HTTP Message Body of the request. See references section below for further information. Install the the module by issuing the command below
```console
npm install --save body-parser
```

Change the code above to reflect the example below. Here you can see we are creating a reference to `body-parser` and telling our `app` instance to use.

```javascript
const express = require('express');
const bodyParser = require('body-parser');

const app = express();
const PORT = 3000;

app.use(bodyParser.json());
app.listen(PORT, () => console.log('IoT REST API running on port 3000'));

app.get('/', (request, response) => {
    console.log(request.url);
    response.send('Hello, World');
});

app.post('/', (request, response) => {
    const content = request.body;

    console.log(content);
    response.json(content);
});
```

Example posting example.json with curl. Explaining the functionality of `curl` is beyond the scope of this tutorial. You can confirm installation of curl by issuing `curl --version`. If you get a `command not found` response install `curl` using your package manager.
```console
curl -X POST -H "Content-Type: application/json" -d "@iot.json" http://localhost:3000
```
curl results
```javascript
{"id":1,"type":"Modem","mfg":"Cradlepoint","serial":"1F2332118"}
```



## References
 - https://en.wikipedia.org/wiki/HTTP_message_body
 - https://www.npmjs.com/package/body-parser


## Continue to [Part 2](README2.md)
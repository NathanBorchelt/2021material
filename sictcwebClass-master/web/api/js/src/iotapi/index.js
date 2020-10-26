const express = require('express');
const bodyParser = require('body-parser');
const NAME = require(__dirname + '/package.json').name;

const app = express();
const PORT = 3000;

app.use(bodyParser.json());
app.listen(PORT, () => console.log('IoT REST API running on port 3000'));
// app.listen(PORT, () => console.log('${NAME} running on port ${PORT}'));


app.get('/', (request, response) => {
    console.log(request.url);
    response.send('Hello, World');
});

app.post('/', (request, response) => {
    const content = request.body;

    console.log(content);
    response.json(content);
});

app.get('/hello_html', (request, response) => {
    console.log(request.url);
    response.send('<div><h1>Hello, HTML</h1></div>');
});
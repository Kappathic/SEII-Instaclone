const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');


const apiController = require('./controllers/api')

const logger = require('./utils/logger')

const app = express();



//Enable CORS falls der frontend extern gelifert wird.
app.use(cors());

app.use(express.json());

//Log all Request
app.use(logger.logToConsole);

app.use('/api', apiController);


app.use(function(req, res) {
    //Responde with 404
    res.statusCode = 404;
	res.json({error: "route not availble"});
});



app.listen(3000, () => {
    console.log(`App listening at 3000`)
});

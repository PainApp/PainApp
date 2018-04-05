const express = require("express");
const bodyParser = require("body-parser");

var app = express();
var configRoutes = require("./routes");

app.use(bodyParser.json());
configRoutes(app);

app.listen(8080, () => {
});

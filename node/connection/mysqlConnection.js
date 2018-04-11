const mysql = require("mysql2/promise");
const config = require("./config.json")

pool = mysql.createPool(config);

exports.pool = pool;

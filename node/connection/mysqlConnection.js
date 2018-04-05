const mysql = require("mysql2/promise");
const prompt = require("prompt-sync")();

var pass = prompt.hide("mysql password: ");

pool = mysql.createPool({
  host               : "pocketdoc.cxx8xktili9j.us-east-2.rds.amazonaws.com",
  user               : "ghost",
  password           : pass,
  port               : "3306",
  database           : "pocket_doc",
  multipleStatements : true,
  connectionLimit    : 100
});

exports.pool = pool;

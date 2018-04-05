const mysql = require("mysql2/promise");
const prompt = require("prompt-sync")();

var pass = prompt.hide("mysql password: ");

var execsql = require('execsql');
var dbConfig = {
      host     : "pocketdoc.cxx8xktili9j.us-east-2.rds.amazonaws.com",
      user     : "ghost",
      port     : "3306",
      password : pass
    };
var sql = 'use pocket_doc;';
var sqlFile = __dirname + '/initialize.sql';

execsql.config(dbConfig)
  .exec(sql)
  .execFile(sqlFile, function(err, results){
  }).end();

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

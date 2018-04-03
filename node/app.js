var mysql = require('mysql');

var connection = mysql.createConnection({
  host     : "pocketdoc.cxx8xktili9j.us-east-2.rds.amazonaws.com",
  user     : "ghost",
  password : "B&n4nA9(",
  port     : "3306"
});

connection.connect(function(err) {
  if (err) {
    console.error('Database connection failed: ' + err.stack);
    return;
  }

  console.log('Connected to database.');
});

//connection.end();



var mysql = require('mysql');

var con = mysql.createConnection({
  host     : "pocketdoc.cxx8xktili9j.us-east-2.rds.amazonaws.com",
  user     : "ghost",
  password : "B&n4nA9(",
  port     : "3306",
  database : "pocketdoc"
});

con.connect(function(err) {
  if (err) {
    console.error('Database connection failed: ' + err.stack);
    return;
  }
  
  console.log('Connected.');
  
  con.query("DROP DATABASE pocketdoc", function(error){
        if(error){
            console.log("Error in destorying database");
            return;
        }
        console.log("Database dropped");
    });
  
    con.query("CREATE DATABASE pocketdoc", function (err, result) {
    if (err) throw err;
    console.log("Database created");
  });

});
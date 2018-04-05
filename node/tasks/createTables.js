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
  
  con.query("CREATE TABLE body_regions (id INT, name VARCHAR(255), PRIMARY KEY (id))", function (err, result) {
    if (err) throw err;
    console.log("body_region table created");
    });
  con.query("CREATE TABLE specific_body_regions (id INT, body_region_id INT, name VARCHAR(255), PRIMARY KEY (id), FOREIGN KEY (body_region_id) REFERENCES body_regions(id))", function (err, result) {
    if (err) throw err;
    console.log("specific_body_region table created");
    });
  con.query("CREATE TABLE causes (id INT, specific_body_region_id INT, name VARCHAR(255), classification varchar(250), PRIMARY KEY (id), FOREIGN KEY (specific_body_region_id) REFERENCES specific_body_regions(id))", function (err, result) {
    if (err) throw err;
    console.log("causes table created");
    });
  
    
  
});
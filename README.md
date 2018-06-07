# PainApp
In here lies all the code to make the PocketDoc application run.

## Node Server
The [node](node) folder contains a NodeJS Express application that hosts the 
REST server used by the mobile app to query the database. Run the command
`npm start` from within the folder to install its dependencies and start the
server. The MySQL database is also cleared andn re-initialized during 
startup.

The file [instructions.txt](node/instructions.txt) contains details on how
the server handles its requests. The server is hosted on an AWS EC2 instance
at http://18.218.162.185:8080. The private key to access the server is 
[pocketdoc.pem](node/pocketdoc.pem).

## MySQL Database
The application data is stored on an AWS RDS instance using MySQL. The
database is cleared and repopulated when the REST server is restarted. 
Details for accessing the server are in [.my.cnf](database/.my.cnf) and the
script used to populate the database is in 
[initialize.sql](database/initialize.sql).

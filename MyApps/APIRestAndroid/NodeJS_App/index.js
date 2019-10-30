//1. Call mysql package
const mysql = require('mysql');
//2. Call express package
const express = require('express');
//3. Call body-parser package
const bodyparser = require('body-parser');
//4. Create a new express instance
var app = express();

//5. Enable body-parser (json) request
app.use(bodyparser.json());

//6. Create mysql database connection
var mysqlconnection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'market'
});

//7. Validate mysql database connection
mysqlconnection.connect((err) => {
    if(!err){
        console.log('::: Successful connection with database :::');
    }else{
        console.log('::: Database connection failed :::' + JSON.stringify(err, undefined, 2));
    }
});

app.listen(3000,()=>console.log('::: Server is running at port 3000 :::'));

//READ all users in database
app.get('/users', (req, res) => {
    mysqlconnection.query('SELECT * from users', (err, rows, fields) => {
        if(!err){
            console.log(rows);
            res.send(rows);
        }else{
            console.log(err);
        }
    })
});
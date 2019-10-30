<?php
    //Data base connections - MySQL credentials.
    $servername = 'localhost'; //127.0.0.1 or staticIP-DinamicIP
    $username = 'root';
    $password = '';
    $dbname = 'market';
    $port = '3306';

    //Create connection
    $conn = new mysqli(
        $servername, 
        $username,
        $password,
        $dbname
    );

    //Check connection
    if($conn->connect_error){
        die("Connection failed: ". $conn->connect_error);
    }else{
        echo "Connection successfully";
    }

?>
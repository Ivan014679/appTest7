<?php
    include('connectionDB.php');

    $Email = $_POST['email'];

    $sql = "INSERT INTO users (email) VALUES ('$Email')";

    if ($conn->query($sql) === TRUE){
        echo "User has been created";
    }else{
        echo "Error: ". $sql . " " . $conn->error;
    }

    $conn->close();
?>
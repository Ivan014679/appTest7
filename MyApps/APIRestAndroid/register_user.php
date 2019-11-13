<?php
    include('connectionDB.php');

    $Firstname = $_POST['firstname'];
    $Lastname = $_POST['lastname'];
    $Email = $_POST['email'];
    $Phone = $_POST['phone'];

    $sql = "INSERT INTO users (firstname,lastname,email,phone) VALUES ('$Firstname','$Lastname','$Email','$Phone')";

    if ($conn->query($sql) === TRUE){
        echo "User has been created";
        echo $Firstname.$Lastname.$Email.$Phone;
    }else{
        echo "Error: ". $sql . " " . $conn->error;
    }

    $conn->close();
?>
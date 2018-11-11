<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbName = "mag";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbName);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$project = "mylyn";

// change metrics
$row = 1;
if (($handle = fopen("change-metrics.csv", "r")) !== FALSE) {
    while (($data = fgetcsv($handle, 5000, ";")) !== FALSE) {
        $num = count($data);
        echo "<p> $num fields in line $row: <br /></p>\n";
        $row++;
        if($row === 2){
            // skip first row
            continue;
        }
        
        $sql = "INSERT INTO change_metrics VALUES (\"$project\", ";

        for ($c=0; $c < $num; $c++) {
            $val = trim($data[$c]);
            if($c === 0) {
                $sql =  $sql ."\"".$val. "\"";
            }
            else {
                $sql = $sql.$val;
            }
            $sql = $sql . ", ";
        }
        $sql = substr($sql, 0, -4);
        $sql = $sql . ")";

        if($conn->query($sql)===TRUE){
            echo "Row ".($row-1)." inserted<br>";
        }
        else {
            echo "error on row ".($row-1)."<br>";
            echo "Query:<br>".$sql."<br>";
            echo "Error:<br>".$conn->error."<br>";
        }


    }
    fclose($handle);
}

// classic metrics
$row = 1;
if (($handle = fopen("single-version-ck-oo.csv", "r")) !== FALSE) {
    while (($data = fgetcsv($handle, 5000, ";")) !== FALSE) {
        $num = count($data);
        echo "<p> $num fields in line $row: <br /></p>\n";
        $row++;
        if($row === 2){
            // skip first row
            continue;
        }
        
        $sql = "INSERT INTO classic_metrics VALUES (\"$project\", ";

        for ($c=0; $c < $num; $c++) {
            $val = trim($data[$c]);
            if($c === 0) {
                $sql =  $sql ."\"".$val. "\"";
            }
            else {
                $sql = $sql.$val;
            }
            $sql = $sql . ", ";
        }
        $sql = substr($sql, 0, -4);
        $sql = $sql . ")";

        if($conn->query($sql)===TRUE){
            echo "Row ".($row-1)." inserted<br>";
        }
        else {
            echo "error on row ".($row-1)."<br>";
            echo "Query:<br>".$sql."<br>";
            echo "Error:<br>".$conn->error."<br>";
        }


    }
    fclose($handle);
}


$conn->close();

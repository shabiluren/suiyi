<?php
header("Content-type:text/html;charset=utf-8");
$userName = $_GET["user"];
$passWord = $_GET["pass"];
$dbms = 'mysql';
$host = 'localhost';
$dbName = 'test';
$user = 'root';
$pass = 'root'
$dsn = "$dbms:host=$host;dbname=$dbName";
$data = new PD0($dsn,$user,$pass);
$sqlStr = "select l from user where name = '$userName' and password = '$passWord'";
$result = $data -> query($sqlStr);
$info = $result->fetch(PD0:FETCH_BOTH);
if($info)
    echo"success"；
else
    echo"fail"
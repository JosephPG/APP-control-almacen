<?php
 $con=mysql_connect("localhost","root","josep215")or die("Sin conectar");
  mysql_select_db("bdclientes");

$codigo=$_REQUEST['codigo'];
$sql = "select * from producto where codpro='$codigo'";


$datos=array();
$rs=mysql_query($sql,$con);
while($row=mysql_fetch_object($rs)){
      $datos[]=$row;
}
echo json_encode($datos);
?>


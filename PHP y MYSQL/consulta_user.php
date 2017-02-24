<?php
 $con=mysql_connect("localhost","root","josep215")or die("Sin conectar");
  mysql_select_db("bdclientes");

$user=$_REQUEST['user'];
$sql = "select usu_usuario,usu_clave from usuario where usu_usuario='$user'";


$datos=array();
$rs=mysql_query($sql,$con);
while($row=mysql_fetch_object($rs)){
      $datos[]=$row;
}
echo json_encode($datos);
?>


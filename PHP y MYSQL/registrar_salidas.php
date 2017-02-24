<?php
 $con=mysql_connect("localhost","root","josep215")or die("Sin conectar");
  mysql_select_db("bdclientes");


$cliente=$_REQUEST['vencimiento_cliente'];
$producto=$_REQUEST['codigo_barra'];
$cantidad=$_REQUEST['cantidad'];
$fec=$_REQUEST['fecha_actual'];
$sql="insert into salidas (codclien,codpro,cantidad,fecha) values( '$cliente', '$producto', $cantidad, '$fec')";
$query= mysql_query($sql,$con);
echo $query;
?>


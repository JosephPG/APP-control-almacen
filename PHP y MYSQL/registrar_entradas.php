<?php
 $con=mysql_connect("localhost","root","josep215")or die("Sin conectar");
  mysql_select_db("bdclientes");


$producto=$_REQUEST['codigo_barra'];
$cantidad=$_REQUEST['cantidad'];
$fecin=$_REQUEST['fecha_actual'];
$fecven=$_REQUEST['vencimiento_cliente'];

$sql="insert into entradas (codpro,cantidad,fechaIn,fechaVenc) values('$producto', $cantidad, '$fecin', '$fecven')";

$query= mysql_query($sql,$con);
echo $query;
?>

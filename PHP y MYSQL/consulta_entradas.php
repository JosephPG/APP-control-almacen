<?php
 $con=mysql_connect("localhost","root","josep215")or die("Sin conectar");
  mysql_select_db("bdclientes");

$sql = "select e.identrada,e.codpro,p.nompro,e.cantidad,fechaIn,fechaVenc from entradas e, producto p where e.codpro=p.codpro";

$datos=array();
$rs=mysql_query($sql,$con);
while($row=mysql_fetch_object($rs)){
      $datos[]=$row;
}
echo json_encode($datos);;
?>


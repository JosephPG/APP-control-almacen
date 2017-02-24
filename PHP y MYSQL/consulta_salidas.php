<?php
 $con=mysql_connect("localhost","root","josep215")or die("Sin conectar");
  mysql_select_db("bdclientes");

$sql = "select e.idsalida,c.nombclien,c.apeclien,p.codpro,p.nompro,e.cantidad,fecha from salidas e, producto p, cliente c where e.codpro=p.codpro and e.codclien=c.codclien";

$datos=array();
$rs=mysql_query($sql,$con);
while($row=mysql_fetch_object($rs)){
      $datos[]=$row;
}
echo json_encode($datos);;
?>


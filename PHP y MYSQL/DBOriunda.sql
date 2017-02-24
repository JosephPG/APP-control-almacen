CREATE DATABASE bdclientes;
USE bdclientes;
CREATE TABLE `cliente` (
`codclien` CHAR( 8 )  CHARACTER SET utf8 COLLATE utf8_general_ci NULL PRIMARY KEY,
`nombclien` VARCHAR( 30 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`apeclien` VARCHAR( 30 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`direccion` VARCHAR( 40 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`email` VARCHAR( 40 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
);

INSERT INTO `cliente`
VALUES (
'1', 'Dario', 'Lozano Mendez', 'Av. Los Jasmines 123', 'darioes@gmail.com'
);
INSERT INTO `cliente`
VALUES (
'2', 'Juan', 'Falcon Vega', 'Av. Argentina 145', 'falconv12juan@gmail.com'
);
INSERT INTO `cliente`
VALUES (
'3', 'Fernando', 'Vargas Gomez', 'Av. Morales Duarez 25', 'fernando34vargas23@gmail.com'
);
INSERT INTO `cliente`
VALUES (
'4', 'Luis', 'Rodrigues Condori', 'Av. Lince 453', 'luis2condori@gmail.com'
);


CREATE TABLE `producto` (
`codpro` CHAR( 30 ) CHARACTER SET utf8 COLLATE utf8_general_ci NULL PRIMARY KEY,
`nompro` VARCHAR( 50 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`precio` VARCHAR( 30 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
);



INSERT INTO `producto`
VALUES ('7751271006347', 'GLORIA LECHE CHOCOLATADA S/LACTOSA', '5.00');

INSERT INTO `producto`
VALUES ('95188441035', 'JUGO TAMPICO CITRUS PUCH OT X 500', '1.00');

INSERT INTO `producto`
VALUES ('7750670003766', 'JUGO CIFRUT X 500 ML', '1.00');

CREATE TABLE `usuario` (
`idusuario` varchar( 10 ) NOT NULL,
`usu_usuario` varchar( 10 ) NOT NULL ,
`usu_clave` varchar( 10 ) NOT NULL ,
PRIMARY KEY ( `idusuario` )
)DEFAULT CHARSET = utf8;

INSERT INTO `usuario`
VALUES (
'1', 'grabiela', 'grabiela'
);
INSERT INTO `usuario`
VALUES (
'2', 'usuario', '123456'
);

CREATE TABLE `salidas` (
`idsalida` int( 10 ) NOT NULL AUTO_INCREMENT,
`codclien` CHAR( 8 ) ,
`codpro` char( 30 ) ,
`cantidad` integer(5) not null ,
`fecha` DATE NULL ,
PRIMARY KEY ( `idsalida` ),
FOREIGN KEY (codpro) REFERENCES producto(codpro),
FOREIGN KEY (codclien) REFERENCES cliente(codclien)
)DEFAULT CHARSET = utf8;

CREATE TABLE `entradas` (
`identrada` int( 10 ) NOT NULL AUTO_INCREMENT,
`codpro` char( 30 ),
`cantidad` integer(5) not null ,
`fechaIn` DATE NULL ,
`fechaVenc` DATE NULL,
PRIMARY KEY ( `identrada` ),
FOREIGN KEY (codpro) REFERENCES producto(codpro)
)CHARSET = utf8;

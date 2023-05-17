USE cancelados;

-- AGREGAR INFORMACION A LA TABLA DE ROLES
INSERT INTO roles VALUES (1,'Administrador','Es un administrador que administra cosas');
INSERT INTO roles VALUES (2,'Empleado','Es un empleado que administra ALGUNAS cosas');

-- AGREGAR INFORMACION A LA TABLA DE EMPLEADO
INSERT INTO empleado (idEmpleado, Nombre, ApellidoPaterno, ApellidoMaterno, Calle, NoExt, Colonia, CP, CURP, RFC, Municipio, Estado, Roles_idRoles)
VALUES
(1,"Hugo","Cancelados","","Calle","5","Colonia","63150","CURP","RFC","Tepic","Nayarit",1),
(2, "Julia", "Garcia", "Vega", "Calle Juárez", "123", "La Cantera", "83140", "GAVJ900601MDFNNS01", "GAVJ900601ABC", "Tepic", "Nayarit", 2),
(3, "Fernanda", "Gonzalez", "Perez", "Calle Reforma", "21", "El Campanario", "83000", "GOPF880212MDFNNS02", "GOPF880212ABC", "Tepic", "Nayarit", 2),
(4, "Pedro", "Lopez", "Garcia", "Av. de la Reforma", "123", "Centro", "06000", "LOPG871103HDFPLR02", "LOPG871103ABC", "Tepic", "Nayarit", 2),
(5, "Ana", "Garcia", "Castro", "Calle Revolución", "456", "Del Valle", "03100", "GACA900808MDFNNS08", "GACA900808ABC", "Tepic", "Nayarit", 2),
(6, "Luis", "Hernandez", "Juarez", "Calle Juárez", "222", "Centro", "63000", "HEJL910112HDFNNS09", "HEJL910112ABC", "Tepic", "Nayarit", 2),
(7, "María", "Lopez", "Hernández", "Calle Morelos", "15", "Los Pinos", "54190", "LOHM930227MDFNNS05", "LOHM930227ABC", "Tepic", "Nayarit", 2),
(8, "Jorge", "Garcia", "Jiménez", "Av. Principal", "789", "La Loma", "83110", "GAJJ880501HDFNNS03", "GAJJ880501ABC", "Tepic", "Nayarit", 2),
(9, "Carmen", "Ramirez", "González", "Calle 5 de Mayo", "27", "La Cruz", "71980", "RAGC840802MDFNNS00", "RAGC840802ABC", "Tepic", "Nayarit", 2),
(10, "Mario", "Hernandez", "Castro", "Calle Hidalgo", "40", "Las Rosas", "63450", "HECM960402HDFNNS00", "HECM960402ABC", "Tepic", "Nayarit", 2);

-- AGREGAR INFORMACION A LA TABLA DE CREDENCIALES
INSERT INTO credenciales (idCredenciales, Contrasena, empleado_idEmpleado, Empleado_Roles_idRoles)
VALUES
(1, "admin123", 1, 1),
(2, "empleado123", 2, 2),
(3, "xyz34", 3, 2),
(4, "pqr12", 4, 2),
(5, "lmn45", 5, 2),
(6, "def23", 6, 2),
(7, "ghi45", 7, 2),
(8, "stu12", 8, 2),
(9, "uvw34", 9, 2),
(10, "rst23", 10, 2);

-- AGREGAR INFORMACION A LA TABLA DE CLIENTES
INSERT INTO cliente (idCliente, Nombre, ApellidoPaterno, ApellidoMaterno, CURP, Calle, Colonia, Ciudad, Estado, CP) VALUES
(1, "Ana", "López", "García", "LOGA951201MNPLNX02", "Calle 1", "Centro", "Tepic", "Nayarit", "63120"),
(2, "Juan", "Martínez", "Hernández", "MAHJ990813HNTZNR09", "Calle 2", "Reforma", "Tepic", "Nayarit", "63150"),
(3, "Sofía", "Ramírez", "Gutiérrez", "RAGS900801MNZMSF06", "Calle 3", "San Juan", "Tepic", "Nayarit", "63000"),
(4, "Diego", "Pérez", "Castro", "PECD920702HNTZGO07", "Calle 4", "Buenavista", "Tepic", "Nayarit", "63180"),
(5, "Luisa", "González", "Díaz", "GODL940812MNZLSL02", "Calle 5", "Insurgentes", "Tepic", "Nayarit", "63140"),
(6, "Pablo", "Hernández", "Sánchez", "HESP880702HNTZPL02", "Calle 6", "México", "Tepic", "Nayarit", "63160"),
(7, "Mariana", "Flores", "Ruiz", "FLRM870501MNZNRN07", "Calle 7", "La Loma", "Tepic", "Nayarit", "63170"),
(8, "Ricardo", "Díaz", "García", "DIGR800501MNZRCR05", "Calle 8", "La Cantera", "Tepic", "Nayarit", "63190"),
(9, "Ana", "Gutiérrez", "Vázquez", "GUVA830101MNZNTN07", "Calle 9", "Indeco", "Tepic", "Nayarit", "63110"),
(10, "Mario", "Martínez", "Flores", "MAFM950703MNZRTL01", "Calle 10", "San Juan", "Tepic", "Nayarit", "63120");



alter table credenciales drop foreign key fk_credenciales_empleado1;
alter table credenciales drop column Empleado_Roles_idRoles;

UPDATE `cancelados`.`credenciales` SET `Contrasena` = '2087348535479320780316101705201136896' WHERE (`idCredenciales` = '1') and (`Empleado_idEmpleado` = '1');


-- *************************
drop table detalleVenta;
drop table venta;

CREATE TABLE venta (
idVenta INT NOT NULL AUTO_INCREMENT,
total DECIMAL(2,0) NOT NULL,
fecha DATETIME,
idCliente INT NOT NULL,
idEmpleado INT NOT NULL,
CONSTRAINT pk_venta PRIMARY KEY (idVenta),
CONSTRAINT fk_venta_cliente FOREIGN KEY (idCliente) REFERENCES cliente(idCliente),
CONSTRAINT fk_venta_empleado FOREIGN KEY (idEmpleado) REFERENCES empleado(idEmpleado)
);

CREATE TABLE detalleVenta(
idDetalleVenta INT NOT NULL AUTO_INCREMENT,
idVenta INT NOT NULL,
cantidad INT NOT NULL,
precioUnitario DECIMAL(2,0),
importe DECIMAL(16,2),
idProducto INT NOT NULL,
CONSTRAINT pk_detalleVenta PRIMARY KEY (idDetalleVenta),
CONSTRAINT fk_detalleVenta_venta FOREIGN KEY (idVenta) REFERENCES venta(idVenta),
CONSTRAINT fk_detalleVenta_producto FOREIGN KEY (idProducto) REFERENCES producto(idProducto)
);
-- *************************



ALTER TABLE venta MODIFY COLUMN fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE venta MODIFY COLUMN total decimal(10,2);
ALTER TABLE detalleVenta MODIFY COLUMN precioUnitario decimal(10,2);
ALTER TABLE producto MODIFY COLUMN precioUnitario decimal(10,2);

-- Inserts de prueba para productos
INSERT INTO producto (idProducto, Nombre, Descripcion, PrecioUnitario, Stock, autor) VALUES (2, 'Biblia Sagrada', 'Biblia Sagrada versión Reina-Valera', 25.99, 100, 'Anónimo');
INSERT INTO producto (idProducto, Nombre, Descripcion, PrecioUnitario, Stock, autor) VALUES (3, 'Crucifijo de plata', 'Crucifijo de plata con detalle en oro', 35.99, 25, 'Anónimo');
INSERT INTO producto (idProducto, Nombre, Descripcion, PrecioUnitario, Stock, autor) VALUES (4, 'Imagen de la Virgen', 'Imagen de la Virgen María de porcelana', 45.99, 15, 'Anónimo');
INSERT INTO producto (idProducto, Nombre, Descripcion, PrecioUnitario, Stock, autor) VALUES (5, 'Vela de altar', 'Vela de cera blanca para altar', 5.99, 75, 'Anónimo');
INSERT INTO producto (idProducto, Nombre, Descripcion, PrecioUnitario, Stock, autor) VALUES (6, 'Estampa religiosa', 'Estampa religiosa con oración a San José', 2.99, 200, 'Anónimo');
INSERT INTO producto (idProducto, Nombre, Descripcion, PrecioUnitario, Stock, autor) VALUES (7, 'Agua bendita', 'Agua bendita en frasco de vidrio', 7.99, 50, 'Anónimo');
INSERT INTO producto (idProducto, Nombre, Descripcion, PrecioUnitario, Stock, autor) VALUES (8, 'Libro de oraciones', 'Libro de oraciones católicas', 15.99, 75, 'Anónimo');
INSERT INTO producto (idProducto, Nombre, Descripcion, PrecioUnitario, Stock, autor) VALUES (9, 'Escapulario de tela', 'Escapulario de tela con medalla de la Virgen', 12.99, 50, 'Anónimo');
INSERT INTO producto (idProducto, Nombre, Descripcion, PrecioUnitario, Stock, autor) VALUES (10, 'Rosario de cristal', 'Rosario de cristal con cuentas facetadas', 18.99, 40, 'Anónimo');
INSERT INTO producto (idProducto, Nombre, Descripcion, PrecioUnitario, Stock, autor) VALUES (11, 'Cruz de madera', 'Cruz de madera tallada a mano', 30.99, 20, 'Anónimo');
INSERT INTO producto (idProducto, Nombre, Descripcion, PrecioUnitario, Stock, autor) VALUES (12, 'Santísimo Sacramento', 'Santísimo Sacramento de oro macizo', 350.99, 5, 'Anónimo');



-- ******************* 30/04/2023 *******************
ALTER TABLE venta ADD COLUMN estado VARCHAR(8);
UPDATE venta set estado = 'VENDIDO' WHERE idVenta>=1;

CREATE VIEW vistaVentas as (
SELECT v.idVenta,v.total, v.fecha, CONCAT(c.nombre, " ", c.apellidoPaterno) AS nombreCliente, CONCAT(e.nombre, " ", e.apellidoPaterno) AS nombreEmpleado, v.estado FROM venta v 
INNER JOIN cliente c ON (v.idCliente = c.idCliente)
INNER JOIN empleado e ON (v.idEmpleado = e.idEmpleado)
);

CREATE VIEW vistaDetalleVenta AS (
SELECT dv.idDetalleVenta,
dv.idVenta,
p.nombre AS producto,
v.fecha, dv.cantidad,
dv.precioUnitario,
dv.importe,
CONCAT(c.nombre, " ", c.apellidoPaterno, " ", c.apellidoMaterno) AS cliente,
c.CURP,
c.calle,
c.cp,
c.colonia,
c.ciudad,
c.estado,
CONCAT(e.nombre, " ", e.apellidoPaterno, " ", e.apellidoMaterno) AS empleado
FROM detalleVenta dv
INNER JOIN venta v ON (dv.idVenta = v.idVenta)
INNER JOIN producto p ON (dv.idProducto = p.idProducto)
INNER JOIN empleado e ON (v.idEmpleado = e.idEmpleado)
INNER JOIN cliente c ON (v.idCliente = c.idCliente)
);
-- **************************************************

-- ********************* 05/05/23 *********************
ALTER TABLE producto ADD COLUMN categoria VARCHAR(20);

ALTER TABLE cliente DROP COLUMN CURP;

ALTER VIEW vistadetalleventa AS (
SELECT dv.idDetalleVenta,
dv.idVenta,
p.nombre AS producto,
v.fecha, dv.cantidad,
dv.precioUnitario,
dv.importe,
CONCAT(c.nombre, " ", c.apellidoPaterno, " ", c.apellidoMaterno) AS cliente,
c.calle,
c.cp,
c.colonia,
c.ciudad,
c.estado,
CONCAT(e.nombre, " ", e.apellidoPaterno, " ", e.apellidoMaterno) AS empleado
FROM detalleVenta dv
INNER JOIN venta v ON (dv.idVenta = v.idVenta)
INNER JOIN producto p ON (dv.idProducto = p.idProducto)
INNER JOIN empleado e ON (v.idEmpleado = e.idEmpleado)
INNER JOIN cliente c ON (v.idCliente = c.idCliente)
);

CREATE TABLE notificacion(
idNotificacion INT NOT NULL AUTO_INCREMENT,
idEmpleado INT NOT NULL,
CONSTRAINT pk_notificacion PRIMARY KEY (idNotificacion),
CONSTRAINT fk_notificacion_empleado FOREIGN KEY (idEmpleado) REFERENCES empleado(idEmpleado)
);

CREATE VIEW vista_not_emp AS (
SELECT n.idNotificacion, e.idEmpleado AS idEmpleado, e.Nombre, e.ApellidoPaterno, e.ApellidoMaterno, r.Nombre AS Rol, n.fecha FROM notificacion n
INNER JOIN empleado e ON (n.idEmpleado = e.idEmpleado)
INNER JOIN roles r ON (e.Roles_idRoles = r.idRoles)
);

-- ****************************************************

-- ********************* 06/05/23 *********************
ALTER TABLE notificacion ADD COLUMN fecha DATETIME;
ALTER TABLE notificacion MODIFY COLUMN fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE empleado ADD COLUMN restContra BOOLEAN;

-- ****************************************************


-- 1.*Quien recibe? 2.-Domicilio envio 3.-Calle 4.Colonia 5.-num- 6.producto 7.-total 8.-Se envio Por: 9.-Ciudad 10.CP 11.Responsable 12.Peso 13.Fecha Pedido. 14: Envió
-- ******************07/05/2023***************
CREATE TABLE Envios(
idEnvio INT NOT NULL auto_increment,
Destinatario VARCHAR(50) NOT NULL,
Domicilio VARCHAR(70) NOT NULL,
Ciudad VARCHAR(50) NOT NULL,
Calle VARCHAR(50) NOT NULL,
Colonia VARCHAR(50) NOT NULL,
Numero VARCHAR(5) NOT NULL,
Producto VARCHAR(100) NOT NULL,
Paqueteria VARCHAR(50) NOT NULL,
CP VARCHAR(5) NOT NULL,
Responsable VARCHAR(50) NOT NULL,
Peso INT,
Fecha date NOT NULL,
Remitente VARCHAR(50) NOT NULL,
constraint pkIdEnvio primary key (idEnvio)
);
-- ****************************************************

-- **********************12/05/2023*********************
DROP TABLE Envios;


CREATE TABLE Envios(
idEnvio INT NOT NULL ,
Destinatario VARCHAR(50) NOT NULL,
Domicilio VARCHAR(70) NOT NULL,
Ciudad VARCHAR(50) NOT NULL,
Calle VARCHAR(50) NOT NULL,
Colonia VARCHAR(50) NOT NULL,
Numero VARCHAR(5) NOT NULL,
Producto VARCHAR(100) NOT NULL,
Paqueteria VARCHAR(50) NOT NULL,
CP VARCHAR(5) NOT NULL,
Responsable VARCHAR(50) NOT NULL,
Peso INT,
Fecha date NOT NULL,
Remitente VARCHAR(50) NOT NULL,
constraint fkIdEnvio foreign key (idEnvio) REFERENCES venta(idVenta)
);

ALTER TABLE venta ADD COLUMN TipoVenta char NOT NULL;
ALTER TABLE detalleventa ADD COLUMN tipoVenta char;



UPDATE `cancelados`.`cliente` SET `Nombre` = 'Público', `ApellidoPaterno` = 'En', `ApellidoMaterno` = 'General', `Calle` = 'Desconocido', `Colonia` = 'Desconocido', `CP` = '63000' WHERE (`idCliente` = '1');


CREATE VIEW vistaVentaEnvios AS(
SELECT total,fecha,tipoVenta from venta
);

SELECT total,fecha,tipoVenta from venta;
-- Consultas de prueba para reporte de venta
SELECT * FROM venta WHERE venta.fecha like '%2023-05-03%';
SELECT SUM(total) from venta where fecha like '%2023-05-03%';
SELECT * FROM envios WHERE envios.fecha like '%2023-05-03%';


-- *******************15/15/2023*******************
UPDATE empleado SET restContra = false WHERE idEmpleado>=1;
-- ************************************************
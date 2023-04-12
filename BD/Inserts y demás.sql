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

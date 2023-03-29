-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Roles` (
  `idRoles` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `Descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`idRoles`),
  UNIQUE INDEX `idRoles_UNIQUE` (`idRoles` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Empleado` (
  `idEmpleado` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `ApellidoPaterno` VARCHAR(45) NULL,
  `ApellidoMaterno` VARCHAR(45) NULL,
  `Calle` VARCHAR(45) NULL,
  `No.Ext` VARCHAR(45) NULL,
  `Colonia` VARCHAR(45) NULL,
  `CP` VARCHAR(45) NULL,
  `CURP` VARCHAR(45) NULL,
  `RFC` VARCHAR(45) NULL,
  `Municipio` VARCHAR(45) NULL,
  `Estado` VARCHAR(45) NULL,
  `Empleadocol` VARCHAR(45) NULL,
  `Roles_idRoles` INT NOT NULL,
  PRIMARY KEY (`idEmpleado`, `Roles_idRoles`),
  UNIQUE INDEX `idUsuario_UNIQUE` (`idEmpleado` ASC) VISIBLE,
  INDEX `fk_Empleado_Roles1_idx` (`Roles_idRoles` ASC) VISIBLE,
  CONSTRAINT `fk_Empleado_Roles1`
    FOREIGN KEY (`Roles_idRoles`)
    REFERENCES `mydb`.`Roles` (`idRoles`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Producto` (
  `idProducto` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `Descripcion` VARCHAR(45) NULL,
  `PrecioUnitario` DECIMAL(2) NULL,
  `Imagen` BLOB NULL,
  `Stock` INT NULL,
  `Autor` VARCHAR(45) NULL,
  PRIMARY KEY (`idProducto`),
  UNIQUE INDEX `idProducto_UNIQUE` (`idProducto` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Categoria` (
  `idCategoria` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `Descripcion` VARCHAR(45) NULL,
  `Producto_idProducto` INT NOT NULL,
  PRIMARY KEY (`idCategoria`, `Producto_idProducto`),
  UNIQUE INDEX `idCategoria_UNIQUE` (`idCategoria` ASC) VISIBLE,
  INDEX `fk_Categoria_Producto1_idx` (`Producto_idProducto` ASC) VISIBLE,
  CONSTRAINT `fk_Categoria_Producto1`
    FOREIGN KEY (`Producto_idProducto`)
    REFERENCES `mydb`.`Producto` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `ApellidoPaterno` VARCHAR(45) NULL,
  `ApellidoMaterno` VARCHAR(45) NULL,
  `CURP` VARCHAR(45) NULL,
  `Calle` VARCHAR(45) NULL,
  `Colonia` VARCHAR(45) NULL,
  `Ciudad` VARCHAR(45) NULL,
  `Estado` VARCHAR(45) NULL,
  `CP` VARCHAR(45) NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE INDEX `idCliente_UNIQUE` (`idCliente` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Venta` (
  `idVenta` INT NOT NULL AUTO_INCREMENT,
  `PrecioTotal` DECIMAL(2) NULL,
  `Fecha` DATETIME NULL,
  `Producto_idProducto` INT NOT NULL,
  `Cliente_idCliente` INT NOT NULL,
  `Empleado_idEmpleado` INT NOT NULL,
  PRIMARY KEY (`idVenta`, `Producto_idProducto`, `Cliente_idCliente`, `Empleado_idEmpleado`),
  UNIQUE INDEX `idVenta_UNIQUE` (`idVenta` ASC) VISIBLE,
  INDEX `fk_Venta_Producto_idx` (`Producto_idProducto` ASC) VISIBLE,
  INDEX `fk_Venta_Cliente1_idx` (`Cliente_idCliente` ASC) VISIBLE,
  INDEX `fk_Venta_Empleado1_idx` (`Empleado_idEmpleado` ASC) VISIBLE,
  CONSTRAINT `fk_Venta_Producto`
    FOREIGN KEY (`Producto_idProducto`)
    REFERENCES `mydb`.`Producto` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Venta_Cliente1`
    FOREIGN KEY (`Cliente_idCliente`)
    REFERENCES `mydb`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Venta_Empleado1`
    FOREIGN KEY (`Empleado_idEmpleado`)
    REFERENCES `mydb`.`Empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`DetalleVenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`DetalleVenta` (
  `idTicket` INT NOT NULL AUTO_INCREMENT,
  `SubTotal` DECIMAL(2) NULL,
  `Impuestos` DECIMAL(2) NULL,
  `Total` DECIMAL(2) NULL,
  `DetalleVentacol` VARCHAR(45) NULL,
  `Venta_idVenta` INT NOT NULL,
  `Venta_Producto_idProducto` INT NOT NULL,
  `Venta_Cliente_idCliente` INT NOT NULL,
  `Venta_Empleado_idEmpleado` INT NOT NULL,
  PRIMARY KEY (`idTicket`, `Venta_idVenta`, `Venta_Producto_idProducto`, `Venta_Cliente_idCliente`, `Venta_Empleado_idEmpleado`),
  UNIQUE INDEX `idTicket_UNIQUE` (`idTicket` ASC) VISIBLE,
  INDEX `fk_DetalleVenta_Venta1_idx` (`Venta_idVenta` ASC, `Venta_Producto_idProducto` ASC, `Venta_Cliente_idCliente` ASC, `Venta_Empleado_idEmpleado` ASC) VISIBLE,
  CONSTRAINT `fk_DetalleVenta_Venta1`
    FOREIGN KEY (`Venta_idVenta` , `Venta_Producto_idProducto` , `Venta_Cliente_idCliente` , `Venta_Empleado_idEmpleado`)
    REFERENCES `mydb`.`Venta` (`idVenta` , `Producto_idProducto` , `Cliente_idCliente` , `Empleado_idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Credenciales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Credenciales` (
  `idCredenciales` INT NOT NULL AUTO_INCREMENT,
  `Contrasena` VARCHAR(45) NULL,
  `Empleado_idEmpleado` INT NOT NULL,
  `Empleado_Roles_idRoles` INT NOT NULL,
  PRIMARY KEY (`idCredenciales`, `Empleado_idEmpleado`, `Empleado_Roles_idRoles`),
  UNIQUE INDEX `idCredenciales_UNIQUE` (`idCredenciales` ASC) VISIBLE,
  INDEX `fk_Credenciales_Empleado1_idx` (`Empleado_idEmpleado` ASC, `Empleado_Roles_idRoles` ASC) VISIBLE,
  CONSTRAINT `fk_Credenciales_Empleado1`
    FOREIGN KEY (`Empleado_idEmpleado` , `Empleado_Roles_idRoles`)
    REFERENCES `mydb`.`Empleado` (`idEmpleado` , `Roles_idRoles`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

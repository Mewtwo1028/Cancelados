/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Código;

import ArchivosBD.Conexion;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

/**
 *
 * @author osmar
 */
public class Empleado {

    int idEmpledo;
    String nombre;
    String aPaterno;
    String aMaterno;
    String calle;
    String noExt;
    String Colonia;
    String cp;
    String curp;
    String rfc;
    String municipio;
    String estado;
    String idRol;

    public Empleado(int idEmpledo, String nombre, String aPaterno, String aMaterno, String calle, String noExt, String Colonia, String cp, String curp, String rfc, String municipio, String estado, String idRol) {
        this.idEmpledo = idEmpledo;
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.calle = calle;
        this.noExt = noExt;
        this.Colonia = Colonia;
        this.cp = cp;
        this.curp = curp;
        this.rfc = rfc;
        this.municipio = municipio;
        this.estado = estado;
        this.idRol = idRol;
    }
    
    

    public Empleado(String nombre, String aPaterno, String aMaterno, String calle, String noExt, String Colonia, String cp, String curp, String rfc, String municipio, String estado, String idRol) {
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.calle = calle;
        this.noExt = noExt;
        this.Colonia = Colonia;
        this.cp = cp;
        this.curp = curp;
        this.rfc = rfc;
        this.municipio = municipio;
        this.estado = estado;
        this.idRol = idRol;
    }

    public int getIdEmpledo() {
        return idEmpledo;
    }

    public void setIdEmpledo(int idEmpledo) {
        this.idEmpledo = idEmpledo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNoExt() {
        return noExt;
    }

    public void setNoExt(String noExt) {
        this.noExt = noExt;
    }

    public String getColonia() {
        return Colonia;
    }

    public void setColonia(String Colonia) {
        this.Colonia = Colonia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public void getIdEmpleado (int idEmpleado) {
      this.idEmpledo = idEmpleado;
    }

}

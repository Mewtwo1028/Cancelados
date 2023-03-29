/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Código;

import java.sql.DriverManager;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author osmar
 */
public class Empleado {
    int idEmpledo;

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

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
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
    int idRol;
    
  public void InsertarEmpleado(JTextField txtNombre, JTextField txtAPaterno, JTextField txtAMaterno, JTextField txtCalle, JTextField txtNoExt,JTextField txtColonia,JTextField txtCP,JTextField txtCurp, JTextField txtRFC, JTextField txtMunicipio, JTextField txtEstado ){
    
        setNombre(txtNombre.getText());
        setaPaterno(txtAPaterno.getText());
        setaMaterno(txtAMaterno.getText());
        setCalle(txtCalle.getText());
        setNoExt(txtNoExt.getText());
        setColonia(txtColonia.getText());
        setCp(txtCP.getText());
        setCurp(txtCurp.getText());
        setRfc(txtRFC.getText());
        setMunicipio(txtMunicipio.getText());
        setEstado(txtEstado.getText());
        
          
       //Mandas llamar a la conexion  conexion objetoConexion = new conexion();
        
        
       
        
       try {
        // Conexión a la base de datos
        conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        
        // Preparar la consulta SQL
        String sql = "INSERT INTO empleados (idEmpleado, nombre, aPaterno, aMaterno, calle, noExt, colonia, cp, curp, rfc, municipio, estado, idRol) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conexion.prepareStatement(sql);
        
        pstmt.setString(2, nombre);
        pstmt.setString(3, aPaterno);
        pstmt.setString(4, aMaterno);
        pstmt.setString(5, calle);
        pstmt.setString(6, noExt);
        pstmt.setString(7, colonia);
        pstmt.setString(8, cp);
        pstmt.setString(9, curp);
        pstmt.setString(10, rfc);
        pstmt.setString(11, municipio);
        pstmt.setString(12, estado);
        pstmt.setInt(13, idRol);
        
        // Ejecutar la consulta SQL
        pstmt.executeUpdate();
        
        // Cerrar la conexión
        conexion.close();
        
        System.out.println("Empleado insertado correctamente en la base de datos.");
    } catch (SQLException e) {
        System.out.println("Error al insertar el empleado en la base de datos: " + e.getMessage());
    }
  } 
    
    
    
}

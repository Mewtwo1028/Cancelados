package Código;

import ArchivosBD.Conexion;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

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
    int idRol;
    
    public Empleado(){
        
    }

    public Empleado(int idEmpledo, String nombre, String aPaterno, String aMaterno, String calle, String noExt, String Colonia, String cp, String curp, String rfc, String municipio, String estado, int idRol) {
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

    public Empleado(String nombre, String aPaterno, String aMaterno, String calle, String noExt, String Colonia, String cp, String curp, String rfc, String municipio, String estado, int idRol) {
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

    public Empleado(int idEmpleado) {
        this.idEmpledo = idEmpleado;
    }

    public boolean eliminarEmpleado(Empleado empleado) {
        Conexion conexion = new Conexion();
        String operacion1 = "DELETE FROM credenciales WHERE Empleado_idEmpleado=?;";
        String operacion2 = "DELETE FROM empleado WHERE idEmpleado=?;";

        System.out.println(String.valueOf(empleado.getIdEmpledo()));

        CallableStatement cs;
        try {
            //Borrar las credenciales del empleado
            cs = conexion.getConexion().prepareCall(operacion1);
            cs.setString(1, String.valueOf(empleado.getIdEmpledo()));
            cs.execute();

            //Borrar al empleado
            cs = conexion.getConexion().prepareCall(operacion2);
            cs.setString(1, String.valueOf(empleado.getIdEmpledo()));
            cs.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean modificaCredenciales(Credenciales credencial) {
        Conexion con = new Conexion();
        String actualizaCredenciales = "UPDATE credenciales SET Contrasena=? WHERE Empleado_idEmpleado=?;";
        try {
            CallableStatement cs = con.getConexion().prepareCall(actualizaCredenciales);

            cs.setString(1, credencial.getContraseña());
            cs.setInt(2, credencial.getIdEmpleado());

            cs.execute();

            return true;

        } catch (NumberFormatException | SQLException e) {
            return false;
        }
    }

    public boolean modificarEmpleado(Empleado empleado) {
        Conexion con = new Conexion();
        String consulta = "UPDATE Empleado SET nombre=?, ApellidoPaterno =?, ApellidoMaterno=?,Calle=?,NoExt=?, Colonia=?,CP=?,Municipio=?,Estado=?,CURP=?,RFC=?, Roles_idRoles=? WHERE idEmpleado=?;";

        try {
            CallableStatement cs = con.getConexion().prepareCall(consulta);

            cs.setString(1, empleado.getNombre());
            cs.setString(2, empleado.getaPaterno());
            cs.setString(3, empleado.getaMaterno());
            cs.setString(4, empleado.getCalle());
            cs.setString(5, empleado.getNoExt());
            cs.setString(6, empleado.getColonia());
            cs.setString(7, empleado.getCp());
            cs.setString(8, empleado.getMunicipio());
            cs.setString(9, empleado.getEstado());
            cs.setString(10, empleado.getCurp());
            cs.setString(11, empleado.getRfc());
            cs.setInt(12, getIdRol());
            cs.setInt(13, empleado.getIdEmpledo());
            

            cs.execute();

            return true;

        } catch (NumberFormatException | SQLException e) {
            return false;
        }
    }

    public void getEmpleadoTabla(JTable parametroEmpleado, JTextField id, JTextField nombre, JTextField aPaterno, JTextField aMaterno, JTextField calle, JTextField ext, JTextField col, JTextField cp, JTextField mun, JTextField estado, JTextField curp, JTextField rfc, JComboBox rol) {//Método que manda a los jTextFields los datos del registro seleccionado en la tabla

        try {
            int fila = parametroEmpleado.getSelectedRow();
            if (fila >= 0) {
                id.setText(parametroEmpleado.getValueAt(fila, 0).toString());
                nombre.setText(parametroEmpleado.getValueAt(fila, 1).toString());
                aPaterno.setText(parametroEmpleado.getValueAt(fila, 2).toString());
                aMaterno.setText(parametroEmpleado.getValueAt(fila, 3).toString());
                calle.setText(parametroEmpleado.getValueAt(fila, 4).toString());
                ext.setText(parametroEmpleado.getValueAt(fila, 5).toString());
                col.setText(parametroEmpleado.getValueAt(fila, 6).toString());
                cp.setText(parametroEmpleado.getValueAt(fila, 7).toString());
                curp.setText(parametroEmpleado.getValueAt(fila, 8).toString());
                rfc.setText(parametroEmpleado.getValueAt(fila, 9).toString());
                mun.setText(parametroEmpleado.getValueAt(fila, 10).toString());
                estado.setText(parametroEmpleado.getValueAt(fila, 11).toString());
                //rol.setText(parametroEmpleado.getValueAt(fila, 12).toString());
                String valorRol = parametroEmpleado.getValueAt(fila, 12).toString();
                rol.setSelectedIndex(valorRol.equals("1") ? 1 : 0);

            } //POR AQUÍ IRÍA LO DEL COMPONENTE!!!
            else {
                // JOptionPane.showInputDialog(this,"Fila no selecccionada");
            }
        } catch (Exception e) {
            //JOptionPane.showInputDialog(this,"Fila no selecccionada");
        }

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

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public void getIdEmpleado(int idEmpleado) {
        this.idEmpledo = idEmpleado;
    }

}

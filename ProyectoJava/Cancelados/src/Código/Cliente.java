package Código;

import ArchivosBD.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    private int idCliente;
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private String curp;
    private String calle;
    private String colonia;
    private String ciudad;
    private String estado;
    private String cp;

    public Cliente(int idCliente, String nombre, String aPaterno, String aMaterno, String curp, String calle, String colonia, String ciudad, String estado, String cp) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.curp = curp;
        this.calle = calle;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.estado = estado;
        this.cp = cp;
    }

    public Cliente(String nombre, String aPaterno, String aMaterno, String curp, String calle, String colonia, String ciudad, String estado, String cp) {
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.curp = curp;
        this.calle = calle;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.estado = estado;
        this.cp = cp;
    }

    public Cliente() {

    }

    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public boolean eliminarCliente(Cliente cliente) {
        Conexion conexion = new Conexion();
        String operacion = "DELETE FROM cliente WHERE idCliente=?;";

        CallableStatement cs;
        try {
            //Borrar al cliente
            cs = conexion.getConexion().prepareCall(operacion);
            cs.setString(1, String.valueOf(cliente.getIdCliente()));
            cs.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean modificarCliente(Cliente cliente) {
        Conexion con = new Conexion();
        String consulta = "UPDATE cliente SET nombre=?, ApellidoPaterno =?, ApellidoMaterno=?,curp=?,calle=?, colonia=?,ciudad=?,estado=?,cp=? WHERE idCliente=?;";

        try {
            CallableStatement cs = con.getConexion().prepareCall(consulta);

            cs.setString(1, cliente.getNombre());
            cs.setString(2, cliente.getaPaterno());
            cs.setString(3, cliente.getaMaterno());
            cs.setString(4, cliente.getCurp());
            cs.setString(5, cliente.getCalle());
            cs.setString(6, cliente.getColonia());
            cs.setString(7, cliente.getCiudad());
            cs.setString(8, cliente.getEstado());
            cs.setString(9, cliente.getCp());
            cs.setString(10, String.valueOf(cliente.getIdCliente()));

            cs.execute();

            return true;

        } catch (NumberFormatException | SQLException e) {
            return false;
        }
    }

    public ArrayList<String[]> consultarTodos() {
        ArrayList<String[]> resultado = new ArrayList<>();

        ResultSet cursor;
        String consulta = "SELECT * FROM cliente;";

        try {
            Conexion con = new Conexion();
            cursor = con.getTransaccion().executeQuery(consulta);
            if (cursor.next()) {
                do {
                    String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10)};
                    resultado.add(renglon);
                } while (cursor.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public boolean insertarCliente(Cliente cliente) {
        String consulta = "INSERT INTO cliente VALUES (null, '%a%','%b%','%c%','%d%','%e%','%f%','%g%','%h%','%i%');";

        try {
            Conexion con = new Conexion();

            consulta = consulta.replace("%a%", cliente.getNombre());
            consulta = consulta.replace("%b%", cliente.getaPaterno());
            consulta = consulta.replace("%c%", cliente.getaMaterno());
            consulta = consulta.replace("%d%", cliente.getCurp());
            consulta = consulta.replace("%e%", cliente.getCalle());
            consulta = consulta.replace("%f%", cliente.getColonia());
            consulta = consulta.replace("%g%", cliente.getCiudad());
            consulta = consulta.replace("%h%", cliente.getEstado());
            consulta = consulta.replace("%i%", cliente.getCp());

            con.getTransaccion().execute(consulta);

            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

}

package controlador;

/**
 *
 * @author osmar
 */
import modelo.Cliente;
import modelo.Credenciales;
import modelo.Empleado;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Conexion {

    //Datos de la conexión
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cancelados";
    private static final String DB_USER = "cancelados";
    private static final String DB_PASSWORD = "ModoDiablo23";

    Connection conexion;
    Statement transaccion;
    ResultSet cursor; //aqui se guardan la informacion de las consultas SELECT

    public Conexion() {
        try {
            Class.forName(DB_DRIVER);
            conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            transaccion = conexion.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String verificarLogin(String nombreCredencial, String contrasena) {

        //Credenciales credencial = new Credenciales();
        try {
            cursor = transaccion.executeQuery("Select E.Roles_idRoles from empleado E inner join Credenciales C On (C.Empleado_idEmpleado = E.idEmpleado) where nombre='" + nombreCredencial + "' and contrasena='" + contrasena + "'");
            if (cursor.next()) {
                String idRol = cursor.getString(1);
                return idRol;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /*
    public boolean registrarCliente(Cliente cliente) {

        String nombre = cliente.getNombre();
        String aPaterno = cliente.getaPaterno();
        String aMaterno = cliente.getaMaterno();
        String curp = cliente.getCurp();
        String calle = cliente.getCalle();
        String colonia = cliente.getColonia();
        String ciudad = cliente.getCiudad();
        String estado = cliente.getEstado();
        String cp = cliente.getCp();

        String SQL_insertar = "INSERT INTO cliente VALUES (null,'" + nombre + "','" + aPaterno + "','" + aMaterno + "','" + curp + "','" + calle + "','" + colonia + "','" + ciudad + "','" + estado + "','" + cp + "')";

        try {
            transaccion.execute(SQL_insertar);
        } catch (SQLException ex) {
            return false;
        }

        return true;
    }
     */
    public Connection getConexion() {
        return conexion;
    }

    public Statement getTransaccion() {
        return transaccion;
    }

    public ResultSet getCursor() {
        return cursor;
    }
    /*
    public ArrayList<String[]> consultarTodos() {
        ArrayList<String[]> resultado = new ArrayList<String[]>();

        try {
            cursor = transaccion.executeQuery("SELECT * FROM empleado");
            if (cursor.next()) {
                do {
                    String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13)};
                    resultado.add(renglon);
                } while (cursor.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;

    }
     */

}

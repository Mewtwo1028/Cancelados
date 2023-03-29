package ArchivosBD;

/**
 *
 * @author osmar
 */
import Código.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    //Datos de la conexión
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cancelados";
    private static final String DB_USER = "cancelados";
    private static final String DB_PASSWORD = "ModoDiablo23";

    Connection conexion;
    Statement transaccion;
    ResultSet cursor; //aqui se guardan la informacion de las consultas SELECT

    //Método para obtener la conexión a la base de datos
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
}

package controlador;

/**
 *
 * @author osmar
 */
import modelo.Cliente;
import modelo.Credencial;
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

    public Connection getConexion() {
        return conexion;
    }

    public Statement getTransaccion() {
        return transaccion;
    }

    public ResultSet getCursor() {
        return cursor;
    }

}

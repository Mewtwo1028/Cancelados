
package ArchivosBD;

/**
 *
 * @author osmar
 */
import Código.Credenciales;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    //Datos de la conexión
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cancelados";
    private static final String DB_USER = "Osmar";
    private static final String DB_PASSWORD = "Mewtwo1028";
    
    Connection conexion;
    Statement transaccion;
    ResultSet cursor; //aqui se guardan la informacion de las consultas SELECT
    
    //Método para obtener la conexión a la base de datos
    public Conexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tap_ejemplo1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            transaccion = conexion.createStatement();
        }catch(SQLException | ClassNotFoundException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean verificarLogin(String nombreCredencial, String contrasena){
    
        Credenciales credencial = new Credenciales();
    
        try {
            cursor = transaccion.executeQuery("SELECT * FROM credenciales WHERE nombre='"+nombreCredencial+"' and contraseña='"+contrasena+"'");
            if (cursor.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}


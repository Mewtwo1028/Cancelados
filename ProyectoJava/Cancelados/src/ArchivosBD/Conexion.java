
package ArchivosBD;

/**
 *
 * @author osmar
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    //Datos de la conexión
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cancelados";
    private static final String DB_USER = "Osmar";
    private static final String DB_PASSWORD = "Mewtwo1028";
    
    //Método para obtener la conexión a la base de datos
    public  Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error al obtener la conexión: " + ex.getMessage());
        }
        return connection;
    }
}


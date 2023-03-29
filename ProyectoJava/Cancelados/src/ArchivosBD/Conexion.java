package ArchivosBD;

/**
 *
 * @author osmar
 */
import Código.Cliente;
import Código.Credenciales;
import Código.Empleado;
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
    
    public Conexion(){
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
    
    public Connection getConexion() {
        return conexion;
    }

    public Statement getTransaccion() {
        return transaccion;
    }

    public ResultSet getCursor() {
        return cursor;
    }
    
    public boolean insertarEmpleado(Empleado empleado, String usuario, String contra){

        String SQL_insertar = "INSERT INTO empleado VALUES (null, '%nom%', '%pa%', '%ma%', '%cal%', '%noe%', '%col%', '%cp%', '%curp%', '%rfc%', '%mun%', '%est%', %rol%)";

        SQL_insertar = SQL_insertar.replace("%nom%", empleado.getNombre());
        SQL_insertar = SQL_insertar.replace("%pa%", empleado.getaPaterno());
        SQL_insertar = SQL_insertar.replace("%ma%", empleado.getaMaterno());
        SQL_insertar = SQL_insertar.replace("%cal%", empleado.getCalle());
        SQL_insertar = SQL_insertar.replace("%noe%", empleado.getNoExt());
        SQL_insertar = SQL_insertar.replace("%col%", empleado.getColonia());
        SQL_insertar = SQL_insertar.replace("%cp%", empleado.getCp());
        SQL_insertar = SQL_insertar.replace("%curp%", empleado.getCurp());
        SQL_insertar = SQL_insertar.replace("%rfc%", empleado.getRfc());
        SQL_insertar = SQL_insertar.replace("%mun%", empleado.getMunicipio());
        SQL_insertar = SQL_insertar.replace("%est%", empleado.getEstado());
        SQL_insertar = SQL_insertar.replace("%rol%", empleado.getIdRol());
        
        String SQL_idEmpleado = "select idEmpleado from empleado order by idEmpleado DESC";

        try {
            transaccion.execute(SQL_insertar);
            cursor = transaccion.executeQuery(SQL_idEmpleado);
            cursor.next();
            String idEmpleado = cursor.getString(1);
            String SQL_insertarCredencial = "INSERT INTO credenciales VALUES (null, '"+contra+"',"+idEmpleado+","+empleado.getIdRol()+")";
            transaccion.execute(SQL_insertarCredencial);
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
    
    
}

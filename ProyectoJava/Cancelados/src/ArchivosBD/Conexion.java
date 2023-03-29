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
    
    public boolean insertarEmpleado(Empleado empleado, String contra){

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
    
    public boolean actualizarEmpleado(Empleado empleado, String contra) {
    String SQL_actualizar = "UPDATE empleado SET nombre='%nom%', aPaterno='%pa%', aMaterno='%ma%', calle='%cal%', noExt='%noe%', colonia='%col%', cp='%cp%', curp='%curp%', rfc='%rfc%', municipio='%mun%', estado='%est%', idRol=%rol%";

    SQL_actualizar = SQL_actualizar.replace("%nom%", empleado.getNombre());
    SQL_actualizar = SQL_actualizar.replace("%pa%", empleado.getaPaterno());
    SQL_actualizar = SQL_actualizar.replace("%ma%", empleado.getaMaterno());
    SQL_actualizar = SQL_actualizar.replace("%cal%", empleado.getCalle());
    SQL_actualizar = SQL_actualizar.replace("%noe%", empleado.getNoExt());
    SQL_actualizar = SQL_actualizar.replace("%col%", empleado.getColonia());
    SQL_actualizar = SQL_actualizar.replace("%cp%", empleado.getCp());
    SQL_actualizar = SQL_actualizar.replace("%curp%", empleado.getCurp());
    SQL_actualizar = SQL_actualizar.replace("%rfc%", empleado.getRfc());
    SQL_actualizar = SQL_actualizar.replace("%mun%", empleado.getMunicipio());
    SQL_actualizar = SQL_actualizar.replace("%est%", empleado.getEstado());
    SQL_actualizar = SQL_actualizar.replace("%rol%", empleado.getIdRol());
    //SQL_actualizar = SQL_actualizar.replace("%id%", empleado.getIdEmpleado());

    //String SQL_actualizarCredencial = "UPDATE credenciales SET contrasena='"+contra+"' WHERE idEmpleado="+empleado.getIdEmpleado();

    try {
        transaccion.execute(SQL_actualizar);
        //transaccion.execute(SQL_actualizarCredencial);
    } catch (SQLException ex) {
        return false;
    }
    return true;
}
    
        public ArrayList<String[]> consultarTodos(){
        ArrayList<String[]> resultado = new ArrayList<String[]>();
        
        try {
            cursor = transaccion.executeQuery("SELECT * FROM empleado");
            if (cursor.next()) {
                do{
                    String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13)};
                    resultado.add(renglon);
                }while(cursor.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultado;
        
    }
        
        
    
    
}

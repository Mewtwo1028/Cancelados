package controlador;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Empleado;

public class EmpleadoManager {

    private Conexion conexion;

    public EmpleadoManager() {
        conexion = new Conexion();
    }

    /**
     * Consulta todos los empleados almacenados en la base de datos y devuelve
     * los resultados en un ArrayList de String[].
     *
     * @return ArrayList de String[] con los datos de todos los empleados.
     *
     * @throwsRuntimeException si ocurre un error al consultar los empleados en
     * la base de datos.
     */
    public ArrayList<String[]> consultarTodos() {
        String sql = "SELECT * FROM empleado";
        ArrayList<String[]> resultado = new ArrayList<>();

        try {
            ResultSet cursor = new Conexion().getConexion().prepareStatement(sql).executeQuery();

            while (cursor.next()) {
                String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13)};
                resultado.add(renglon);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar a los empleados", ex);
        }
        return resultado;
    }

    /**
     * Inserta un nuevo registro en la tabla "empleado" con los datos del
     * empleado pasado como parámetro, y crea una nueva credencial asociada a
     * dicho empleado con la contraseña especificada.
     *
     * @param empleado el objeto Empleado que contiene los datos del empleado a
     * insertar
     * @return true si se insertó correctamente el registro del empleado y se
     * creó la credencial, false si no se pudo insertar el registro o crear la
     * credencial
     */
    public boolean insertarEmpleado(Empleado empleado) {
        String SQL_insertar = "INSERT INTO empleado VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, false)";
        String SQL_idEmpleado = "select idEmpleado from empleado order by idEmpleado DESC";
        String SQL_insertarCredencial = "INSERT INTO credenciales VALUES (null, ?, ?)";
        String SQL_empleado = "UPDATE empleado SET restContra = true WHERE idEmpleado = ?";

        try (PreparedStatement psInsertar = conexion.getConexion().prepareStatement(SQL_insertar); PreparedStatement psIdEmpleado = conexion.getConexion().prepareStatement(SQL_idEmpleado); PreparedStatement psInsertarCredencial = conexion.getConexion().prepareStatement(SQL_insertarCredencial);PreparedStatement psContra = conexion.getConexion().prepareStatement(SQL_empleado)) {
            psInsertar.setString(1, empleado.getNombre());
            psInsertar.setString(2, empleado.getaPaterno());
            psInsertar.setString(3, empleado.getaMaterno());
            psInsertar.setString(4, empleado.getCalle());
            psInsertar.setString(5, empleado.getNoExt());
            psInsertar.setString(6, empleado.getColonia());
            psInsertar.setString(7, empleado.getCp());
            psInsertar.setString(8, empleado.getCurp());
            psInsertar.setString(9, empleado.getRfc());
            psInsertar.setString(10, empleado.getMunicipio());
            psInsertar.setString(11, empleado.getEstado());
            psInsertar.setInt(12, empleado.getIdRol());
            psInsertar.executeUpdate();

            ResultSet cursor = psIdEmpleado.executeQuery();
            cursor.next();
            int idEmpleado = cursor.getInt(1);

            psInsertarCredencial.setString(1, "");
            psInsertarCredencial.setInt(2, idEmpleado);
            psInsertarCredencial.execute();
            
            psContra.setInt(1, idEmpleado);
            psContra.execute();

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    /**
     * Elimina un empleado de la base de datos, incluyendo también sus
     * credenciales.
     *
     * @param empleado el objeto Empleado a eliminar
     *
     * @return true si se elimina correctamente, false si ocurre un error
     *
     * @throws RuntimeException si ocurre un error al eliminar al empleado
     */
    public boolean eliminarEmpleado(Empleado empleado) {
        String SQL_borrarCredencial = "DELETE FROM credenciales WHERE Empleado_idEmpleado=?;";
        String SQL_borrarEmpleado = "DELETE FROM empleado WHERE idEmpleado=?;";

        try (PreparedStatement psBorrarCredencial = conexion.getConexion().prepareStatement(SQL_borrarCredencial); PreparedStatement psBorrarEmpleado = conexion.getConexion().prepareStatement(SQL_borrarEmpleado);) {
            psBorrarCredencial.setInt(1, empleado.getIdEmpledo());
            psBorrarCredencial.execute();

            psBorrarEmpleado.setInt(1, empleado.getIdEmpledo());
            psBorrarEmpleado.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    /**
     * Modifica los datos de un empleado en la base de datos.
     *
     * @param empleado el objeto Empleado con los datos actualizados.
     *
     * @return true si la modificación fue exitosa, false en caso contrario.
     *
     * @throws RuntimeException si ocurre un error al modificar el empleado.
     */
    public boolean modificarEmpleado(Empleado empleado) {
        String SQL_actualizar = "UPDATE Empleado SET nombre=?, ApellidoPaterno =?, ApellidoMaterno=?,Calle=?,NoExt=?, Colonia=?,CP=?,Municipio=?,Estado=?,CURP=?,RFC=?, Roles_idRoles=? WHERE idEmpleado=?;";

        try (PreparedStatement cs = conexion.getConexion().prepareStatement(SQL_actualizar)) {

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
            cs.setInt(12, empleado.getIdRol());
            cs.setInt(13, empleado.getIdEmpledo());

            return cs.executeUpdate() == 1;

        } catch (SQLException ex) {
            throw new RuntimeException("Error al modificar el empleado", ex);
        }
    }

    public boolean eliminarEstado(int idEmpleado) {
        String sql = "UPDATE empleado SET restContra = false WHERE idEmpleado=?";

        try (PreparedStatement p = new Conexion().getConexion().prepareStatement(sql)) {

            p.setInt(1, idEmpleado);

            return p.executeUpdate() == 1;

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }

    /**
     * Busca los empleados cuyo apellido materno comience con el texto
     * especificado.
     *
     * @param texto El texto a buscar.
     *
     * @return Una lista de matrices de cadenas que representan a los empleados
     * encontrados, o una lista vacía si no se encontró ningún empleado.
     */
    public ArrayList<String[]> buscarEmpleadoMaterno(String texto) {
        String filtro = texto + "%";
        String SQL = "SELECT * FROM empleado WHERE apellidoMaterno like" + '"' + filtro + '"';
        ArrayList<String[]> resultado = new ArrayList<>();

        try {
            ResultSet rs = conexion.getConexion().prepareStatement(SQL).executeQuery();

            while (rs.next()) {
                String[] renglon = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)};
                resultado.add(renglon);
            }

            return resultado;

        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return resultado;
    }
    
    /**
     * Busca los empleados cuyo apellido paterno comience con el texto
     * especificado.
     *
     * @param texto El texto a buscar.
     *
     * @return Una lista de matrices de cadenas que representan a los empleados
     * encontrados, o una lista vacía si no se encontró ningún empleado.
     */
    public ArrayList<String[]> buscarEmpleadoPaterno(String texto) {
        String filtro = texto + "%";
        String SQL = "SELECT * FROM empleado WHERE apellidoPaterno like" + '"' + filtro + '"';
        ArrayList<String[]> resultado = new ArrayList<>();

        try {
            ResultSet rs = conexion.getConexion().prepareStatement(SQL).executeQuery();

            while (rs.next()) {
                String[] renglon = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)};
                resultado.add(renglon);
            }

            return resultado;

        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return resultado;
    }
    
    /**
     * Busca los empleados cuyo nombre comience con el texto
     * especificado.
     *
     * @param texto El texto a buscar.
     *
     * @return Una lista de matrices de cadenas que representan a los empleados
     * encontrados, o una lista vacía si no se encontró ningún empleado.
     */
    public ArrayList<String[]> buscarEmpleadoNombre(String texto) {
        String filtro = texto + "%";
        String SQL = "SELECT * FROM empleado WHERE nombre like" + '"' + filtro + '"';
        ArrayList<String[]> resultado = new ArrayList<>();

        try {
            ResultSet rs = conexion.getConexion().prepareStatement(SQL).executeQuery();

            while (rs.next()) {
                String[] renglon = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)};
                resultado.add(renglon);
            }

            return resultado;

        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return resultado;
    }

}

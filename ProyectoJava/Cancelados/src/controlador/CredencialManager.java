package controlador;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class CredencialManager {

    private Conexion conexion;

    public CredencialManager() {
        conexion = new Conexion();
    }

    /**
     * Verifica que el campo estadoEmpleado del empleado sea 'ELIMINADO'.
     *
     * @param idEmpleado el id del empleado a verificar
     * @return true si el campo estadoEmpleado esta 'ELIMINADO', false en
     * cualquier otro caso.
     */
    public boolean isEmpleadoEstadoEliminado(int idEmpleado) {
        String SQL_verificar = "Select estadoEmpleado from empleado where idEmpleado=?;";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(SQL_verificar)) {

            ps.setInt(1, idEmpleado);

            ResultSet cursor = ps.executeQuery();

            if (cursor.next()) {
                return cursor.getString(1).equals("ELIMINADO");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    /**
     * Verifica si un usuario puede iniciar sesión en el sistema.
     *
     * @param nombreCredencial el nombre del usuario que intenta iniciar sesión.
     *
     * @param contrasena la contraseña del usuario que intenta iniciar sesión.
     *
     * @return el ID del rol del usuario si las credenciales son válidas, de lo
     * contrario una cadena vacía.
     */
    public String verificarLogin(String nombreCredencial, String contrasena) {
        String SQL_verificar = "Select E.Roles_idRoles from empleado E inner join Credenciales C On (C.Empleado_idEmpleado = E.idEmpleado) where nombreUsuario=? and contrasena=?";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(SQL_verificar)) {

            ps.setString(1, nombreCredencial);
            ps.setString(2, contrasena);

            ResultSet cursor = ps.executeQuery();

            if (cursor.next()) {
                String idRol = cursor.getString(1);
                return idRol;
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error al verificar el login", ex);
        }
        return "";
    }

    /**
     * Metodo para notificar al administrador de que un empleado a olvidado su
     * contraseña
     *
     * @param nombreEmpleado que olvido la contraseña
     * @return true si correcto, false en cualquier otro caso
     */
    public boolean notificarContra(String nombreEmpleado) {

        int idEmpleado = existeEmpleado(nombreEmpleado);

        if (idEmpleado == -1) {
            return false;
        }

        String SQL = "INSERT INTO notificacion (idEmpleado) VALUES (?);";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(SQL)) {

            ps.setInt(1, idEmpleado);

            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    /**
     * Metodo para verificar si un empleado existe
     *
     * @param nombreEmpleado
     * @return el idEmpleado o -1 en caso de que no exista
     */
    private int existeEmpleado(String nombreEmpleado) {
        String SQL_verificar = "Select idEmpleado from empleado where nombreUsuario=?;";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(SQL_verificar)) {

            ps.setString(1, nombreEmpleado);

            ResultSet cursor = ps.executeQuery();

            if (cursor.next()) {
                return cursor.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return -1;
    }

    public boolean isRestContra(String nombre) {
        String SQL = "SELECT restContra FROM empleado WHERE nombreUsuario=?";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(SQL)) {

            ps.setString(1, nombre);

            ResultSet cursor = ps.executeQuery();

            if (cursor.next()) {
                return cursor.getBoolean(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public int getidEmpleado(String nombreEmpleado) {
        String SQL_verificar = "Select idEmpleado from empleado where nombreUsuario=? ORDER BY idEmpleado ASC;";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(SQL_verificar)) {

            ps.setString(1, nombreEmpleado);

            ResultSet cursor = ps.executeQuery();

            if (cursor.next()) {
                return cursor.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return -1;
    }

    public int getIdEmpleado(String nombreEmpleado, String contra) {
        String SQL_verificar = "Select c.Empleado_idEmpleado from empleado E inner join Credenciales C On (C.Empleado_idEmpleado = E.idEmpleado) where nombreUsuario=? and contrasena=?";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(SQL_verificar)) {

            ps.setString(1, nombreEmpleado);
            ps.setString(2, contra);

            ResultSet cursor = ps.executeQuery();

            if (cursor.next()) {
                return cursor.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return -1;
    }

}

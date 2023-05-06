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
        String SQL_verificar = "Select E.Roles_idRoles from empleado E inner join Credenciales C On (C.Empleado_idEmpleado = E.idEmpleado) where nombre=? and contrasena=?";

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

        String SQL = "INSERT INTO notificacion VALUES (null, ?)";

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
        String SQL_verificar = "Select idEmpleado from empleado where nombre=?;";

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

}

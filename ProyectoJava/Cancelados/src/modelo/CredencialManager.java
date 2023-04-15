package modelo;

import controlador.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

}

package controlador;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotificacionManager {

    public ArrayList<String[]> consultarTodos() {
        String sql = "SELECT * FROM vista_not_emp ORDER BY idNotificacion DESC";
        ArrayList<String[]> resultado = new ArrayList<>();

        try {
            ResultSet cursor = new Conexion().getConexion().prepareStatement(sql).executeQuery();

            while (cursor.next()) {
                String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7)};
                resultado.add(renglon);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return resultado;
    }

    public boolean restaurarContrasena(int idEmpleado) {
        String sql = "UPDATE empleado SET restContra = true WHERE idEmpleado=?";

        try (PreparedStatement p = new Conexion().getConexion().prepareStatement(sql)) {

            p.setInt(1, idEmpleado);

            return p.executeUpdate() == 1;

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }

    public boolean eliminarNotEmpleado(int idEmpleado) {
        String sql = "DELETE FROM notificacion WHERE idEmpleado=?";

        try (PreparedStatement p = new Conexion().getConexion().prepareStatement(sql)) {

            p.setInt(1, idEmpleado);

            p.execute();

            return true;

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }

    public boolean eliminarNotificacion(int idNotificacion) {
        String sql = "DELETE FROM notificacion WHERE idNotificacion=?";

        try (PreparedStatement p = new Conexion().getConexion().prepareStatement(sql)) {

            p.setInt(1, idNotificacion);

            p.execute();

            return true;

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }

}

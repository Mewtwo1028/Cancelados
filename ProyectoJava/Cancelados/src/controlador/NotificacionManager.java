package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotificacionManager {

    public ArrayList<String[]> consultarTodos() {
        String sql = "SELECT * FROM vista_not_emp";
        ArrayList<String[]> resultado = new ArrayList<>();

        try {
            ResultSet cursor = new Conexion().getConexion().prepareStatement(sql).executeQuery();

            while (cursor.next()) {
                String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)};
                resultado.add(renglon);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return resultado;
    }

}

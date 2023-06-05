package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnviosManager {
    
    public ArrayList<String[]> getDireccionesUsa(String estado, String ciudad, String zip) {

        String sql = "SELECT state, city, zipcode FROM us WHERE ";

        // Lista para almacenar las condiciones del filtro
        ArrayList<String> condiciones = new ArrayList<>();

        // Verificar si cada campo de texto está vacío o no, y agregar la condición correspondiente si no está vacío
        if (!estado.isBlank()) {
            condiciones.add("state LIKE '%" + estado + "%'");
        }
        if (!ciudad.isBlank()) {
            condiciones.add("city LIKE '%" + ciudad + "%'");
        }
        if (!zip.isBlank()) {
            condiciones.add("zipcode LIKE '%" + zip + "%'");
        }

        // Combinar las condiciones utilizando la cláusula AND
        if (!condiciones.isEmpty()) {
            sql += String.join(" AND ", condiciones);
        } else {
            // Si no hay condiciones, obtener todos los registros
            sql += "1"; // Condición siempre verdadera
        }

        ArrayList<String[]> resultado = new ArrayList<>();
        try (ResultSet cursor = new Conexion().getConexion().prepareStatement(sql).executeQuery()) {

            while (cursor.next()) {
                String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3)};
                resultado.add(renglon);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar las direcciones de USA", ex);
        }

        return resultado;
    }

    public ArrayList<String[]> getDireccionesMex(String estado, String municipio, String colonia, String cp) {

        String sql = "SELECT estado, municipio, colonia, cp FROM codpos WHERE ";

        // Lista para almacenar las condiciones del filtro
        ArrayList<String> condiciones = new ArrayList<>();

        // Verificar si cada campo de texto está vacío o no, y agregar la condición correspondiente si no está vacío
        if (!estado.isBlank()) {
            condiciones.add("estado LIKE '%" + estado + "%'");
        }
        if (!municipio.isBlank()) {
            condiciones.add("municipio LIKE '%" + municipio + "%'");
        }
        if (!colonia.isBlank()) {
            condiciones.add("colonia LIKE '%" + colonia + "%'");
        }
        if (!cp.isBlank()) {
            condiciones.add("cp LIKE '%" + cp + "%'");
        }

        // Combinar las condiciones utilizando la cláusula AND
        if (!condiciones.isEmpty()) {
            sql += String.join(" AND ", condiciones);
        } else {
            // Si no hay condiciones, obtener todos los registros
            sql += "1"; // Condición siempre verdadera
        }

        ArrayList<String[]> resultado = new ArrayList<>();
        try (ResultSet cursor = new Conexion().getConexion().prepareStatement(sql).executeQuery()) {

            while (cursor.next()) {
                String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)};
                resultado.add(renglon);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar las direcciones de Mexico", ex);
        }

        return resultado;
    }

    public ArrayList<String[]> consultarCPMex(String cp, String estado, String municipio, String colonia) {
        String sql = "SELECT * FROM codpos WHERE cp like ? or estado like ? or municipio like ?  or colonia like ?;";
        ArrayList<String[]> resultado = new ArrayList<>();
        try {
            PreparedStatement stmt = new Conexion().getConexion().prepareStatement(sql);
            stmt.setString(1, cp);
            stmt.setString(2, estado);
            stmt.setString(3, municipio);
            stmt.setString(4, colonia);
            ResultSet cursor = stmt.executeQuery();

            while (cursor.next()) {
                String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10)};
                resultado.add(renglon);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar los códigos postales", ex);
        }

        return resultado;
    }

    public ArrayList<String[]> consultarCPUsa(String cp, String estado, String municipio, String colonia) {
        String sql = "SELECT * FROM us WHERE zipcode like ? or state like ? or city like ?;";
        ArrayList<String[]> resultado = new ArrayList<>();
        try {
            PreparedStatement stmt = new Conexion().getConexion().prepareStatement(sql);
            stmt.setString(1, cp);
            stmt.setString(2, municipio);
            stmt.setString(3, estado);
            ResultSet cursor = stmt.executeQuery();

            while (cursor.next()) {
                String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)};
                resultado.add(renglon);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar los códigos postales", ex);
        }

        return resultado;
    }

    public ArrayList<String[]> consultarCP(String cp, String estado, String municipio, String colonia, int pais) {
        return pais == 0 ? consultarCPMex(cp, estado, municipio, colonia) : consultarCPUsa(cp, estado, municipio, colonia);
    }

}

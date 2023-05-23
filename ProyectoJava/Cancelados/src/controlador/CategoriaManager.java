package controlador;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaManager {

    private Conexion conexion;

    public CategoriaManager() {
        conexion = new Conexion();
    }

    public Object[] getCategorias() {
        ArrayList<String> resultado = new ArrayList<>();
        resultado.add("SELECCIONA UNO");
        String sql = "SELECT nombre FROM categoria;";

        try (ResultSet cursor = conexion.getConexion().prepareStatement(sql).executeQuery()) {
            while (cursor.next()) {
                resultado.add(cursor.getString(1));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar a las categorias", ex);
        }
        return resultado.toArray();

    }
    
    public boolean registrarCategoria(String nombreCategoria){
        String sql = "INSERT INTO categoria VALUES (null, ?);";

        try (PreparedStatement p = conexion.getConexion().prepareStatement(sql)) {

            p.setString(1, nombreCategoria);

            return p.executeUpdate() == 1;

        } catch (SQLException ex) {
            System.out.println("Error al consultar a las categorias" + ex.getMessage());
        }
        return false;
    }

    public int getIdCategoria(String nombreCategoria) {
        String sql = "SELECT idCategoria FROM categoria WHERE nombre = ?;";

        try (PreparedStatement p = conexion.getConexion().prepareStatement(sql)) {

            p.setString(1, nombreCategoria);

            ResultSet c = p.executeQuery();

            if (c.next()) {
                return c.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error al consultar a las categorias" + ex.getMessage());
        }
        return -1;
    }

    public boolean existeCategoria(String nombreCategoria) {
        String sql = "SELECT idCategoria FROM categoria WHERE nombre like ?%";

        try (PreparedStatement p = conexion.getConexion().prepareStatement(sql)) {

            p.setString(1, nombreCategoria);

            return p.executeUpdate() >= 1;

        } catch (SQLException ex) {
            System.out.println("Error al consultar a las categorias" + ex.getMessage());
        }
        return false;
    }

}

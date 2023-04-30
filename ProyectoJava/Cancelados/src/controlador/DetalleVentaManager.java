package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Producto;

public class DetalleVentaManager {

    private Conexion conexion;

    public DetalleVentaManager() {
        conexion = new Conexion();
    }

    /**
     * Consulta todos los productos de una venta almacenados en la base de datos
     * y devuelve los resultados en un ArrayList de String[].
     *
     * @param idVenta que contiene el id de la venta.
     * @return ArrayList de String[] con los productos de una venta.
     *
     * @throwsRuntimeException si ocurre un error al consultar los productos de
     * la venta en la base de datos.
     */
    public ArrayList<String[]> consultarProductos(int idVenta) {
        String sql = "SELECT producto, cantidad, precioUnitario, importe FROM vistaDetalleVenta where idVenta=?";
        ArrayList<String[]> resultado = new ArrayList<>();

        try (PreparedStatement cs = conexion.getConexion().prepareStatement(sql)) {
            cs.setInt(1, idVenta);
            ResultSet cursor = cs.executeQuery();

            while (cursor.next()) {
                String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)};
                resultado.add(renglon);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar los productos", ex);
        }
        return resultado;
    }

    /**
     * Consulta todos los detalles de una venta almacenados en la base de datos
     * y devuelve los resultados en un ArrayList de String.
     *
     * @param idVenta que contiene el id de la venta.
     * @return ArrayList de String con los detalles de una venta.
     *
     * @throwsRuntimeException si ocurre un error al consultar los detalles de
     * una venta en la base de datos.
     */
    public ArrayList<String> consultarTodo(int idVenta) {
        String sql = "SELECT fecha, cliente, empleado FROM vistaDetalleVenta where idVenta=? GROUP BY cliente;";
        ArrayList<String> resultado = new ArrayList<>();

        try (PreparedStatement cs = conexion.getConexion().prepareStatement(sql)) {
            cs.setInt(1, idVenta);
            ResultSet cursor = cs.executeQuery();

            if (cursor.next()) {
                resultado.add(cursor.getString(1));
                resultado.add(cursor.getString(2));
                resultado.add(cursor.getString(3));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar los detalles de la venta", ex);
        }
        return resultado;
    }

}

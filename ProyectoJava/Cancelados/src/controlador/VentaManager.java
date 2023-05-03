package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.Venta;

public class VentaManager {

    Conexion conexion;

    public VentaManager() {
        conexion = new Conexion();
    }

    /**
     * Realiza una venta e inserta los datos en la base de datos.
     *
     * @param venta un objeto de tipo Venta que contiene los datos de la venta
     *
     * @return el ID de la venta recién creada, o -1 si se produce un error
     */
    public int realizarVenta(Venta venta) {
        String consulta1 = "INSERT INTO venta (total, idCliente, idEmpleado, estado) VALUES (?,?,?,?);";
        String consulta2 = "SELECT idVenta FROM venta ORDER BY idVenta DESC LIMIT 1;";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(consulta1); PreparedStatement ps2 = conexion.getConexion().prepareStatement(consulta2)) {

            ps.setFloat(1, venta.getTotal());
            ps.setInt(2, venta.getIdCliente());
            ps.setInt(3, venta.getIdEmpleado());
            ps.setString(4, "VENDIDO");
            ps.execute();

            ResultSet cursor = ps2.executeQuery();

            if (cursor.next()) {
                return cursor.getInt(1);
            }

        } catch (SQLException e) {
            return -1;
        }
        return -1;
    }

    /**
     * Consulta todos las ventas almacenados en la base de datos y devuelve los
     * resultados en un ArrayList de String[].
     *
     * @return ArrayList de String[] con los datos de todos las ventas.
     *
     * @throws RuntimeException si ocurre un error al consultar las ventas en la
     * base de datos.
     */
    public ArrayList<String[]> consultarTodos() {
        String sql = "SELECT * FROM vistaVentas;";
        ArrayList<String[]> resultado = new ArrayList<>();

        try (ResultSet cursor = conexion.getConexion().prepareStatement(sql).executeQuery()) {
            while (cursor.next()) {
                String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)};
                resultado.add(renglon);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar a las ventas", ex);
        }
        return resultado;
    }

    /**
     * Actualiza el estado de una venta en la base de datos.
     *
     * @param venta El objeto Venta que debe de contener el id de una venta.
     * @return true si la actualización se realizó correctamente, false en caso
     * contrario.
     * @throws RuntimeException Si ocurre un error al ejecutar la consulta SQL.
     */
    public boolean cancelarVenta(Venta venta) {
        String sql = "UPDATE venta SET estado=? WHERE idVenta=?;";

        try (PreparedStatement cs = conexion.getConexion().prepareStatement(sql)) {

            cs.setString(1, venta.getEstado());
            cs.setInt(2, venta.getIdVenta());

            if (cs.executeUpdate() == 1) {
                devolverInventario(venta);
                return true;
            }
            return false;

        } catch (SQLException ex) {
            throw new RuntimeException("Error al modificar el estado de la venta", ex);
        }
    }

    private boolean devolverInventario(Venta venta) {
        String consulta = "SELECT idProducto, cantidad FROM detalleVenta WHERE idVenta = ?;";
        String modificar = "UPDATE producto SET stock = stock + ? WHERE idProducto = ?;";

        try (PreparedStatement cs = conexion.getConexion().prepareStatement(consulta)) {

            cs.setInt(1, venta.getIdVenta());
            ResultSet cursor = cs.executeQuery();

            while (cursor.next()) {
                PreparedStatement up = conexion.getConexion().prepareStatement(modificar);
                up.setInt(1, cursor.getInt("cantidad"));
                System.out.println(cursor.getInt("cantidad"));
                up.setInt(2, cursor.getInt("idProducto"));
                System.out.println(cursor.getInt("idProducto"));
                up.executeUpdate();
            }
            return true;

        } catch (SQLException ex) {
            return false;
        }
    }
}

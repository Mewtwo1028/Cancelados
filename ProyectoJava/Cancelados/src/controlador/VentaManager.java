package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Venta;

public class VentaManager {

    Conexion conexion;

    public VentaManager() {
        conexion = new Conexion();
    }

    /**
     * Realiza una venta e inserta los datos en la base de datos.
     *
     * @param venta un objeto de tipo Venta que contiene los datos de la
     * venta
     *
     * @return el ID de la venta recién creada, o -1 si se produce un error
     */
    public int realizarVenta(Venta venta) {
        String consulta1 = "INSERT INTO venta (total, idCliente, idEmpleado) VALUES (?,?,?);";
        String consulta2 = "SELECT idVenta FROM venta ORDER BY idVenta DESC LIMIT 1;";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(consulta1); PreparedStatement ps2 = conexion.getConexion().prepareStatement(consulta2)) {

            ps.setFloat(1, venta.getTotal());
            ps.setInt(2, venta.getIdCliente());
            ps.setInt(3, venta.getIdEmpleado());
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
}

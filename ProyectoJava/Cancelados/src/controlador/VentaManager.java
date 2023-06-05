package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.Direccion;
import modelo.Envio;
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
     * @param tipoVenta el tipo de la venta
     *
     * @return el ID de la venta recién creada, o -1 si se produce un error
     */
    public int realizarVenta(Venta venta, char tipoVenta) {
        String consulta1 = "INSERT INTO venta (total, idCliente, idEmpleado, estado,TipoVenta) VALUES (?,?,?,?,?);";
        String consulta2 = "SELECT idVenta FROM venta ORDER BY idVenta DESC LIMIT 1;";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(consulta1); PreparedStatement ps2 = conexion.getConexion().prepareStatement(consulta2)) {

            ps.setFloat(1, venta.getTotal());
            ps.setInt(2, venta.getIdCliente());
            ps.setInt(3, venta.getIdEmpleado());
            ps.setString(4, "VENDIDO");
            ps.setString(5, String.valueOf(tipoVenta));
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

    public int realizarVentaEnvio(Venta venta, char tipoVenta) {
        String consulta1 = "INSERT INTO venta (total, idCliente, idEmpleado, estado,TipoVenta) VALUES (?,?,?,?,?);";
        String consulta2 = "SELECT idVenta FROM venta ORDER BY idVenta DESC LIMIT 1;";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(consulta1); PreparedStatement ps2 = conexion.getConexion().prepareStatement(consulta2)) {

            ps.setFloat(1, venta.getTotal());
            ps.setInt(2, venta.getIdCliente());
            ps.setInt(3, venta.getIdEmpleado());
            ps.setString(4, "ENVIO-PENDIENTE");
            ps.setString(5, String.valueOf(tipoVenta));
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

    public void realizarDireccion(int idCliente, int idVenta, Direccion direccion) {
        String consulta4 = "SELECT estado, ciudad, colonia, cp, calle FROM cliente WHERE idCliente = ?;";
        String consulta3 = "INSERT INTO direccionClienteGeneral VALUES (null, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement p4 = conexion.getConexion().prepareStatement(consulta4); PreparedStatement p3 = conexion.getConexion().prepareStatement(consulta3);) {

            if (direccion == null) {
                //llenar la direccion con la del cliente
                p4.setInt(1, idCliente);
                ResultSet c4 = p4.executeQuery();
                
                if (c4.next()) {
                    direccion = new Direccion();
                    direccion.setPais("Mexico");
                    direccion.setEstado(c4.getString(1));
                    direccion.setMunicipio(c4.getString(2));
                    direccion.setColonia(c4.getString(3));
                    direccion.setCp(c4.getString(4));
                    direccion.setCalle(c4.getString(5));
                }
            }

            if (direccion.getPais().equals("Mexico")) {
                p3.setString(1, direccion.getPais());
                p3.setString(2, direccion.getEstado());
                p3.setString(3, direccion.getMunicipio());
                p3.setString(4, direccion.getColonia());
                p3.setString(5, direccion.getCp());
                p3.setString(6, direccion.getCalle());
                p3.setInt(7, idVenta);
            } else {
                p3.setString(1, direccion.getPais());
                p3.setString(2, direccion.getEstado());
                p3.setString(3, direccion.getCiudad());
                p3.setString(4, "");
                p3.setString(5, direccion.getZip());
                p3.setString(6, direccion.getCalle());
                p3.setInt(7, idVenta);
            }

            p3.execute();

        } catch (SQLException e) {
            System.out.println("error al registrar la direccion");
        }
    }

    public int realizarVentaEnvio3(Venta venta, char tipoVenta, Direccion direccion) {
        String consulta1 = "INSERT INTO venta (total, idCliente, idEmpleado, estado, TipoVenta) VALUES (?,?,?,?,?);";
        String consulta2 = "SELECT idVenta FROM venta ORDER BY idVenta DESC LIMIT 1;";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(consulta1); PreparedStatement ps2 = conexion.getConexion().prepareStatement(consulta2)) {

            ps.setFloat(1, venta.getTotal());
            ps.setInt(2, venta.getIdCliente());
            ps.setInt(3, venta.getIdEmpleado());
            ps.setString(4, "ENVIO-PENDIENTE");
            ps.setString(5, String.valueOf(tipoVenta));
            ps.execute();

            ResultSet cursor = ps2.executeQuery();

            if (cursor.next()) {

                int idVenta = cursor.getInt(1);

                if (direccion.getPais().isBlank()) {
                    //llenar la direccion con la del cliente
                    String consulta4 = "SELECT estado, ciudad, colonia, cp, calle FROM cliente WHERE idCliente = ?;";
                    PreparedStatement p4 = conexion.getConexion().prepareStatement(consulta4);
                    p4.setInt(1, venta.getIdCliente());

                    ResultSet c4 = p4.executeQuery();

                    if (c4.next()) {
                        direccion.setPais("Mexico");
                        direccion.setEstado(c4.getString(1));
                        direccion.setMunicipio(c4.getString(2));
                        direccion.setColonia(c4.getString(3));
                        direccion.setCp(c4.getString(4));
                        direccion.setCalle(c4.getString(5));
                    }
                }

                String consulta3 = "INSERT INTO direccionClienteGeneral VALUES (null, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement p3 = conexion.getConexion().prepareStatement(consulta3);

                if (direccion.getPais().equals("Mexico")) {
                    p3.setString(1, direccion.getPais());
                    p3.setString(2, direccion.getEstado());
                    p3.setString(3, direccion.getMunicipio());
                    p3.setString(4, direccion.getColonia());
                    p3.setString(5, direccion.getCp());
                    p3.setString(6, direccion.getCalle());
                    p3.setInt(7, direccion.getIdVenta());
                } else {
                    p3.setString(1, direccion.getPais());
                    p3.setString(2, direccion.getEstado());
                    p3.setString(3, direccion.getCiudad());
                    p3.setString(4, "");
                    p3.setString(5, direccion.getZip());
                    p3.setString(6, direccion.getCalle());
                    p3.setInt(7, direccion.getIdVenta());
                }

                p3.execute();

                return idVenta;
            }

        } catch (SQLException e) {
            return -1;
        }
        return -1;
    }

    public int realizarEnvio(Venta venta, char tipoVenta, Envio env) {
        String consulta = "INSERT INTO envios (Destinatario,Domicilio,Ciudad,Calle,Colonia,Numero,Producto,Paqueteria,CP,Responsable,Peso,Fecha,Remitente,pais) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(consulta);) {
            ps.setString(1, env.getDestinatario());
            ps.setString(2, env.getDomicilio());
            ps.setString(3, env.getCiudad());
            ps.setString(4, env.getCalle());
            ps.setString(5, env.getColonia());
            ps.setString(6, env.getNumero());
            ps.setString(7, env.getProducto());
            ps.setString(8, env.getPaqueteria());
            ps.setString(9, env.getCp());
            ps.setInt(10, env.getResponsable()); //id de quien realiza la venta
            ps.setString(11, env.getPeso());
            ps.setString(12, env.getFecha());
            ps.setString(13, "Cancelados");
            ps.setString(14, env.getPais());

        } catch (SQLException e) {
            return -1;
        }

        return -1;
    }

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

    public int getIdUltimaVenta() {
        String sql = "SELECT idVenta FROM venta ORDER BY idVenta DESC LIMIT 1;";

        try (PreparedStatement cs = conexion.getConexion().prepareStatement(sql)) {

            ResultSet c = cs.executeQuery();

            if (c.next()) {
                return c.getInt("idVenta");
            }

            return -1;

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
                //System.out.println(cursor.getInt("cantidad"));
                up.setInt(2, cursor.getInt("idProducto"));
                //System.out.println(cursor.getInt("idProducto"));
                up.executeUpdate();
            }
            return true;

        } catch (SQLException ex) {
            return false;
        }
    }
}

package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Cliente;

public class ClienteManager {

    private Conexion conexion;

    public ClienteManager() {
        conexion = new Conexion();
    }

    /**
     * Consulta todos los clientes almacenados en la base de datos y devuelve
     * los resultados en un ArrayList de String[].
     *
     * @return ArrayList de String[] con los datos de todos los clientes.
     *
     * @throws RuntimeException si ocurre un error al consultar los clientes en
     * la base de datos.
     */
    public ArrayList<String[]> consultarTodos() {
        String sql = "SELECT * FROM cliente;";
        ArrayList<String[]> resultado = new ArrayList<>();

        try (ResultSet cursor = conexion.getConexion().prepareStatement(sql).executeQuery()) {
            while (cursor.next()) {
                String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9)};
                resultado.add(renglon);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar a los clientes", ex);
        }
        return resultado;
    }

    /**
     * Consulta la lista de clientes desde la base de datos y devuelve un
     * ArrayList con los nombres y sus IDs.
     *
     * @return ArrayList de Cliente que contiene la lista de nombres y IDs de
     * los clientes.
     * @throws RuntimeException Si ocurre un error al consultar la lista de
     * clientes.
     */
    public ArrayList<Cliente> consultarNombres() {
        String sql = "SELECT idCliente, nombre FROM cliente;";
        ArrayList<Cliente> resultado = new ArrayList<>();

        try (ResultSet cursor = conexion.getConexion().prepareStatement(sql).executeQuery()) {
            while (cursor.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(cursor.getInt(1));
                cliente.setNombre(cursor.getString(2));
                resultado.add(cliente);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar clientes", ex);
        }
        return resultado;
    }

    /**
     * Inserta un nuevo cliente en la base de datos.
     *
     * @param cliente El objeto Cliente que contiene los datos del cliente a
     * insertar.
     * @return true si se insertó el cliente correctamente, false en caso
     * contrario.
     * @throws RuntimeException si ocurre un error al insertar el cliente.
     */
    public boolean insertarCliente(Cliente cliente) {
        String consulta = "INSERT INTO cliente VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(consulta)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getaPaterno());
            ps.setString(3, cliente.getaMaterno());
            ps.setString(4, cliente.getCalle());
            ps.setString(5, cliente.getColonia());
            ps.setString(6, cliente.getCiudad());
            ps.setString(7, cliente.getEstado());
            ps.setString(8, cliente.getCp());

            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            throw new RuntimeException("Error al insertar el cliente", ex);
        }
    }

    /**
     * Actualiza los datos de un cliente en la base de datos.
     *
     * @param cliente El objeto Cliente con los nuevos datos.
     * @return true si la actualización se realizó correctamente, false en caso
     * contrario.
     * @throws RuntimeException Si ocurre un error al ejecutar la consulta SQL.
     */
    public boolean modificarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET nombre=?, ApellidoPaterno=?, ApellidoMaterno=?, calle=?, colonia=?, ciudad=?, estado=?, cp=? WHERE idCliente=?;";

        try (PreparedStatement cs = conexion.getConexion().prepareStatement(sql)) {

            cs.setString(1, cliente.getNombre());
            cs.setString(2, cliente.getaPaterno());
            cs.setString(3, cliente.getaMaterno());
            cs.setString(4, cliente.getCalle());
            cs.setString(5, cliente.getColonia());
            cs.setString(6, cliente.getCiudad());
            cs.setString(7, cliente.getEstado());
            cs.setString(8, cliente.getCp());
            cs.setInt(9, cliente.getIdCliente());

            return cs.executeUpdate() == 1;

        } catch (SQLException ex) {
            throw new RuntimeException("Error al modificar al cliente", ex);
        }
    }

    /**
     * Elimina un cliente de la base de datos.
     *
     * @param cliente El objeto Cliente que se va a eliminar con el idCliente.
     * @return true si se elimina el cliente correctamente, false si no se puede
     * eliminar.
     * @throws RuntimeException Si ocurre un error al eliminar el cliente.
     */
    public boolean eliminarCliente(Cliente cliente) {
        String sql = "DELETE FROM cliente WHERE idCliente=?;";

        try (PreparedStatement cs = conexion.getConexion().prepareStatement(sql)) {
            cs.setInt(1, cliente.getIdCliente());
            cs.execute();
            return true;
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());            
        }
        return false;
    }

    /**
     * Busca los clientes cuyo nombre comience con el texto especificado.
     *
     * @param texto El texto a buscar.
     *
     * @return Una lista de matrices de cadenas que representan a los clientes
     * encontrados, o una lista vacía si no se encontró ningún cliente.
     */
    public ArrayList<String[]> buscarClienteNombre(String texto) {
        String filtro = texto + "%";
        String SQL = "SELECT * FROM cliente WHERE nombre like" + '"' + filtro + '"';
        ArrayList<String[]> resultado = new ArrayList<>();

        try {
            ResultSet rs = conexion.getConexion().prepareStatement(SQL).executeQuery();

            while (rs.next()) {
                String[] renglon = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)};
                resultado.add(renglon);
            }

            return resultado;

        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return resultado;
    }

    /**
     * Busca el nombre, apellido paterno, apellido materno, calle y colonia de
     * los clientes cuyo nombre comience con el texto especificado.
     *
     * @param texto El texto a buscar.
     *
     * @return Una lista de matrices de cadenas que representan a los clientes
     * encontrados, o una lista vacía si no se encontró ningún cliente.
     */
    public ArrayList<String[]> buscarClienteNombreEx(String texto) {
        String filtro = texto + "%";
        String SQL = "SELECT idCliente, nombre, apellidoPaterno, apellidoMaterno, calle, colonia FROM cliente WHERE nombre like" + '"' + filtro + '"';
        ArrayList<String[]> resultado = new ArrayList<>();

        try {
            ResultSet rs = conexion.getConexion().prepareStatement(SQL).executeQuery();

            while (rs.next()) {
                String[] renglon = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
                resultado.add(renglon);
            }

            return resultado;

        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return resultado;
    }

    /**
     * Busca los clientes cuyo apellido paterno comience con el texto
     * especificado.
     *
     * @param texto El texto a buscar.
     *
     * @return Una lista de matrices de cadenas que representan a los clientes
     * encontrados, o una lista vacía si no se encontró ningún cliente.
     */
    public ArrayList<String[]> buscarClientePaterno(String texto) {
        String filtro = texto + "%";
        String SQL = "SELECT * FROM cliente WHERE apellidoPaterno like" + '"' + filtro + '"';
        ArrayList<String[]> resultado = new ArrayList<>();

        try {
            ResultSet rs = conexion.getConexion().prepareStatement(SQL).executeQuery();

            while (rs.next()) {
                String[] renglon = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)};
                resultado.add(renglon);
            }

            return resultado;

        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return resultado;
    }

    /**
     * Busca el nombre, apellido paterno, apellido materno, calle y colonia de
     * los clientes cuyo apellido paterno comience con el texto especificado.
     *
     * @param texto El texto a buscar.
     *
     * @return Una lista de matrices de cadenas que representan a los clientes
     * encontrados, o una lista vacía si no se encontró ningún cliente.
     */
    public ArrayList<String[]> buscarClientePaternoEx(String texto) {
        String filtro = texto + "%";
        String SQL = "SELECT idCliente, nombre, apellidoPaterno, apellidoMaterno, calle, colonia FROM cliente WHERE apellidoPaterno like" + '"' + filtro + '"';
        ArrayList<String[]> resultado = new ArrayList<>();

        try {
            ResultSet rs = conexion.getConexion().prepareStatement(SQL).executeQuery();

            while (rs.next()) {
                String[] renglon = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
                resultado.add(renglon);
            }

            return resultado;

        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return resultado;
    }

    /**
     * Busca los clientes cuyo apellido materno comience con el texto
     * especificado.
     *
     * @param texto El texto a buscar.
     *
     * @return Una lista de matrices de cadenas que representan a los clientes
     * encontrados, o una lista vacía si no se encontró ningún cliente.
     */
    public ArrayList<String[]> buscarClienteMaterno(String texto) {
        String filtro = texto + "%";
        String SQL = "SELECT * FROM cliente WHERE apellidoMaterno like" + '"' + filtro + '"';
        ArrayList<String[]> resultado = new ArrayList<>();

        try {
            ResultSet rs = conexion.getConexion().prepareStatement(SQL).executeQuery();

            while (rs.next()) {
                String[] renglon = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)};
                resultado.add(renglon);
            }

            return resultado;

        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return resultado;
    }

    /**
     * Busca el nombre, apellido paterno, apellido materno, calle y colonia de
     * los clientes cuyo apellido materno comience con el texto especificado.
     *
     * @param texto El texto a buscar.
     *
     * @return Una lista de matrices de cadenas que representan a los clientes
     * encontrados, o una lista vacía si no se encontró ningún cliente.
     */
    public ArrayList<String[]> buscarClienteMaternoEx(String texto) {
        String filtro = texto + "%";
        String SQL = "SELECT idCliente, nombre, apellidoPaterno, apellidoMaterno, calle, colonia FROM cliente WHERE apellidoMaterno like" + '"' + filtro + '"';
        ArrayList<String[]> resultado = new ArrayList<>();

        try {
            ResultSet rs = conexion.getConexion().prepareStatement(SQL).executeQuery();

            while (rs.next()) {
                String[] renglon = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
                resultado.add(renglon);
            }

            return resultado;

        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return resultado;
    }
}

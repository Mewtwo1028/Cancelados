package Código;

import ArchivosBD.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private String curp;
    private String calle;
    private String colonia;
    private String ciudad;
    private String estado;
    private String cp;

    public Cliente(int idCliente, String nombre, String aPaterno, String aMaterno, String curp, String calle, String colonia, String ciudad, String estado, String cp) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.curp = curp;
        this.calle = calle;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.estado = estado;
        this.cp = cp;
    }

    public Cliente(String nombre, String aPaterno, String aMaterno, String curp, String calle, String colonia, String ciudad, String estado, String cp) {
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.curp = curp;
        this.calle = calle;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.estado = estado;
        this.cp = cp;
    }

    public Cliente() {

    }

    public Cliente(int idCliente) {
        this.idCliente = idCliente;
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

        try {
            ResultSet cursor = new Conexion().getConexion().prepareStatement(sql).executeQuery();
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
     * Elimina un cliente de la base de datos.
     *
     * @param cliente El objeto Cliente que se va a eliminar con el idCliente.
     * @return true si se elimina el cliente correctamente, false si no se puede
     * eliminar.
     * @throws RuntimeException Si ocurre un error al eliminar el cliente.
     */
    public boolean eliminarCliente(Cliente cliente) {
        String sql = "DELETE FROM cliente WHERE idCliente=?;";

        try {
            PreparedStatement cs = new Conexion().getConexion().prepareStatement(sql);
            cs.setInt(1, cliente.getIdCliente());
            return cs.executeUpdate() == 1;
        } catch (SQLException ex) {
            throw new RuntimeException("Error al eliminar el cliente", ex);
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
        String sql = "UPDATE cliente SET nombre=?, ApellidoPaterno=?, ApellidoMaterno=?, curp=?, calle=?, colonia=?, ciudad=?, estado=?, cp=? WHERE idCliente=?;";

        try {
            PreparedStatement cs = new Conexion().getConexion().prepareStatement(sql);

            cs.setString(1, cliente.getNombre());
            cs.setString(2, cliente.getaPaterno());
            cs.setString(3, cliente.getaMaterno());
            cs.setString(4, cliente.getCurp());
            cs.setString(5, cliente.getCalle());
            cs.setString(6, cliente.getColonia());
            cs.setString(7, cliente.getCiudad());
            cs.setString(8, cliente.getEstado());
            cs.setString(9, cliente.getCp());
            cs.setInt(10, cliente.getIdCliente());

            return cs.executeUpdate() == 1;

        } catch (SQLException ex) {
            throw new RuntimeException("Error al modificar al cliente", ex);
        }
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
    public ArrayList<String[]> consultarClientes() {
        String sql = "SELECT * FROM cliente;";
        ArrayList<String[]> resultado = new ArrayList<>();

        try {
            ResultSet cursor = new Conexion().getConexion().prepareStatement(sql).executeQuery();
            while (cursor.next()) {
                String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10)};
                resultado.add(renglon);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar a los clientes", ex);
        }
        return resultado;
    }

    /**
     * Inserta un nuevo cliente en la tabla 'cliente' de la base de datos.
     *
     * @param cliente El objeto Cliente que contiene los datos del cliente a
     * insertar.
     * @return true si se insertó el cliente correctamente, false en caso
     * contrario.
     * @throws RuntimeException si ocurre un error al insertar el cliente.
     */
    public boolean insertarCliente(Cliente cliente) {
        String consulta = "INSERT INTO cliente VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = new Conexion().getConexion().prepareStatement(consulta);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getaPaterno());
            ps.setString(3, cliente.getaMaterno());
            ps.setString(4, cliente.getCurp());
            ps.setString(5, cliente.getCalle());
            ps.setString(6, cliente.getColonia());
            ps.setString(7, cliente.getCiudad());
            ps.setString(8, cliente.getEstado());
            ps.setString(9, cliente.getCp());

            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            throw new RuntimeException("Error al insertar el cliente", ex);
        }
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

}

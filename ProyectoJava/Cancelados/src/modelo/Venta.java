package modelo;

import controlador.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Venta {

    private int idVenta;
    private float total;
    private String fecha;
    private int idCliente;
    private int idEmpleado;

    public Venta() {

    }

    public Venta(float total, int idCliente, int idEmpleado) {
        this.total = total;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
    }

    public int realizarVenta(Venta venta) {
        String consulta1 = "INSERT INTO venta (total, idCliente, idEmpleado) VALUES (?,?,?);";
        String consulta2 = "SELECT idVenta FROM venta ORDER BY idVenta DESC LIMIT 1;";
        Conexion con;
        CallableStatement cs;

        try {
            con = new Conexion();
            cs = con.getConexion().prepareCall(consulta1);

            cs.setFloat(1, venta.getTotal());
            //cs.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
            cs.setInt(2, venta.getIdCliente());
            cs.setInt(3, venta.getIdEmpleado());
            cs.execute();

            ResultSet cursor = con.getConexion().createStatement().executeQuery(consulta2);
            
            if(cursor.next()){
                return cursor.getInt(1);
            }

        } catch (SQLException e) {
            return -1;
        }
        return -1;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

}

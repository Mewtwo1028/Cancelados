package modelo;

import controlador.Conexion;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DetalleVenta {

    private int idDetalleVenta;
    int idVenta;
    int cantidad;
    float precioUnitario;
    float importe;
    int idProducto;

    public DetalleVenta() {

    }

    public DetalleVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public DetalleVenta(int idVenta, int cantidad, float precioUnitario, float importe, int idProducto) {
        this.idVenta = idVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.idProducto = idProducto;
    }

    public boolean realizarDetalleVenta(ArrayList<Producto> productos, int idVenta) {
        String consulta = "INSERT INTO detalleVenta VALUES (null,?,?,?,?,?);";
        Conexion con;
        CallableStatement cs;

        try {
            con = new Conexion();

            for (Producto producto : productos) {
                cs = con.getConexion().prepareCall(consulta);
                cs.setInt(1, idVenta);
                cs.setInt(2, producto.getStock());
                cs.setFloat(3, producto.getPrecioUnitario());
                cs.setFloat(4, producto.getImporte());
                cs.setInt(5, producto.getIdProducto());
                cs.execute();
            }

            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

}

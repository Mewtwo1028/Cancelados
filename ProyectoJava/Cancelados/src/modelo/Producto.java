package modelo;

import controlador.Conexion;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Producto {

    private int idProducto;
    private String nombre;
    private String descripcion;
    private float precioUnitario;
    private String imagen;
    private int stock;
    private String autor;
    private float importe;

    public Producto() {

    }

    public Producto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(String nombre, String descripcion, float precioUnitario, String imagen, int stock, String autor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.imagen = imagen;
        this.stock = stock;
        this.autor = autor;
    }

    public Producto(int idProducto, String nombre, String descripcion, float precioUnitario, String imagen, int stock, String autor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.imagen = imagen;
        this.stock = stock;
        this.autor = autor;
    }

    public ArrayList<Producto> consultarNombres() {
        ArrayList<Producto> resultado = new ArrayList<>();
        Producto producto;

        ResultSet cursor;
        String consulta = "SELECT idProducto, nombre, precioUnitario, stock FROM producto;";

        try {
            Conexion con = new Conexion();
            cursor = con.getTransaccion().executeQuery(consulta);
            if (cursor.next()) {
                do {
                    producto = new Producto();
                    producto.setIdProducto(cursor.getInt(1));
                    producto.setNombre(cursor.getString(2));
                    producto.setPrecioUnitario(cursor.getFloat(3));
                    producto.setStock(cursor.getInt(4));
                    resultado.add(producto);
                } while (cursor.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public String consultarPrecio(Producto producto) {
        String consulta = "SELECT precioUnitario FROM producto WHERE nombre=?;";

        try {
            Conexion con = new Conexion();
            PreparedStatement cs = con.getConexion().prepareStatement(consulta);
            cs.setString(1, String.valueOf(producto.getNombre()));
            ResultSet cursor = cs.executeQuery();
            if (cursor.next()) {
                do {
                    return cursor.getString(1);
                } while (cursor.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public ArrayList<String[]> consultarTodos() {
        ArrayList<String[]> resultado = new ArrayList<>();

        ResultSet cursor;
        String consulta = "SELECT * FROM producto;";

        try {
            Conexion con = new Conexion();
            cursor = con.getTransaccion().executeQuery(consulta);
            if (cursor.next()) {
                do {
                    String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(6), cursor.getString(7)};
                    resultado.add(renglon);
                } while (cursor.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public boolean obtenerImagen(Producto producto, JLabel jlabel) {
        Conexion con = new Conexion();

        String consulta = "SELECT Imagen FROM producto WHERE idProducto=?;";

        try {
            PreparedStatement cs = con.getConexion().prepareStatement(consulta);
            cs.setString(1, String.valueOf(producto.getIdProducto()));

            ResultSet cursor = cs.executeQuery();

            if (cursor.next()) {

                byte[] imageData = cursor.getBytes("Imagen");
                ImageIcon format = new ImageIcon(imageData);
                Image img = format.getImage();
                Image img2 = img.getScaledInstance(jlabel.getWidth(), jlabel.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon img3 = new ImageIcon(img2);
                jlabel.setIcon(img3);
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean insertarProducto(Producto producto) {

        File f = new File(producto.getImagen());

        String consulta = "INSERT INTO producto (nombre,descripcion,PrecioUnitario,Imagen,stock,Autor) VALUES (?,?,?,?,?,?);";

        try {
            InputStream is = new FileInputStream(f);

            Conexion con = new Conexion();

            PreparedStatement cs = con.getConexion().prepareStatement(consulta);

            cs.setString(1, producto.getNombre());
            cs.setString(2, producto.getDescripcion());
            cs.setString(3, String.valueOf(producto.getPrecioUnitario()));

            cs.setBlob(4, is);

            cs.setString(5, String.valueOf(producto.getStock()));
            cs.setString(6, producto.getAutor());

            cs.execute();

            return true;

        } catch (SQLException | FileNotFoundException e) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public boolean modificarProducto(Producto producto) {
        File f = new File(producto.getImagen());
        Conexion con = new Conexion();
        String consulta = "UPDATE producto SET nombre=?, descripcion=?, precioUnitario=?,imagen=?,stock=?, autor=? WHERE idProducto=?;";

        try {
            InputStream is = new FileInputStream(f);
            CallableStatement cs = con.getConexion().prepareCall(consulta);

            cs.setString(1, producto.getNombre());
            cs.setString(2, producto.getDescripcion());
            cs.setString(3, String.valueOf(producto.getPrecioUnitario()));
            //cs.setString(4, String.valueOf(producto.getImagen()));

            cs.setBlob(4, is);

            cs.setString(5, String.valueOf(producto.getStock()));
            cs.setString(6, producto.getAutor());
            cs.setString(7, String.valueOf(producto.getIdProducto()));

            cs.execute();

            return true;
        } catch (NumberFormatException | SQLException | FileNotFoundException e) {
            return false;
        }
    }

    public boolean eliminarProducto(Producto producto) {
        Conexion conexion = new Conexion();
        String operacion = "DELETE FROM producto WHERE idProducto=?;";

        try {
            CallableStatement cs = conexion.getConexion().prepareCall(operacion);

            cs.setString(1, String.valueOf(producto.getIdProducto()));

            cs.execute();

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public String getImagen() {
        return imagen;
    }

    public int getStock() {
        return stock;
    }

    public String getAutor() {
        return autor;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public void setImporte(float importe){
        this.importe = importe;
    }
    
    public float getImporte(){
        return importe;
    }

}

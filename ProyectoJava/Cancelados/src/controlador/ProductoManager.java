package controlador;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import modelo.Producto;

public class ProductoManager {

    private Conexion conexion;

    public ProductoManager() {
        conexion = new Conexion();
    }

    /**
     * Consulta todos los productos almacenados en la base de datos y devuelve
     * los resultados en un ArrayList de String[].
     *
     * @return ArrayList de String[] con los datos de todos los productos.
     *
     * @throws RuntimeException si se produce un error al consultar los
     * productos
     */
    public ArrayList<Producto> consultarProductos() {
        String consulta = "SELECT idProducto, nombre, descripcion, precioUnitario, stock, autor  FROM producto;";
        ArrayList<Producto> resultado = new ArrayList<>();

        try (ResultSet cursor = conexion.getConexion().prepareStatement(consulta).executeQuery()) {
            Producto producto;
            while (cursor.next()) {
                producto = new Producto();

                producto.setIdProducto(cursor.getInt(1));
                producto.setNombre(cursor.getString(2));
                producto.setDescripcion(cursor.getString(3));
                producto.setPrecioUnitario(cursor.getFloat(4));
                producto.setStock(cursor.getInt(5));
                producto.setAutor(cursor.getString(6));

                resultado.add(producto);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar los productos", ex);
        }
        return resultado;
    }

    /**
     * Actualiza los datos de un producto en la base de datos incluyendo la
     * imagen.
     *
     * @param producto El objeto producto con los nuevos datos.
     * @return true si la actualización se realizó correctamente, false en caso
     * contrario.
     * @throws RuntimeException si ocurre un error al modificar el producto.
     */
    public boolean modificarProductoConImagen(Producto producto) {
        String consulta = "UPDATE producto SET nombre=?, descripcion=?, precioUnitario=?,imagen=?, autor=? WHERE idProducto=?;";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(consulta)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setFloat(3, producto.getPrecioUnitario());
            ps.setBlob(4, new FileInputStream(new File(producto.getImagen())));
            ps.setString(5, producto.getAutor());
            ps.setInt(7, producto.getIdProducto());

            return ps.executeUpdate() == 1;

        } catch (SQLException | FileNotFoundException ex) {
            throw new RuntimeException("Error al modificar el producto", ex);
        }
    }

    /**
     * Actualiza los datos de un producto en la base de datos sin incluir la
     * imagen.
     *
     * @param producto El objeto producto con los nuevos datos.
     * @return true si la actualización se realizó correctamente, false en caso
     * contrario.
     * @throws RuntimeException si ocurre un error al modificar el producto.
     */
    public boolean modificarProductoSinImagen(Producto producto) {
        String consulta = "UPDATE producto SET nombre=?, descripcion=?, precioUnitario=?, autor=? WHERE idProducto=?;";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(consulta)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setFloat(3, producto.getPrecioUnitario());
            ps.setString(4, producto.getAutor());
            ps.setInt(5, producto.getIdProducto());

            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            throw new RuntimeException("Error al modificar el producto", ex);
        }
    }

    public boolean modificarStock(ArrayList<Producto> productos) {
        String consulta = "UPDATE producto SET stock = stock - ? where idProducto = ?";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(consulta)) {

            for (Producto producto : productos) {
                ps.setInt(1, producto.getStock());
                ps.setInt(2, producto.getIdProducto());
                ps.execute();
            }

            return true;

        } catch (SQLException ex) {
            return false;
        }
    }

    /**
     * Consulta los nombres, precios unitarios y stock de todos los productos en
     * la base de datos.
     *
     * @return ArrayList de String[] con los datos de todos los productos.
     *
     * @throwsRuntimeException si ocurre un error al consultar los productos en
     * la base de datos.
     */
    public ArrayList<Producto> consultarNombres() {
        String consulta = "SELECT idProducto, nombre, precioUnitario, stock FROM producto;";
        ArrayList<Producto> resultado = new ArrayList<>();
        Producto producto;

        try (ResultSet cursor = conexion.getConexion().prepareStatement(consulta).executeQuery()) {

            while (cursor.next()) {
                producto = new Producto();
                producto.setIdProducto(cursor.getInt(1));
                producto.setNombre(cursor.getString(2));
                producto.setPrecioUnitario(cursor.getFloat(3));
                producto.setStock(cursor.getInt(4));
                resultado.add(producto);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar el producto", ex);
        }
        return resultado;
    }

    /*
    public String consultarPrecio(Producto producto) {
        String consulta = "SELECT precioUnitario FROM producto WHERE nombre=?;";

        try {
            Conexion con = new Conexion();
            PreparedStatement ps = con.getConexion().prepareStatement(consulta);
            ps.setString(1, String.valueOf(producto.getNombre()));
            ResultSet cursor = ps.executeQuery();
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
     */
    /**
     * Obtiene la imagen del producto desde la base de datos y la muestra en un
     * objeto JLabel.
     *
     * @param producto el producto del cual se desea obtener la imagen.
     * @param jlabel el objeto JLabel en el cual se mostrará la imagen.
     *
     * @return true si se pudo obtener y mostrar la imagen correctamente, false
     * en caso contrario.
     *
     * @throws RuntimeException si ocurre algún error al obtener la imagen desde
     * la base de datos.
     */
    public boolean obtenerImagen(Producto producto, JLabel jlabel) {
        String consulta = "SELECT Imagen FROM producto WHERE idProducto=?;";

        try (PreparedStatement cs = conexion.getConexion().prepareStatement(consulta)) {
            cs.setInt(1, producto.getIdProducto());
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
            throw new RuntimeException("Error al obtener la imagen del producto", ex);
        }
        return false;
    }

    /**
     * Inserta un nuevo producto en la base de datos.
     *
     * @param producto el producto que se desea insertar.
     *
     * @return true si se pudo insertar el producto correctamente, false en caso
     * contrario.
     *
     * @throws RuntimeException si ocurre algún error al insertar el producto en
     * la base de datos.
     */
    public boolean insertarProducto(Producto producto) {
        String consulta = "INSERT INTO producto (nombre,descripcion,PrecioUnitario,Imagen,stock,Autor) VALUES (?,?,?,?,?,?);";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(consulta);) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setString(3, String.valueOf(producto.getPrecioUnitario()));
            ps.setBlob(4, new FileInputStream(new File(producto.getImagen())));
            ps.setString(5, String.valueOf(producto.getStock()));
            ps.setString(6, producto.getAutor());

            return ps.executeUpdate() == 1;

        } catch (SQLException | FileNotFoundException ex) {
            throw new RuntimeException("Error al insertar el producto", ex);
        }
    }

    /**
     * Elimina un producto existente de la base de datos.
     *
     * @param producto el producto que se desea eliminar.
     *
     * @return true si se pudo eliminar el producto correctamente, false en caso
     * contrario.
     *
     * @throws RuntimeException si ocurre algún error al eliminar el producto de
     * la base de datos.
     */
    public boolean eliminarProducto(Producto producto) {
        String operacion = "DELETE FROM producto WHERE idProducto=?;";

        try (PreparedStatement ps = conexion.getConexion().prepareStatement(operacion)) {

            ps.setInt(1, producto.getIdProducto());
            ps.executeUpdate();
            
            return true;

        } catch (SQLException ex) {
            return false;
        }
    }

}
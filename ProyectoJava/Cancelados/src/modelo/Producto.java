package modelo;

public class Producto {

    private int idProducto;
    private String nombre;
    private String descripcion;
    private float precioUnitario;
    private String imagen;
    private int stock;
    private String autor;
    private float importe;
    private String categoria;

    public Producto() {

    }

    public Producto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(int idProducto, String nombre, String descripcion, float precioUnitario, int stock, String autor, String categoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.stock = stock;
        this.autor = autor;
        this.categoria = categoria;
    }

    public Producto(String nombre, String descripcion, float precioUnitario, String imagen, int stock, String autor, String categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.imagen = imagen;
        this.stock = stock;
        this.autor = autor;
        this.categoria = categoria;
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

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public float getImporte() {
        return importe;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}

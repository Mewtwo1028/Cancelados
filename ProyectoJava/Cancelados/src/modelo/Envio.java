
package modelo;

/**
 *
 * @author osmar
 */
public class Envio {
    
    private int idEnvio;
    private String destinatario;
    private String domicilio;
    private String pais;
    private String ciudad;
    private String calle;
    private String colonia;
    private String numero;
    private String producto;

    public Envio() {
    }

    public Envio(int responsable) {
        this.responsable = responsable;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setPaqueteria(String paqueteria) {
        this.paqueteria = paqueteria;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public void setResponsable(int responsable) {
        this.responsable = responsable;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getPais() {
        return pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public String getColonia() {
        return colonia;
    }

    public String getNumero() {
        return numero;
    }

    public String getProducto() {
        return producto;
    }

    public String getPaqueteria() {
        return paqueteria;
    }

    public String getCp() {
        return cp;
    }

    public int getResponsable() {
        return responsable;
    }

    public String getPeso() {
        return peso;
    }

    public String getFecha() {
        return fecha;
    }

    public String getRemitente() {
        return remitente;
    }
    private String paqueteria;
    private String cp;
    private int responsable;
    private String peso;
    private String fecha;
    private String remitente;
    
}

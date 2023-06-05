package modelo;

public class Direccion {

    private int idDireccionClienteGeneral;
    private String pais;
    private String estado;
    private String ciudad;
    private String cp;
    private String calle;
    private int idVenta;
    private String colonia;
    private String municipio;
    private String zip;
    private String noExt;

    public Direccion(int idDireccionClienteGeneral, String pais, String estado, String ciudad, String cp, String calle, int idVenta) {
        this.idDireccionClienteGeneral = idDireccionClienteGeneral;
        this.pais = pais;
        this.estado = estado;
        this.ciudad = ciudad;
        this.cp = cp;
        this.calle = calle;
        this.idVenta = idVenta;
    }

    public Direccion() {

    }

    public int getIdDireccionClienteGeneral() {
        return idDireccionClienteGeneral;
    }

    public void setIdDireccionClienteGeneral(int idDireccionClienteGeneral) {
        this.idDireccionClienteGeneral = idDireccionClienteGeneral;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getNoExt() {
        return noExt;
    }

    public void setNoExt(String noExt) {
        this.noExt = noExt;
    }

}

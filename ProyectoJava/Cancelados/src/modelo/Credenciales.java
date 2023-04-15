package modelo;

public class Credenciales {
    private int idCredencial;
    private String contraseña;
    private int idEmpleado;
    private int idRol;
    
    public Credenciales(){
        
    }

    public Credenciales(String contraseña, int idEmpleado) {
        this.contraseña = contraseña;
        this.idEmpleado = idEmpleado;
    }

    public Credenciales(int idCredencial, String contraseña, int idEmpleado, int idRol) {
        this.idCredencial = idCredencial;
        this.contraseña = contraseña;
        this.idEmpleado = idEmpleado;
        this.idRol = idRol;
    }

    public int getIdCredencial() {
        return idCredencial;
    }

    public void setIdCredencial(int idCredencial) {
        this.idCredencial = idCredencial;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    
}

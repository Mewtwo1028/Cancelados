package controlador;

import Util.FuncionesUtiles;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import modelo.Empleado;
import vista.EnvioCorreos;

public class EmpleadoManager {

    private Conexion conexion;

    public EmpleadoManager() {
        conexion = new Conexion();
    }

    /**
     * Consulta todos los empleados almacenados en la base de datos y devuelve
     * los resultados en un ArrayList de String[].
     *
     * @return ArrayList de String[] con los datos de todos los empleados.
     *
     * @throwsRuntimeException si ocurre un error al consultar los empleados en
     * la base de datos.
     */
    public ArrayList<String[]> consultarTodos() {
        String sql = "SELECT idEmpleado, nombre, apellidoPaterno, apellidoMaterno, calle, noExt, colonia, cp, curp, rfc, municipio, estado, Roles_idRoles, nombreUsuario, correo FROM empleado WHERE estadoEmpleado = 'ACTIVO'";
        ArrayList<String[]> resultado = new ArrayList<>();

        try {
            ResultSet cursor = new Conexion().getConexion().prepareStatement(sql).executeQuery();

            while (cursor.next()) {
                String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(15)};
                resultado.add(renglon);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar a los empleados" + ex.getMessage());
        }
        return resultado;
    }

    public String getNombreEmpleado(String nombreUsuario) {
        String sql = "SELECT nombre FROM empleado WHERE estadoEmpleado = 'ACTIVO' AND nombreUsuario = '" + nombreUsuario + "'";
        String resultado = "";

        try {
            ResultSet cursor = new Conexion().getConexion().prepareStatement(sql).executeQuery();

            while (cursor.next()) {
                resultado = cursor.getString(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al getNombreEmpleado" + ex.getMessage());
        }
        return resultado;
    }

    /**
     * Inserta un nuevo registro en la tabla "empleado" con los datos del
     * empleado pasado como parámetro, y crea una nueva credencial asociada a
     * dicho empleado con la contraseña especificada.
     *
     * @param empleado el objeto Empleado que contiene los datos del empleado a
     * insertar
     * @return true si se insertó correctamente el registro del empleado y se
     * creó la credencial, false si no se pudo insertar el registro o crear la
     * credencial
     */
    public boolean insertarEmpleado(Empleado empleado) {
        String SQL_insertar = "INSERT INTO empleado VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, false, 'ACTIVO', ?, ?)";
        String SQL_idEmpleado = "select idEmpleado from empleado order by idEmpleado DESC";
        String SQL_insertarCredencial = "INSERT INTO credenciales VALUES (null, ?, ?)";
        String SQL_empleado = "UPDATE empleado SET restContra = true WHERE idEmpleado = ?";

        String nombreUsuario = new FuncionesUtiles().generarNombreUsuario(empleado);

        try (PreparedStatement psInsertar = conexion.getConexion().prepareStatement(SQL_insertar); PreparedStatement psIdEmpleado = conexion.getConexion().prepareStatement(SQL_idEmpleado); PreparedStatement psInsertarCredencial = conexion.getConexion().prepareStatement(SQL_insertarCredencial); PreparedStatement psContra = conexion.getConexion().prepareStatement(SQL_empleado)) {

            psInsertar.setString(1, empleado.getNombre());
            psInsertar.setString(2, empleado.getaPaterno());
            psInsertar.setString(3, empleado.getaMaterno());
            psInsertar.setString(4, empleado.getCalle());
            psInsertar.setString(5, empleado.getNoExt());
            psInsertar.setString(6, empleado.getColonia());
            psInsertar.setString(7, empleado.getCp());
            psInsertar.setString(8, empleado.getCurp());
            psInsertar.setString(9, empleado.getRfc());
            psInsertar.setString(10, empleado.getMunicipio());
            psInsertar.setString(11, empleado.getEstado());
            psInsertar.setInt(12, empleado.getIdRol());
            psInsertar.setString(13, nombreUsuario);
            psInsertar.setString(14, empleado.getCorreo());
            psInsertar.executeUpdate();

            ResultSet cursor = psIdEmpleado.executeQuery();
            cursor.next();
            int idEmpleado = cursor.getInt(1);

            psInsertarCredencial.setString(1, "");
            psInsertarCredencial.setInt(2, idEmpleado);
            psInsertarCredencial.execute();

            psContra.setInt(1, idEmpleado);
            psContra.execute();

            sendMail(empleado.getCorreo(), nombreUsuario);

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    /**
     * Elimina un empleado de la base de datos, incluyendo también sus
     * credenciales.
     *
     * @param empleado el objeto Empleado a eliminar
     *
     * @return true si se elimina correctamente, false si ocurre un error
     *
     * @throws RuntimeException si ocurre un error al eliminar al empleado
     */
    public boolean eliminarEmpleado(Empleado empleado) {
        //String SQL_borrarCredencial = "DELETE FROM credenciales WHERE Empleado_idEmpleado=?;";
        String SQL_borrarEmpleado = "UPDATE empleado SET estadoEmpleado = 'ELIMINADO' WHERE idEmpleado=?;";

        try (PreparedStatement psBorrarEmpleado = conexion.getConexion().prepareStatement(SQL_borrarEmpleado);) {

            psBorrarEmpleado.setInt(1, empleado.getIdEmpledo());
            psBorrarEmpleado.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    /**
     * Modifica los datos de un empleado en la base de datos.
     *
     * @param empleado el objeto Empleado con los datos actualizados.
     *
     * @return true si la modificación fue exitosa, false en caso contrario.
     *
     * @throws RuntimeException si ocurre un error al modificar el empleado.
     */
    public boolean modificarEmpleado(Empleado empleado) {
        String SQL_actualizar = "UPDATE Empleado SET nombre=?, ApellidoPaterno =?, ApellidoMaterno=?,Calle=?,NoExt=?, Colonia=?,CP=?,Municipio=?,Estado=?,CURP=?,RFC=?, Roles_idRoles=? WHERE idEmpleado=?;";

        try (PreparedStatement cs = conexion.getConexion().prepareStatement(SQL_actualizar)) {

            cs.setString(1, empleado.getNombre());
            cs.setString(2, empleado.getaPaterno());
            cs.setString(3, empleado.getaMaterno());
            cs.setString(4, empleado.getCalle());
            cs.setString(5, empleado.getNoExt());
            cs.setString(6, empleado.getColonia());
            cs.setString(7, empleado.getCp());
            cs.setString(8, empleado.getMunicipio());
            cs.setString(9, empleado.getEstado());
            cs.setString(10, empleado.getCurp());
            cs.setString(11, empleado.getRfc());
            cs.setInt(12, empleado.getIdRol());
            cs.setInt(13, empleado.getIdEmpledo());

            return cs.executeUpdate() == 1;

        } catch (SQLException ex) {
            throw new RuntimeException("Error al modificar el empleado", ex);
        }
    }

    public boolean eliminarEstado(int idEmpleado) {
        String sql = "UPDATE empleado SET restContra = false WHERE idEmpleado=?";

        try (PreparedStatement p = new Conexion().getConexion().prepareStatement(sql)) {

            p.setInt(1, idEmpleado);

            return p.executeUpdate() == 1;

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }

    /**
     * Busca los empleados cuyo apellido materno comience con el texto
     * especificado.
     *
     * @param texto El texto a buscar.
     *
     * @return Una lista de matrices de cadenas que representan a los empleados
     * encontrados, o una lista vacía si no se encontró ningún empleado.
     */
    public ArrayList<String[]> buscarEmpleadoMaterno(String texto) {
        String filtro = texto + "%";
        String SQL = "SELECT * FROM empleado WHERE apellidoMaterno like" + '"' + filtro + '"';
        ArrayList<String[]> resultado = new ArrayList<>();

        try {
            ResultSet rs = conexion.getConexion().prepareStatement(SQL).executeQuery();

            while (rs.next()) {
                String[] renglon = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)};
                resultado.add(renglon);
            }

            return resultado;

        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return resultado;
    }

    /**
     * Busca los empleados cuyo apellido paterno comience con el texto
     * especificado.
     *
     * @param texto El texto a buscar.
     *
     * @return Una lista de matrices de cadenas que representan a los empleados
     * encontrados, o una lista vacía si no se encontró ningún empleado.
     */
    public ArrayList<String[]> buscarEmpleadoPaterno(String texto) {
        String filtro = texto + "%";
        String SQL = "SELECT * FROM empleado WHERE apellidoPaterno like" + '"' + filtro + '"';
        ArrayList<String[]> resultado = new ArrayList<>();

        try {
            ResultSet rs = conexion.getConexion().prepareStatement(SQL).executeQuery();

            while (rs.next()) {
                String[] renglon = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)};
                resultado.add(renglon);
            }

            return resultado;

        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return resultado;
    }

    /**
     * Busca los empleados cuyo nombre comience con el texto especificado.
     *
     * @param texto El texto a buscar.
     *
     * @return Una lista de matrices de cadenas que representan a los empleados
     * encontrados, o una lista vacía si no se encontró ningún empleado.
     */
    public ArrayList<String[]> buscarEmpleadoNombre(String texto) {
        String filtro = texto + "%";
        String SQL = "SELECT * FROM empleado WHERE nombre like" + '"' + filtro + '"';
        ArrayList<String[]> resultado = new ArrayList<>();

        try {
            ResultSet rs = conexion.getConexion().prepareStatement(SQL).executeQuery();

            while (rs.next()) {
                String[] renglon = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)};
                resultado.add(renglon);
            }

            return resultado;

        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return resultado;
    }

    public static void sendMail(String correo, String nombreUsuario) {

        String emailFrom = "chechocita@gmail.com";
        String passwordFrom = "rxrhrkxsgvdrftxx";
        String emailTo;
        String subject;
        String content;

        Properties mProperties;
        Session mSession;
        MimeMessage mCorreo;

        emailTo = correo;
        subject = "Nuevo registro";

        content = "Tu usuario es: " + nombreUsuario;
        // Simple mail transfer protocol
        mProperties = new Properties();
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");

        mSession = Session.getDefaultInstance(mProperties);

        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setText(content, "ISO-8859-1", "html");

        } catch (AddressException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    public static void main(String[] args) {
        sendMail("cesarinzunsa@gmail.com", "Prueba");
    }
     */

}

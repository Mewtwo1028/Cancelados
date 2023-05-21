/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Empleado;

/**
 *
 * @author osmar
 */
public class CajaManager {

    public int idCaja;
    Conexion conexion = new Conexion();

    public boolean insertarCaja(String horaInicio, int idEmpleado, double montoInicial, String estado) {
        String SQL_insertar = "INSERT INTO Caja (horaInicio, idEmpleado , montoInicial, estado) VALUES (?, ?, ?, ?)";
        String SQL_idEmpleado = "select idEmpleado from empleado order by idEmpleado DESC";
        try (PreparedStatement psInsertar = conexion.getConexion().prepareStatement(SQL_insertar);) {
            psInsertar.setString(1, horaInicio);
            psInsertar.setInt(2, idEmpleado);
            psInsertar.setDouble(3, montoInicial);
            psInsertar.setString(4, estado);
            psInsertar.execute();

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public int consultaId() {
        String consulta = "SELECT COUNT(*) FROM cancelados.caja;";
        try (ResultSet cursor = new Conexion().getConexion().prepareStatement(consulta).executeQuery()) {
            if (cursor.next()) {
                idCaja = cursor.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar la caja", ex);
        }
        return idCaja;
    }

    public boolean consultaEstadoCaja(int idEmp) {
    String consulta = "SELECT Estado FROM cancelados.caja WHERE IdEmpleado= ? AND idApertura=?;";
    String estado = "";
    try (PreparedStatement statement = new Conexion().getConexion().prepareStatement(consulta)) {
       statement.setInt(1, idEmp);
       statement.setInt(2, consultaId());
        try (ResultSet cursor = statement.executeQuery()) {
            if (cursor.next()) {
                estado = cursor.getString(1);
            }
        }
    } catch (SQLException ex) {
        throw new RuntimeException("Error al consultar la caja", ex);
    }

    return estado.equals("Abierta");
}

    public boolean cierraCaja(String estado, Double montoF, String horaFin, int idEmp) {
        String SQL_actualizar = "UPDATE Caja SET estado=?, montoFinal=?, horaFin=? WHERE idEmpleado=? AND estado = 'Abierta';";

        try (PreparedStatement cs = conexion.getConexion().prepareStatement(SQL_actualizar)) {

            cs.setString(1, estado);
            cs.setDouble(2, montoF);
            cs.setString(3, horaFin);
            cs.setInt(4, idEmp);
            //cs.setInt(4, consultaId());

            cs.execute();
            return true;

        } catch (SQLException ex) {
            throw new RuntimeException("Error al cerrar caja", ex);

        }

    }

}

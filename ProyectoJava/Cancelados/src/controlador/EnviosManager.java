/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author osmar
 */
public class EnviosManager {
    
    public ArrayList<String[]> consultarCPMex(String cp, String estado, String municipio, String colonia) {
         String sql = "SELECT * FROM codpos WHERE cp like ? or estado like ? or municipio like ?  or colonia like ?;";
   ArrayList<String[]> resultado = new ArrayList<>();
   try {
       PreparedStatement stmt = new Conexion().getConexion().prepareStatement(sql);
       stmt.setString(1, cp);
       stmt.setString(2, estado);
       stmt.setString(3, municipio);
       stmt.setString(4, colonia);
       ResultSet cursor = stmt.executeQuery();

       while (cursor.next()) {
           String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10)};
           resultado.add(renglon);
       }
   } catch (SQLException ex) {
       throw new RuntimeException("Error al consultar los códigos postales", ex);
   }
   
   return resultado;
    }
    
    public ArrayList<String[]> consultarCPUsa(String cp, String estado, String municipio, String colonia) {
         String sql = "SELECT * FROM us WHERE zipcode like ? or state like ? or city like ?;";
   ArrayList<String[]> resultado = new ArrayList<>();
   try {
       PreparedStatement stmt = new Conexion().getConexion().prepareStatement(sql);
       stmt.setString(1, cp);
       stmt.setString(2, municipio);
       stmt.setString(3, estado);
       ResultSet cursor = stmt.executeQuery();

       while (cursor.next()) {
           String[] renglon = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)};
           resultado.add(renglon);
       }
   } catch (SQLException ex) {
       throw new RuntimeException("Error al consultar los códigos postales", ex);
   }
   
   return resultado;
    }
    
    public ArrayList<String[]> consultarCP(String cp, String estado, String municipio, String colonia, int pais) {
        return pais == 0 ? consultarCPMex(cp,estado,municipio,colonia) : consultarCPUsa(cp,estado,municipio,colonia);
    }

}

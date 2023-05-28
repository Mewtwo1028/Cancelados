package controlador;

import java.awt.Cursor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Util.FuncionesUtiles;

/**
 *
 * @author osmar
 */
public class CorteDeCaja {
    float total=0;
    private Conexion conexion=new Conexion();


    //Función que recibe una fecha y retorna datos de ingreso de efectivo
    public ArrayList<String[]> consultaDatos(String fecha){
        String sql = "SELECT SUM(total) FROM venta where fecha like '%?%' ;";
        ArrayList<String[]> resultado = new ArrayList<>();
        try(PreparedStatement cs = conexion.getConexion().prepareStatement(sql)){
            cs.setString(1, fecha);
            ResultSet cursor = cs.executeQuery();
             while (cursor.next()) {
                String[] renglon = {cursor.getString(1)};
                resultado.add(renglon);
            }
            
        }catch (SQLException ex) {
            throw new RuntimeException("", ex);
        }
        
        return resultado;
   
    }
    
}

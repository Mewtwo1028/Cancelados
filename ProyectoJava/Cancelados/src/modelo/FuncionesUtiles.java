package modelo;

import java.awt.Color;
import java.awt.Image;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class FuncionesUtiles {

    public FuncionesUtiles() {

    }
    
    /**
     * Funcion que coloca una imagen en un jButton pero que no lo ajusta al tamaño del boton
     * @param imagen
     * @param jButton 
     */
    public void colocarImagen(String imagen, JButton jButton) {
        ImageIcon img = new ImageIcon(getClass().getResource(imagen));
        jButton.setIcon(img);
    }
    
    /**
     * Funcion que coloca una imagen en un jlabel y la justa deacuerdo al tamaño
     * del jlabel.
     *
     * @param imagen
     * @param jLabel
     */
    public void colocarImagen(String imagen, JLabel jLabel) {
        ImageIcon img = new ImageIcon(getClass().getResource(imagen));
        ImageIcon imgPequeno = escalarImagen(img, jLabel);
        jLabel.setIcon(imgPequeno);
    }

    /**
     * Funcion que escala una imagen para que se acomode deacuerdo al tamaño del
     * jlabel
     *
     * @param img
     * @param donde
     * @return
     */
    public ImageIcon escalarImagen(ImageIcon img, JLabel donde) {
        Image imgLabel = img.getImage().getScaledInstance(donde.getWidth(), donde.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(imgLabel);
    }

    /**
     * Funcion que ayuda a configurar los botones que van a la izquierda de cada
     * pantalla. Elimina el fondo y alinea el texto a la izquierda.
     *
     * @param boton
     */
    public void confBtnMenu(JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setHorizontalAlignment(SwingConstants.LEFT);
    }

    /**
     * Funcion que quita el fondo a los botones y deja centrado el texto.
     *
     * @param boton
     */
    public void confBtn(JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * Funcion pinta el fondo de un boton del color (94,29,27), y cambia el
     * color de las letras a blanco.
     *
     * @param boton
     */
    public void confBtnColor(JButton boton) {
        boton.setBackground(getColorCancelados());
        boton.setForeground(Color.WHITE);
    }

    /**
     * Returna el color 94,29,27 que es ese rojo tinto
     *
     * @return Color(94, 29, 27);
     */
    public Color getColorCancelados() {
        return new Color(94, 29, 27);
    }

    /**
     * retorna la hora actual en formato de 24 horas como un string
     *
     * @return String
     */
    public String getHora() {
        Calendar calendario = GregorianCalendar.getInstance();
        String cad = "";

        int hora = calendario.get(Calendar.HOUR_OF_DAY)-1;

        if (hora < 10) {
            cad += "0" + hora + ":";
        } else {
            cad += hora + ":";
        }

        int minuto = calendario.get(Calendar.MINUTE);
        if (minuto < 10) {
            cad += "0" + minuto;
        } else {
            cad += minuto;
        }

        return cad;

    }
    
    /**
     * Retorna la fecha actual: dia nombre del mes y el año.
     * @return String
     */
    public String getFecha(){
        Calendar calendario = GregorianCalendar.getInstance();
        String cad = "";
        String[] monthName = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
        
        if (calendario.get(Calendar.DATE)<10) {
            cad += "0"+calendario.get(Calendar.DATE)+" de ";
        }else{
            cad += ""+calendario.get(Calendar.DATE)+" de ";
        }
        
        cad += monthName[calendario.get(Calendar.MONTH)]+" del ";
        cad += calendario.get(Calendar.YEAR);
        
        return cad;
    }
    
    /**
 * Retorna la fecha actual formateada como una cadena en el formato "yyyy-MM-dd".
 *
 * @return La fecha actual formateada como una cadena.
 */
    public String formatoFecha(){
    LocalDate fechaActual = LocalDate.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String fechaFormateada = fechaActual.format(formato);
    return fechaFormateada;
}
}

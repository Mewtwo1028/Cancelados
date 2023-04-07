package Código;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FuncionesUtiles {

    public FuncionesUtiles() {

    }

    /**
     * Funcion que coloca una imagen en un jlabel y la justa deacuerdo al tamaño del jlabel.
     * @param imagen
     * @param jLabel 
     */
    public void colocarImagen(String imagen, JLabel jLabel) {
        ImageIcon img = new ImageIcon(getClass().getResource(imagen));
        ImageIcon imgPequeno = escalarImagen(img, jLabel);
        jLabel.setIcon(imgPequeno);
    }

    /**
     * Funcion que escala una imagen para que se acomode deacuerdo al tamaño del jlabel
     * @param img
     * @param donde
     * @return 
     */
    public ImageIcon escalarImagen(ImageIcon img, JLabel donde) {
        Image imgLabel = img.getImage().getScaledInstance(donde.getWidth(), donde.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(imgLabel);
    }
    
    /**
     * Funcion que ayuda a configurar los botones que van a la izquierda de cada pantalla.
     * Elimina el fondo y alinea el texto a la izquierda.
     * @param boton 
     */
    public void confBtnMenu(JButton boton){
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setHorizontalAlignment(SwingConstants.LEFT);
    }
    
    /**
     * Funcion que quita el fondo a los botones y deja centrado el texto.
     * @param boton 
     */
    public void confBtn(JButton boton){
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    /**
     * Funcion pinta el fondo de un boton del color (94,29,27), y cambia el color de las letras a blanco.
     * @param boton 
     */
    public void confBtnColor(JButton boton){
        boton.setBackground(getColorCancelados());
        boton.setForeground(Color.WHITE);
    }
    
    /**
     * Returna el color 94,29,27 que es ese rojo tinto
     * @return Color(94, 29, 27);
     */
    public Color getColorCancelados(){
        return new Color(94, 29, 27);
    }
}

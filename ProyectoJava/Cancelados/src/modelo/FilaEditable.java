/*
 * Fila para los productos en el ticket
 */
package modelo;

import javax.swing.*;
import java.awt.*;

public class FilaEditable extends JPanel {
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    public FilaEditable(String descripcion, Double cantidad, Double precio) {
        setLayout(new GridLayout(1, 3));
        setBackground(Color.white);
        
        label1 = new JLabel(descripcion);
        label2 = new JLabel(String.valueOf(cantidad));
        label3 = new JLabel(String.valueOf(precio));
        
        add(label1);
        add(label2);
        add(label3);
    }
    
    public void setTextoLabel1(String texto) {
        label1.setText(texto);
    }
    
    public void setTextoLabel2(String texto) {
        label2.setText(texto);
    }
    
    public void setTextoLabel3(String texto) {
        label3.setText(texto);
    }
    
    public String getTextoLabel1() {
        return label1.getText();
    }
    
    public String getTextoLabel2() {
        return label2.getText();
    }
    
    public String getTextoLabel3() {
        return label3.getText();
    }
}
package Ventanas;

import ArchivosBD.Conexion;
import Código.FuncionesUtiles;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author osmar
 */
public class Login extends javax.swing.JFrame {
    
    public Login() {
        initComponents();
        inicializar();
    }
    
    private void inicializar() {
        //Configuracion ventana
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Iniciar sesion");

        //Imagenes
        FuncionesUtiles funcionesUtiles = new FuncionesUtiles();
        funcionesUtiles.colocarImagen("/Imagenes/LogoLetrasDegradado.png", jLabelLogoDegradado);
        funcionesUtiles.colocarImagen("/Imagenes/bolitas.png", jLabelBolitas);

        //Boton olvidar contraseña
        btnForgottenPassword.setOpaque(false);
        btnForgottenPassword.setContentAreaFilled(false);
        btnForgottenPassword.setBorderPainted(false);

        //Configurar jPanelCredenciales
        jPanelCredenciales.setBackground(Color.WHITE);

        //Boton ingresar
        btnIngresar.setBackground(new Color(94, 29, 27));
        btnIngresar.setForeground(Color.WHITE);

        //Bienvenida
        jLabelBienvenida.setForeground(new Color(94, 29, 27));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCredenciales = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        btnForgottenPassword = new javax.swing.JButton();
        jLabelLogoDegradado = new javax.swing.JLabel();
        jLabelBienvenida = new javax.swing.JLabel();
        jLabelBolitas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelCredenciales.setForeground(new java.awt.Color(255, 0, 51));
        jPanelCredenciales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Username");
        jPanelCredenciales.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel2.setText("Contraseña");
        jPanelCredenciales.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));
        jPanelCredenciales.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 270, 40));

        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        jPanelCredenciales.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 270, 40));

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });
        jPanelCredenciales.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 270, 40));

        btnForgottenPassword.setText("Olvide mi contraseña");
        jPanelCredenciales.add(btnForgottenPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 270, -1));

        getContentPane().add(jPanelCredenciales, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 200, 320, 290));
        getContentPane().add(jLabelLogoDegradado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 730, 720));

        jLabelBienvenida.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabelBienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBienvenida.setText("Bienvenido");
        getContentPane().add(jLabelBienvenida, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 140, -1, -1));
        getContentPane().add(jLabelBolitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 490, 248, 231));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            iniciarSesion();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        iniciarSesion();
    }//GEN-LAST:event_btnIngresarActionPerformed
    
    private void iniciarSesion() {
        Conexion con = new Conexion();
        String username = txtUsername.getText();
        String pass = txtPassword.getText();
        
        if ((username.isBlank() | pass.isBlank()) | con.verificarLogin(username, pass).equals("")) {
            DialogoEmergente dEmergente = new DialogoEmergente(this, true);
            dEmergente.setTexto("ERROR! Usuario o\ncontraseña no validos");
            dEmergente.setVisible(true);
        } else {
            if (con.verificarLogin(username, pass).equals("1")) {
                PanelAdministrador a = new PanelAdministrador();
                this.dispose();
                a.setVisible(true);
            } else {
                PanelControlEmpleado b = new PanelControlEmpleado();
                this.dispose();
                b.setVisible(true);
            }
        }
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnForgottenPassword;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelBienvenida;
    private javax.swing.JLabel jLabelBolitas;
    private javax.swing.JLabel jLabelLogoDegradado;
    private javax.swing.JPanel jPanelCredenciales;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}

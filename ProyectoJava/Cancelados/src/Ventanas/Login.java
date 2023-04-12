package Ventanas;

import ArchivosBD.Conexion;
import Código.FuncionesUtiles;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private String encriptaContra(String contrasena) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(contrasena.getBytes());

        BigInteger bigInt = new BigInteger(1, messageDigest);

        return bigInt.toString();
    }
    
    private void inicializar() {
        //Configuracion ventana
        this.setLocationRelativeTo(null);
        //this.getContentPane().setBackground(Color.WHITE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Iniciar sesion");
        
        FuncionesUtiles funcionesUtiles = new FuncionesUtiles();
        
        //Configurar panel principal
        jPanelPrincipal.setBounds(0, 0, (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        jPanelPrincipal.setBackground(Color.WHITE);
        
        //Configurar bolitas
        jLabelBolitas.setBounds((int)jPanelPrincipal.getBounds().getMaxX(), (int)jPanelPrincipal.getBounds().getMaxY(), 248, 231);

        //Imagenes
        funcionesUtiles.colocarImagen("/Imagenes/LogoLetrasDegradado.png", jLabelLogoDegradado);
        funcionesUtiles.colocarImagen("/Imagenes/bolitas.png", jLabelBolitas);

        //Boton olvidar contraseña
        funcionesUtiles.confBtn(btnForgottenPassword);

        //Configurar jPanelCredenciales
        jPanelCredenciales.setBackground(Color.WHITE);

        //Boton ingresar
        funcionesUtiles.confBtnColor(btnIngresar);

        //Bienvenida
        jLabelBienvenida.setForeground(funcionesUtiles.getColorCancelados());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPrincipal = new javax.swing.JPanel();
        jLabelLogoDegradado = new javax.swing.JLabel();
        jLabelBienvenida = new javax.swing.JLabel();
        jPanelCredenciales = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        btnForgottenPassword = new javax.swing.JButton();
        jLabelBolitas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));

        jLabelBienvenida.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabelBienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBienvenida.setText("Bienvenido");

        jPanelCredenciales.setForeground(new java.awt.Color(255, 0, 51));
        jPanelCredenciales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Usuario");
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

        btnForgottenPassword.setText("Olvidé mi contraseña");
        btnForgottenPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnForgottenPasswordMouseEntered(evt);
            }
        });
        btnForgottenPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForgottenPasswordActionPerformed(evt);
            }
        });
        jPanelCredenciales.add(btnForgottenPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 270, -1));

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addComponent(jLabelLogoDegradado, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPrincipalLayout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(jLabelBolitas))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPrincipalLayout.createSequentialGroup()
                                .addComponent(jPanelCredenciales, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                                .addGap(63, 63, 63))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelBienvenida)
                        .addGap(142, 142, 142))))
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabelBienvenida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelCredenciales, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jLabelBolitas, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabelLogoDegradado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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

    private void btnForgottenPasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnForgottenPasswordMouseEntered
        btnForgottenPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnForgottenPasswordMouseEntered

    private void btnForgottenPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForgottenPasswordActionPerformed
        DialogoEmergente t = new DialogoEmergente(this, rootPaneCheckingEnabled);
        t.setTexto("Por favor, solicite a un \nadministrador que le \nrestaure la contraseña");
        t.setVisible(true);
        
    }//GEN-LAST:event_btnForgottenPasswordActionPerformed
    
    private void iniciarSesion() {
        Conexion con = new Conexion();
        String username = txtUsername.getText();
        String pass = null;
        try {
            pass = encriptaContra(txtPassword.getText());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if ((username.isBlank() | pass.isBlank()) | con.verificarLogin(username, pass).equals("")) {
            DialogoEmergente dEmergente = new DialogoEmergente(this, true);
            dEmergente.setTexto("¡Error! Usuario o\ncontraseña no válidos");
            dEmergente.setVisible(true);
        } else {
            if (con.verificarLogin(username, pass).equals("1")) {
                PanelControlAdministrador a = new PanelControlAdministrador();
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
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}

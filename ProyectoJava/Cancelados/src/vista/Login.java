package vista;

import modelo.FuncionesUtiles;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import controlador.CredencialManager;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.util.Arrays;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    GregorianCalendar a = new GregorianCalendar();

    public Login() {
        initComponents();
        inicializar();
        //System.out.print(a.getTime());
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
        this.getContentPane().setBackground(Color.WHITE);
        //double width = Toolkit.getDefaultToolkit().getScreenSize().width;
        //double height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //System.out.print((int)(1280*width/100)+","+height);
        //this.setSize((int)(width),(int) height);
        //jLabelLogoDegradado.setPreferredSize(new Dimension((int)(this.getSize().width/100),(int) (this.getSize().height*720/100)));
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("Iniciar sesion");

        FuncionesUtiles funcionesUtiles = new FuncionesUtiles();

        //Configurar panel principal
        //jPanelPrincipal.setBounds(0, 0, (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        //jPanelPrincipal.setBounds(0, 0, (int) this.getBounds().getWidth(), (int) this.getBounds().getHeight());
        jPanelPrincipal.setBackground(Color.WHITE);

        //Configurar logo
        //int height = (int) jPanelPrincipal.getHeight();
        //int width = (int) jPanelPrincipal.getWidth() / 2;
        //jLabelLogoDegradado.setBounds(0, 0, width, height);
        //Configurar bolitas
        //jLabelBolitas.setBounds((int) jPanelPrincipal.getBounds().getMaxX() - 248, (int) jPanelPrincipal.getBounds().getMaxY() - 231, 248, 231);
        int height = (int) this.getBounds().getHeight();
        int width = (int) this.getBounds().getWidth();
        int maxX = (int) this.getBounds().getMaxX();
        int maxY = (int) this.getBounds().getMaxY();

        jPanelPrincipal.setBounds(0, 0, width, height);
        jPanelPrincipal.setBackground(Color.WHITE);

        //Configurar logo
        jLabelLogoDegradado.setBounds(0, 0, (int) width / 2, height);

        //Configurar bolitas
        jLabelBolitas.setBounds(maxX - 248, maxY - 231, 248, 231);

        //Imagenes
        new FuncionesUtiles().colocarImagen("/Imagenes/LogoLetrasDegradado.png", jLabelLogoDegradado);
        new FuncionesUtiles().colocarImagen("/Imagenes/bolitas.png", jLabelBolitas);

        //Imagenes
        //funcionesUtiles.colocarImagen("/Imagenes/LogoLetrasDegradado.png", jLabelLogoDegradado);
        //funcionesUtiles.colocarImagen("/Imagenes/bolitas.png", jLabelBolitas);
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
        jPanelCredenciales = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        btnForgottenPassword = new javax.swing.JButton();
        jLabelBienvenida = new javax.swing.JLabel();
        jLabelBolitas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jPanelPrincipal.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanelPrincipal.setPreferredSize(new java.awt.Dimension(1280, 720));

        jLabelLogoDegradado.setMinimumSize(new java.awt.Dimension(777, 720));
        jLabelLogoDegradado.setPreferredSize(new Dimension((int)(this.getSize().width/100),(int) (this.getSize().height*720/100)));
        jLabelLogoDegradado.setRequestFocusEnabled(false);
        jLabelLogoDegradado.setVerifyInputWhenFocusTarget(false);

        jPanelCredenciales.setForeground(new java.awt.Color(255, 0, 51));
        jPanelCredenciales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Usuario");
        jPanelCredenciales.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel2.setText("Contraseña");
        jPanelCredenciales.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));
        jPanelCredenciales.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 270, 40));

        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        jPanelCredenciales.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 270, 40));

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });
        jPanelCredenciales.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 270, 40));

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
        jPanelCredenciales.add(btnForgottenPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 270, -1));

        jLabelBienvenida.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabelBienvenida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBienvenida.setText("Bienvenido");
        jPanelCredenciales.add(jLabelBienvenida, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jLabelBolitas.setMaximumSize(new java.awt.Dimension(248, 231));
        jLabelBolitas.setMinimumSize(new java.awt.Dimension(248, 231));
        jLabelBolitas.setPreferredSize(new java.awt.Dimension(248, 231));

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addComponent(jLabelLogoDegradado, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(jPanelCredenciales, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111))
                    .addComponent(jLabelBolitas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addContainerGap(119, Short.MAX_VALUE)
                .addComponent(jPanelCredenciales, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabelBolitas, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabelLogoDegradado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        String nombre = JOptionPane.showInputDialog(this, "Ingresa tu primer nombre", DISPOSE_ON_CLOSE);

        notificarAdmon(nombre);

        /*
        DialogoEmergente t = new DialogoEmergente(this, rootPaneCheckingEnabled);
        t.setTexto("Por favor, solicite a un \nadministrador que le \nrestaure la contraseña");
        t.setVisible(true);
         */

    }//GEN-LAST:event_btnForgottenPasswordActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized

        int height = (int) this.getBounds().getHeight();
        int width = (int) this.getBounds().getWidth();
        int maxX = (int) this.getBounds().getMaxX();
        int maxY = (int) this.getBounds().getMaxY();

        jPanelPrincipal.setBounds(0, 0, width, height);
        jPanelPrincipal.setBackground(Color.WHITE);

        //Configurar logo
        jLabelLogoDegradado.setBounds(0, 0, (int) width / 2, height - 43);

        //Configurar bolitas
        jLabelBolitas.setBounds(maxX - 248, maxY - 231, 248, 231);

        //Imagenes
        new FuncionesUtiles().colocarImagen("/Imagenes/LogoLetrasDegradado.png", jLabelLogoDegradado);
        new FuncionesUtiles().colocarImagen("/Imagenes/bolitas.png", jLabelBolitas);

        this.repaint();
        jPanelPrincipal.repaint();
        jLabelLogoDegradado.repaint();
        jLabelBolitas.repaint();

    }//GEN-LAST:event_formComponentResized

    private boolean isRestContra(String nombre){
        return new CredencialManager().isRestContra(nombre);
    }
    
    private void formularioRestContra(String nombre){
        int idEmpleado = new CredencialManager().getidEmpleado(nombre);
        DialogoFomularioR form = new DialogoFomularioR(this, true, idEmpleado);
        
        form.setVisible(true);
        
    }
    
    private void iniciarSesion() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        String pass = null;
        
        if (isRestContra(username)) {
            formularioRestContra(username);
            return;
        }

        if (username.isEmpty() || password.isEmpty()) {
            DialogoEmergente dEmergente = new DialogoEmergente(this, true);
            dEmergente.setTexto("¡Error! Campos de entrada vacios!");
            dEmergente.setVisible(true);
            return;
        }

        try {
            pass = encriptaContra(password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("¡Error! No se pudo encriptar la contraseña");
            return;
        }

        String rolId = new CredencialManager().verificarLogin(username, pass);

        if (rolId.isEmpty()) {
            DialogoEmergente dEmergente = new DialogoEmergente(this, true);
            dEmergente.setTexto("¡Error! Usuario o\ncontraseña no válidos");
            dEmergente.setVisible(true);
            return;
        }

        if (rolId.equals("1")) {
            PanelControlAdministrador panel = new PanelControlAdministrador();
            this.dispose();
            panel.setVisible(true);
        } else {
            PanelControlEmpleado panel = new PanelControlEmpleado();
            this.dispose();
            panel.setVisible(true);
        }
    }

    private void notificarAdmon(String nombre) {

        if (new CredencialManager().notificarContra(nombre)) {
            JOptionPane.showMessageDialog(this, "Se notifico al administrador");
        } else {
            JOptionPane.showMessageDialog(this, "ERROR!, EL EMPLEADO NO EXISTE");
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
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
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

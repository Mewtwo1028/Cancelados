package vista;

import Util.FuncionesUtiles;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JFrame;

public class AccionesRapidasEmpleado extends javax.swing.JPanel {

    JFrame actual;
    private String nombre = "";
    private int idEmpleado;

    public AccionesRapidasEmpleado(JFrame actual) {
        initComponents();
        inicializar();
        this.actual = actual;
    }
    
    public void setIdEmpleado(int idEmpleado){
        this.idEmpleado = idEmpleado;
    }

    private void inicializar() {
        this.setBounds(0, 0, 266, 650);
        jLabelLogo.setSize(233, 245);
        //Imagenes
        FuncionesUtiles funcionesUtiles = new FuncionesUtiles();
        funcionesUtiles.colocarImagen("/Imagenes/LogoLetras.png", jLabelLogo);

        //Configurar jPanelCredenciales
        this.setBackground(Color.WHITE);

        //Configurar botones
        FuncionesUtiles funUtil = new FuncionesUtiles();
        funUtil.confBtnMenu(btnMenu);
        funUtil.confBtnMenu(btnConfiguraciones);
        funUtil.confBtnMenu(btnAcercaDe);
        funUtil.confBtnMenu(btnVolver);

        //Configurar linea
        jPanelLinea.setBackground(funUtil.getColorCancelados());

        //Agregar iconos a los botones
        funUtil.colocarImagen("/Iconos/menu.png", btnMenu);
        funUtil.colocarImagen("/Iconos/configuraciones.png", btnConfiguraciones);
        funUtil.colocarImagen("/Iconos/acercaDe.png", btnAcercaDe);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMenu = new javax.swing.JButton();
        jLabelLogo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnConfiguraciones = new javax.swing.JButton();
        btnAcercaDe = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnVolver = new javax.swing.JButton();
        jPanelLinea = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        btnMenu.setText("MENU");
        btnMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMenuMouseEntered(evt);
            }
        });
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        btnConfiguraciones.setText("CONFIGURACIONES");
        btnConfiguraciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfiguracionesMouseEntered(evt);
            }
        });

        btnAcercaDe.setText("ACERCA DE");
        btnAcercaDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAcercaDeMouseEntered(evt);
            }
        });

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jPanelLinea.setPreferredSize(new java.awt.Dimension(15, 100));

        javax.swing.GroupLayout jPanelLineaLayout = new javax.swing.GroupLayout(jPanelLinea);
        jPanelLinea.setLayout(jPanelLineaLayout);
        jPanelLineaLayout.setHorizontalGroup(
            jPanelLineaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanelLineaLayout.setVerticalGroup(
            jPanelLineaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\osmar\\OneDrive\\Documentos\\GitHub\\Cancelados\\ProyectoJava\\Arrow Icon.png")); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator1)
                                    .addComponent(btnConfiguraciones, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                    .addComponent(btnAcercaDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator2)
                                    .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabelLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanelLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMenu)
                        .addGap(134, 134, 134)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnConfiguraciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAcercaDe)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVolver)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelLinea, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        PanelControlEmpleado menu = new PanelControlEmpleado();
        menu.setNombre(nombre);
        menu.setIdAdmon(idEmpleado);
        actual.dispose();
        menu.setVisible(true);
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        if (actual.getTitle().equals("Panel de control")) {
            Login login = new Login();
            login.setVisible(true);
            actual.dispose();
        } else {
            PanelControlEmpleado admon = new PanelControlEmpleado();
            admon.setIdAdmon(idEmpleado);
            admon.setNombre(nombre);
            admon.setVisible(true);
            actual.dispose();
        }
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuMouseEntered
        btnMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnMenuMouseEntered

    private void btnConfiguracionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfiguracionesMouseEntered
        btnConfiguraciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnConfiguracionesMouseEntered

    private void btnAcercaDeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAcercaDeMouseEntered
        btnAcercaDe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnAcercaDeMouseEntered

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        if (actual.getTitle().equals("Panel de control")) {
            Login login = new Login();
            login.setVisible(true);
            actual.dispose();
        } else {
            PanelControlEmpleado admon = new PanelControlEmpleado();
            admon.setIdAdmon(idEmpleado);
            admon.setNombre(nombre);
            admon.setVisible(true);
            actual.dispose();
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcercaDe;
    private javax.swing.JButton btnConfiguraciones;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JPanel jPanelLinea;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}

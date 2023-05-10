package vista;

import controlador.NotificacionManager;
import modelo.FuncionesUtiles;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class AccionesRapidasAdministrador extends javax.swing.JPanel {

    JFrame actual;
    private String nombre = "";

    public AccionesRapidasAdministrador(JFrame actual) {
        initComponents();
        inicializar();
        this.actual = actual;
        double width = Toolkit.getDefaultToolkit().getScreenSize().width;
        double height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize((int) (1280 * width / 100), (int) (height * 720 / 100));
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
        funUtil.confBtnMenu(btnPendientes);
        funUtil.confBtnMenu(btnReportes);
        funUtil.confBtnMenu(btnNotificaciones);
        funUtil.confBtnMenu(btnPagos);
        funUtil.confBtnMenu(btnConfiguraciones);
        funUtil.confBtnMenu(btnAcercaDe);
        funUtil.confBtnMenu(btnVolver);

        //Configurar linea
        jPanelLinea.setBackground(funUtil.getColorCancelados());

        //Agregar iconos a los botones
        funUtil.colocarImagen("/Iconos/menu.png", btnMenu);
        funUtil.colocarImagen("/Iconos/pendientes.png", btnPendientes);
        funUtil.colocarImagen("/Iconos/reportes.png", btnReportes);
        imagenNot();
        funUtil.colocarImagen("/Iconos/pagos.png", btnPagos);
        funUtil.colocarImagen("/Iconos/configuraciones.png", btnConfiguraciones);
        funUtil.colocarImagen("/Iconos/acercaDe.png", btnAcercaDe);
    }

    public void imagenNot() {
        FuncionesUtiles funUtil = new FuncionesUtiles();

        if (new NotificacionManager().hayNot()) {
            funUtil.colocarImagen("/Iconos/notificacion.png", btnNotificaciones);
        } else {
            funUtil.colocarImagen("/Iconos/notificaciones.png", btnNotificaciones);
        }
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
        btnPendientes = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnNotificaciones = new javax.swing.JButton();
        btnPagos = new javax.swing.JButton();
        jLabelLogo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnConfiguraciones = new javax.swing.JButton();
        btnAcercaDe = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnVolver = new javax.swing.JButton();
        jPanelLinea = new javax.swing.JPanel();

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

        btnPendientes.setText("PENDIENTES");
        btnPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPendientesMouseEntered(evt);
            }
        });

        btnReportes.setText("REPORTES");
        btnReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReportesMouseEntered(evt);
            }
        });

        btnNotificaciones.setText("NOTIFICACIONES");
        btnNotificaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNotificacionesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNotificacionesMouseEntered(evt);
            }
        });

        btnPagos.setText("PAGOS");
        btnPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPagosMouseEntered(evt);
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
        jPanelLinea.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPendientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNotificaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                    .addComponent(btnPagos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(btnConfiguraciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAcercaDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPendientes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReportes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNotificaciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPagos)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnConfiguraciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAcercaDe)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                        .addComponent(btnVolver)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelLinea, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        imagenNot();
        PanelControlAdministrador menu = new PanelControlAdministrador();
        menu.setNombre(nombre);
        actual.dispose();
        menu.setVisible(true);
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        if (actual.getTitle().equals("Panel de control")) {
            Login login = new Login();
            login.setVisible(true);
            actual.dispose();
        } else {
            PanelControlAdministrador admon = new PanelControlAdministrador();
            admon.setNombre(nombre);
            admon.setVisible(true);
            actual.dispose();
        }
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuMouseEntered
        btnMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnMenuMouseEntered

    private void btnNotificacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNotificacionesMouseClicked
        // TODO add your handling code here:

        imagenNot();

        DialogoNotificacion not = new DialogoNotificacion(actual, true);
        not.setVisible(true);
    }//GEN-LAST:event_btnNotificacionesMouseClicked

    private void btnPendientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPendientesMouseEntered
        btnPendientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnPendientesMouseEntered

    private void btnReportesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseEntered
        btnReportes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnReportesMouseEntered

    private void btnNotificacionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNotificacionesMouseEntered
        btnNotificaciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnNotificacionesMouseEntered

    private void btnPagosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPagosMouseEntered
        btnPagos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnPagosMouseEntered

    private void btnConfiguracionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfiguracionesMouseEntered
        btnConfiguraciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnConfiguracionesMouseEntered

    private void btnAcercaDeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAcercaDeMouseEntered
        btnAcercaDe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnAcercaDeMouseEntered

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcercaDe;
    private javax.swing.JButton btnConfiguraciones;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnNotificaciones;
    private javax.swing.JButton btnPagos;
    private javax.swing.JButton btnPendientes;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JPanel jPanelLinea;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}

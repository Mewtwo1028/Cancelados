package vista;

import Util.FuncionesUtiles;
import controlador.EnviosManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import modelo.Direccion;

public class DialogoDireccionClienteEnvio extends javax.swing.JDialog {

    private AdministrarCliente fr;

    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private HashMap<JCheckBox, JTextField> checkBoxTextFieldMap = new HashMap<>();

    public DialogoDireccionClienteEnvio(AdministrarCliente parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.fr = parent;
        initDialog();
        initTbl();
        initColors();
        initHashMap();
        initCampos();
    }

    private void initCampos() {
        this.jTextFieldEstado.setEnabled(false);
        this.jTextFieldMunicipio.setEnabled(false);
        this.jTextFieldColonia.setEnabled(false);
        this.jTextFieldCP.setEnabled(false);

        this.jTextFieldEstado.setName("estado");
        this.jTextFieldMunicipio.setName("municipio");
        this.jTextFieldColonia.setName("colonia");
        this.jTextFieldCP.setName("cp");

        this.jTextFieldEstado.setText("");
        this.jTextFieldMunicipio.setText("");
        this.jTextFieldColonia.setText("");
        this.jTextFieldCP.setText("");
    }

    private void initHashMap() {
        // Asociar cada JCheckBox con su JTextField correspondiente
        checkBoxTextFieldMap.put(jCheckBoxEstado, jTextFieldEstado);
        checkBoxTextFieldMap.put(jCheckBoxMunicipio, jTextFieldMunicipio);
        checkBoxTextFieldMap.put(jCheckBoxColonia, jTextFieldColonia);
        checkBoxTextFieldMap.put(jCheckBoxCP, jTextFieldCP);
    }

    private void initDialog() {
        this.setTitle("Seleccionar una Direccion");
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(700, 417));
        this.getContentPane().requestFocusInWindow();
    }

    private void initTbl() {
        modelo.addColumn("Estado");
        modelo.addColumn("Municipio");
        modelo.addColumn("Colonia");
        modelo.addColumn("CP");

        jTable1.setModel(modelo);
    }

    private void initTablaUsa() {
        modelo.addColumn("Estado");
        modelo.addColumn("Ciudad");
        modelo.addColumn("ZIP Code");
        jTable1.setModel(modelo);
    }

    private void initColors() {
        FuncionesUtiles tool = new FuncionesUtiles();
        this.getContentPane().setBackground(Color.WHITE);
        tool.confBtnColor(jButton1);
        tool.confBtnColor(jButton2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextFieldEstado = new javax.swing.JTextField();
        jTextFieldMunicipio = new javax.swing.JTextField();
        jTextFieldColonia = new javax.swing.JTextField();
        jTextFieldCP = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jCheckBoxEstado = new javax.swing.JCheckBox();
        jCheckBoxMunicipio = new javax.swing.JCheckBox();
        jCheckBoxColonia = new javax.swing.JCheckBox();
        jCheckBoxCP = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        cbPais = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTextFieldEstado.setText("jTextField1");
        jTextFieldEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldEstadoKeyPressed(evt);
            }
        });

        jTextFieldMunicipio.setText("jTextField1");
        jTextFieldMunicipio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldMunicipioKeyPressed(evt);
            }
        });

        jTextFieldColonia.setText("jTextField1");
        jTextFieldColonia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldColoniaKeyPressed(evt);
            }
        });

        jTextFieldCP.setText("jTextField1");
        jTextFieldCP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCPKeyPressed(evt);
            }
        });

        jButton1.setText("BUSCAR (FILTRAR)");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jCheckBoxEstado.setText("Estado");
        jCheckBoxEstado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxEstadoStateChanged(evt);
            }
        });

        jCheckBoxMunicipio.setText("Municipio");
        jCheckBoxMunicipio.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxMunicipioStateChanged(evt);
            }
        });

        jCheckBoxColonia.setText("Colonia");
        jCheckBoxColonia.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxColoniaStateChanged(evt);
            }
        });

        jCheckBoxCP.setText("CP");
        jCheckBoxCP.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxCPStateChanged(evt);
            }
        });

        jButton2.setText("SELECCIONAR");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        cbPais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "México", "EUA" }));
        cbPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPaisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxCP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldCP, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 232, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(cbPais, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldEstado)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jCheckBoxEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbPais, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jCheckBoxMunicipio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldColonia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBoxColonia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxCP, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jTextFieldCP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

        buscar();

    }//GEN-LAST:event_jButton1MouseClicked

    private void jCheckBoxEstadoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxEstadoStateChanged
        encender((JCheckBox) evt.getSource());
    }//GEN-LAST:event_jCheckBoxEstadoStateChanged

    private void jCheckBoxMunicipioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxMunicipioStateChanged
        encender((JCheckBox) evt.getSource());
    }//GEN-LAST:event_jCheckBoxMunicipioStateChanged

    private void jCheckBoxColoniaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxColoniaStateChanged
        encender((JCheckBox) evt.getSource());
    }//GEN-LAST:event_jCheckBoxColoniaStateChanged

    private void jCheckBoxCPStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxCPStateChanged
        encender((JCheckBox) evt.getSource());
    }//GEN-LAST:event_jCheckBoxCPStateChanged

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // Obtener el renglon
        
        int renglon = jTable1.getSelectedRow();

        if (renglon == -1) {
            JOptionPane.showMessageDialog(this, "ERROR! Debe de seleccionar un renglon.");
            return;
        }

        //fr.setDireccion(datos);
        Direccion dir = new Direccion();

        if (cbPais.getSelectedIndex() == 1) {
            //usa
            dir.setPais("USA");
            dir.setEstado(jTable1.getValueAt(renglon, 0).toString());
            dir.setCiudad(jTable1.getValueAt(renglon, 1).toString());
            dir.setZip(jTable1.getValueAt(renglon, 2).toString());
            //dir.setCalle(jTextField1.getText());
        } else {
            //mexico
            dir.setPais("México");
            dir.setEstado(jTable1.getValueAt(renglon, 0).toString());
            dir.setMunicipio(jTable1.getValueAt(renglon, 1).toString());
            dir.setColonia(jTable1.getValueAt(renglon, 2).toString());
            dir.setCp(jTable1.getValueAt(renglon, 3).toString());
            //dir.setCalle(jTextField1.getText());
        }
        
        //retornar la direccion
        fr.setDireccion(dir);
        
        this.dispose();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jTextFieldEstadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEstadoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscar();
        }
    }//GEN-LAST:event_jTextFieldEstadoKeyPressed

    private void jTextFieldMunicipioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMunicipioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscar();
        }
    }//GEN-LAST:event_jTextFieldMunicipioKeyPressed

    private void jTextFieldColoniaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldColoniaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscar();
        }
    }//GEN-LAST:event_jTextFieldColoniaKeyPressed

    private void jTextFieldCPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCPKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscar();
        }
    }//GEN-LAST:event_jTextFieldCPKeyPressed

    private void cbPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPaisActionPerformed
        eliminarTodasLasColumnas(jTable1);
        modelo.setColumnCount(0);
        if (cbPais.getSelectedIndex() == 1) {
            jCheckBoxMunicipio.setText("Ciudad");
            initTablaUsa();
        } else {
            jCheckBoxMunicipio.setText("Municipio");
            initTbl();
        }
    }//GEN-LAST:event_cbPaisActionPerformed

    public void eliminarTodasLasColumnas(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();

        // Recorremos todas las columnas en reversa y las eliminamos una por una
        for (int i = columnModel.getColumnCount() - 1; i >= 0; i--) {
            TableColumn column = columnModel.getColumn(i);
            columnModel.removeColumn(column);
        }
    }

    private void buscar() {
        String[] datos = new String[4];

        datos[0] = jTextFieldEstado.getText().toLowerCase().trim();
        datos[1] = jTextFieldMunicipio.getText().toLowerCase().trim();
        datos[2] = jTextFieldColonia.getText().toLowerCase().trim();
        datos[3] = jTextFieldCP.getText().toLowerCase().trim();

        if (cbPais.getSelectedIndex() == 1) {
            consultarDireccionesUsa(datos);
        } else {
            consultarDirecciones(datos);
        }

    }

    private void encender(JCheckBox checkBox) {
        JTextField textField = checkBoxTextFieldMap.get(checkBox);
        if (checkBox.isSelected()) {
            textField.setEnabled(true);
        } else {
            textField.setEnabled(false);
        }
    }

    private void consultarDireccionesUsa(String[] datos) {

        //Obtener datos
        String estado = datos[0];
        String ciudad = datos[1];
        String zip = datos[2];

        //Consultar en la BD
        ArrayList<String[]> direcciones = new EnviosManager().getDireccionesUsa(estado, ciudad, zip);

        //Llenar la tabla con los datos consultados
        llenarTabla(direcciones);
    }

    private void consultarDirecciones(String[] datos) {

        //Obtener datos
        String estado = datos[0];
        String municipio = datos[1];
        String colonia = datos[2];
        String cp = datos[3];

        //Consultar en la BD
        ArrayList<String[]> direcciones = new EnviosManager().getDireccionesMex(estado, municipio, colonia, cp);

        //Llenar la tabla con los datos consultados
        llenarTabla(direcciones);
    }

    private void llenarTabla(ArrayList<String[]> direcciones) {
        modelo.setRowCount(0); //Limpiamos la tabla
        for (int i = 0; i < direcciones.size(); i++) {
            modelo.addRow(direcciones.get(i));
        }
    }

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(DialogoDireccionClienteEnvio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogoDireccionClienteEnvio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogoDireccionClienteEnvio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogoDireccionClienteEnvio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogoDireccionClienteEnvio dialog = new DialogoDireccionClienteEnvio(new AdministrarCliente(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbPais;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBoxCP;
    private javax.swing.JCheckBox jCheckBoxColonia;
    private javax.swing.JCheckBox jCheckBoxEstado;
    private javax.swing.JCheckBox jCheckBoxMunicipio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldCP;
    private javax.swing.JTextField jTextFieldColonia;
    private javax.swing.JTextField jTextFieldEstado;
    private javax.swing.JTextField jTextFieldMunicipio;
    // End of variables declaration//GEN-END:variables
}

package vista;

import controlador.ClienteManager;
import controlador.ProductoManager;
import controlador.VentaManager;
import modelo.Cliente;
import modelo.DetalleVenta;
import modelo.FuncionesUtiles;
import modelo.Producto;
import modelo.Venta;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class RegistrarVenta extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    ArrayList<Producto> productos = new ProductoManager().consultarNombres();
    ArrayList<Cliente> clientes = new ClienteManager().consultarNombres();
    float subTotal = 0;

    public RegistrarVenta() {
        initComponents();
        inicializar();
        initTabla();
    }

    private void inicializar() {
        FuncionesUtiles tool = new FuncionesUtiles();
        //Configuracion ventana
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Administrar Producto");

        //Configurar panel principal
        jPanelPrincipal.setBackground(Color.WHITE);

        //Colocar panel de la izquierda
        AccionesRapidasAdministrador panelBotones = new AccionesRapidasAdministrador(this);
        panelBotones.setBounds(0, 0, 266, (int) this.getBounds().getHeight() - 80);
        jPanelIzquierda.removeAll();
        jPanelIzquierda.setMinimumSize(panelBotones.getPreferredSize());
        jPanelIzquierda.add(panelBotones);
        panelBotones.revalidate();
        panelBotones.repaint();

        //Configurar panel de arriba
        jPanelInformacion.setBackground(Color.WHITE);
        jPanelInformacion.setBounds(278, 6, (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() - (int) jPanelIzquierda.getBounds().getWidth() - (int) jPanelLinea.getBounds().getWidth(), 110);
        PanelInformacionArriba panelInformacion = new PanelInformacionArriba();
        panelInformacion.setBounds(0, 0, (int) jPanelInformacion.getBounds().getWidth(), 110);
        jPanelInformacion.removeAll();
        jPanelInformacion.setMinimumSize(panelInformacion.getPreferredSize());
        jPanelInformacion.add(panelInformacion);
        panelInformacion.revalidate();
        panelInformacion.repaint();

        //Paneles
        jPanelOperaciones.setBackground(Color.WHITE);
        jPanelAcciones.setBackground(Color.WHITE);
        jPanelFormulario.setBackground(Color.WHITE);

        //Fondo de la tabla
        jScrollPane1.setBackground(Color.WHITE);

        //Linea
        jPanelLinea.setBackground(tool.getColorCancelados());

        //Agregar estilo a los botones
        tool.confBtnColor(btnAgregar);
        tool.confBtnColor(btnRegistrarVenta);
        tool.confBtnColor(btnEliminar);

        //Limpiar txtFields
        limpiarTodosTxtFields();

        //desactivar
        txtImporte.setEnabled(false);
        txtPrecioUnitario.setEnabled(false);
        txtSubtotal.setEnabled(false);
        txtTotal.setEnabled(false);
        txtIDProducto.setEnabled(false);

        //Llenar comboBox
        llenarProducto();
        llenarCliente();

        //Limpiar
        cbCantidad.removeAllItems();

        //total
        txtSubtotal.setText(String.valueOf(subTotal));
    }

    private void llenarProducto() {
        DefaultComboBoxModel b = new DefaultComboBoxModel();
        b.addElement("SELECCIONA UN PRODUCTO");

        for (Producto producto : productos) {
            b.addElement(producto.getNombre());
        }

        cbNombreProducto.setModel(b);
    }

    private void llenarCliente() {
        DefaultComboBoxModel dl = new DefaultComboBoxModel();
        dl.addElement("SELECCIONA UN CLIENTE");

        for (Cliente cliente : clientes) {
            dl.addElement(cliente.getNombre());
        }

        cbCliente.setModel(dl);
    }

    private void limpiarTxtFields() {
        cbNombreProducto.setSelectedIndex(0);
        cbCantidad.setSelectedIndex(0);
        txtPrecioUnitario.setText("");
        txtImporte.setText("");
    }

    private void limpiarTodosTxtFields() {
        cbNombreProducto.setSelectedIndex(0);
        cbCantidad.setSelectedIndex(0);
        txtPrecioUnitario.setText("");
        txtImporte.setText("");
        txtSubtotal.setText("");
        txtTotal.setText("");
        txtIDProducto.setText("");
    }

    private void initTabla() {
        modelo.addColumn("ID Producto");
        modelo.addColumn("Producto");
        modelo.addColumn("Precio Unitario");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Importe");
        tblProducto.setModel(modelo);
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
        jPanelIzquierda = new javax.swing.JPanel();
        jPanelInformacion = new javax.swing.JPanel();
        jPanelLinea = new javax.swing.JPanel();
        jPanelOperaciones = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        jPanelFormulario = new javax.swing.JPanel();
        labelProducto = new javax.swing.JLabel();
        labelCantidad = new javax.swing.JLabel();
        labelPrecioUnitario = new javax.swing.JLabel();
        txtPrecioUnitario = new javax.swing.JTextField();
        labelimporte = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        cbNombreProducto = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox<>();
        labelSubtotal = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        labelTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtIDProducto = new javax.swing.JTextField();
        cbCantidad = new javax.swing.JComboBox<>();
        jPanelAcciones = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnRegistrarVenta = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanelIzquierdaLayout = new javax.swing.GroupLayout(jPanelIzquierda);
        jPanelIzquierda.setLayout(jPanelIzquierdaLayout);
        jPanelIzquierdaLayout.setHorizontalGroup(
            jPanelIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );
        jPanelIzquierdaLayout.setVerticalGroup(
            jPanelIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelInformacionLayout = new javax.swing.GroupLayout(jPanelInformacion);
        jPanelInformacion.setLayout(jPanelInformacionLayout);
        jPanelInformacionLayout.setHorizontalGroup(
            jPanelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelInformacionLayout.setVerticalGroup(
            jPanelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelLineaLayout = new javax.swing.GroupLayout(jPanelLinea);
        jPanelLinea.setLayout(jPanelLineaLayout);
        jPanelLineaLayout.setHorizontalGroup(
            jPanelLineaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        jPanelLineaLayout.setVerticalGroup(
            jPanelLineaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tblProducto.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProducto);

        labelProducto.setText("Producto");

        labelCantidad.setText("Cantidad");

        labelPrecioUnitario.setText("Precio Unitario");

        txtPrecioUnitario.setText("jTextField5");

        labelimporte.setText("Importe");

        txtImporte.setText("jTextField6");

        cbNombreProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbNombreProducto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbNombreProductoItemStateChanged(evt);
            }
        });

        jLabel1.setText("Cliente");

        cbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        labelSubtotal.setText("Subtotal");

        txtSubtotal.setText("jTextField1");

        labelTotal.setText("Total");

        txtTotal.setText("jTextField2");

        jLabel2.setText("ID Producto");

        txtIDProducto.setText("jTextField1");

        cbCantidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCantidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCantidadItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelFormularioLayout = new javax.swing.GroupLayout(jPanelFormulario);
        jPanelFormulario.setLayout(jPanelFormularioLayout);
        jPanelFormularioLayout.setHorizontalGroup(
            jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormularioLayout.createSequentialGroup()
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelFormularioLayout.createSequentialGroup()
                                        .addComponent(labelProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanelFormularioLayout.createSequentialGroup()
                                        .addComponent(labelCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(6, 6, 6)))
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbNombreProducto, 0, 130, Short.MAX_VALUE)
                                    .addComponent(cbCantidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelPrecioUnitario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelimporte, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPrecioUnitario)
                                    .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(labelTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(txtTotal)
                            .addComponent(txtIDProducto)))
                    .addGroup(jPanelFormularioLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(350, Short.MAX_VALUE))
        );
        jPanelFormularioLayout.setVerticalGroup(
            jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelSubtotal)
                        .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelTotal)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPrecioUnitario)
                        .addComponent(jLabel2)
                        .addComponent(txtIDProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelPrecioUnitario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelimporte)
                    .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnRegistrarVenta.setText("Registrar venta");
        btnRegistrarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarVentaActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAccionesLayout = new javax.swing.GroupLayout(jPanelAcciones);
        jPanelAcciones.setLayout(jPanelAccionesLayout);
        jPanelAccionesLayout.setHorizontalGroup(
            jPanelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelAccionesLayout.setVerticalGroup(
            jPanelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegistrarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelOperacionesLayout = new javax.swing.GroupLayout(jPanelOperaciones);
        jPanelOperaciones.setLayout(jPanelOperacionesLayout);
        jPanelOperacionesLayout.setHorizontalGroup(
            jPanelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOperacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelOperacionesLayout.createSequentialGroup()
                        .addComponent(jPanelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelOperacionesLayout.setVerticalGroup(
            jPanelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelOperacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelIzquierda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelOperaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelIzquierda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(jPanelInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelOperaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanelLinea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String idProducto = txtIDProducto.getText();
        String cantidad = cbCantidad.getSelectedItem().toString();
        String importe = txtImporte.getText();
        String producto = cbNombreProducto.getSelectedItem().toString();
        String precioUnitario = txtPrecioUnitario.getText();

        subTotal += Float.parseFloat(importe);
        txtSubtotal.setText(String.valueOf(subTotal));

        if (existeProductoTabla(idProducto)) {
            actualizarCantidadProducto(idProducto, cantidad, importe);
        } else {
            agregarTabla(idProducto, producto, precioUnitario, cantidad, importe);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tblProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductoMouseClicked
        obtenerRenglonTabla();
    }//GEN-LAST:event_tblProductoMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarVentaActionPerformed
        ArrayList<Producto> listaProductos = obtenerListaProductos();
        float total = Float.parseFloat(txtSubtotal.getText());
        int idCliente = obtenerIdCliente(cbCliente.getSelectedItem().toString());
        int idEmpleado = 1;

        Venta venta = new Venta(total, idCliente, idEmpleado);

        DialogoEmergente dl = new DialogoEmergente(this, true);

        if (registrarVenta(venta, listaProductos)) {
            dl.setTexto("Venta registrada de\nforma correcta!");
            dl.setVisible(true);
        } else {
            dl.setTexto("ERROR! NO SE PUDO REGISTRAR LA VENTA");
            dl.setVisible(true);
        }
    }//GEN-LAST:event_btnRegistrarVentaActionPerformed

    private void cbNombreProductoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbNombreProductoItemStateChanged
        if (evt.getStateChange() == 1 && !evt.getItem().equals("SELECCIONA UN PRODUCTO")) {
            for (Producto producto : productos) {
                if (producto.getNombre().equals(evt.getItem())) {
                    txtPrecioUnitario.setText(String.valueOf(producto.getPrecioUnitario()));
                    llenarCantidad(producto);
                    txtIDProducto.setText(String.valueOf(producto.getIdProducto()));
                }
            }
            if (Integer.parseInt(cbCantidad.getSelectedItem().toString()) >= 1) {
                calcularImporte();
            }
        }
    }//GEN-LAST:event_cbNombreProductoItemStateChanged

    private void cbCantidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCantidadItemStateChanged
        calcularImporte();
    }//GEN-LAST:event_cbCantidadItemStateChanged

    private boolean registrarVenta(Venta venta, ArrayList<Producto> productos) {
        int idVenta = new VentaManager().realizarVenta(venta);

        return new DetalleVenta().realizarDetalleVenta(productos, idVenta);
    }

    private void actualizarCantidadProducto(String idProducto, String cantidad, String importe) {
        for (int i = 0; i < tblProducto.getRowCount(); i++) {
            Object id = tblProducto.getValueAt(i, 0);
            if (id != null && id.toString().equals(idProducto)) {

                int vCantidad = Integer.parseInt(tblProducto.getValueAt(i, 3).toString());
                int nCantidad = vCantidad + Integer.parseInt(cantidad);
                tblProducto.setValueAt(nCantidad, i, 3);

                float vImporte = Float.parseFloat(tblProducto.getValueAt(i, 4).toString());
                float nImporte = vImporte + Float.parseFloat(importe);
                tblProducto.setValueAt(nImporte, i, 4);
                break; // Para salir del ciclo si el producto ha sido encontrado
            }
        }
        tblProducto.repaint();
    }

    private boolean existeProductoTabla(String idProducto) {
        ArrayList<Producto> listaProductos = obtenerCarrito();
        int id = Integer.parseInt(idProducto);

        for (Producto producto : listaProductos) {
            if (producto.getIdProducto() == id) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Producto> obtenerCarrito() {
        ArrayList<Producto> carrito = new ArrayList<>();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            Producto producto = new Producto();
            for (int j = 0; j < modelo.getColumnCount(); j++) {
                Object valorCelda = modelo.getValueAt(i, j);
                switch (j) {
                    case 0 ->
                        producto.setIdProducto(Integer.parseInt(valorCelda.toString()));
                    case 1 ->
                        producto.setNombre(valorCelda.toString());
                    case 2 ->
                        producto.setPrecioUnitario(Float.parseFloat(valorCelda.toString()));
                    case 3 ->
                        producto.setStock(Integer.parseInt(valorCelda.toString()));
                    case 4 ->
                        producto.setImporte(Float.parseFloat(valorCelda.toString()));
                    default -> {
                    }
                }
            }
            carrito.add(producto);
        }

        return carrito;
    }

    private ArrayList<Producto> obtenerListaProductos() {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        TableModel modelo = tblProducto.getModel();
        Producto producto;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            producto = new Producto();
            try {
                producto.setIdProducto(Integer.parseInt(modelo.getValueAt(i, 0).toString()));
                producto.setNombre(modelo.getValueAt(i, 1).toString());
                producto.setPrecioUnitario(Float.parseFloat(modelo.getValueAt(i, 2).toString()));
                producto.setStock(Integer.parseInt(modelo.getValueAt(i, 3).toString()));
                producto.setImporte(Float.parseFloat(modelo.getValueAt(i, 4).toString()));

                listaProductos.add(producto);
            } catch (NumberFormatException ex) {

            }
        }

        return listaProductos;
    }

    private void calcularImporte() {
        if (cbCantidad.getSelectedItem() != null) {
            int cantidad = Integer.parseInt(cbCantidad.getSelectedItem().toString());
            float precioUn = Float.parseFloat(txtPrecioUnitario.getText());

            float calculo = cantidad * precioUn;

            txtImporte.setText(String.valueOf(calculo));
        }
    }

    private int obtenerIdCliente(String nombreCliente) {

        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(nombreCliente)) {
                return cliente.getIdCliente();
            }
        }

        return -1;
    }

    private void llenarCantidad(Producto producto) {
        DefaultComboBoxModel dl = new DefaultComboBoxModel();
        for (int i = 1; i <= producto.getStock(); i++) {
            dl.addElement(i);
        }
        cbCantidad.setModel(dl);
    }

    private void obtenerRenglonTabla() {
        //Aqui va el codigo para eliminar algun productos o para modificarlo
    }

    private void agregarTabla(String idProducto, String nombre, String precioUnitario, String cantidad, String importe) {
        String[] renglon = {idProducto, nombre, precioUnitario, cantidad, importe};
        modelo.addRow(renglon);
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
            java.util.logging.Logger.getLogger(RegistrarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegistrarVenta;
    private javax.swing.JComboBox<String> cbCantidad;
    private javax.swing.JComboBox<String> cbCliente;
    private javax.swing.JComboBox<String> cbNombreProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelAcciones;
    private javax.swing.JPanel jPanelFormulario;
    private javax.swing.JPanel jPanelInformacion;
    private javax.swing.JPanel jPanelIzquierda;
    private javax.swing.JPanel jPanelLinea;
    private javax.swing.JPanel jPanelOperaciones;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCantidad;
    private javax.swing.JLabel labelPrecioUnitario;
    private javax.swing.JLabel labelProducto;
    private javax.swing.JLabel labelSubtotal;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelimporte;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTextField txtIDProducto;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtPrecioUnitario;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

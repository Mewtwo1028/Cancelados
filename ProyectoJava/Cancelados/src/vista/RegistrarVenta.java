package vista;

import controlador.ClienteManager;
import controlador.ProductoManager;
import controlador.VentaManager;
import modelo.Cliente;
import modelo.DetalleVenta;
import Util.FuncionesUtiles;
import modelo.Producto;
import modelo.Venta;
import java.awt.Color;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class RegistrarVenta extends javax.swing.JFrame {
    
    private int idAdmon;
    private float cambio;
    
    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    ArrayList<Producto> productos = new ArrayList<>();
    //ArrayList<Cliente> clientes = new ClienteManager().consultarNombres();
    float subTotal = 0;
    
    public RegistrarVenta() {
        initComponents();
        initProductos();
        inicializar();
        initTabla();
    }
    
    private void initProductos() {
        ArrayList<Producto> pr = new ProductoManager().consultarNombres();
        
        for (Producto e : pr) {
            if (e.getStock() >= 1) {
                productos.add(e);
            }
        }
    }
    
    private void inicializar() {
        FuncionesUtiles tool = new FuncionesUtiles();
        //Configuracion ventana
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Registrar venta");
        double width = Toolkit.getDefaultToolkit().getScreenSize().width;
        double height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //System.out.print((int)(1280*width/100)+","+height);
        this.setSize((int) (width), (int) height);
        this.setExtendedState(MAXIMIZED_BOTH);

        //Configurar panel principal
        jPanelPrincipal.setBackground(Color.WHITE);

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
        tool.confBtnColor(RegistrarEnvio);

        //Limpiar txtFields
        limpiarTodosTxtFields();

        //desactivar
        txtImporte.setEnabled(false);
        txtPrecioUnitario.setEnabled(false);
        txtSubtotal.setEnabled(false);
        txtTotal.setEnabled(false);
        txtIDProducto.setEnabled(false);
        txtIdCliente.setEnabled(false);
        txtNombreCliente.setEnabled(false);
        cbNombreProducto.setEnabled(false);

        //Llenar comboBox
        llenarProducto();
        llenarCliente();

        //Limpiar
        //spnCantidad.removeAll();
        //total
        txtSubtotal.setText(String.valueOf(subTotal));
    }
    
    private void llenarProducto() {
        DefaultComboBoxModel b = new DefaultComboBoxModel();
        b.addElement("SELECCIONA UN PRODUCTO");
        for (Producto producto : productos) {
            if (producto.getStock() >= 1) {
                b.addElement(producto.getNombre());
            }
        }
        
        cbNombreProducto.setModel(b);
        
    }
    
    private void llenarCliente() {
        txtIdCliente.setText("1");
        txtNombreCliente.setText("Publico En General");
    }
    
    private void limpiarTxtFields() {
        cbNombreProducto.setSelectedIndex(0);
        spnCantidad.setModel(new SpinnerNumberModel(1, 1, 1, 1));
        txtPrecioUnitario.setText("");
        txtImporte.setText("");
        //txtStock.setText("");
        txtIDProducto.setText("");
    }
    
    private void limpiarTodosTxtFields() {
        cbNombreProducto.setSelectedIndex(0);
        spnCantidad.setValue(1);
        txtPrecioUnitario.setText("");
        txtImporte.setText("");
        txtSubtotal.setText("");
        txtTotal.setText("");
        txtIDProducto.setText("");
        txtIdCliente.setText("1");
        txtNombreCliente.setText("Publico En General");
        modelo.setRowCount(0);
    }
    
    private void initTabla() {
        modelo.addColumn("ID Producto");
        modelo.addColumn("Producto");
        modelo.addColumn("Precio Unitario");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Importe");
        tblProducto.setModel(modelo);
    }
    
    public void setEmpleado(String nombre, int idEmpleado) {
        //Colocar panel de la izquierda
        AccionesRapidasEmpleado panelBotones = new AccionesRapidasEmpleado(this);
        this.idAdmon = idEmpleado;
        panelBotones.setIdEmpleado(idEmpleado);
        panelBotones.setNombre(nombre);
        panelBotones.setBounds(0, 0, 266, (int) this.getBounds().getHeight() - 50);
        jPanelIzquierda.removeAll();
        jPanelIzquierda.setMinimumSize(panelBotones.getPreferredSize());
        jPanelIzquierda.add(panelBotones);
        panelBotones.revalidate();
        panelBotones.repaint();
    }
    
    public void setAdmon(String nombre, int idAdmon) {
        //Colocar panel de la izquierda
        AccionesRapidasAdministrador panelBotones = new AccionesRapidasAdministrador(this);
        this.idAdmon = idAdmon;
        panelBotones.setIdAdmon(idAdmon);
        panelBotones.setNombre(nombre);
        panelBotones.setBounds(0, 0, 266, (int) this.getBounds().getHeight() - 50);
        jPanelIzquierda.removeAll();
        jPanelIzquierda.setMinimumSize(panelBotones.getPreferredSize());
        jPanelIzquierda.add(panelBotones);
        panelBotones.revalidate();
        panelBotones.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        venta1 = new modelo.Venta();
        venta2 = new modelo.Venta();
        jPanelPrincipal = new javax.swing.JPanel();
        jPanelIzquierda = new javax.swing.JPanel();
        jPanelInformacion = new javax.swing.JPanel();
        jPanelLinea = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ticket1 = new modelo.Ticket();
        jButton3 = new javax.swing.JButton();
        jPanelOperaciones = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        jPanelFormulario = new javax.swing.JPanel();
        labelCantidad = new javax.swing.JLabel();
        labelPrecioUnitario = new javax.swing.JLabel();
        txtPrecioUnitario = new javax.swing.JTextField();
        labelimporte = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        cbNombreProducto = new javax.swing.JComboBox<>();
        labelSubtotal = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        labelTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtIDProducto = new javax.swing.JTextField();
        spnCantidad = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        txtIdCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanelAcciones = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnRegistrarVenta = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        RegistrarEnvio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanelPrincipal.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanelPrincipal.setPreferredSize(new java.awt.Dimension(1280, 720));

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

        jScrollPane2.setViewportView(ticket1);

        jButton3.setText("Imprimir Ticket");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLineaLayout = new javax.swing.GroupLayout(jPanelLinea);
        jPanelLinea.setLayout(jPanelLineaLayout);
        jPanelLineaLayout.setHorizontalGroup(
            jPanelLineaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLineaLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 284, Short.MAX_VALUE))
            .addGroup(jPanelLineaLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLineaLayout.setVerticalGroup(
            jPanelLineaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLineaLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelOperaciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanelOperaciones.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 251, 743, 350));

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

        labelSubtotal.setText("Subtotal");

        txtSubtotal.setText("jTextField1");

        labelTotal.setText("Total");

        txtTotal.setText("jTextField2");

        jLabel2.setText("ID Producto");

        txtIDProducto.setText("jTextField1");

        spnCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spnCantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnCantidadStateChanged(evt);
            }
        });

        jButton1.setText("SELECCIONAR UN CLIENTE");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        txtIdCliente.setText("jTextField1");

        txtNombreCliente.setText("jTextField1");

        jButton2.setText("SELECCIONAR UN PRODUCTO");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelFormularioLayout = new javax.swing.GroupLayout(jPanelFormulario);
        jPanelFormulario.setLayout(jPanelFormularioLayout);
        jPanelFormularioLayout.setHorizontalGroup(
            jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelFormularioLayout.createSequentialGroup()
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                                .addComponent(labelCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6)
                                .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelPrecioUnitario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelimporte, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPrecioUnitario)
                                    .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                    .addComponent(labelTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(txtTotal)
                                    .addComponent(txtIDProducto)))
                            .addComponent(cbNombreProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelFormularioLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombreCliente)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanelFormularioLayout.setVerticalGroup(
            jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormularioLayout.createSequentialGroup()
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPrecioUnitario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelimporte)
                            .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelFormularioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelSubtotal)
                            .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTotal)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtIDProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanelOperaciones.add(jPanelFormulario, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnRegistrarVenta.setText("Registrar venta (Local)");
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

        RegistrarEnvio.setText("Registrar envio (Internacional)");
        RegistrarEnvio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegistrarEnvioMouseClicked(evt);
            }
        });
        RegistrarEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarEnvioActionPerformed(evt);
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
                    .addComponent(btnRegistrarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RegistrarEnvio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RegistrarEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanelOperaciones.add(jPanelAcciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(549, 6, -1, 179));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                        .addComponent(jPanelOperaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanelLinea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        
        
        
        if (validarFormulario()) {
            JOptionPane.showMessageDialog(this, "ERROR! Debe de llenar el formulario!");
            return;
        }
        
        String idProducto = txtIDProducto.getText();
        
        String cantidad = spnCantidad.getValue().toString();
        String importe = txtImporte.getText();
        String producto = cbNombreProducto.getSelectedItem().toString();
        String precioUnitario = txtPrecioUnitario.getText();
        
         
        ticket1.agregarProducto(producto, Double.parseDouble(cantidad), Double.parseDouble(importe));
         
         
        
       

        /*
        subTotal += Float.parseFloat(importe);
        txtSubtotal.setText(String.valueOf(subTotal));
        txtTotal.setText(txtSubtotal.getText());
         */
        if (existeProductoTabla(idProducto)) {
            actualizarCantidadProducto(idProducto, cantidad, importe);
        } else {
            agregarTabla(idProducto, producto, precioUnitario, cantidad, importe);
        }
        
        int id = Integer.parseInt(idProducto);

        //calcular la nueva cantidad, tomar la que hay en el arreglo de productos
        //y restarle la cantidad que acabamos de ingresar a la tabla
        Producto proc = obtenerProducto(id);
        int nStock = proc.getStock() - Integer.parseInt(cantidad);

        //bajar la cantidad en stock en la variable de productos
        try {
            for (Producto p : productos) {
                if (p.getIdProducto() == id) {
                    if (nStock == 0 || proc == null) {
                        productos.remove(p);
                        llenarProducto();
                    } else {
                        p.setStock(nStock);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        limpiarTxtFields();
        calcularTotal();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tblProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductoMouseClicked
        obtenerRenglonTabla();
    }//GEN-LAST:event_tblProductoMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
            ticket1.quitarProducto();
       
        int renglon = tblProducto.getSelectedRow();
        
        if (renglon == -1) {
            JOptionPane.showMessageDialog(this, "ERROR! DEBE DE SELECCIONAR UN RENGLON DE LA TABLA");
            return;
        }
        
        int idProducto = Integer.parseInt(tblProducto.getValueAt(renglon, 0).toString());
        String nombre = tblProducto.getValueAt(renglon, 1).toString();
        float precioUnitario = Float.parseFloat(tblProducto.getValueAt(renglon, 2).toString());
        int stock = 1;
        
        Producto producto = new Producto();
        
        producto.setIdProducto(idProducto);
        producto.setNombre(nombre);
        producto.setPrecioUnitario(precioUnitario);
        producto.setStock(stock);
        
        float precioUnitarioTabla = Float.parseFloat(tblProducto.getValueAt(renglon, 2).toString());
        int cantidadTabla = Integer.parseInt(tblProducto.getValueAt(renglon, 3).toString());
        
        DefaultTableModel m = (DefaultTableModel) tblProducto.getModel();
        
        if (Integer.parseInt(tblProducto.getValueAt(renglon, 3).toString()) > 1) {
            int valor = Integer.parseInt(tblProducto.getValueAt(renglon, 3).toString()) - 1;
            tblProducto.setValueAt(valor, renglon, 3);
            
            float imp = Float.parseFloat(tblProducto.getValueAt(renglon, 2).toString()) * Float.parseFloat(tblProducto.getValueAt(renglon, 3).toString());
            
            tblProducto.setValueAt(imp, renglon, 4);
            
        } else {
            m.removeRow(renglon);
        }
        
        tblProducto.setModel(m);
        
        if (existeProducto(productos, producto)) {
            for (int i = 0; i < productos.size(); i++) {
                if (productos.get(i).getIdProducto() == producto.getIdProducto()) {
                    productos.get(i).setStock(productos.get(i).getStock() + 1);
                }
            }
        } else {
            productos.add(producto);
            llenarProducto();
        }
        
        calcularTotal();
        
        cbNombreProducto.setSelectedIndex(0);
        spnCantidad.setModel(new SpinnerNumberModel(1, 1, 1, 1));
        txtPrecioUnitario.setText("");
        txtIDProducto.setText("");

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarVentaActionPerformed
        
        if (tblProducto.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "DEBE DE AGREGAR AL MENOS UN PRODUCTO AL CARRITO","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        ArrayList<Producto> listaProductos = obtenerListaProductos();
        float total = Float.parseFloat(txtSubtotal.getText());
        int idCliente = Integer.parseInt(txtIdCliente.getText());
        int idEmpleado = idAdmon;
        //double cambio;

        DetallePago dp = new DetallePago(this, true, total);
        dp.setVisible(true);
        
        Venta venta = new Venta(total, idCliente, idEmpleado);
        
        DialogoEmergente dl = new DialogoEmergente(this, true);
        
        if (registrarVenta(venta, listaProductos, 'V') && dp.validaPago()) {
            dl.setTexto("Venta registrada de\nforma correcta!\nSu Cambio es de: " + cambio);
            limpiarTodosTxtFields();
        } else {
            dl.setTexto("¡ERROR! NO SE PUDO REGISTRAR LA VENTA");
        }
        
        dl.setVisible(true);
    }//GEN-LAST:event_btnRegistrarVentaActionPerformed

    private void cbNombreProductoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbNombreProductoItemStateChanged
        if (evt.getStateChange() == 1 && !evt.getItem().equals("SELECCIONA UN PRODUCTO")) {
            for (Producto producto : productos) {
                if (producto.getNombre().equals(evt.getItem())) {
                    txtPrecioUnitario.setText(String.valueOf(producto.getPrecioUnitario()));
                    llenarCantidad(producto);
                    txtIDProducto.setText(String.valueOf(producto.getIdProducto()));
                    spnCantidad.setModel(new SpinnerNumberModel(1, 1, producto.getStock(), 1));
                }
            }
            if (Integer.parseInt(spnCantidad.getValue().toString()) >= 1) {
                calcularImporte();
            }
        }
    }//GEN-LAST:event_cbNombreProductoItemStateChanged

    //Función que filtra a partir de un campo de texto el cliente que busca
    private int calculaPosicionCliente(JTextField nombre) {
        return 1;
    }

    private void RegistrarEnvioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegistrarEnvioMouseClicked
        // TODO add your handling code here:
        
        if (tblProducto.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "DEBE DE AGREGAR AL MENOS UN PRODUCTO AL CARRITO","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        DialogoEnvio de = new DialogoEnvio(this, true);
        de.setVisible(true);
    }//GEN-LAST:event_RegistrarEnvioMouseClicked

    private void spnCantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnCantidadStateChanged
        // TODO add your handling code here:
        calcularImporte();
    }//GEN-LAST:event_spnCantidadStateChanged

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        DialogoSeleccionarCliente dCliente = new DialogoSeleccionarCliente(this, true);
        dCliente.setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        //Abrir el dialogo para seleccionar el producto
        DialogoSeleccionProducto dp = new DialogoSeleccionProducto(this, true, productos);
        dp.setVisible(true);
    }//GEN-LAST:event_jButton2MouseClicked

    private void RegistrarEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarEnvioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RegistrarEnvioActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:PrinterJob job = PrinterJob.getPrinterJob();
        
       PrinterJob job = PrinterJob.getPrinterJob();
        
        job.setPrintable(ticket1);
        
        if(job.printDialog()){
            try {
                job.print();
            } catch (PrinterException ex) {
                //Logger.getLogger(RegistrarVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "no se imprimio el ticket");
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    
    private void calcularTotal() {
        float total = 0;
        for (int i = 0; i < tblProducto.getRowCount(); i++) {
            total += Float.parseFloat(tblProducto.getValueAt(i, 4).toString());
        }
        
        txtSubtotal.setText(String.valueOf(total));
        txtTotal.setText(String.valueOf(total));
    }
    
    private Producto obtenerProducto(int idProducto) {
        for (Producto producto : productos) {
            if (producto.getIdProducto() == idProducto) {
                return producto;
            }
        }
        return null;
    }
    
    private boolean existeProducto(ArrayList<Producto> productos, Producto p) {
        for (Producto producto : productos) {
            if (producto.getIdProducto() == p.getIdProducto()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean validarFormulario() {
        return cbNombreProducto.getSelectedIndex() == 0 || txtIdCliente.getText().isEmpty();
    }
    
    public void setIdCliente(int idCliente, String nombreCliente) {
        this.txtIdCliente.setText(String.valueOf(idCliente));
        this.txtNombreCliente.setText(nombreCliente);
    }
    
    public void setCambio(float cambio) {
        this.cambio = cambio;
    }
    
    private boolean registrarVenta(Venta venta, ArrayList<Producto> productos, char tipoVenta) {
        int idVenta = new VentaManager().realizarVenta(venta, tipoVenta);
        
        if (new DetalleVenta().realizarDetalleVenta(productos, idVenta, tipoVenta)) {
            return new ProductoManager().modificarStock(productos);
        }
        
        return false;
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
    
    public void setNombre(String nombre) {
        PanelInformacionArriba panelInformacion = new PanelInformacionArriba();
        panelInformacion.setNombre(nombre);
        panelInformacion.setBounds(0, 0, (int) jPanelInformacion.getBounds().getWidth(), 110);
        jPanelInformacion.removeAll();
        jPanelInformacion.setMinimumSize(panelInformacion.getPreferredSize());
        jPanelInformacion.add(panelInformacion);
        panelInformacion.revalidate();
        panelInformacion.repaint();
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
        int cantidad = Integer.parseInt(spnCantidad.getValue().toString());
        float precioUnitrario = Float.parseFloat(txtPrecioUnitario.getText());
        
        float calculo = cantidad * precioUnitrario;
        
        txtImporte.setText(String.valueOf(calculo));
        
    }
    
    private void llenarCantidad(Producto producto) {
        spnCantidad.setModel(new SpinnerNumberModel(1, 1, producto.getStock(), 1));
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
    private javax.swing.JButton RegistrarEnvio;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegistrarVenta;
    protected javax.swing.JComboBox<String> cbNombreProducto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelAcciones;
    private javax.swing.JPanel jPanelFormulario;
    private javax.swing.JPanel jPanelInformacion;
    private javax.swing.JPanel jPanelIzquierda;
    private javax.swing.JPanel jPanelLinea;
    private javax.swing.JPanel jPanelOperaciones;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelCantidad;
    private javax.swing.JLabel labelPrecioUnitario;
    private javax.swing.JLabel labelSubtotal;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelimporte;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tblProducto;
    private modelo.Ticket ticket1;
    private javax.swing.JTextField txtIDProducto;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtPrecioUnitario;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    private modelo.Venta venta1;
    private modelo.Venta venta2;
    // End of variables declaration//GEN-END:variables
}

package vista;

import controlador.CategoriaManager;
import controlador.ProductoManager;
import Util.FuncionesUtiles;
import modelo.Producto;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.Toolkit;

public class AdministrarProducto extends javax.swing.JFrame {

    private int idAdmon;

    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    File f = null;
    String path = null;
    private ImageIcon format = null;
    String fname = null;
    int s = 0;
    byte[] pimage = null;

    public AdministrarProducto() {
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
        this.setMinimumSize(new Dimension(1280, 720));
        double width = Toolkit.getDefaultToolkit().getScreenSize().width;
        double height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //System.out.print((int)(1280*width/100)+","+height);
        this.setSize((int) (width), (int) height);
        this.setExtendedState(MAXIMIZED_BOTH);
        //Configurar panel principal
        jPanelPrincipal.setBackground(Color.WHITE);

        jPanel1.setBackground(Color.WHITE);

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
        //System.out.println(jPanelLinea.getBounds());
        jPanelLinea.setBounds((int) this.getBounds().getWidth() - 36, 0, 36, (int) this.getBounds().getHeight());
        jPanelLinea.setMaximumSize(new Dimension(36, (int) this.getBounds().getHeight()));
        jPanelLinea.setBackground(tool.getColorCancelados());

        //Agregar estilo a los botones
        tool.confBtnColor(btnConsultar);
        tool.confBtnColor(btnRegistrar);
        tool.confBtnColor(btnModificar);
        tool.confBtnColor(btnEliminar);

        //Limpiar txtFields
        limpiarTxtFields();

        //idCliente
        txtIDProducto.setEnabled(false);
        txtImagePath.setEnabled(false);

        //Categoria
        jComboBox1.removeAllItems();

        //String[] categorias = {"SELECCIONA UNO", "Pulseras", "Collares, Cruces", "Decenario", "Frascos", "Imagen bulto", "Llaveros", "Medallas", "Rosarios", "Cirios"};
        //jComboBox1.setModel(new DefaultComboBoxModel(categorias));
        llenarCategoria();

        btnFind.setText("");

        initFiltro();

    }

    private void llenarCategoria() {
        jComboBox1.removeAllItems();
        Object[] categorias = new CategoriaManager().getCategorias();
        jComboBox1.setModel(new DefaultComboBoxModel(categorias));
    }

    private void initFiltro() {
        String[] filtro = {"Nombre", "Autor", "Categoria"};
        jComboBox2.setModel(new DefaultComboBoxModel(filtro));
    }

    private void limpiarTxtFields() {
        btnFind.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecioUnitario.setText("");
        txtImagePath.setText("");
        //txtStock.setText("");
        txtAutor.setText("");
        txtIDProducto.setText("");
        labelProductoImagen.setIcon(null);
        spnStock.setValue(1);
        jComboBox1.setSelectedIndex(0);
        labelProductoImagen.revalidate();

    }

    private void initTabla() {
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Precio Unitario");
        modelo.addColumn("Stock");
        modelo.addColumn("Autor");
        modelo.addColumn("Categoria");
        tblProducto.setModel(modelo);
    }

    public void setAdmon(String nombre, int idAdmon) {
        //Colocar panel de la izquierda
        AccionesRapidasAdministrador panelBotones = new AccionesRapidasAdministrador(this);
        this.idAdmon = idAdmon;
        panelBotones.setIdAdmon(idAdmon);
        panelBotones.setNombre(nombre);
        //panelBotones.setBounds(0, 0, 266, (int) this.getBounds().getHeight() - 70);
        panelBotones.setBounds(0, 0, 266, (int) this.jPanelIzquierda.getBounds().getHeight());
        jPanelIzquierda.removeAll();
        jPanelIzquierda.setMinimumSize(panelBotones.getPreferredSize());
        jPanelIzquierda.add(panelBotones);
        panelBotones.revalidate();
        panelBotones.repaint();
        jPanelIzquierda.setBackground(Color.WHITE);
    }

    public void setEmpleado(String nombre, int idEmpleado) {
        //Colocar panel de la izquierda
        AccionesRapidasEmpleado panelBotones = new AccionesRapidasEmpleado(this);
        this.idAdmon = idEmpleado;
        panelBotones.setIdEmpleado(idEmpleado);
        panelBotones.setNombre(nombre);
        //panelBotones.setBounds(0, 0, 266, (int) this.getBounds().getHeight() - 70);
        panelBotones.setBounds(0, 0, 266, (int) this.jPanelIzquierda.getBounds().getHeight());
        jPanelIzquierda.removeAll();
        jPanelIzquierda.setMinimumSize(panelBotones.getPreferredSize());
        jPanelIzquierda.add(panelBotones);
        panelBotones.revalidate();
        panelBotones.repaint();
        jPanelIzquierda.setBackground(Color.WHITE);

        //Desactivar botones
        btnRegistrar.setVisible(false);
        btnModificar.setVisible(false);
        btnEliminar.setVisible(false);
        btnStock.setVisible(false);
        spnStock.setVisible(false);
        labelStock.setVisible(false);
        btnImagen.setEnabled(false);
        txtNombre.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtPrecioUnitario.setEnabled(false);
        txtAutor.setEnabled(false);
        jComboBox1.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanelPrincipal = new javax.swing.JPanel();
        jPanelInformacion = new javax.swing.JPanel();
        jPanelLinea = new javax.swing.JPanel();
        jPanelOperaciones = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        jPanelFormulario = new javax.swing.JPanel();
        labelNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        labelDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        labelPrecioUnitario = new javax.swing.JLabel();
        txtPrecioUnitario = new javax.swing.JTextField();
        labelImagen = new javax.swing.JLabel();
        labelAutor = new javax.swing.JLabel();
        txtAutor = new javax.swing.JTextField();
        labelIDProducto = new javax.swing.JLabel();
        txtIDProducto = new javax.swing.JTextField();
        labelStock = new javax.swing.JLabel();
        labelProductoImagen = new javax.swing.JLabel();
        btnImagen = new javax.swing.JButton();
        txtImagePath = new javax.swing.JTextField();
        spnStock = new javax.swing.JSpinner();
        btnStock = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnFind = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanelAcciones = new javax.swing.JPanel();
        btnConsultar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanelIzquierda = new javax.swing.JPanel();

        jPopupMenu1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jPopupMenu1PopupMenuWillBecomeVisible(evt);
            }
        });

        jMenuItem1.setText("jMenuItem1");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jPanelPrincipal.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanelPrincipal.setPreferredSize(new java.awt.Dimension(1280, 720));

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

        jPanelLinea.setPreferredSize(new Dimension((int)(jPanelIzquierda.getSize().width*1280/100),(int) (jPanelIzquierda.getSize().height*720/100)));

        javax.swing.GroupLayout jPanelLineaLayout = new javax.swing.GroupLayout(jPanelLinea);
        jPanelLinea.setLayout(jPanelLineaLayout);
        jPanelLineaLayout.setHorizontalGroup(
            jPanelLineaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );
        jPanelLineaLayout.setVerticalGroup(
            jPanelLineaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 724, Short.MAX_VALUE)
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

        labelNombre.setText("Nombre");

        txtNombre.setText("jTextField1");
        txtNombre.setMinimumSize(new java.awt.Dimension(64, 25));

        labelDescripcion.setText("Descripcion");

        txtDescripcion.setText("jTextField2");
        txtDescripcion.setMinimumSize(new java.awt.Dimension(64, 25));

        labelPrecioUnitario.setText("Precio Unitario");

        txtPrecioUnitario.setText("jTextField3");
        txtPrecioUnitario.setMinimumSize(new java.awt.Dimension(64, 25));

        labelImagen.setText("Imagen");

        labelAutor.setText("Autor");

        txtAutor.setText("jTextField5");
        txtAutor.setMinimumSize(new java.awt.Dimension(64, 25));

        labelIDProducto.setText("ID. Producto");

        txtIDProducto.setText("jTextField6");
        txtIDProducto.setMinimumSize(new java.awt.Dimension(64, 25));
        txtIDProducto.setPreferredSize(new java.awt.Dimension(71, 25));

        labelStock.setText("Stock");

        labelProductoImagen.setMaximumSize(new java.awt.Dimension(288, 188));
        labelProductoImagen.setMinimumSize(new java.awt.Dimension(288, 188));

        btnImagen.setText("Elegir imagen");
        btnImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImagenActionPerformed(evt);
            }
        });

        txtImagePath.setText("jTextField1");
        txtImagePath.setMinimumSize(new java.awt.Dimension(64, 25));

        spnStock.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spnStock.setMinimumSize(new java.awt.Dimension(64, 25));
        spnStock.setPreferredSize(new java.awt.Dimension(64, 25));
        spnStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                spnStockMouseEntered(evt);
            }
        });

        btnStock.setText("Agregar");
        btnStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockActionPerformed(evt);
            }
        });

        jLabel1.setText("Categoria");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pulseras", "Collares Cruces", "Decenario", "Frascos", "Imagen bulto", "Llaveros", "Medallas", "Rosarios", "Cirios" }));
        jComboBox1.setComponentPopupMenu(jPopupMenu1);
        jComboBox1.setMinimumSize(new java.awt.Dimension(160, 25));

        btnFind.setText("jTextField1");
        btnFind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnFindKeyTyped(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
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
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(labelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelPrecioUnitario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(txtPrecioUnitario, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                    .addComponent(txtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                    .addComponent(btnImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                .addGap(18, 18, 18))
                            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                                .addComponent(labelStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(132, 132, 132)))
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelAutor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                    .addComponent(labelIDProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox1, 0, 160, Short.MAX_VALUE)
                                    .addComponent(txtIDProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtAutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtImagePath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                                .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanelFormularioLayout.createSequentialGroup()
                        .addComponent(spnStock, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnStock)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelProductoImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelFormularioLayout.setVerticalGroup(
            jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormularioLayout.createSequentialGroup()
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelAutor)
                                .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelDescripcion)
                                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelIDProducto)
                                        .addComponent(txtIDProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelPrecioUnitario)
                                        .addComponent(txtPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelImagen)
                                    .addComponent(btnImagen)
                                    .addComponent(txtImagePath, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelStock)
                            .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spnStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStock)))
                    .addComponent(labelProductoImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
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
                    .addComponent(btnConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelAccionesLayout.setVerticalGroup(
            jPanelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addGroup(jPanelOperacionesLayout.createSequentialGroup()
                        .addComponent(jPanelAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(29, 29, 29))
                    .addGroup(jPanelOperacionesLayout.createSequentialGroup()
                        .addComponent(jPanelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel1.setMinimumSize(new java.awt.Dimension(266, 650));
        jPanel1.setPreferredSize(new java.awt.Dimension(266, 650));

        jPanelIzquierda.setMinimumSize(new java.awt.Dimension(266, 650));
        jPanelIzquierda.setPreferredSize(new Dimension((int)(this.getSize().width/100),(int) (this.getSize().height*720/100)));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelIzquierda, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelIzquierda, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelOperaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(jPanelInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelOperaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(jPanelLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // ELIMINAR PRODUCTO
        DialogoEmergente dEmergente = new DialogoEmergente(this, true);
        Producto producto = new Producto();
        producto.setIdProducto(Integer.parseInt(txtIDProducto.getText()));

        if (new ProductoManager().eliminarProducto(producto)) {
            llenarTabla();
            dEmergente.setTexto("El producto se elimino de\nforma correcta");
        } else {
            dEmergente.setTexto("ERROR AL INTENTAR\nELIMINAR EL PRODUCTO!");
        }
        dEmergente.setVisible(true);
        limpiarTxtFields();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        DialogoEmergente dEmergente = new DialogoEmergente(this, true);
        Producto producto;

        String categoria = jComboBox1.getSelectedItem().toString();

        categoria = String.valueOf(new CategoriaManager().getIdCategoria(categoria));

        producto = new Producto(Integer.parseInt(txtIDProducto.getText()), txtNombre.getText(), txtDescripcion.getText(), Float.parseFloat(txtPrecioUnitario.getText()), txtAutor.getText(), categoria.trim());

        System.out.println(categoria + "");
        String mensajeDeTexto = "";

        // Verifica si la ruta de la imagen está en blanco
        if (txtImagePath.getText().isEmpty()) {
            // Actualiza el producto sin imagen
            if (new ProductoManager().modificarProductoSinImagen(producto)) {
                llenarTabla();
                mensajeDeTexto = "El producto se modificó de\nforma correcta.";
            }
        } else {
            // Actualiza el producto con imagen
            producto.setImagen(txtImagePath.getText());
            if (new ProductoManager().modificarProductoConImagen(producto)) {
                llenarTabla();
                mensajeDeTexto = "El producto se modificó de\nforma correcta.";
            } else {
                mensajeDeTexto = "ERROR AL INTENTAR\nMODIFICAR EL\nPRODUCTO!";
            }
        }

        // Muestra el diálogo emergente y limpia los campos de texto
        dEmergente.setTexto(mensajeDeTexto);
        dEmergente.setVisible(true);
        limpiarTxtFields();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        DialogoEmergente dEmergente = new DialogoEmergente(this, true);

        if (validarFormulario()) {
            dEmergente.setTexto("ERROR HAY AL MENOS\nUN CAMPO SIN LLENAR");
            dEmergente.setVisible(true);
            return;
        }

        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        Float precioUnitario = Float.valueOf(txtPrecioUnitario.getText());
        String imagen = txtImagePath.getText();
        int stock = Integer.parseInt(spnStock.getValue().toString());
        String autor = txtAutor.getText();
        String categoria = jComboBox1.getSelectedItem().toString();

        categoria = String.valueOf(new CategoriaManager().getIdCategoria(categoria));

        System.out.println(categoria);

        Producto producto = new Producto(nombre, descripcion, precioUnitario, imagen, stock, autor, categoria);

        if (new ProductoManager().insertarProducto(producto)) {
            llenarTabla();
            dEmergente.setTexto("El producto se registro de\nforma correcta");
            dEmergente.setVisible(true);
        } else {
            dEmergente.setTexto("ERROR AL INTENTAR\nREGISTRAR EL\nPRODUCTO!");
            dEmergente.setVisible(true);
        }
        limpiarTxtFields();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        llenarTabla();
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockActionPerformed

        DialogoEmergente dEmergente = new DialogoEmergente(this, true);

        int renglon = tblProducto.getSelectedRow();

        if (renglon == -1) {
            dEmergente.setTexto("ERROR! DEBE DE\nSELECCIONAR UN\nRENGLON!");
            dEmergente.setVisible(true);
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas modificar\nel stock de este producto?");

        int nuevoStock = Integer.parseInt(spnStock.getValue().toString());
        int viejoStock = Integer.parseInt(tblProducto.getValueAt(renglon, 4).toString());
        int idProducto = Integer.parseInt(tblProducto.getValueAt(renglon, 0).toString());

        Producto producto = new Producto();
        producto.setStock(viejoStock + nuevoStock);
        producto.setIdProducto(idProducto);

        String mensajeDeTexto = "";

        if (respuesta == 0) {
            if (new ProductoManager().incrementarStock(producto)) {
                llenarTabla();
                mensajeDeTexto = "El Stock se modificó de\nforma correcta.";
            } else {
                mensajeDeTexto = "Hubo un error al modificar\nel stock";
            }
        } else {
            mensajeDeTexto = "Stock no modificado";
        }
        // Muestra el diálogo emergente y limpia los campos de texto
        dEmergente.setTexto(mensajeDeTexto);
        dEmergente.setVisible(true);
        limpiarTxtFields();
    }//GEN-LAST:event_btnStockActionPerformed

    private void spnStockMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spnStockMouseEntered

    }//GEN-LAST:event_spnStockMouseEntered

    private void btnImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagenActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("PNG AND JPG", "PNG", "JPG");
        fileChooser.addChoosableFileFilter(filtro);
        int load = fileChooser.showOpenDialog(null);

        if (load == JFileChooser.APPROVE_OPTION) {
            f = fileChooser.getSelectedFile();
            path = f.getAbsolutePath();
            txtImagePath.setText(path);
            ImageIcon ii = new ImageIcon(path);
            Image img = ii.getImage().getScaledInstance(labelProductoImagen.getWidth(), labelProductoImagen.getHeight(), Image.SCALE_SMOOTH);
            labelProductoImagen.setIcon(new ImageIcon(img));
        }
    }//GEN-LAST:event_btnImagenActionPerformed

    private void tblProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductoMouseClicked
        //String txtIdRol = String.valueOf(jComboBoxRol.getSelectedItem()).equals("Empleado") ? "2" : "1";
        //Cliente temp = new Cliente(txtNombre.getText(), txtApPaterno.getText(), txtApMaterno.getText(), txtCURP.getText(), txtCalle.getText(), txtColonia.getText(), txtCiudad.getText(), txtEstado.getText(), txtCP.getText(), txtMunicipio.getText(), txtEstado.getText(), txtIdRol);
        //temp.getEmpleadoTabla(tblCliente, txtIDEmpleado, txtNombre, txtApPaterno, txtApMaterno, txtCURP, txtCalle, txtColonia, txtCiudad, txtMunicipio, txtEstado, txtEstado, txtCP, jComboBoxRol);

        obtenerRenglonTabla();
    }//GEN-LAST:event_tblProductoMouseClicked

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
        //System.out.println(jPanelLinea.getBounds());
        //jPanelLinea.setBounds((int) this.getBounds().getWidth() - 52, 6, 36, (int) this.getBounds().getHeight());
        //jPanelLinea.setMaximumSize(new Dimension(36, (int) this.getBounds().getHeight()));

        //redimenIzq();

    }//GEN-LAST:event_formComponentResized

    private void btnFindKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnFindKeyTyped
        // TODO add your handling code here:
        buscarProducto(btnFind.getText(), jComboBox2.getSelectedItem());
    }//GEN-LAST:event_btnFindKeyTyped

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        buscarProducto(btnFind.getText(), jComboBox2.getSelectedItem());
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jPopupMenu1PopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jPopupMenu1PopupMenuWillBecomeVisible
        // TODO add your handling code here:
        jMenuItem1.setText("Agregar nueva categoria");
    }//GEN-LAST:event_jPopupMenu1PopupMenuWillBecomeVisible

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked

    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //Abrir interfaz para crear una nueva categoria
        DialogoCategoriaNueva dc = new DialogoCategoriaNueva(this, true);
        dc.setVisible(true);
        llenarCategoria();
        //La interfaz debe de validar que la categoria nueva no exista (sea de verdad nueva!).
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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

    private void obtenerRenglonTabla() {
        int fila = tblProducto.getSelectedRow();

        if (fila >= 0) {
            txtIDProducto.setText(tblProducto.getValueAt(fila, 0).toString());
            txtNombre.setText(tblProducto.getValueAt(fila, 1).toString());
            txtDescripcion.setText(tblProducto.getValueAt(fila, 2).toString());
            txtPrecioUnitario.setText(tblProducto.getValueAt(fila, 3).toString());
            //txtImagePath.setText(tblProducto.getValueAt(fila, 4).toString());

            Producto producto = new Producto(Integer.parseInt(txtIDProducto.getText()));
            new ProductoManager().obtenerImagen(producto, labelProductoImagen);

            //spnStock.setValue(tblProducto.getValueAt(fila, 4).toString());
            //txtStock.setText(tblProducto.getValueAt(fila, 4).toString());
            //spnStock.setValue(Integer.parseInt(tblProducto.getValueAt(fila, 4).toString()));
            txtAutor.setText(tblProducto.getValueAt(fila, 5).toString());
            jComboBox1.setSelectedItem(tblProducto.getValueAt(fila, 6).toString());
        }
    }

    private void llenarTabla() {
        modelo.setRowCount(0); //Limpiamos la tabla
        ArrayList<Producto> lista = new ProductoManager().consultarProductos();
        for (Producto p : lista) {
            String[] fila = {String.valueOf(p.getIdProducto()), p.getNombre(), p.getDescripcion(), String.valueOf(p.getPrecioUnitario()), String.valueOf(p.getStock()), p.getAutor(), p.getCategoria()};
            modelo.addRow(fila);
        }
        tblProducto.repaint();
    }

    private boolean validarFormulario() {
        return txtNombre.getText().isBlank() | txtDescripcion.getText().isBlank() | txtPrecioUnitario.getText().isBlank() | txtImagePath.getText().isBlank() | txtAutor.getText().isBlank() | jComboBox1.getSelectedItem().equals("SELECCIONA UNO");
    }

    public void buscarProducto(String txt, Object filtro) {
        modelo.setRowCount(0);
        switch (filtro.toString()) {
            case "Nombre" ->
                llenarTabla(new ProductoManager().buscarProductoNombre(txt));
            case "Autor" ->
                llenarTabla(new ProductoManager().buscarProductoAutor(txt));
            case "Categoria" ->
                llenarTabla(new ProductoManager().buscarProductoCategoria(txt));
        }
        tblProducto.repaint();
        tblProducto.revalidate();
    }

    private void llenarTabla(ArrayList<String[]> lista) {
        for (int i = 0; i < lista.size(); i++) {
            modelo.addRow(lista.get(i));
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
            java.util.logging.Logger.getLogger(AdministrarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministrarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministrarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministrarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministrarProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JTextField btnFind;
    private javax.swing.JButton btnImagen;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnStock;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelAcciones;
    private javax.swing.JPanel jPanelFormulario;
    private javax.swing.JPanel jPanelInformacion;
    private javax.swing.JPanel jPanelIzquierda;
    private javax.swing.JPanel jPanelLinea;
    private javax.swing.JPanel jPanelOperaciones;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelAutor;
    private javax.swing.JLabel labelDescripcion;
    private javax.swing.JLabel labelIDProducto;
    private javax.swing.JLabel labelImagen;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelPrecioUnitario;
    private javax.swing.JLabel labelProductoImagen;
    private javax.swing.JLabel labelStock;
    private javax.swing.JSpinner spnStock;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtIDProducto;
    private javax.swing.JTextField txtImagePath;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecioUnitario;
    // End of variables declaration//GEN-END:variables
}

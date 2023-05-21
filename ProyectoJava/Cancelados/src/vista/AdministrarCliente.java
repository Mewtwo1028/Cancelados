package vista;

import controlador.ClienteManager;
import controlador.Conexion;
import modelo.Cliente;
import modelo.FuncionesUtiles;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;

public class AdministrarCliente extends javax.swing.JFrame {

    private int idAdmon;

    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public AdministrarCliente() {
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
        this.setTitle("Administrar Cliente");
        this.setMinimumSize(new Dimension(1280, 720));
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
        tool.confBtnColor(btnConsultar);
        tool.confBtnColor(btnRegistrar);
        tool.confBtnColor(btnModificar);
        tool.confBtnColor(btnEliminar);

        //Limpiar txtFields
        limpiarTxtFields();

        //idCliente
        txtIDCliente.setEnabled(false);

        initFiltro();
    }

    private void initFiltro() {
        btnFind.setText("");
        String[] filtro = {"Nombre", "Apellido Paterno", "Apellido Materno"};
        jComboBox1.setModel(new DefaultComboBoxModel(filtro));
    }

    private void limpiarTxtFields() {
        txtNombre.setText("");
        txtApPaterno.setText("");
        txtApMaterno.setText("");
        txtCalle.setText("");
        txtColonia.setText("");
        txtCiudad.setText("");
        txtEstado.setText("");
        txtCP.setText("");
        txtIDCliente.setText("");
    }

    private void initTabla() {
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido Paterno");
        modelo.addColumn("Apellido Materno");
        modelo.addColumn("Calle");
        modelo.addColumn("Colonia");
        modelo.addColumn("Ciudad");
        modelo.addColumn("Estado");
        modelo.addColumn("CP");
        tblCliente.setModel(modelo);
    }

    public void setAdministrador(String nombre, int idAdmon) {
        //Colocar panel de la izquierda
        AccionesRapidasAdministrador panelBotones = new AccionesRapidasAdministrador(this);
        this.idAdmon = idAdmon;
        panelBotones.setIdAdmon(idAdmon);
        panelBotones.setNombre(nombre);
        panelBotones.setBounds(0, 0, 266, (int) this.getBounds().getHeight() - 70);
        jPanelIzquierda.removeAll();
        jPanelIzquierda.setMinimumSize(panelBotones.getPreferredSize());
        jPanelIzquierda.add(panelBotones);
        panelBotones.revalidate();
        panelBotones.repaint();
    }

    public void setEmpleado(String nombre, int idEmpleado) {

        //Desactivar botones de, modificar, consultar y eliminar
        btnEliminar.setVisible(false);
        btnConsultar.setVisible(false);
        btnModificar.setVisible(false);
        btnFind.setVisible(false);
        jComboBox1.setVisible(false);

        //Colocar panel de la izquierda
        AccionesRapidasEmpleado panelBotones = new AccionesRapidasEmpleado(this);
        this.idAdmon = idEmpleado;
        panelBotones.setIdEmpleado(idEmpleado);
        panelBotones.setNombre(nombre);
        panelBotones.setBounds(0, 0, 266, (int) this.getBounds().getHeight() - 70);
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

        jPanelPrincipal = new javax.swing.JPanel();
        jPanelIzquierda = new javax.swing.JPanel();
        jPanelInformacion = new javax.swing.JPanel();
        jPanelLinea = new javax.swing.JPanel();
        jPanelOperaciones = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        jPanelFormulario = new javax.swing.JPanel();
        labelNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        labelApPaterno = new javax.swing.JLabel();
        txtApPaterno = new javax.swing.JTextField();
        labelApMaterno = new javax.swing.JLabel();
        txtApMaterno = new javax.swing.JTextField();
        labelColonia = new javax.swing.JLabel();
        txtColonia = new javax.swing.JTextField();
        labelCiudad = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JTextField();
        labelEstado = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        labelCP = new javax.swing.JLabel();
        txtCP = new javax.swing.JTextField();
        labelCalle = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        labelIDCliente = new javax.swing.JLabel();
        txtIDCliente = new javax.swing.JTextField();
        btnFind = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanelAcciones = new javax.swing.JPanel();
        btnConsultar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
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

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCliente);

        labelNombre.setText("Nombre");

        txtNombre.setText("jTextField1");

        labelApPaterno.setText("Apellido Paterno");

        txtApPaterno.setText("jTextField2");

        labelApMaterno.setText("Apellido Materno");

        txtApMaterno.setText("jTextField3");

        labelColonia.setText("Colonia");

        txtColonia.setText("jTextField5");

        labelCiudad.setText("Ciudad");

        txtCiudad.setText("jTextField6");

        labelEstado.setText("Estado");

        txtEstado.setText("jTextField7");

        labelCP.setText("CP");

        txtCP.setText("jTextField8");

        labelCalle.setText("Calle");

        txtCalle.setText("jTextField10");

        labelIDCliente.setText("ID. Cliente");

        txtIDCliente.setText("jTextField1");

        btnFind.setText("jTextField1");
        btnFind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnFindKeyTyped(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelFormularioLayout = new javax.swing.GroupLayout(jPanelFormulario);
        jPanelFormulario.setLayout(jPanelFormularioLayout);
        jPanelFormularioLayout.setHorizontalGroup(
            jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelCalle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelApPaterno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelApMaterno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labelCP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCP)
                    .addComponent(txtNombre)
                    .addComponent(txtApMaterno)
                    .addComponent(txtApPaterno)
                    .addComponent(txtCalle, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelColonia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelIDCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                    .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtColonia)
                    .addComponent(txtCiudad)
                    .addComponent(txtEstado)
                    .addComponent(txtIDCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(240, Short.MAX_VALUE))
        );
        jPanelFormularioLayout.setVerticalGroup(
            jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelColonia)
                    .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelApPaterno)
                    .addComponent(txtApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCiudad)
                    .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelApMaterno)
                    .addComponent(txtApMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEstado)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelIDCliente)
                        .addComponent(txtIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelCalle)
                        .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCP)
                    .addComponent(txtCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
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

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        llenarTabla();
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        DialogoEmergente dEmergente = new DialogoEmergente(this, true);

        String nombre = txtNombre.getText();
        String aPaterno = txtApPaterno.getText();
        String aMaterno = txtApMaterno.getText();
        String calle = txtCalle.getText();
        String colonia = txtColonia.getText();
        String ciudad = txtCiudad.getText();
        String estado = txtEstado.getText();
        String cp = txtCP.getText();

        Cliente cliente = new Cliente(nombre, aPaterno, aMaterno, calle, colonia, ciudad, estado, cp);

        if (validarFormulario()) {
            dEmergente.setTexto("ERROR HAY AL MENOS\nUN CAMPO SIN LLENAR");
            dEmergente.setVisible(true);
            return;
        }

        if (new ClienteManager().insertarCliente(cliente)) {
            llenarTabla();
            dEmergente.setTexto("El cliente se registro de\nforma correcta");
            dEmergente.setVisible(true);
        } else {
            dEmergente.setTexto("ERROR AL INTENTAR\nREGISTRAR AL\nCLIENTE!");
            dEmergente.setVisible(true);
        }
        limpiarTxtFields();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        DialogoEmergente dEmergente = new DialogoEmergente(this, true);

        int idCliente = Integer.parseInt(txtIDCliente.getText());
        String nombre = txtNombre.getText();
        String apPaterno = txtApPaterno.getText();
        String apMaterno = txtApMaterno.getText();
        String calle = txtCalle.getText();
        String colonia = txtColonia.getText();
        String ciudad = txtCiudad.getText();
        String estado = txtEstado.getText();
        String cp = txtCP.getText();

        Cliente cliente = new Cliente(idCliente, nombre, apPaterno, apMaterno, calle, colonia, ciudad, estado, cp);

        if (new ClienteManager().modificarCliente(cliente)) {
            llenarTabla();
            dEmergente.setTexto("El cliente se modifico de\nforma correcta");
            dEmergente.setVisible(true);
        } else {
            dEmergente.setTexto("ERROR AL INTENTAR\nMODIFICAR AL CLIENTE!");
            dEmergente.setVisible(true);
        }
        limpiarTxtFields();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteMouseClicked
        //String txtIdRol = String.valueOf(jComboBoxRol.getSelectedItem()).equals("Empleado") ? "2" : "1";
        //Cliente temp = new Cliente(txtNombre.getText(), txtApPaterno.getText(), txtApMaterno.getText(), txtCURP.getText(), txtCalle.getText(), txtColonia.getText(), txtCiudad.getText(), txtEstado.getText(), txtCP.getText(), txtMunicipio.getText(), txtEstado.getText(), txtIdRol);
        //temp.getEmpleadoTabla(tblCliente, txtIDEmpleado, txtNombre, txtApPaterno, txtApMaterno, txtCURP, txtCalle, txtColonia, txtCiudad, txtMunicipio, txtEstado, txtEstado, txtCP, jComboBoxRol);

        obtenerRenglonTabla();

    }//GEN-LAST:event_tblClienteMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // ELIMINAR CLIENTE
        DialogoEmergente dEmergente = new DialogoEmergente(this, true);
        Cliente cliente = new Cliente(Integer.parseInt(txtIDCliente.getText()));

        if (new ClienteManager().eliminarCliente(cliente)) {
            llenarTabla();
            dEmergente.setTexto("El cliente se elimino de\nforma correcta");
            dEmergente.setVisible(true);
        } else {
            dEmergente.setTexto("ERROR AL INTENTAR\nELIMINAR AL CLIENTE!");
            dEmergente.setVisible(true);
        }
        limpiarTxtFields();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnFindKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnFindKeyTyped
        // TODO add your handling code here:
        buscarProducto(btnFind.getText(), jComboBox1.getSelectedItem());
    }//GEN-LAST:event_btnFindKeyTyped

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        buscarProducto(btnFind.getText(), jComboBox1.getSelectedItem());
    }//GEN-LAST:event_jComboBox1ItemStateChanged

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

    private void buscarProducto(String txt, Object filtro) {
        modelo.setRowCount(0);
        switch (filtro.toString()) {
            case "Nombre" ->
                llenarTabla(new ClienteManager().buscarClienteNombre(txt));
            case "Apellido Paterno" ->
                llenarTabla(new ClienteManager().buscarClientePaterno(txt));
            case "Apellido Materno" ->
                llenarTabla(new ClienteManager().buscarClienteMaterno(txt));
        }
        tblCliente.repaint();
        tblCliente.revalidate();
    }

    private void llenarTabla(ArrayList<String[]> lista) {
        for (int i = 0; i < lista.size(); i++) {
            modelo.addRow(lista.get(i));
        }
    }

    private void obtenerRenglonTabla() {
        int fila = tblCliente.getSelectedRow();

        if (fila >= 0) {
            txtIDCliente.setText(tblCliente.getValueAt(fila, 0).toString());
            txtNombre.setText(tblCliente.getValueAt(fila, 1).toString());
            txtApPaterno.setText(tblCliente.getValueAt(fila, 2).toString());
            txtApMaterno.setText(tblCliente.getValueAt(fila, 3).toString());
            txtCalle.setText(tblCliente.getValueAt(fila, 4).toString());
            txtColonia.setText(tblCliente.getValueAt(fila, 5).toString());
            txtCiudad.setText(tblCliente.getValueAt(fila, 6).toString());
            txtEstado.setText(tblCliente.getValueAt(fila, 7).toString());
            txtCP.setText(tblCliente.getValueAt(fila, 8).toString());
        }
    }

    private void llenarTabla() {
        modelo.setRowCount(0); //Limpiamos la tabla
        ArrayList<String[]> lista = new ClienteManager().consultarTodos();
        for (int i = 0; i < lista.size(); i++) {
            modelo.addRow(lista.get(i));
        }
    }

    private boolean validarFormulario() {
        return txtNombre.getText().isBlank() | txtApPaterno.getText().isBlank() | txtApMaterno.getText().isBlank() | txtCalle.getText().isBlank() | txtColonia.getText().isBlank() | txtCiudad.getText().isBlank() | txtEstado.getText().isBlank() | txtCP.getText().isBlank();
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
            java.util.logging.Logger.getLogger(AdministrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministrarCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JTextField btnFind;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanelAcciones;
    private javax.swing.JPanel jPanelFormulario;
    private javax.swing.JPanel jPanelInformacion;
    private javax.swing.JPanel jPanelIzquierda;
    private javax.swing.JPanel jPanelLinea;
    private javax.swing.JPanel jPanelOperaciones;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelApMaterno;
    private javax.swing.JLabel labelApPaterno;
    private javax.swing.JLabel labelCP;
    private javax.swing.JLabel labelCalle;
    private javax.swing.JLabel labelCiudad;
    private javax.swing.JLabel labelColonia;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelIDCliente;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTextField txtApMaterno;
    private javax.swing.JTextField txtApPaterno;
    private javax.swing.JTextField txtCP;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtColonia;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtIDCliente;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}

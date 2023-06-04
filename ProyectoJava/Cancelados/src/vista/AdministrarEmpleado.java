package vista;

import controlador.EmpleadoManager;
import controlador.NotificacionManager;
import modelo.Empleado;
import Util.FuncionesUtiles;
import java.awt.Color;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class AdministrarEmpleado extends javax.swing.JFrame {

    private int idAdmon;
    private String[] datos;

    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public AdministrarEmpleado() {
        initComponents();
        inicializar();
        initTabla();
    }

    //Función que comprueba si el campo contraseña y repetir contraseña tienen el mismo contenido
    private boolean compruebaContra(String pass1, String pass2) {
        return pass1.compareTo(pass2) < 0 ? false : true;
    }

    private void inicializar() {
        FuncionesUtiles tool = new FuncionesUtiles();
        //Configuracion ventana
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Administrar Empleado");

        //Configurar panel principal
        jPanelPrincipal.setBackground(Color.WHITE);

        //Colocar panel de la izquierda
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
        tool.confBtnColor(btnRestaurarContrasena);

        //Limpiar txtFields
        limpiarTxtFields();

        btnFind.setText("");

        initFiltro();

        initCampos();
    }

    private void initCampos() {
        this.txtEstado.setEnabled(false);
        this.txtMunicipio.setEnabled(false);
        this.txtColonia.setEnabled(false);
        this.txtCP.setEnabled(false);
    }

    private void initFiltro() {
        btnFind.setText("");
        String[] filtro = {"Nombre", "Apellido Paterno", "Apellido Materno"};
        jComboBox1.setModel(new DefaultComboBoxModel(filtro));
    }

    public void setAdmon(String nombre, int id) {

        this.idAdmon = id;

        AccionesRapidasAdministrador panelBotones = new AccionesRapidasAdministrador(this);

        panelBotones.setBounds(0, 0, 266, (int) this.getBounds().getHeight() - 80);
        jPanelIzquierda.removeAll();
        jPanelIzquierda.setMinimumSize(panelBotones.getPreferredSize());
        jPanelIzquierda.add(panelBotones);
        panelBotones.revalidate();
        panelBotones.repaint();
    }

    private void limpiarTxtFields() {
        txtNombre.setText("");
        txtApPaterno.setText("");
        txtApMaterno.setText("");
        txtCalle.setText("");
        txtNoExt.setText("");
        txtColonia.setText("");
        txtCP.setText("");
        txtCURP.setText("");
        txtRFC.setText("");
        txtMunicipio.setText("");
        txtEstado.setText("");
        jComboBoxRol.setSelectedIndex(0);
        txtIDEmpleado.setText("");
        jTextFieldCorreo.setText("");
    }

    private void initTabla() {
        modelo.addColumn("ID Empleado");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido Paterno");
        modelo.addColumn("Apellido Materno");
        modelo.addColumn("Calle");
        modelo.addColumn("No. Exterior");
        modelo.addColumn("Colonia");
        modelo.addColumn("CP");
        modelo.addColumn("CURP");
        modelo.addColumn("RFC");
        modelo.addColumn("Municipio");
        modelo.addColumn("Estado");
        modelo.addColumn("ROL");
        modelo.addColumn("Nombre Usuario");
        modelo.addColumn("Correo");
        //modelo.addColumn("Contraseña");
        tblEmpleado.setModel(modelo);
        txtIDEmpleado.setEnabled(false);
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
        jPanelFormulario = new javax.swing.JPanel();
        labelNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        labelApPaterno = new javax.swing.JLabel();
        txtApPaterno = new javax.swing.JTextField();
        labelApMaterno = new javax.swing.JLabel();
        txtApMaterno = new javax.swing.JTextField();
        labelCalle = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        labelColonia = new javax.swing.JLabel();
        txtColonia = new javax.swing.JTextField();
        labelCP = new javax.swing.JLabel();
        txtCP = new javax.swing.JTextField();
        labelCURP = new javax.swing.JLabel();
        txtCURP = new javax.swing.JTextField();
        labelRFC = new javax.swing.JLabel();
        txtRFC = new javax.swing.JTextField();
        labelEstadp = new javax.swing.JLabel();
        labelNoExt = new javax.swing.JLabel();
        txtNoExt = new javax.swing.JTextField();
        labelMunicipio = new javax.swing.JLabel();
        txtMunicipio = new javax.swing.JTextField();
        labelRol = new javax.swing.JLabel();
        labelIDEmpleado = new javax.swing.JLabel();
        txtIDEmpleado = new javax.swing.JTextField();
        jComboBoxRol = new javax.swing.JComboBox<>();
        btnFind = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        txtEstado = new javax.swing.JTextField();
        jLabelCorreo = new javax.swing.JLabel();
        jTextFieldCorreo = new javax.swing.JTextField();
        jPanelAcciones = new javax.swing.JPanel();
        btnConsultar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnRestaurarContrasena = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleado = new javax.swing.JTable();

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

        labelNombre.setText("Nombre");

        txtNombre.setText("jTextField1");
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });

        labelApPaterno.setText("Apellido Paterno");

        txtApPaterno.setText("jTextField2");

        labelApMaterno.setText("Apellido Materno");

        txtApMaterno.setText("jTextField3");

        labelCalle.setText("Calle");

        txtCalle.setText("jTextField4");

        labelColonia.setText("Colonia");

        txtColonia.setText("jTextField5");

        labelCP.setText("CP");

        txtCP.setText("jTextField6");

        labelCURP.setText("CURP");

        txtCURP.setText("jTextField7");

        labelRFC.setText("RFC");

        txtRFC.setText("jTextField8");

        labelEstadp.setText("Estado");

        labelNoExt.setText("No. Exterior");

        txtNoExt.setText("jTextField10");

        labelMunicipio.setText("Municipio");

        txtMunicipio.setText("jTextField11");

        labelRol.setText("Rol");

        labelIDEmpleado.setText("ID. Empleado");

        txtIDEmpleado.setText("jTextField14");

        jComboBoxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado", "Administrador" }));

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

        jButton1.setText("Seleccionar direccion");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        txtEstado.setText("jTextField1");

        jLabelCorreo.setText("Correo");

        jTextFieldCorreo.setText("jTextField1");

        javax.swing.GroupLayout jPanelFormularioLayout = new javax.swing.GroupLayout(jPanelFormulario);
        jPanelFormulario.setLayout(jPanelFormularioLayout);
        jPanelFormularioLayout.setHorizontalGroup(
            jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormularioLayout.createSequentialGroup()
                        .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelFormularioLayout.createSequentialGroup()
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelNoExt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelCalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelApPaterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelApMaterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombre)
                            .addComponent(txtApMaterno)
                            .addComponent(txtApPaterno)
                            .addComponent(txtCalle)
                            .addComponent(txtNoExt, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelColonia, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(labelCP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelCURP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelRFC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelMunicipio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtColonia)
                            .addComponent(txtCP)
                            .addComponent(txtCURP)
                            .addComponent(txtRFC)
                            .addComponent(txtMunicipio, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabelCorreo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelEstadp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelRol, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                    .addComponent(labelIDEmpleado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIDEmpleado)
                                    .addComponent(jComboBoxRol, 0, 130, Short.MAX_VALUE)
                                    .addComponent(txtEstado)
                                    .addComponent(jTextFieldCorreo))))))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanelFormularioLayout.setVerticalGroup(
            jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelColonia)
                    .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEstadp)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelApPaterno)
                    .addComponent(txtApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCP)
                    .addComponent(txtCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRol)
                    .addComponent(jComboBoxRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelApMaterno)
                    .addComponent(txtApMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCURP)
                    .addComponent(txtCURP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCalle)
                    .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRFC)
                    .addComponent(txtRFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCorreo)
                    .addComponent(jTextFieldCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNoExt)
                    .addComponent(txtNoExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMunicipio)
                    .addComponent(txtMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelIDEmpleado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
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

        btnRestaurarContrasena.setText("Restaurar Contraseña");
        btnRestaurarContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarContrasenaActionPerformed(evt);
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
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRestaurarContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRestaurarContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblEmpleado.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmpleado);

        javax.swing.GroupLayout jPanelOperacionesLayout = new javax.swing.GroupLayout(jPanelOperaciones);
        jPanelOperaciones.setLayout(jPanelOperacionesLayout);
        jPanelOperacionesLayout.setHorizontalGroup(
            jPanelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOperacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelOperacionesLayout.setVerticalGroup(
            jPanelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelOperacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelOperacionesLayout.createSequentialGroup()
                        .addComponent(jPanelAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(389, 389, 389))
                    .addGroup(jPanelOperacionesLayout.createSequentialGroup()
                        .addComponent(jPanelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(350, 350, 350))))
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
        String noExt = txtNoExt.getText();
        String Colonia = txtColonia.getText();
        String cp = txtCP.getText();
        String curp = txtCURP.getText();
        String rfc = txtRFC.getText();
        String municipio = txtMunicipio.getText();
        String estado = txtEstado.getText();
        String correo = jTextFieldCorreo.getText();

        if (estado.equals("SELECCIONE UN ESTADO")) {
            JOptionPane.showMessageDialog(this, "ERROR Seleccione una entidad federativa");
            return;
        }

        String rol = String.valueOf(jComboBoxRol.getSelectedItem());
        int idRol = rol.equals("Empleado") ? 2 : 1;

        Empleado empleado = new Empleado(nombre, aPaterno, aMaterno, calle, noExt, Colonia, cp, curp, rfc, municipio, estado, idRol, correo);
        //empleado.setCorreo(correo);

        if (validarFormulario()) {
            dEmergente.setTexto("¡ERROR! HAY AL MENOS\nUN CAMPO SIN LLENAR");
            dEmergente.setVisible(true);
            return;
        }

        if (!validarCorreo(correo)) {
            JOptionPane.showMessageDialog(this, "ERROR en el correo");
            return;
        }

        //Valida el rfc y que la edad minima y maxima sean las especificadas
        if (!new FuncionesUtiles().validarRFC(empleado, 18, 65, this)) {
            return;
        }

        //Valida la curp y que la edad minima y maxima sean las especificadas
        if (!new FuncionesUtiles().validarCurp(empleado, 18, 65, this)) {
            return;
        }

        //Validar que los 4 primeros caracteres del rfc y curp sea la misma
        char[] rfcV = empleado.getRfc().substring(0, 4).toCharArray();
        char[] curpV = empleado.getCurp().substring(0, 4).toCharArray();
        
        if (!Arrays.equals(rfcV, curpV)) {
            JOptionPane.showMessageDialog(this, "ERROR los primeros 4 caracteres de la CURP y del RFC no concuerdan");
            return;
        }

        //Validar que la fecha de nacimiento del rfc y curp sea la misma
        char[] rfcV2 = empleado.getRfc().substring(4, 10).toCharArray();
        char[] curpV2 = empleado.getCurp().substring(4, 10).toCharArray();

        if (!Arrays.equals(rfcV2, curpV2)) {
            JOptionPane.showMessageDialog(this, "ERROR la fecha de nacimiento de la CURP y del RFC no concuerdan");
            return;
        }

        if (new EmpleadoManager().insertarEmpleado(empleado)) {
            llenarTabla();
            dEmergente.setTexto("El empleado se registró de  forma correcta");
            dEmergente.setVisible(true);
        } else {
            dEmergente.setTexto("ERROR AL INTENTAR\nREGISTRAR AL\nEMPLEADO!");
            dEmergente.setVisible(true);
        }
        limpiarTxtFields();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private String encriptaContra(String contrasena) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(contrasena.getBytes());

        BigInteger bigInt = new BigInteger(1, messageDigest);

        return bigInt.toString();
    }


    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        DialogoEmergente dEmergente = new DialogoEmergente(this, true);
        int txtIdRol = String.valueOf(jComboBoxRol.getSelectedItem()).equals("Empleado") ? 2 : 1;

        String estado = txtEstado.getText();
        String correo = jTextFieldCorreo.getText();

        Empleado empleado = new Empleado(Integer.parseInt(txtIDEmpleado.getText()), txtNombre.getText(), txtApPaterno.getText(), txtApMaterno.getText(), txtCalle.getText(), txtNoExt.getText(), txtColonia.getText(), txtCP.getText(), txtCURP.getText(), txtRFC.getText(), txtMunicipio.getText(), estado, txtIdRol, correo);

        //Valida el rfc y que la edad minima y maxima sean las especificadas
        if (!new FuncionesUtiles().validarRFC(empleado, 18, 65, this)) {
            return;
        }

        //Valida la curp y que la edad minima y maxima sean las especificadas
        if (!new FuncionesUtiles().validarCurp(empleado, 18, 65, this)) {
            return;
        }

        if (!validarCorreo(correo)) {
            JOptionPane.showMessageDialog(this, "ERROR en el correo");
            return;
        }

        //Validar que los 4 primeros caracteres del rfc y curp sea la misma
        char[] rfcV = empleado.getRfc().substring(0, 4).toCharArray();
        char[] curpV = empleado.getCurp().substring(0, 4).toCharArray();

        if (!Arrays.equals(rfcV, curpV)) {
            JOptionPane.showMessageDialog(this, "ERROR los primeros 4 caracteres de la CURP y del RFC no concuerdan");
            return;
        }

        //Validar que la fecha de nacimiento del rfc y curp sea la misma
        char[] rfcV2 = empleado.getRfc().substring(4, 10).toCharArray();
        char[] curpV2 = empleado.getCurp().substring(4, 10).toCharArray();

        if (!Arrays.equals(rfcV2, curpV2)) {
            JOptionPane.showMessageDialog(this, "ERROR la fecha de nacimiento de la CURP y del RFC no concuerdan");
            return;
        }

        if (new EmpleadoManager().modificarEmpleado(empleado)) {
            llenarTabla();
            dEmergente.setTexto("El empleado se modificó de forma correcta");
            dEmergente.setVisible(true);
        } else {
            dEmergente.setTexto("¡ERROR AL INTENTAR MODIFICAR AL EMPLEADO!");
            dEmergente.setVisible(true);
        }
        limpiarTxtFields();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tblEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadoMouseClicked
        //String txtIdRol = String.valueOf(jComboBoxRol.getSelectedItem()).equals("Empleado") ? "2" : "1";
        Empleado temp = new Empleado();
        temp.getEmpleadoTabla(tblEmpleado, txtIDEmpleado, txtNombre, txtApPaterno, txtApMaterno, txtCalle, txtNoExt, txtColonia, txtCP, txtMunicipio, txtEstado, txtCURP, txtRFC, jComboBoxRol, jTextFieldCorreo);
    }//GEN-LAST:event_tblEmpleadoMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // ELIMINAR EMPLEADO
        DialogoEmergente dEmergente = new DialogoEmergente(this, true);

        int idEmpleado;

        try {
            idEmpleado = Integer.parseInt(txtIDEmpleado.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ERROR!, SELECCIONE UN RENGLON");
            return;
        }

        if (idEmpleado == idAdmon) {
            JOptionPane.showMessageDialog(this, "NO PUEDE ELIMINARSE ASI MISMO", "ERROR!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Empleado empleado = new Empleado(idEmpleado);

        if (new EmpleadoManager().eliminarEmpleado(empleado)) {
            llenarTabla();
            dEmergente.setTexto("El empleado se eliminó de\nforma correcta");
            dEmergente.setVisible(true);
        } else {
            dEmergente.setTexto("¡ERROR AL INTENTAR\nELIMINAR AL EMPLEADO!");
            dEmergente.setVisible(true);
        }
        limpiarTxtFields();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRestaurarContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarContrasenaActionPerformed

        if (tblEmpleado.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "ERROR, DEBE DE SELECIONAR UN RENGLON de la tabla");
            return;
        }
        int idEmpleado = Integer.parseInt(tblEmpleado.getValueAt(tblEmpleado.getSelectedRow(), 0).toString());

        if (this.idAdmon == idEmpleado) {
            JOptionPane.showMessageDialog(this, "ERROR! No puedes restaurar tu propia contraseña");
            return;
        }

        if (restContra(idEmpleado) && new NotificacionManager().eliminarNotEmpleado(idEmpleado)) {
            JOptionPane.showMessageDialog(this, "Todo correcto");
        } else {
            JOptionPane.showMessageDialog(this, "ERROR");
        }

        limpiarTxtFields();

    }//GEN-LAST:event_btnRestaurarContrasenaActionPerformed

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed

    }//GEN-LAST:event_txtNombreKeyPressed

    private void btnFindKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnFindKeyTyped
        // TODO add your handling code here:
        buscarEmpleado(btnFind.getText(), jComboBox1.getSelectedItem());
    }//GEN-LAST:event_btnFindKeyTyped

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        buscarEmpleado(btnFind.getText(), jComboBox1.getSelectedItem());
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // Abrir dialogo de seleccion de direccion
        DialogoSeleccionDireccion d = new DialogoSeleccionDireccion(this, true);
        d.setVisible(true);

        if (datos != null) {
            //mostrar direccion al actor
            txtEstado.setText(this.datos[0]);
            txtMunicipio.setText(this.datos[1]);
            txtColonia.setText(this.datos[2]);
            txtCP.setText(this.datos[3]);
        }


    }//GEN-LAST:event_jButton1MouseClicked

    private void buscarEmpleado(String txt, Object filtro) {
        modelo.setRowCount(0);
        switch (filtro.toString()) {
            case "Nombre" ->
                llenarTabla(new EmpleadoManager().buscarEmpleadoNombre(txt));
            case "Apellido Paterno" ->
                llenarTabla(new EmpleadoManager().buscarEmpleadoPaterno(txt));
            case "Apellido Materno" ->
                llenarTabla(new EmpleadoManager().buscarEmpleadoMaterno(txt));
        }
        tblEmpleado.repaint();
        tblEmpleado.revalidate();
    }

    private void llenarTabla(ArrayList<String[]> lista) {
        for (int i = 0; i < lista.size(); i++) {
            modelo.addRow(lista.get(i));
        }
    }

    private boolean validarCorreo(String correo) {
        String patron = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return correo.matches(patron);
    }

    private boolean restContra(int idEmpleado) {
        return new NotificacionManager().restaurarContrasena(idEmpleado);
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

    private void llenarTabla() {
        modelo.setRowCount(0); //Limpiamos la tabla
        ArrayList<String[]> lista = new EmpleadoManager().consultarTodos();
        for (int i = 0; i < lista.size(); i++) {
            modelo.addRow(lista.get(i));
        }
    }

    private boolean validarFormulario() {
        return txtNombre.getText().isBlank() | txtApPaterno.getText().isBlank() | txtApMaterno.getText().isBlank() | txtCalle.getText().isBlank() | txtNoExt.getText().isBlank() | txtColonia.getText().isBlank() | txtCP.getText().isBlank() | txtCURP.getText().isBlank() | txtRFC.getText().isBlank() | txtMunicipio.getText().isBlank() | txtEstado.getText().isBlank() | jTextFieldCorreo.getText().isBlank();
    }

    public void setDireccion(String[] datos) {
        this.datos = datos;
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
            java.util.logging.Logger.getLogger(AdministrarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministrarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministrarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministrarEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministrarEmpleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JTextField btnFind;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRestaurarContrasena;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxRol;
    private javax.swing.JLabel jLabelCorreo;
    private javax.swing.JPanel jPanelAcciones;
    private javax.swing.JPanel jPanelFormulario;
    private javax.swing.JPanel jPanelInformacion;
    private javax.swing.JPanel jPanelIzquierda;
    private javax.swing.JPanel jPanelLinea;
    private javax.swing.JPanel jPanelOperaciones;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldCorreo;
    private javax.swing.JLabel labelApMaterno;
    private javax.swing.JLabel labelApPaterno;
    private javax.swing.JLabel labelCP;
    private javax.swing.JLabel labelCURP;
    private javax.swing.JLabel labelCalle;
    private javax.swing.JLabel labelColonia;
    private javax.swing.JLabel labelEstadp;
    private javax.swing.JLabel labelIDEmpleado;
    private javax.swing.JLabel labelMunicipio;
    private javax.swing.JLabel labelNoExt;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelRFC;
    private javax.swing.JLabel labelRol;
    private javax.swing.JTable tblEmpleado;
    private javax.swing.JTextField txtApMaterno;
    private javax.swing.JTextField txtApPaterno;
    private javax.swing.JTextField txtCP;
    private javax.swing.JTextField txtCURP;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtColonia;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtIDEmpleado;
    private javax.swing.JTextField txtMunicipio;
    private javax.swing.JTextField txtNoExt;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRFC;
    // End of variables declaration//GEN-END:variables
}

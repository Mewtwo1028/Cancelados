package vista;

import controlador.Conexion;
import controlador.EmpleadoManager;
import modelo.Credencial;
import modelo.Empleado;
import modelo.FuncionesUtiles;
import java.awt.Color;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdministrarEmpleado extends javax.swing.JFrame {

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
    
    public void buscarempleado(String texto) {
        try {

            String[] titulos = {"IdEmpleado", "Nombre", "Apellido Paterno", "Apellido Materno", "Calle", "NoExt", "Colonia", "CP", "RFC", "Municipio","Estado","Roles_idRoles"};
            String filtro = "" + texto + "_%";

            String SQL = "SELECT * FROM empleado WHERE Nombre like" + '"' + filtro + '"';

            System.out.print(SQL);

            Conexion conexion = new Conexion();

            modelo = new DefaultTableModel(null, titulos);
            ResultSet rs = conexion.getConexion().prepareStatement(SQL).executeQuery();
            String[] fila = new String[13];
            while (rs.next()) {
                fila[0] = rs.getString("IdEmpleado");
                fila[1] = rs.getString("Nombre");
                fila[2] = rs.getString("ApellidoPaterno");
                fila[3] = rs.getString("ApellidoMaterno");
                fila[4] = rs.getString("Calle");
                fila[5] = rs.getString("NoExt");
                fila[6] = rs.getString("Colonia");
                fila[7] = rs.getString("CP");
                fila[8] = rs.getString("CURP");
                fila[9] = rs.getString("RFC");
                fila[10] = rs.getString("Municipio");
                fila[11] = rs.getString("Estado");
                fila[12] = rs.getString("Roles_idRoles");
                modelo.addRow(fila);

            }
            tblEmpleado.setModel(modelo);
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }

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
        tool.confBtnColor(btnConsultar);
        tool.confBtnColor(btnRegistrar);
        tool.confBtnColor(btnModificar);
        tool.confBtnColor(btnEliminar);
        tool.confBtnColor(btnRestaurarContrasena);

        //Limpiar txtFields
        limpiarTxtFields();
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
        txtContra.setText("");
        txtIDEmpleado.setText("");
        txtRepContrasena.setText("");


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
        txtEstado = new javax.swing.JTextField();
        labelNoExt = new javax.swing.JLabel();
        txtNoExt = new javax.swing.JTextField();
        labelMunicipio = new javax.swing.JLabel();
        txtMunicipio = new javax.swing.JTextField();
        labelRol = new javax.swing.JLabel();
        labelContra = new javax.swing.JLabel();
        txtContra = new javax.swing.JTextField();
        labelIDEmpleado = new javax.swing.JLabel();
        txtIDEmpleado = new javax.swing.JTextField();
        jComboBoxRol = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtRepContrasena = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleado = new javax.swing.JTable();
        jPanelAcciones = new javax.swing.JPanel();
        btnConsultar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnRestaurarContrasena = new javax.swing.JButton();

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

        txtEstado.setText("jTextField9");

        labelNoExt.setText("No. Exterior");

        txtNoExt.setText("jTextField10");

        labelMunicipio.setText("Municipio");

        txtMunicipio.setText("jTextField11");

        labelRol.setText("Rol");

        labelContra.setText("Contraseña");

        txtContra.setText("jTextField13");

        labelIDEmpleado.setText("ID. Empleado");

        txtIDEmpleado.setText("jTextField14");

        jComboBoxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado", "Administrador" }));

        jLabel1.setText("Repetir Contraseña");

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

        javax.swing.GroupLayout jPanelFormularioLayout = new javax.swing.GroupLayout(jPanelFormulario);
        jPanelFormulario.setLayout(jPanelFormularioLayout);
        jPanelFormularioLayout.setHorizontalGroup(
            jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelEstadp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelRol, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                    .addComponent(labelContra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelIDEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEstado)
                                    .addComponent(txtContra, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(txtIDEmpleado)
                                    .addComponent(jComboBoxRol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRepContrasena)))
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
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
                    .addComponent(labelContra)
                    .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCalle)
                    .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRFC)
                    .addComponent(txtRFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtRepContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNoExt)
                    .addComponent(txtNoExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMunicipio)
                    .addComponent(txtMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelIDEmpleado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        javax.swing.GroupLayout jPanelOperacionesLayout = new javax.swing.GroupLayout(jPanelOperaciones);
        jPanelOperaciones.setLayout(jPanelOperacionesLayout);
        jPanelOperacionesLayout.setHorizontalGroup(
            jPanelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOperacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelOperacionesLayout.setVerticalGroup(
            jPanelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelOperacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(389, 389, 389))
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
        String rol = String.valueOf(jComboBoxRol.getSelectedItem());
        String contra = txtContra.getText();

        int idRol = rol.equals("Empleado") ? 2 : 1;

        Empleado empleado = new Empleado(nombre, aPaterno, aMaterno, calle, noExt, Colonia, cp, curp, rfc, municipio, estado, idRol);

        if (validarFormulario()) {
            dEmergente.setTexto("¡ERROR! HAY AL MENOS\nUN CAMPO SIN LLENAR");
            dEmergente.setVisible(true);
            return;
        }

        if (new EmpleadoManager().insertarEmpleado(empleado, contra)) {
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
        Empleado empleado = new Empleado(Integer.parseInt(txtIDEmpleado.getText()), txtNombre.getText(), txtApPaterno.getText(), txtApMaterno.getText(), txtCalle.getText(), txtNoExt.getText(), txtColonia.getText(), txtCP.getText(), txtCURP.getText(), txtRFC.getText(), txtMunicipio.getText(), txtEstado.getText(), txtIdRol);

        if (txtContra.isEnabled()) {
            try {
                Credencial credencial = new Credencial(encriptaContra(txtContra.getText()), Integer.parseInt(txtIDEmpleado.getText()));

                if (compruebaContra(encriptaContra(txtContra.getText()), encriptaContra(txtRepContrasena.getText()))) {

                    empleado.modificaCredenciales(credencial);
                    dEmergente.setTexto("OK");
                } else {
                    dEmergente.setTexto("Error. Las contraseñas no coinciden");
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(AdministrarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
            dEmergente.setVisible(true);
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
        String txtIdRol = String.valueOf(jComboBoxRol.getSelectedItem()).equals("Empleado") ? "2" : "1";
        Empleado temp = new Empleado();
        temp.getEmpleadoTabla(tblEmpleado, txtIDEmpleado, txtNombre, txtApPaterno, txtApMaterno, txtCalle, txtNoExt, txtColonia, txtCP, txtMunicipio, txtEstado, txtCURP, txtRFC, jComboBoxRol);
    }//GEN-LAST:event_tblEmpleadoMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // ELIMINAR EMPLEADO
        DialogoEmergente dEmergente = new DialogoEmergente(this, true);
        Empleado empleado = new Empleado(Integer.parseInt(txtIDEmpleado.getText()));
        
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
        String aux[] = new String[2];
        aux[0] = txtContra.getText();
        aux[1] = txtRepContrasena.getText();
        int txtIdRol = String.valueOf(jComboBoxRol.getSelectedItem()).equals("Empleado") ? 2 : 1;
        Empleado empleado = new Empleado(Integer.parseInt(txtIDEmpleado.getText()), txtNombre.getText(), txtApPaterno.getText(), txtApMaterno.getText(), txtCalle.getText(), txtNoExt.getText(), txtColonia.getText(), txtCP.getText(), txtCURP.getText(), txtRFC.getText(), txtMunicipio.getText(), txtEstado.getText(), txtIdRol);
          
      DialogoEmergente a = new DialogoEmergente(this, rootPaneCheckingEnabled);
      if (aux[0].isEmpty() || aux[1].isEmpty()){
          a.setTexto("Error. Contraseña vacía");
          
      }else{
            try {
                Credencial credencial = new Credencial(encriptaContra(txtContra.getText()),Integer.parseInt(txtIDEmpleado.getText()));
                if(compruebaContra(txtRepContrasena.getText(),txtContra.getText())){
                    empleado.modificaCredenciales(credencial);
                    a.setTexto("Contraseña restaurada");
                }else{
                    a.setTexto("Error. Las contraseñas no coinciden");
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(AdministrarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
      a.setVisible(true);
      limpiarTxtFields();
      
    }//GEN-LAST:event_btnRestaurarContrasenaActionPerformed

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        // TODO add your handling code here:
        String buscar = txtNombre.getText();
        buscarempleado(buscar);
    }//GEN-LAST:event_txtNombreKeyPressed

    private void llenarTabla() {
        modelo.setRowCount(0); //Limpiamos la tabla
        ArrayList<String[]> lista = new EmpleadoManager().consultarTodos();
        for (int i = 0; i < lista.size(); i++) {
            modelo.addRow(lista.get(i));
        }
    }

    private boolean validarFormulario() {
        return txtNombre.getText().isBlank() | txtApPaterno.getText().isBlank() | txtApMaterno.getText().isBlank() | txtCalle.getText().isBlank() | txtNoExt.getText().isBlank() | txtColonia.getText().isBlank() | txtCP.getText().isBlank() | txtCURP.getText().isBlank() | txtRFC.getText().isBlank() | txtMunicipio.getText().isBlank() | txtEstado.getText().isBlank();
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
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRestaurarContrasena;
    private javax.swing.JComboBox<String> jComboBoxRol;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel labelCURP;
    private javax.swing.JLabel labelCalle;
    private javax.swing.JLabel labelColonia;
    private javax.swing.JLabel labelContra;
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
    private javax.swing.JTextField txtContra;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtIDEmpleado;
    private javax.swing.JTextField txtMunicipio;
    private javax.swing.JTextField txtNoExt;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRFC;
    private javax.swing.JPasswordField txtRepContrasena;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

/** ****************************************************************************
 * Autores:
 * Nicolas Herrera Marulanda - 202182551
 * Samuel Galindo Cuevas - 202177491
 * Julian David Rendon Cardona - 202177387
 * *****************************************************************************/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class VentanaLogin extends javax.swing.JFrame {

    public VentanaLogin()
    {
        setResizable(false);
        initComponents();
        this.pagLogin();
        this.addBotonesPrincipalListener(new CambiarPaginaListener());

        UIManager.put("OptionPane.messageFont", new java.awt.Font("Montserrat", 0, 12));
        UIManager.put("Button.font", new java.awt.Font("Montserrat", 0, 12));
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
    }

    public void pantallaCompleta()
    {
        this.setVisible(true);
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jpInferior = new javax.swing.JPanel();
        jpSuperior = new javax.swing.JPanel();
        jpDerecha = new javax.swing.JPanel();
        jpIzquierda = new javax.swing.JPanel();
        jpCentral = new javax.swing.JPanel();
        jpCentralLogin = new javax.swing.JPanel();
        jpEncabezadoLogin = new javax.swing.JPanel();
        lblTituloEncabezado = new javax.swing.JLabel();
        lblIconoUnivalle = new javax.swing.JLabel();
        jpCentroLogin = new javax.swing.JPanel();
        txtCorreoLogin = new javax.swing.JTextField();
        txtClaveLogin = new javax.swing.JPasswordField();
        lblIconoUsuario = new javax.swing.JLabel();
        lblIconoClave = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblClave = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        lblLoginTitulo = new javax.swing.JLabel();
        jpInferiorLogin = new javax.swing.JPanel();
        btnIrRegistrarse = new javax.swing.JButton();
        lblPregunta = new javax.swing.JLabel();
        jpCentralRegistroA = new javax.swing.JPanel();
        jpEncabezadoRegistroA = new javax.swing.JPanel();
        lblTituloEncabezado1 = new javax.swing.JLabel();
        lblIconoUnivalle1 = new javax.swing.JLabel();
        jpCentroRegistroA = new javax.swing.JPanel();
        lblUsuarioRegistroA = new javax.swing.JLabel();
        txtUsuarioRegistroA = new javax.swing.JTextField();
        lblCorreoRegistroA = new javax.swing.JLabel();
        txtCorreoRegistroA = new javax.swing.JTextField();
        lblClaveRegistroA = new javax.swing.JLabel();
        txtClaveRegistroA = new javax.swing.JTextField();
        lblDireccionRegistroA = new javax.swing.JLabel();
        txtDireccionRegistroA = new javax.swing.JTextField();
        lblTelefonoRegistroA = new javax.swing.JLabel();
        txtTelefonoRegistroA = new javax.swing.JTextField();
        lblCarreraRegistroA = new javax.swing.JLabel();
        txtCarreraRegistroA = new javax.swing.JTextField();
        lblUniversidadRegistroA = new javax.swing.JLabel();
        txtUniversidadRegistroA = new javax.swing.JTextField();
        btnRegistroA = new javax.swing.JButton();
        lblRegistroA = new javax.swing.JLabel();
        lblCedulaRegistroA = new javax.swing.JLabel();
        txtCedulaRegistroA = new javax.swing.JTextField();
        jpInferiorRegistroA = new javax.swing.JPanel();
        btnIrIniciarSesionA = new javax.swing.JButton();
        lblPreguntaLoginA = new javax.swing.JLabel();
        lblPreguntaDocente = new javax.swing.JLabel();
        btnDocente = new javax.swing.JButton();
        jpCentralRegistroD = new javax.swing.JPanel();
        jpEncabezadoRegistroD = new javax.swing.JPanel();
        lblTituloEncabezado2 = new javax.swing.JLabel();
        lblIconoUnivalle2 = new javax.swing.JLabel();
        jpCentroRegistroD = new javax.swing.JPanel();
        lblUsuarioRegistroD = new javax.swing.JLabel();
        txtUsuarioRegistroD = new javax.swing.JTextField();
        lblCorreoRegistroD = new javax.swing.JLabel();
        txtCorreoRegistroD = new javax.swing.JTextField();
        lblClaveRegistroD = new javax.swing.JLabel();
        txtClaveRegistroD = new javax.swing.JTextField();
        lblDireccionRegistroD = new javax.swing.JLabel();
        txtDireccionRegistroD = new javax.swing.JTextField();
        lblTelefonoRegistroD = new javax.swing.JLabel();
        txtTelefonoRegistroD = new javax.swing.JTextField();
        lblTituloRegistroD = new javax.swing.JLabel();
        txtTituloRegistroD = new javax.swing.JTextField();
        lblDependenciaRegistroD = new javax.swing.JLabel();
        txtDependenciaRegistroD = new javax.swing.JTextField();
        btnRegistroD = new javax.swing.JButton();
        lblRegistroD = new javax.swing.JLabel();
        lblCedulaRegistroD = new javax.swing.JLabel();
        txtCedulaRegistroD = new javax.swing.JTextField();
        jpInferiorRegistroD = new javax.swing.JPanel();
        btnIrIniciarSesionD = new javax.swing.JButton();
        lblPreguntaLoginD = new javax.swing.JLabel();
        lblPreguntaEstudiante = new javax.swing.JLabel();
        btnEstudiante = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpInferior.setBackground(new java.awt.Color(235, 238, 243));

        javax.swing.GroupLayout jpInferiorLayout = new javax.swing.GroupLayout(jpInferior);
        jpInferior.setLayout(jpInferiorLayout);
        jpInferiorLayout.setHorizontalGroup(
                jpInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 823, Short.MAX_VALUE)
        );
        jpInferiorLayout.setVerticalGroup(
                jpInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jpInferior, java.awt.BorderLayout.PAGE_END);

        jpSuperior.setBackground(new java.awt.Color(235, 238, 243));

        javax.swing.GroupLayout jpSuperiorLayout = new javax.swing.GroupLayout(jpSuperior);
        jpSuperior.setLayout(jpSuperiorLayout);
        jpSuperiorLayout.setHorizontalGroup(
                jpSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 823, Short.MAX_VALUE)
        );
        jpSuperiorLayout.setVerticalGroup(
                jpSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jpSuperior, java.awt.BorderLayout.PAGE_START);

        jpDerecha.setBackground(new java.awt.Color(235, 238, 243));

        javax.swing.GroupLayout jpDerechaLayout = new javax.swing.GroupLayout(jpDerecha);
        jpDerecha.setLayout(jpDerechaLayout);
        jpDerechaLayout.setHorizontalGroup(
                jpDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jpDerechaLayout.setVerticalGroup(
                jpDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 633, Short.MAX_VALUE)
        );

        getContentPane().add(jpDerecha, java.awt.BorderLayout.LINE_END);

        jpIzquierda.setBackground(new java.awt.Color(235, 238, 243));

        javax.swing.GroupLayout jpIzquierdaLayout = new javax.swing.GroupLayout(jpIzquierda);
        jpIzquierda.setLayout(jpIzquierdaLayout);
        jpIzquierdaLayout.setHorizontalGroup(
                jpIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jpIzquierdaLayout.setVerticalGroup(
                jpIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 633, Short.MAX_VALUE)
        );

        getContentPane().add(jpIzquierda, java.awt.BorderLayout.LINE_START);

        jpCentral.setBackground(new java.awt.Color(249, 250, 252));
        jpCentral.setOpaque(false);
        jpCentral.setLayout(new java.awt.CardLayout());

        jpCentralLogin.setBackground(new java.awt.Color(249, 250, 252));
        jpCentralLogin.setForeground(new java.awt.Color(249, 250, 252));
        jpCentralLogin.setLayout(new java.awt.BorderLayout());

        jpEncabezadoLogin.setOpaque(false);
        jpEncabezadoLogin.setLayout(new java.awt.GridBagLayout());

        lblTituloEncabezado.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        lblTituloEncabezado.setText("Biblioteca Universitaria");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 20, 0);
        jpEncabezadoLogin.add(lblTituloEncabezado, gridBagConstraints);

        lblIconoUnivalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoUnivalle.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(40, 0, 10, 0);
        jpEncabezadoLogin.add(lblIconoUnivalle, gridBagConstraints);

        jpCentralLogin.add(jpEncabezadoLogin, java.awt.BorderLayout.PAGE_START);

        jpCentroLogin.setInheritsPopupMenu(true);
        jpCentroLogin.setOpaque(false);
        jpCentroLogin.setLayout(new java.awt.GridBagLayout());

        txtCorreoLogin.setColumns(5);
        txtCorreoLogin.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txtCorreoLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoLoginActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 10, 0);
        jpCentroLogin.add(txtCorreoLogin, gridBagConstraints);

        txtClaveLogin.setColumns(5);
        txtClaveLogin.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txtClaveLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveLoginActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 20, 0);
        jpCentroLogin.add(txtClaveLogin, gridBagConstraints);

        lblIconoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuario.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jpCentroLogin.add(lblIconoUsuario, gridBagConstraints);

        lblIconoClave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clave.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jpCentroLogin.add(lblIconoClave, gridBagConstraints);

        lblUsuario.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblUsuario.setText("Correo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpCentroLogin.add(lblUsuario, gridBagConstraints);

        lblClave.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblClave.setText("Clave");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpCentroLogin.add(lblClave, gridBagConstraints);

        btnLogin.setBackground(new java.awt.Color(255, 0, 0));
        btnLogin.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Iniciar Sesion");
        btnLogin.setActionCommand("Login");
        btnLogin.setBorderPainted(false);
        btnLogin.setFocusPainted(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 20, 0);
        jpCentroLogin.add(btnLogin, gridBagConstraints);

        lblLoginTitulo.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        lblLoginTitulo.setText("Inicio de Sesion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        jpCentroLogin.add(lblLoginTitulo, gridBagConstraints);

        jpCentralLogin.add(jpCentroLogin, java.awt.BorderLayout.CENTER);

        jpInferiorLogin.setOpaque(false);
        jpInferiorLogin.setLayout(new java.awt.GridBagLayout());

        btnIrRegistrarse.setBackground(new java.awt.Color(249, 250, 252));
        btnIrRegistrarse.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        btnIrRegistrarse.setText("Registrate");
        btnIrRegistrarse.setActionCommand("Registro");
        btnIrRegistrarse.setFocusPainted(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 40, 0);
        jpInferiorLogin.add(btnIrRegistrarse, gridBagConstraints);

        lblPregunta.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblPregunta.setText("¿No tienes una cuenta?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 40, 5);
        jpInferiorLogin.add(lblPregunta, gridBagConstraints);

        jpCentralLogin.add(jpInferiorLogin, java.awt.BorderLayout.PAGE_END);

        jpCentral.add(jpCentralLogin, "jpCentralLogin");

        jpCentralRegistroA.setBackground(new java.awt.Color(249, 250, 252));
        jpCentralRegistroA.setForeground(new java.awt.Color(249, 250, 252));
        jpCentralRegistroA.setLayout(new java.awt.BorderLayout());

        jpEncabezadoRegistroA.setOpaque(false);
        jpEncabezadoRegistroA.setLayout(new java.awt.GridBagLayout());

        lblTituloEncabezado1.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        lblTituloEncabezado1.setText("Biblioteca Universitaria");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 20, 0);
        jpEncabezadoRegistroA.add(lblTituloEncabezado1, gridBagConstraints);

        lblIconoUnivalle1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoUnivalle.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(40, 0, 10, 0);
        jpEncabezadoRegistroA.add(lblIconoUnivalle1, gridBagConstraints);

        jpCentralRegistroA.add(jpEncabezadoRegistroA, java.awt.BorderLayout.PAGE_START);

        jpCentroRegistroA.setInheritsPopupMenu(true);
        jpCentroRegistroA.setOpaque(false);
        jpCentroRegistroA.setLayout(new java.awt.GridBagLayout());

        lblUsuarioRegistroA.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblUsuarioRegistroA.setText("Usuario");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpCentroRegistroA.add(lblUsuarioRegistroA, gridBagConstraints);

        txtUsuarioRegistroA.setColumns(5);
        txtUsuarioRegistroA.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txtUsuarioRegistroA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioRegistroAActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 5);
        jpCentroRegistroA.add(txtUsuarioRegistroA, gridBagConstraints);

        lblCorreoRegistroA.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblCorreoRegistroA.setText("Correo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpCentroRegistroA.add(lblCorreoRegistroA, gridBagConstraints);

        txtCorreoRegistroA.setColumns(5);
        txtCorreoRegistroA.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 5);
        jpCentroRegistroA.add(txtCorreoRegistroA, gridBagConstraints);

        lblClaveRegistroA.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblClaveRegistroA.setText("Clave");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpCentroRegistroA.add(lblClaveRegistroA, gridBagConstraints);

        txtClaveRegistroA.setColumns(5);
        txtClaveRegistroA.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 5);
        jpCentroRegistroA.add(txtClaveRegistroA, gridBagConstraints);

        lblDireccionRegistroA.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblDireccionRegistroA.setText("Direccion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jpCentroRegistroA.add(lblDireccionRegistroA, gridBagConstraints);

        txtDireccionRegistroA.setColumns(5);
        txtDireccionRegistroA.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        jpCentroRegistroA.add(txtDireccionRegistroA, gridBagConstraints);

        lblTelefonoRegistroA.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblTelefonoRegistroA.setText("Telefono");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jpCentroRegistroA.add(lblTelefonoRegistroA, gridBagConstraints);

        txtTelefonoRegistroA.setColumns(5);
        txtTelefonoRegistroA.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        jpCentroRegistroA.add(txtTelefonoRegistroA, gridBagConstraints);

        lblCarreraRegistroA.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblCarreraRegistroA.setText("Carrera");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jpCentroRegistroA.add(lblCarreraRegistroA, gridBagConstraints);

        txtCarreraRegistroA.setColumns(5);
        txtCarreraRegistroA.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        jpCentroRegistroA.add(txtCarreraRegistroA, gridBagConstraints);

        lblUniversidadRegistroA.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblUniversidadRegistroA.setText("Universidad");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jpCentroRegistroA.add(lblUniversidadRegistroA, gridBagConstraints);

        txtUniversidadRegistroA.setColumns(5);
        txtUniversidadRegistroA.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 20, 0);
        jpCentroRegistroA.add(txtUniversidadRegistroA, gridBagConstraints);

        btnRegistroA.setBackground(new java.awt.Color(255, 0, 0));
        btnRegistroA.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnRegistroA.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistroA.setText("Registrarse");
        btnRegistroA.setActionCommand("RegistrarseA");
        btnRegistroA.setBorderPainted(false);
        btnRegistroA.setFocusPainted(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 20, 0);
        jpCentroRegistroA.add(btnRegistroA, gridBagConstraints);

        lblRegistroA.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        lblRegistroA.setText("Registro Estudiante");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        jpCentroRegistroA.add(lblRegistroA, gridBagConstraints);

        lblCedulaRegistroA.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblCedulaRegistroA.setText("Cedula");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpCentroRegistroA.add(lblCedulaRegistroA, gridBagConstraints);

        txtCedulaRegistroA.setColumns(5);
        txtCedulaRegistroA.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txtCedulaRegistroA.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 5);
        jpCentroRegistroA.add(txtCedulaRegistroA, gridBagConstraints);

        jpCentralRegistroA.add(jpCentroRegistroA, java.awt.BorderLayout.CENTER);

        jpInferiorRegistroA.setOpaque(false);
        jpInferiorRegistroA.setLayout(new java.awt.GridBagLayout());

        btnIrIniciarSesionA.setBackground(new java.awt.Color(249, 250, 252));
        btnIrIniciarSesionA.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        btnIrIniciarSesionA.setText("Inicia Sesion");
        btnIrIniciarSesionA.setActionCommand("Login");
        btnIrIniciarSesionA.setFocusPainted(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 40, 0);
        jpInferiorRegistroA.add(btnIrIniciarSesionA, gridBagConstraints);

        lblPreguntaLoginA.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblPreguntaLoginA.setText("¿Ya tienes una cuenta?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 40, 5);
        jpInferiorRegistroA.add(lblPreguntaLoginA, gridBagConstraints);

        lblPreguntaDocente.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblPreguntaDocente.setText("¿Eres docente?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 5, 40, 5);
        jpInferiorRegistroA.add(lblPreguntaDocente, gridBagConstraints);

        btnDocente.setBackground(new java.awt.Color(249, 250, 252));
        btnDocente.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        btnDocente.setText("Registrate aqui");
        btnDocente.setToolTipText("");
        btnDocente.setActionCommand("RegistroD");
        btnDocente.setFocusPainted(false);
        btnDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocenteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 40, 0);
        jpInferiorRegistroA.add(btnDocente, gridBagConstraints);

        jpCentralRegistroA.add(jpInferiorRegistroA, java.awt.BorderLayout.PAGE_END);

        jpCentral.add(jpCentralRegistroA, "jpCentralRegistroA");

        jpCentralRegistroD.setBackground(new java.awt.Color(249, 250, 252));
        jpCentralRegistroD.setForeground(new java.awt.Color(249, 250, 252));
        jpCentralRegistroD.setLayout(new java.awt.BorderLayout());

        jpEncabezadoRegistroD.setOpaque(false);
        jpEncabezadoRegistroD.setLayout(new java.awt.GridBagLayout());

        lblTituloEncabezado2.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        lblTituloEncabezado2.setText("Biblioteca Universitaria");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 20, 0);
        jpEncabezadoRegistroD.add(lblTituloEncabezado2, gridBagConstraints);

        lblIconoUnivalle2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoUnivalle.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(40, 0, 10, 0);
        jpEncabezadoRegistroD.add(lblIconoUnivalle2, gridBagConstraints);

        jpCentralRegistroD.add(jpEncabezadoRegistroD, java.awt.BorderLayout.PAGE_START);

        jpCentroRegistroD.setInheritsPopupMenu(true);
        jpCentroRegistroD.setOpaque(false);
        jpCentroRegistroD.setLayout(new java.awt.GridBagLayout());

        lblUsuarioRegistroD.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblUsuarioRegistroD.setText("Usuario");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpCentroRegistroD.add(lblUsuarioRegistroD, gridBagConstraints);

        txtUsuarioRegistroD.setColumns(5);
        txtUsuarioRegistroD.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txtUsuarioRegistroD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioRegistroDActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 5);
        jpCentroRegistroD.add(txtUsuarioRegistroD, gridBagConstraints);

        lblCorreoRegistroD.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblCorreoRegistroD.setText("Correo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpCentroRegistroD.add(lblCorreoRegistroD, gridBagConstraints);

        txtCorreoRegistroD.setColumns(5);
        txtCorreoRegistroD.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 5);
        jpCentroRegistroD.add(txtCorreoRegistroD, gridBagConstraints);

        lblClaveRegistroD.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblClaveRegistroD.setText("Clave");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpCentroRegistroD.add(lblClaveRegistroD, gridBagConstraints);

        txtClaveRegistroD.setColumns(5);
        txtClaveRegistroD.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 5);
        jpCentroRegistroD.add(txtClaveRegistroD, gridBagConstraints);

        lblDireccionRegistroD.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblDireccionRegistroD.setText("Direccion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jpCentroRegistroD.add(lblDireccionRegistroD, gridBagConstraints);

        txtDireccionRegistroD.setColumns(5);
        txtDireccionRegistroD.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        jpCentroRegistroD.add(txtDireccionRegistroD, gridBagConstraints);

        lblTelefonoRegistroD.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblTelefonoRegistroD.setText("Telefono");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jpCentroRegistroD.add(lblTelefonoRegistroD, gridBagConstraints);

        txtTelefonoRegistroD.setColumns(5);
        txtTelefonoRegistroD.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        jpCentroRegistroD.add(txtTelefonoRegistroD, gridBagConstraints);

        lblTituloRegistroD.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblTituloRegistroD.setText("Titulo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jpCentroRegistroD.add(lblTituloRegistroD, gridBagConstraints);

        txtTituloRegistroD.setColumns(5);
        txtTituloRegistroD.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        jpCentroRegistroD.add(txtTituloRegistroD, gridBagConstraints);

        lblDependenciaRegistroD.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblDependenciaRegistroD.setText("Dependencia");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jpCentroRegistroD.add(lblDependenciaRegistroD, gridBagConstraints);

        txtDependenciaRegistroD.setColumns(5);
        txtDependenciaRegistroD.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 20, 0);
        jpCentroRegistroD.add(txtDependenciaRegistroD, gridBagConstraints);

        btnRegistroD.setBackground(new java.awt.Color(255, 0, 0));
        btnRegistroD.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnRegistroD.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistroD.setText("Registrarse");
        btnRegistroD.setActionCommand("RegistrarseD");
        btnRegistroD.setBorderPainted(false);
        btnRegistroD.setFocusPainted(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 20, 0);
        jpCentroRegistroD.add(btnRegistroD, gridBagConstraints);

        lblRegistroD.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        lblRegistroD.setText("Registro Docente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        jpCentroRegistroD.add(lblRegistroD, gridBagConstraints);

        lblCedulaRegistroD.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblCedulaRegistroD.setText("Cedula");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpCentroRegistroD.add(lblCedulaRegistroD, gridBagConstraints);

        txtCedulaRegistroD.setColumns(5);
        txtCedulaRegistroD.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 5);
        jpCentroRegistroD.add(txtCedulaRegistroD, gridBagConstraints);

        jpCentralRegistroD.add(jpCentroRegistroD, java.awt.BorderLayout.CENTER);

        jpInferiorRegistroD.setOpaque(false);
        jpInferiorRegistroD.setLayout(new java.awt.GridBagLayout());

        btnIrIniciarSesionD.setBackground(new java.awt.Color(249, 250, 252));
        btnIrIniciarSesionD.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        btnIrIniciarSesionD.setText("Inicia Sesion");
        btnIrIniciarSesionD.setActionCommand("Login");
        btnIrIniciarSesionD.setFocusPainted(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 40, 0);
        jpInferiorRegistroD.add(btnIrIniciarSesionD, gridBagConstraints);

        lblPreguntaLoginD.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblPreguntaLoginD.setText("¿Ya tienes una cuenta?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 40, 5);
        jpInferiorRegistroD.add(lblPreguntaLoginD, gridBagConstraints);

        lblPreguntaEstudiante.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        lblPreguntaEstudiante.setText("¿Eres estudiante?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 5, 40, 5);
        jpInferiorRegistroD.add(lblPreguntaEstudiante, gridBagConstraints);

        btnEstudiante.setBackground(new java.awt.Color(249, 250, 252));
        btnEstudiante.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        btnEstudiante.setText("Registrate aqui");
        btnEstudiante.setToolTipText("");
        btnEstudiante.setActionCommand("RegistroA");
        btnEstudiante.setFocusPainted(false);
        btnEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstudianteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 40, 0);
        jpInferiorRegistroD.add(btnEstudiante, gridBagConstraints);

        jpCentralRegistroD.add(jpInferiorRegistroD, java.awt.BorderLayout.PAGE_END);

        jpCentral.add(jpCentralRegistroD, "jpCentralRegistroD");

        getContentPane().add(jpCentral, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>

    private void txtClaveLoginActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void txtCorreoLoginActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void txtUsuarioRegistroAActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void txtUsuarioRegistroDActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btnEstudianteActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btnDocenteActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public void pagLogin()
    {
        CardLayout a = (CardLayout)jpCentral.getLayout();
        a.show(jpCentral, "jpCentralLogin");
    }

    public void pagRegistroAlumno()
    {
        CardLayout a = (CardLayout)jpCentral.getLayout();
        a.show(jpCentral, "jpCentralRegistroA");
    }

    public void pagRegistroDocente()
    {
        CardLayout a = (CardLayout)jpCentral.getLayout();
        a.show(jpCentral, "jpCentralRegistroD");
    }

    class CambiarPaginaListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equalsIgnoreCase("Login"))
            {
                limpiarRegistroA();
                limpiarRegistroD();
                pagLogin();
            }
            if (e.getActionCommand().equalsIgnoreCase("Registro"))
            {
                limpiarLogin();
                pagRegistroAlumno();
            }
            if (e.getActionCommand().equalsIgnoreCase("RegistroA"))
            {
                limpiarRegistroD();
                pagRegistroAlumno();
            }
            if (e.getActionCommand().equalsIgnoreCase("RegistroD"))
            {
                limpiarRegistroA();
                pagRegistroDocente();
            }
        }
    }

    public void addBotonesPrincipalListener(ActionListener listenControles)
    {
        btnIrRegistrarse.addActionListener(listenControles);
        btnDocente.addActionListener(listenControles);
        btnEstudiante.addActionListener(listenControles);
        btnIrIniciarSesionA.addActionListener(listenControles);
        btnIrIniciarSesionD.addActionListener(listenControles);
    }

    public void limpiarLogin()
    {
        txtCorreoLogin.setText("");
        txtClaveLogin.setText("");
    }

    public void limpiarRegistroA()
    {
        txtCedulaRegistroA.setText("");
        txtUsuarioRegistroA.setText("");
        txtCorreoRegistroA.setText("");
        txtClaveRegistroA.setText("");
        txtDireccionRegistroA.setText("");
        txtTelefonoRegistroA.setText("");
        txtCarreraRegistroA.setText("");
        txtUniversidadRegistroA.setText("");
    }

    public void limpiarRegistroD()
    {
        txtCedulaRegistroD.setText("");
        txtUsuarioRegistroD.setText("");
        txtCorreoRegistroD.setText("");
        txtClaveRegistroD.setText("");
        txtDireccionRegistroD.setText("");
        txtTelefonoRegistroD.setText("");
        txtTituloRegistroD.setText("");
        txtDependenciaRegistroD.setText("");
    }

    public ArrayList<String> getFormularioEstudiante()
    {
        ArrayList<String> formulario;
        formulario = new ArrayList<String>();

        formulario.add(txtCedulaRegistroA.getText());
        formulario.add(txtUsuarioRegistroA.getText());
        formulario.add(txtCorreoRegistroA.getText());
        formulario.add(txtClaveRegistroA.getText());
        formulario.add(txtDireccionRegistroA.getText());
        formulario.add(txtTelefonoRegistroA.getText());
        formulario.add(txtCarreraRegistroA.getText());
        formulario.add(txtUniversidadRegistroA.getText());

        return formulario;
    }

    public ArrayList<String> getFormularioProfesor()
    {
        ArrayList<String> formulario;
        formulario = new ArrayList<String>();

        formulario.add(txtCedulaRegistroD.getText());
        formulario.add(txtUsuarioRegistroD.getText());
        formulario.add(txtCorreoRegistroD.getText());
        formulario.add(txtClaveRegistroD.getText());
        formulario.add(txtDireccionRegistroD.getText());
        formulario.add(txtTelefonoRegistroD.getText());
        formulario.add(txtTituloRegistroD.getText());
        formulario.add(txtDependenciaRegistroD.getText());

        return formulario;
    }

    public ArrayList<String> getFormularioLogin()
    {
        ArrayList<String> formulario;
        formulario = new ArrayList<String>();

        formulario.add(txtCorreoLogin.getText());
        formulario.add(txtClaveLogin.getText());

        return formulario;
    }

    public void mostrarMensaje(String auxMensaje)
    {
        JOptionPane.showMessageDialog(this, auxMensaje);
    }

    public void mostrarMensajeError(String auxMensaje)
    {
        JOptionPane.showMessageDialog(this, auxMensaje, "", JOptionPane.ERROR_MESSAGE);
    }

    public void addBtnRegistrarseAListener(ActionListener listenControles)
    {
        btnRegistroA.addActionListener(listenControles);
    }

    public void addBtnRegistrarseDListener(ActionListener listenControles)
    {
        btnRegistroD.addActionListener(listenControles);
    }

    public void addBtnLoginListener(ActionListener listenControles)
    {
        btnLogin.addActionListener(listenControles);
    }


    // Variables declaration - do not modify
    private javax.swing.JButton btnDocente;
    private javax.swing.JButton btnEstudiante;
    private javax.swing.JButton btnIrIniciarSesionA;
    private javax.swing.JButton btnIrIniciarSesionD;
    private javax.swing.JButton btnIrRegistrarse;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegistroA;
    private javax.swing.JButton btnRegistroD;
    private javax.swing.JPanel jpCentral;
    private javax.swing.JPanel jpCentralLogin;
    private javax.swing.JPanel jpCentralRegistroA;
    private javax.swing.JPanel jpCentralRegistroD;
    private javax.swing.JPanel jpCentroLogin;
    private javax.swing.JPanel jpCentroRegistroA;
    private javax.swing.JPanel jpCentroRegistroD;
    private javax.swing.JPanel jpDerecha;
    private javax.swing.JPanel jpEncabezadoLogin;
    private javax.swing.JPanel jpEncabezadoRegistroA;
    private javax.swing.JPanel jpEncabezadoRegistroD;
    private javax.swing.JPanel jpInferior;
    private javax.swing.JPanel jpInferiorLogin;
    private javax.swing.JPanel jpInferiorRegistroA;
    private javax.swing.JPanel jpInferiorRegistroD;
    private javax.swing.JPanel jpIzquierda;
    private javax.swing.JPanel jpSuperior;
    private javax.swing.JLabel lblCarreraRegistroA;
    private javax.swing.JLabel lblCedulaRegistroA;
    private javax.swing.JLabel lblCedulaRegistroD;
    private javax.swing.JLabel lblClave;
    private javax.swing.JLabel lblClaveRegistroA;
    private javax.swing.JLabel lblClaveRegistroD;
    private javax.swing.JLabel lblCorreoRegistroA;
    private javax.swing.JLabel lblCorreoRegistroD;
    private javax.swing.JLabel lblDependenciaRegistroD;
    private javax.swing.JLabel lblDireccionRegistroA;
    private javax.swing.JLabel lblDireccionRegistroD;
    private javax.swing.JLabel lblIconoClave;
    private javax.swing.JLabel lblIconoUnivalle;
    private javax.swing.JLabel lblIconoUnivalle1;
    private javax.swing.JLabel lblIconoUnivalle2;
    private javax.swing.JLabel lblIconoUsuario;
    private javax.swing.JLabel lblLoginTitulo;
    private javax.swing.JLabel lblPregunta;
    private javax.swing.JLabel lblPreguntaDocente;
    private javax.swing.JLabel lblPreguntaEstudiante;
    private javax.swing.JLabel lblPreguntaLoginA;
    private javax.swing.JLabel lblPreguntaLoginD;
    private javax.swing.JLabel lblRegistroA;
    private javax.swing.JLabel lblRegistroD;
    private javax.swing.JLabel lblTelefonoRegistroA;
    private javax.swing.JLabel lblTelefonoRegistroD;
    private javax.swing.JLabel lblTituloEncabezado;
    private javax.swing.JLabel lblTituloEncabezado1;
    private javax.swing.JLabel lblTituloEncabezado2;
    private javax.swing.JLabel lblTituloRegistroD;
    private javax.swing.JLabel lblUniversidadRegistroA;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblUsuarioRegistroA;
    private javax.swing.JLabel lblUsuarioRegistroD;
    private javax.swing.JTextField txtCarreraRegistroA;
    private javax.swing.JTextField txtCedulaRegistroA;
    private javax.swing.JTextField txtCedulaRegistroD;
    private javax.swing.JPasswordField txtClaveLogin;
    private javax.swing.JTextField txtClaveRegistroA;
    private javax.swing.JTextField txtClaveRegistroD;
    private javax.swing.JTextField txtCorreoLogin;
    private javax.swing.JTextField txtCorreoRegistroA;
    private javax.swing.JTextField txtCorreoRegistroD;
    private javax.swing.JTextField txtDependenciaRegistroD;
    private javax.swing.JTextField txtDireccionRegistroA;
    private javax.swing.JTextField txtDireccionRegistroD;
    private javax.swing.JTextField txtTelefonoRegistroA;
    private javax.swing.JTextField txtTelefonoRegistroD;
    private javax.swing.JTextField txtTituloRegistroD;
    private javax.swing.JTextField txtUniversidadRegistroA;
    private javax.swing.JTextField txtUsuarioRegistroA;
    private javax.swing.JTextField txtUsuarioRegistroD;
}

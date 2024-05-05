package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.Empleado;
import app.MainInterfaz;

public class logueo extends javax.swing.JFrame {

    public logueo() {
        initComponents();
        setSize(500, 400);
        getContentPane().setBackground(new Color(30, 144, 255));
        jPanel1.setBackground(new Color(204, 204, 255));
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtusuario = new javax.swing.JTextField();
        Password = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        btnInformacion = new javax.swing.JButton();
        btnMostrarContrasena = new javax.swing.JToggleButton(); // Botón para mostrar/ocultar contraseña

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 255));

        jLabel1.setText("Usuario 👤");

        jLabel2.setText("Contraseña 🔒");

        jButton1.setText("Ingresar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        // Configuración del nuevo botón "Crear Usuario"
        jButton2.setText("Crear Usuario");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 38)); // NOI18N
        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 0, 24));
        jLabel3.setText(" Bienvenido Usuario \uD83E\uDD1D");

        btnInformacion.setText("Información de la Aplicación");
        btnInformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformacionActionPerformed(evt);
            }
        });

        // Configuración del botón para mostrar/ocultar contraseña
        btnMostrarContrasena.setText("👁️");
        btnMostrarContrasena.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnMostrarContrasenaActionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(txtusuario, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Password, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnMostrarContrasena))))))) // Agrega el botón para mostrar/ocultar contraseña
                .addContainerGap(221, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(214, 214, 214))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(68, 68, 68))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(btnMostrarContrasena))) // Agrega el botón para mostrar/ocultar contraseña
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1) // Botón "Ingresar"
                    .addComponent(jButton2)) // Botón "Crear Usuario"
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnInformacion) // Botón "Información de la Aplicación"
                .addGap(125, 125, 125))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        String usuarioIngresado = txtusuario.getText();
        String contrasenaIngresada = new String(Password.getPassword());

        if (verificarCredenciales(usuarioIngresado, contrasenaIngresada)) {
            MainInterfaz mainInterfaz = new MainInterfaz();
            mainInterfaz.setUsuario(usuarioIngresado);
            mainInterfaz.setVisible(true);
            dispose(); // Cerramos la ventana de inicio de sesión
        } else {
            JOptionPane.showMessageDialog(this, "Usuario / Contraseña incorrecta");
        }
    }//GEN-LAST:event_jButton1MouseClicked

    // Evento de clic para el botón "Crear Usuario"
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Abrir la ventana para crear un nuevo usuario (empleado)
        Empleado empleado = new Empleado();
        empleado.setVisible(true);
        this.dispose(); // Cerrar la ventana de inicio de sesión
    }//GEN-LAST:event_jButton2ActionPerformed

    // Evento de clic para el botón "Información de la Aplicación"
    private void btnInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformacionActionPerformed
        // Mostrar el cuadro de diálogo con el mensaje personalizado
        JOptionPane.showMessageDialog(this, "Bienvenido a la discoteca. Si usted es empleado, por favor regístrese y proceda a iniciar sesión.");
    }//GEN-LAST:event_btnInformacionActionPerformed

    // Evento de clic para el botón "Mostrar Contraseña"
    private void btnMostrarContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarContrasenaActionPerformed
        // Cambia el modo de visualización de la contraseña
        if (btnMostrarContrasena.isSelected()) {
            Password.setEchoChar((char) 0); // Muestra la contraseña
        } else {
            Password.setEchoChar('*'); // Oculta la contraseña
        }
    }//GEN-LAST:event_btnMostrarContrasenaActionPerformed

    private boolean verificarCredenciales(String usuario, String contrasena) {
        // Lógica para verificar las credenciales en la base de datos de empleados
        // Aquí se debe reemplazar esta lógica con la que se adapte a tu sistema de base de datos y estructura de empleados
        // Suponiendo que tienes una tabla llamada "empleados" con columnas "nombre_usuario" y "contrasena"
        String jdbcUrl = "jdbc:mysql://localhost:3306/bd_discoteca";
        String usuarioDB = "";
        String contrasenaDB = "";

        try {
            Connection conexion = DriverManager.getConnection(jdbcUrl, "root", ""); // Cambia usuario y contraseña si es necesario
            String consulta = "SELECT nombre_usuario, contraseña FROM empleados WHERE nombre_usuario = ?";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, usuario);
            ResultSet resultado = statement.executeQuery();
            // Si hay un resultado, actualizamos usuarioDB y contrasenaDB con los valores de la base de datos
            if (resultado.next()) {
                usuarioDB = resultado.getString("nombre_usuario");
                contrasenaDB = resultado.getString("contraseña");
            }
            resultado.close();
            statement.close();
            conexion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al verificar las credenciales: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        // Verificar si el usuario y la contraseña ingresados coinciden con los almacenados en la base de datos
        return usuario.equals(usuarioDB) && contrasena.equals(contrasenaDB);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new logueo().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Password;
    private javax.swing.JButton btnInformacion;
    private javax.swing.JToggleButton btnMostrarContrasena; // Botón para mostrar/ocultar contraseña
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
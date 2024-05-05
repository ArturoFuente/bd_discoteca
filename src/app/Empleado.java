package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import login.logueo;

public class Empleado extends JFrame implements ActionListener {

    private JTextField txtId, txtNombre, txtCargo, txtFechaContratacion, txtNombreUsuario, txtContrasena;
    private JButton btnGuardar, btnCerrar, btnVolverALogin; // Agregamos el botón btnVolverALogin

    public Empleado() {
        setTitle("Empleado");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("icono.png").getImage());
        getContentPane().setBackground(new Color(12, 171, 168));

        txtId = new JTextField(10);
        txtNombre = new JTextField(20);
        txtCargo = new JTextField(15);
        txtFechaContratacion = new JTextField(10);
        txtNombreUsuario = new JTextField(20);
        txtContrasena = new JTextField(20);

        btnGuardar = new JButton("Guardar");
        btnCerrar = new JButton("Cerrar");
        btnVolverALogin = new JButton("Volver al inicio de sesión"); // Creamos el botón

        btnGuardar.setBackground(new Color(36, 138, 61));
        btnCerrar.setBackground(new Color(192, 57, 43));
        btnGuardar.setForeground(Color.WHITE);
        btnCerrar.setForeground(Color.WHITE);

        setUIFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));

        btnGuardar.addActionListener(this);
        btnCerrar.addActionListener(this);
        btnVolverALogin.addActionListener(this); // Agregamos el ActionListener al botón

        setLayout(new GridLayout(9, 2));
        add(createLabel("ID_empleado:"));
        add(txtId);
        add(createLabel("Nombre_empleado:"));
        add(txtNombre);
        add(createLabel("Cargo:"));
        add(txtCargo);
        add(createLabel("Fecha_contratacion (AAAA-MM-DD):"));
        add(txtFechaContratacion);
        add(createLabel("Nombre_usuario:"));
        add(txtNombreUsuario);
        add(createLabel("Contraseña:"));
        add(txtContrasena);
        add(btnGuardar);
        add(btnCerrar);
        add(btnVolverALogin); // Agregamos el botón a la ventana
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnGuardar) {
            guardarEmpleado();
        } else if (source == btnCerrar) {
            cerrarVentana();
        } else if (source == btnVolverALogin) { // Manejamos el evento del botón de volver al inicio de sesión
            volverALogin();
        }
    }

    private void guardarEmpleado() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bd_discoteca";
        String usuario = "root";
        String contraseña = "";

        try {
            Connection conexion = DriverManager.getConnection(jdbcUrl, usuario, contraseña);

            String consulta = "INSERT INTO Empleados (ID_empleado, Nombre_empleado, Cargo, Fecha_contratacion, Nombre_usuario, Contraseña) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, Integer.parseInt(txtId.getText()));
            statement.setString(2, txtNombre.getText());
            statement.setString(3, txtCargo.getText());
            statement.setDate(4, java.sql.Date.valueOf(txtFechaContratacion.getText()));
            statement.setString(5, txtNombreUsuario.getText());
            statement.setString(6, txtContrasena.getText());

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Inserción exitosa");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo insertar el empleado");
            }

            statement.close();
            conexion.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al insertar empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID_empleado debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void cerrarVentana() {
        this.dispose();
    }

    private void volverALogin() {
        // Volvemos al inicio de sesión (logueo)
        logueo login = new logueo();
        login.setVisible(true);
        dispose();
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLACK);
        return label;
    }

    private static void setUIFont(Font font) {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            if (UIManager.get(key) instanceof Font) {
                UIManager.put(key, font);
            }
        }
    }

    public static void main(String[] args) {
        // Ejecuta la interfaz en el hilo de eventos de la interfaz de usuario (Swing)
        SwingUtilities.invokeLater(() -> {
            Empleado interfazEmpleado = new Empleado();
            interfazEmpleado.setVisible(true);
        });
    }
}

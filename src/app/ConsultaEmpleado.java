package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import login.logueo;

public class ConsultaEmpleado extends JFrame implements ActionListener {

    private JTextField txtId, txtNombre, txtCargo, txtFechaContratacion, txtNombreUsuario, txtContrasena;
    private JButton btnEliminar, btnCerrar, btnVolverALogin, btnBuscar, btnModificar;
    private Connection conexion;

    public ConsultaEmpleado() {
        setTitle("Consulta Empleado");
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

        btnEliminar = new JButton("Eliminar");
        btnCerrar = new JButton("Cerrar");
        btnVolverALogin = new JButton("Volver al inicio de sesión");
        btnBuscar = new JButton("Buscar");
        btnModificar = new JButton("Modificar");

        btnEliminar.setBackground(new Color(192, 57, 43));
        btnCerrar.setBackground(new Color(192, 57, 43));
        btnVolverALogin.setBackground(new Color(52, 152, 219));
        btnBuscar.setBackground(new Color(52, 152, 219));
        btnModificar.setBackground(new Color(230, 126, 34));

        btnEliminar.setForeground(Color.WHITE);
        btnCerrar.setForeground(Color.WHITE);
        btnVolverALogin.setForeground(Color.WHITE);
        btnBuscar.setForeground(Color.WHITE);
        btnModificar.setForeground(Color.WHITE);

        setUIFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));

        btnEliminar.addActionListener(this);
        btnCerrar.addActionListener(this);
        btnVolverALogin.addActionListener(this);
        btnBuscar.addActionListener(this);
        btnModificar.addActionListener(this);

        setLayout(new GridLayout(10, 2));
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
        add(btnBuscar);
        add(btnModificar);
        add(btnEliminar);
        add(btnCerrar);
        add(btnVolverALogin);

        conectarBaseDeDatos();
    }

    private void conectarBaseDeDatos() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bd_discoteca";
        String usuario = "root";
        String contraseña = "";

        try {
            conexion = DriverManager.getConnection(jdbcUrl, usuario, contraseña);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnEliminar) {
            eliminarEmpleado();
        } else if (source == btnCerrar) {
            cerrarVentana();
        } else if (source == btnVolverALogin) {
            volverALogin();
        } else if (source == btnBuscar) {
            buscarEmpleado();
        } else if (source == btnModificar) {
            modificarEmpleado();
        }
    }

    private void eliminarEmpleado() {
        try {
            String consulta = "DELETE FROM Empleados WHERE ID_empleado = ?";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, Integer.parseInt(txtId.getText()));

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Eliminación exitosa");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el empleado");
            }

            statement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID_empleado debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void buscarEmpleado() {
        try {
            String consulta = "SELECT * FROM Empleados WHERE ID_empleado = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, Integer.parseInt(txtId.getText()));

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                txtNombre.setText(resultSet.getString("Nombre_empleado"));
                txtCargo.setText(resultSet.getString("Cargo"));
                txtFechaContratacion.setText(resultSet.getDate("Fecha_contratacion").toString());
                txtNombreUsuario.setText(resultSet.getString("Nombre_usuario"));
                txtContrasena.setText(resultSet.getString("Contraseña"));
            } else {
                JOptionPane.showMessageDialog(this, "Empleado no encontrado");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID_empleado debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void modificarEmpleado() {
        try {
            String consulta = "UPDATE Empleados SET Nombre_empleado = ?, Cargo = ?, Fecha_contratacion = ?, Nombre_usuario = ?, Contraseña = ? WHERE ID_empleado = ?";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, txtNombre.getText());
            statement.setString(2, txtCargo.getText());
            statement.setDate(3, java.sql.Date.valueOf(txtFechaContratacion.getText()));
            statement.setString(4, txtNombreUsuario.getText());
            statement.setString(5, txtContrasena.getText());
            statement.setInt(6, Integer.parseInt(txtId.getText()));

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Modificación exitosa");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo modificar el empleado");
            }

            statement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al modificar empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtCargo.setText("");
        txtFechaContratacion.setText("");
        txtNombreUsuario.setText("");
        txtContrasena.setText("");
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
            ConsultaEmpleado interfazEmpleado = new ConsultaEmpleado();
            interfazEmpleado.setVisible(true);
        });
    }
}
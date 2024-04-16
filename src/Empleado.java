import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Empleado extends JFrame implements ActionListener {

    private JTextField txtId, txtNombre, txtCargo, txtFechaContratacion, txtNombreUsuario, txtContrasena;
    private JButton btnGuardar, btnCerrar;

    public Empleado() {
        setTitle("\uD83D\uDCBC Empleado");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Establece el icono de la aplicación (reemplaza "icono.png" con la ruta de tu ícono)
        setIconImage(new ImageIcon("icono.png").getImage());

        // Configura el fondo con un color específico
        getContentPane().setBackground(new Color(12, 171, 168));

        // Crear componentes
        txtId = new JTextField(10);
        txtNombre = new JTextField(20);
        txtCargo = new JTextField(15);
        txtFechaContratacion = new JTextField(10);
        txtNombreUsuario = new JTextField(20);
        txtContrasena = new JTextField(20);

        btnGuardar = new JButton("\uD83D\uDCBE Guardar");
        btnCerrar = new JButton("\uD83D\uDD34 Cerrar");

        // Establece el color de fondo y el color de texto para los botones
        btnGuardar.setBackground(new Color(36, 138, 61));
        btnCerrar.setBackground(new Color(192, 57, 43));
        btnGuardar.setForeground(Color.WHITE);
        btnCerrar.setForeground(Color.WHITE);

        // Configura la fuente para admitir emojis
        setUIFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));

        // Agregar oyentes de eventos a los botones
        btnGuardar.addActionListener(this);
        btnCerrar.addActionListener(this);

        // Configura el diseño de la interfaz
        setLayout(new GridLayout(8, 2));
        add(createLabel("\uD83D\uDD0E ID_empleado:"));
        add(txtId);
        add(createLabel("\uD83D\uDC64 Nombre_empleado:"));
        add(txtNombre);
        add(createLabel("\uD83D\uDCBC Cargo:"));
        add(txtCargo);
        add(createLabel("\uD83D\uDCC5 Fecha_contratacion (AAAA-MM-DD):"));
        add(txtFechaContratacion);
        add(createLabel("\uD83D\uDC68\u200D\uD83D\uDCBC Nombre_usuario:"));
        add(txtNombreUsuario);
        add(createLabel("\uD83D\uDD12 Contrasena:"));
        add(txtContrasena);
        add(btnGuardar);
        add(btnCerrar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            guardarEmpleado();
        } else if (e.getSource() == btnCerrar) {
            cerrarVentana();
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
        // Cierra la interfaz actual (Empleado)
        this.dispose();
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

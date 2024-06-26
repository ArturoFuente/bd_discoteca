package app;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlAsistenciaDiscoteca extends JFrame implements ActionListener {

    private JTextField txtIdRegistro, txtFechaHoraEntrada, txtFechaHoraSalida, txtPrecioPagado;
    private JComboBox<String> cbClientes, cbEventos;
    private JButton btnGuardar, btnCerrar;

    public ControlAsistenciaDiscoteca() {
        // Configuración de la ventana
        setTitle("\uD83D\uDCBB Control de Asistencia");  // Emoji para el icono de control de asistencia
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("icono.png").getImage());  // Reemplaza "icono.png" con la ruta de tu ícono

        // Configura el fondo con color #0CABA8
        getContentPane().setBackground(new Color(12, 171, 168));

        // Crear componentes
        txtIdRegistro = new JTextField(10);
        txtFechaHoraEntrada = new JTextField(20);
        txtFechaHoraSalida = new JTextField(20);
        txtPrecioPagado = new JTextField(10);
        cbClientes = new JComboBox<>();
        cbEventos = new JComboBox<>();

        btnGuardar = new JButton("\uD83D\uDCBE Guardar");  // Emoji para el icono de disco
        btnCerrar = new JButton("\uD83D\uDD34 Cerrar");    // Emoji para el icono de cerrar

        // Estilo de los botones
        btnGuardar.setBackground(new Color(36, 138, 61)); // Verde
        btnCerrar.setBackground(new Color(192, 57, 43));  // Rojo
        btnGuardar.setForeground(Color.WHITE);
        btnCerrar.setForeground(Color.WHITE);

        // Configurar la fuente para admitir emojis
        setUIFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));

        // Agregar oyentes de eventos a los botones
        btnGuardar.addActionListener(this);
        btnCerrar.addActionListener(this);

        // Configurar el diseño de la interfaz
        setLayout(new GridLayout(7, 2));
        add(createLabel("\uD83D\uDCBB ID_registro_asistencia:"));
        add(txtIdRegistro);
        add(createLabel("\uD83D\uDC65 ID_Cliente:"));
        add(cbClientes);
        add(createLabel("\uD83C\uDFAB ID_Evento:"));
        add(cbEventos);
        add(createLabel("\uD83D\uDD56 Fecha_hora_entrada (AAAA-MM-DD HH:MM:SS):"));
        add(txtFechaHoraEntrada);
        add(createLabel("\uD83D\uDD56 Fecha_hora_salida (AAAA-MM-DD HH:MM:SS):"));
        add(txtFechaHoraSalida);
        add(createLabel("\uD83D\uDCB0 Precio_pagado:"));
        add(txtPrecioPagado);
        add(btnGuardar);
        add(btnCerrar);

        cargarClientes();
        cargarEventos();
    }

    private void cargarClientes() {
        // Lógica para cargar datos de clientes desde la base de datos
        String jdbcUrl = "jdbc:mysql://localhost:3306/bd_discoteca";
        String usuario = "root";
        String contraseña = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(jdbcUrl, usuario, contraseña);

            String consulta = "SELECT ID_cliente, Nombre_cliente FROM Clientes";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idCliente = resultSet.getInt("ID_cliente");
                String nombreCliente = resultSet.getString("Nombre_cliente");
                cbClientes.addItem(idCliente + " - " + nombreCliente);
            }

            resultSet.close();
            statement.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void cargarEventos() {
        // Lógica para cargar datos de eventos desde la base de datos
        String jdbcUrl = "jdbc:mysql://localhost:3306/bd_discoteca";
        String usuario = "root";
        String contraseña = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(jdbcUrl, usuario, contraseña);

            String consulta = "SELECT ID_evento, Nombre_evento FROM Eventos";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idEvento = resultSet.getInt("ID_evento");
                String nombreEvento = resultSet.getString("Nombre_evento");
                cbEventos.addItem(idEvento + " - " + nombreEvento);
            }

            resultSet.close();
            statement.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            guardarAsistencia();
        } else if (e.getSource() == btnCerrar) {
            cerrarVentana();
        }
    }

    private void guardarAsistencia() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bd_discoteca";
        String usuario = "root";
        String contraseña = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(jdbcUrl, usuario, contraseña);

            String consulta = "INSERT INTO Control_Asistencia_Discoteca (ID_registro_asistencia, ID_cliente, ID_evento, Fecha_hora_entrada, Fecha_hora_salida, Precio_pagado) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, Integer.parseInt(txtIdRegistro.getText()));

            // Obtener el ID del cliente seleccionado
            int idCliente = Integer.parseInt(cbClientes.getSelectedItem().toString().split(" - ")[0]);
            statement.setInt(2, idCliente);

            // Obtener el ID del evento seleccionado
            int idEvento = Integer.parseInt(cbEventos.getSelectedItem().toString().split(" - ")[0]);
            statement.setInt(3, idEvento);

            statement.setString(4, txtFechaHoraEntrada.getText());
            statement.setString(5, txtFechaHoraSalida.getText());
            statement.setBigDecimal(6, new java.math.BigDecimal(txtPrecioPagado.getText()));

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Inserción exitosa");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo insertar la asistencia");
            }

            statement.close();
            conexion.close();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void cerrarVentana() {
        // Cierra la interfaz actual (ControlAsistenciaDiscoteca)
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
        SwingUtilities.invokeLater(() -> {
            ControlAsistenciaDiscoteca interfazAsistencia = new ControlAsistenciaDiscoteca();
            interfazAsistencia.setVisible(true);
        });
    }
}

package app;


/**
 * MainInterfaz - Ventana principal de la aplicación Discoteca.
 * Esta interfaz proporciona acceso a diversas funciones de la discoteca.
 *
 * Autor: Arturo Fuentes
 * Fecha: 13/11/2023
 * Versión: 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainInterfaz extends JFrame implements ActionListener {

    private JButton btnClientes, btnEmpleados, btnEventos, btnControlAsistencia, btnVentas, btnProductos, btnProveedores, btnInventario, btnConsultaClientes, btnConsultaProductos, btnConsultaEventos, btnConsultaEmpleado, btnListaProductos;
    private JLabel lblUsuario; // JLabel para mostrar el nombre de usuario

    public MainInterfaz() {
         // Configuración de la ventana principal
         setTitle("Discoteca"); // Título de la ventana
         setSize(800, 750); // Tamaño de la ventana
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana

         // Configuración del fondo con color
         getContentPane().setBackground(new Color(12, 171, 168));  // Color verde azulado

         // Crear componentes
        btnClientes = createButton("Clientes", "👤");
        btnEmpleados = createButton("Empleados", "💼");
        btnEventos = createButton("Eventos", "🎉");
        btnControlAsistencia = createButton("Control de Asistencia", "📅");
        btnVentas = createButton("Ventas", "💰");
        btnProductos = createButton("Productos", "📦");
        btnProveedores = createButton("Proveedores", "🤝");
        btnInventario = createButton("Inventario", "📋");
        btnConsultaClientes = createButton("Consulta de Clientes", "🔍");
        btnConsultaProductos = createButton("Consulta de Productos", "🔍");
        btnConsultaEventos = createButton("Consulta de Eventos", "🔍");
        btnConsultaEmpleado = createButton("Consulta de Empleados ","👤");
        btnListaProductos = createButton("Lista de Productos", "📋");

         // Agregar oyentes de eventos a los botones
        btnClientes.addActionListener(this);
        btnConsultaClientes.addActionListener(this);
        btnEmpleados.addActionListener(this);
        btnEventos.addActionListener(this);
        btnControlAsistencia.addActionListener(this);
        btnVentas.addActionListener(this);
        btnProductos.addActionListener(this);
        btnProveedores.addActionListener(this);
        btnInventario.addActionListener(this);
        btnConsultaProductos.addActionListener(this);
        btnConsultaEventos.addActionListener(this);
        btnConsultaEmpleado.addActionListener(this);
        btnListaProductos.addActionListener(this);

         // Configurar el diseño de la interfaz principal con BorderLayout
        setLayout(new BorderLayout());

         // Crear un panel para los botones
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(12, 1));
        buttonsPanel.setBackground(new Color(12, 171, 168));

         // Agregar los botones al panel
        buttonsPanel.add(btnClientes);
        buttonsPanel.add(btnEmpleados);
        buttonsPanel.add(btnEventos);
        buttonsPanel.add(btnControlAsistencia);
        buttonsPanel.add(btnVentas);
        buttonsPanel.add(btnProductos);
        buttonsPanel.add(btnProveedores);
        buttonsPanel.add(btnInventario);
        buttonsPanel.add(btnConsultaClientes);
        buttonsPanel.add(btnConsultaProductos);
        buttonsPanel.add(btnConsultaEventos);
        buttonsPanel.add(btnConsultaEmpleado);
        buttonsPanel.add(btnListaProductos);

         // Agregar el panel de botones al oeste de la ventana
        add(buttonsPanel, BorderLayout.EAST);

         // Agregar una etiqueta con el título al sur de la ventana
        JLabel titleLabel = new JLabel("🎉¡BIENVENIDO AL CONTROL DE LA DISCOTECA!🎉");
        titleLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.MAGENTA);
        add(titleLabel, BorderLayout.PAGE_START);

        // Crear JLabel para mostrar el nombre de usuario
        lblUsuario = new JLabel("<html> &#128100; Usuario : <html>", SwingConstants.RIGHT);
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUsuario.setForeground(Color.BLACK);
        add(lblUsuario, BorderLayout.PAGE_END); // Agregar en la esquina superior derecha
    }

    private JButton createButton(String text, String emoji) {
        JButton button = new JButton(text + " " + emoji);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBackground(new Color(46, 134, 193));  // Azul oscuro
        button.setForeground(Color.black);
        button.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
         //logica para abrir a las respectivas tablas y consultas
        if (source == btnClientes) {
            Cliente clientesFrame = new Cliente();
            clientesFrame.setVisible(true);
        } else if (source == btnConsultaClientes) {
            ConsultaCliente consultaClientesFrame = new ConsultaCliente();
            consultaClientesFrame.setVisible(true);
        } else if (source == btnConsultaProductos) {
            ConsultaProducto consultaProductoFrame = new ConsultaProducto();
            consultaProductoFrame.setVisible(true);
        } else if (source == btnConsultaEventos) {
            ConsultaEvento consultaEventoFrame = new ConsultaEvento();
            consultaEventoFrame.setVisible(true);
        } else if (source == btnEmpleados) {
            Empleado empleadosFrame = new Empleado();
            empleadosFrame.setVisible(true);
        } else if (source == btnEventos) {
            Evento eventosFrame = new Evento();
            eventosFrame.setVisible(true);
        } else if (source == btnControlAsistencia) {
            ControlAsistenciaDiscoteca controlAsistenciaFrame = new ControlAsistenciaDiscoteca();
            controlAsistenciaFrame.setVisible(true);
        } else if (source == btnVentas) {
            Ventas ventasFrame = new Ventas();
            ventasFrame.setVisible(true);
        } else if (source == btnProductos) {
            Productos productosFrame = new Productos();
            productosFrame.setVisible(true);
        } else if (source == btnProveedores) {
            Proveedores proveedoresFrame = new Proveedores();
            proveedoresFrame.setVisible(true);
        } else if (source == btnInventario) {
            Inventario inventarioFrame = new Inventario();
            inventarioFrame.setVisible(true);
        } else if (source == btnListaProductos) {
            ListaProductos listaProductosFrame = new ListaProductos();
            listaProductosFrame.setVisible(true);
        } else if (source == btnConsultaEmpleado){
            ConsultaEmpleado ConsultaEmpleadoFrame = new ConsultaEmpleado();
            ConsultaEmpleadoFrame.setVisible(true);
        }
    }

     // Método para establecer el nombre de usuario en la esquina superior derecha
    public void setUsuario(String nombreUsuario) {
        lblUsuario.setText("Empleado: " + nombreUsuario);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainInterfaz mainInterfaz = new MainInterfaz();
            mainInterfaz.setVisible(true);
        });
    }
}
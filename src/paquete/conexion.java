package paquete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class conexion {
    public static Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bd_discoteca";
        String usuario = "root";
        String contraseña = "";

        return DriverManager.getConnection(jdbcUrl, usuario, contraseña);
    }
}

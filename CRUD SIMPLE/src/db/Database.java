package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/clientes";  //base de datos
    private static final String USER = "root";  // Usuario base de datos
    private static final String PASSWORD = "tuclave";  // Contraseña  base de datos

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD); //conecion
    }
}

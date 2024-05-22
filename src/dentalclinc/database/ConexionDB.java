/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dentalclinc.database;

/**
 *
 * @author pereiras-house
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    
    Connection conn;
    private String host = "localhost";
    private String port = "3306";
    private String dbName = "dentalclinc";
    private String userName = "root";
    private String password = "casa1996";
    
    public ConexionDB() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbName;
            conn = DriverManager.getConnection(url, this.userName, this.password);
            System.out.println("Conexion Exitosa");
        }catch (ClassNotFoundException | SQLException e){
            System.out.println("Error, no se conecto");
        }
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    /*
    // Anteriormente intentando con JAR de mariaDB
    private static final String URL = "jdbc:mysql://localhost:3306/dentalclinc";
    private static final String USER = "root";
    private static final String PASSWORD = "casa1996";

    public static Connection conectar() throws SQLException {
        Connection conexion = null;
        try {
            // Cargar el controlador JDBC de MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
            // Establecer la conexión
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos MySQL");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
        return conexion;
    }
    */
}

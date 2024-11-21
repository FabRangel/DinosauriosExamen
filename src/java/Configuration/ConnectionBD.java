/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configuration;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author fgmrr
 */
public class ConnectionBD {
        Connection connection;

    public ConnectionBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Properties props = new Properties();
            FileInputStream inputStream = new FileInputStream("/Users/fgmrr/NetBeansProjects/Dinosaurios/config.properties");
            props.load(inputStream);

            String dbUrl = props.getProperty("DB_URL");
            String dbUser = props.getProperty("DB_USER");
            String dbPassword = props.getProperty("DB_PASSWORD");
     

            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            if (connection != null) {
                System.out.println("Conexión establecida correctamente.");
            }
        } catch (Exception e) {
            System.err.println("El error está en la conexión: " + e);
        }
    }

    public Connection getConnectionBD() {
        return connection;
    }

    public static void main(String[] args) {
        ConnectionBD conexion = new ConnectionBD();
        if (conexion.getConnectionBD() != null) {
            System.out.println("La conexión a la base de datos está activa.");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    }
}

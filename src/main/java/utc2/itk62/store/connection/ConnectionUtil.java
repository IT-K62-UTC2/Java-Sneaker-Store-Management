package utc2.itk62.store.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static Connection connection = null;
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/sneaker_management";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "1234";

    public static Connection getConnection(){
        if (connection == null) {
            try {
                System.out.println("Open connection");
                Class.forName(DRIVER_CLASS_NAME);
                connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                return connection;
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error connecting to database: " + e.getMessage());
            }
        }
        System.out.println("Available connection");
        return connection;
    }

    public static void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.out.println("Error closing database connection: " + e.getMessage());
            }
        }
    }
}

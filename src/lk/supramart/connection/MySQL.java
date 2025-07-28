package lk.supramart.connection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

    private static final String DATABASE = "supramart";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ENTER YOUR PASSWORD";
    private static Connection connection;
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() {
        return connection;
    }
    
    public static ResultSet executeSearch(String query) throws SQLException {
        return connection.createStatement().executeQuery(query);
    }

    public static void executeIUD(String query) {
        try {
            connection.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

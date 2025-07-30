package lk.supramart.connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Properties;
import lk.supramart.util.LoggerUtil;

public class MySQL {

    private static Properties appProperties;
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            appProperties = new Properties();
            String filePath = System.getProperty("user.dir") + "\\config.properties";

            try (java.io.InputStream fis = new java.io.FileInputStream(filePath);) {
                appProperties.load(fis);
            } catch (FileNotFoundException e) {
                LoggerUtil.Log.severe(MySQL.class, "File Not Found Exception :" + e.getMessage());
            } catch (IOException e) {
                LoggerUtil.Log.severe(MySQL.class, "IO Exception :" + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            LoggerUtil.Log.severe(MySQL.class, "Class Not Found Exception :" + e.getMessage());
        }

    }

    private static final String DB_URL = appProperties.getProperty("db.url");
    private static final String DB_USERNAME = appProperties.getProperty("db.username");
    private static final String DB_PASSWORD = appProperties.getProperty("db.password");

    public static Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            }
        } catch (SQLException e) {
            LoggerUtil.Log.severe(MySQL.class, "SQL Exception :" + e.getMessage());
        }
        return connection;
    }

    public static ResultSet executePreparedSearch(String query, Object... params) throws SQLException {
        PreparedStatement ps = prepareStatement(query, params);
        return ps.executeQuery();
    }

    public static int executePreparedIUD(String query, Object... params) throws SQLException {
        PreparedStatement ps = prepareStatement(query, params);
        return ps.executeUpdate();
    }

    private static PreparedStatement prepareStatement(String query, Object... params) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
        return ps;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            LoggerUtil.Log.severe(MySQL.class, "SQL Exception :" + e.getMessage());
        }
    }

    public static Properties getAppProperties() {
        return appProperties;
    }
}

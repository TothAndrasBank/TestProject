package utils;

import exceptions.SQLConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static final String DATA_BASE_NAME = "project";
    private static final String DATA_BASE_PORT = "5432";
    private static final String DATA_BASE_HOST = "localhost";
    private static final String DATA_BASE_DRIVER = "postgresql";

    private static final Properties PROPERTIES = new Properties();

    static {
        PROPERTIES.setProperty("user", "postgres");
        PROPERTIES.setProperty("password", "admin");
    }

    private JdbcUtils() {
    }

    public static Connection getConnection() {
        final String url = String.format("jdbc:%s://%s:%s/%s", DATA_BASE_DRIVER, DATA_BASE_HOST, DATA_BASE_PORT, DATA_BASE_NAME);
        try {
            return DriverManager.getConnection(url, PROPERTIES);
        } catch (final SQLException e) {
            throw new SQLConnectionException(e);
        }


    }
}
